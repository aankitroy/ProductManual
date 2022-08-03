package com.example.scaler_task.activity

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.speech.RecognitionListener
import android.speech.RecognizerIntent
import android.speech.SpeechRecognizer
import android.speech.tts.TextToSpeech
import android.speech.tts.UtteranceProgressListener
import android.text.method.ScrollingMovementMethod
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.scaler_task.R
import com.example.scaler_task.adapter.QuestionsAdapter
import com.example.scaler_task.constants.Constants
import com.example.scaler_task.databinding.ActivityKhabriTaskBinding
import com.example.scaler_task.pojo.QuestionDemo
import dagger.hilt.android.AndroidEntryPoint
import java.util.*


@AndroidEntryPoint
class KhabriTaskActivity : AppCompatActivity(), RecognitionListener {

    lateinit var binding: ActivityKhabriTaskBinding
    lateinit var speechRecognizer: SpeechRecognizer
    lateinit var speechIntent: Intent
    lateinit var textToSpeech: TextToSpeech



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_khabri_task)
        binding.downArrow.setAnimation(R.raw.arrow_down_purple)
        binding.downArrow.playAnimation()
        initSpeech()
        initTextToSpeech()
        binding.listenContainer.setOnClickListener {
            if (checkPermission()) {
                binding.subTitle.setText("Start speaking")
                binding.answer.text = ""
                textToSpeech.stop()
                speechRecognizer.startListening(speechIntent)
            }
        }


        binding.lgSwitch.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                binding.title.setText("English")
                speechIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, "en-IN")
                textToSpeech.language = Locale("en", "IN")
            } else {
                binding.title.setText("Hindi")
                speechIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, "hi-IN")
                textToSpeech.language = Locale("hi", "IN")
            }

        }

        val recyclerview = findViewById<RecyclerView>(R.id.questionlist)
        recyclerview.layoutManager = LinearLayoutManager(this)
        val data = QuestionDemo.getQuestionList()
        val adapter = QuestionsAdapter(data)
        recyclerview.adapter = adapter

        binding.answer.setMovementMethod(ScrollingMovementMethod())

        binding.seeQuestions.setOnClickListener { buttonView ->
            binding.airpurifierIcon.visibility = View.GONE
            binding.answer.text = ""
            binding.downArrow.visibility = View.GONE
            binding.Instruction.visibility = View.GONE
            binding.productName.visibility = View.GONE
            binding.questionlist.visibility = View.VISIBLE

        }

    }

    private fun initTextToSpeech() {
        textToSpeech = TextToSpeech(
            applicationContext
        ) { i ->
            // if No error is found then only it will run
            if (i != TextToSpeech.ERROR) {
                // To Choose language of speech
                textToSpeech.language = Locale("hi", "IN")
            } 
        }
        class speechListener : UtteranceProgressListener() {
            override fun onDone(utteranceId: String?) {
                binding.airpurifierIcon.visibility = View.VISIBLE
                binding.answer.text = ""
                binding.downArrow.visibility = View.VISIBLE
                binding.Instruction.visibility = View.VISIBLE
                binding.questionlist.visibility = View.GONE

            }

            override fun onError(utteranceId: String?) {
                Toast.makeText(applicationContext, "speaking error", Toast.LENGTH_SHORT).show()
            }

            override fun onStart(utteranceId: String?) {
                Toast.makeText(applicationContext, "speaking started", Toast.LENGTH_SHORT).show()

            }

        }
        textToSpeech.setOnUtteranceProgressListener(speechListener())
    }

    fun initSpeech() {
        speechRecognizer = SpeechRecognizer.createSpeechRecognizer(this)
        speechRecognizer.setRecognitionListener(this)
        speechIntent = Intent().apply {
            action = "android.speech.action.RECOGNIZE_SPEECH"
            putExtra("android.speech.extra.LANGUAGE_MODEL", "free_form")
            putExtra("android.speech.extra.MAX_RESULTS", 10)
            putExtra("calling_package", application.opPackageName)
        }
    }


    override fun onDestroy() {
        speechRecognizer.cancel()
        speechRecognizer.destroy()
        super.onDestroy()
    }

    override fun onReadyForSpeech(params: Bundle?) {

    }

    override fun onBeginningOfSpeech() {

    }

    override fun onRmsChanged(rmsdB: Float) {

    }

    override fun onBufferReceived(buffer: ByteArray?) {

    }

    override fun onEndOfSpeech() {
        binding.airpurifierIcon.visibility = View.VISIBLE
        binding.answer.text = ""
        binding.downArrow.visibility = View.VISIBLE
        binding.Instruction.visibility = View.VISIBLE
        binding.productName.visibility = View.VISIBLE
        binding.questionlist.visibility = View.GONE

    }

    override fun onError(error: Int) {
        Log.d("zzz", "error : " + error)
    }


    override fun onResults(results: Bundle) {
        binding.subTitle.setText("Stop speaking")
        val stringArrayList: ArrayList<String>? =
            results.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION)
        if (stringArrayList != null) {
            val it: Iterator<String> = stringArrayList.iterator()
            while (it.hasNext()) {
                Log.d("zzz", "the speech all result: " + it.next())
            }
        }
        if (stringArrayList != null) {
            searchInQuestionList(stringArrayList.get(0))
        }
    }

    override fun onPartialResults(partialResults: Bundle?) {

    }

    override fun onEvent(eventType: Int, params: Bundle?) {

    }

    private fun checkPermission(): Boolean {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(
                    this,
                    Manifest.permission.RECORD_AUDIO
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                ActivityCompat.requestPermissions(
                    this, arrayOf(Manifest.permission.RECORD_AUDIO),
                    200
                )
                return false
            }
        }
        return true
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String?>,
        grantResults: IntArray
    ) {
        try {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults)
            if (requestCode == 200 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                binding.listenContainer.callOnClick()
            }
        } catch (ex: Exception) {
        }
    }

    private fun searchInQuestionList(query: String) {
        val list = QuestionDemo.getQuestionList()
        for (item in list.indices) {
            if ((Constants.similarity(list.get(item).question, query) > .7)) {
                passAnswerInTTS(list.get(item).answer)
                break;
            }

        }
    }


    private fun passAnswerInTTS(answer: String) {
        binding.airpurifierIcon.visibility = View.GONE
        binding.answer.text = answer
        binding.downArrow.visibility = View.GONE
        binding.Instruction.visibility = View.GONE
        binding.productName.visibility = View.GONE
        textToSpeech.speak(answer, TextToSpeech.QUEUE_FLUSH, null)
    }


}