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
import com.example.scaler_task.R
import com.example.scaler_task.constants.Constants
import com.example.scaler_task.databinding.ActivityKhabriTaskBinding
import com.example.scaler_task.pojo.QuestionDemo
import java.util.*


class KhabriTaskActivityV2 : AppCompatActivity(), RecognitionListener {

    lateinit var binding: ActivityKhabriTaskBinding
    lateinit var speechRecognizer: SpeechRecognizer
    lateinit var speechIntent: Intent
    lateinit var textToSpeech: TextToSpeech
    var map = HashMap<String, String>()




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_khabri_task)
        initSpeech()
        initTextToSpeech()
        binding.listenContainer.setOnClickListener {
            if (checkPermission()) {
                binding.startMessage.setImageResource(R.drawable.askmestopmessage)
                binding.mic.setImageResource(R.drawable.red_mic)
               // binding.answer.text = ""
                textToSpeech.stop()
                binding.juicerMixerDesignRef.visibility = View.VISIBLE
                binding.instructPlaceHolder.visibility = View.VISIBLE
                binding.startMessage.visibility = View.VISIBLE
                binding.juicerMixerDesignRefV2.root.visibility = View.GONE
                binding.juicerMixerDesignRefV2.question.text = ""
                speechRecognizer.startListening(speechIntent)
            }
        }


        binding.lgSwitch.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                binding.title.text = "English"
                speechIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, "en-IN")
                textToSpeech.language = Locale("en", "IN")
            } else {
                binding.title.text = "Hindi"
                speechIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, "hi-IN")
                textToSpeech.language = Locale("hi", "IN")
            }

        }

        binding.juicerMixerDesignRefV2.answer.movementMethod = ScrollingMovementMethod()

       /* val recyclerview = findViewById<RecyclerView>(R.id.questionlist)
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

        }*/

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
                binding.juicerMixerDesignRef.visibility = View.VISIBLE
                binding.instructPlaceHolder.visibility = View.VISIBLE
                binding.startMessage.visibility = View.VISIBLE
                binding.juicerMixerDesignRefV2.root.visibility = View.GONE
                binding.juicerMixerDesignRefV2.question.text = ""

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
        binding.juicerMixerDesignRef.visibility = View.VISIBLE
        binding.instructPlaceHolder.visibility = View.VISIBLE
        binding.startMessage.visibility = View.VISIBLE
        binding.juicerMixerDesignRefV2.root.visibility = View.GONE
        binding.juicerMixerDesignRefV2.question.text = ""

    }

    override fun onError(error: Int) {
        Log.d("zzz", "error : " + error)
    }


    fun capitalize(str: String?): String? {
        return str?.capitalize() ?: str
    }

    override fun onResults(results: Bundle) {
        binding.startMessage.setImageResource(R.drawable.askmeanything)
        binding.mic.setImageResource(R.drawable.black_mic)
        val stringArrayList: ArrayList<String>? =
            results.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION)
        if (stringArrayList != null) {
            val it: Iterator<String> = stringArrayList.iterator()
            while (it.hasNext()) {
                Log.d("zzz", "the speech all result: " + it.next())
            }
        }
        if (stringArrayList != null) {
            val question = stringArrayList.get(0);
            binding.juicerMixerDesignRef.visibility = View.GONE
            binding.instructPlaceHolder.visibility = View.GONE
            binding.startMessage.visibility = View.GONE
            binding.juicerMixerDesignRefV2.root.visibility = View.VISIBLE
            searchInQuestionList(question)
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
        var ismatch = false
        for (item in list.indices) {
            if ((Constants.similarity(list.get(item).question, query) > .7)) {
//                passAnswerInTTS(list.get(item).question, list.get(item).answer)
                ismatch = true
                break;
            }

        }
        if(ismatch == false){
            binding.juicerMixerDesignRef.visibility = View.VISIBLE
            binding.instructPlaceHolder.visibility = View.VISIBLE
            binding.startMessage.visibility = View.VISIBLE
            binding.juicerMixerDesignRefV2.root.visibility = View.GONE
            binding.juicerMixerDesignRefV2.question.text = ""
        }
    }


    private fun passAnswerInTTS(question: String, answer: String) {
        binding.juicerMixerDesignRefV2.bigElipse.visibility = View.VISIBLE
        binding.juicerMixerDesignRefV2.answerbase.visibility = View.VISIBLE
        binding.juicerMixerDesignRefV2.answer.visibility = View.VISIBLE
        binding.juicerMixerDesignRefV2.question.text = question + "?"
        binding.juicerMixerDesignRefV2.answer.text = answer
        map.put(TextToSpeech.Engine.KEY_PARAM_UTTERANCE_ID, Math.random().toString());
        textToSpeech.speak(answer, TextToSpeech.QUEUE_FLUSH, map)
    }


}