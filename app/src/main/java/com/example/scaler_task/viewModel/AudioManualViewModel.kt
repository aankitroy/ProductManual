package com.example.scaler_task.viewModel

import android.app.Application
import android.content.Intent
import android.os.Bundle
import android.speech.RecognitionListener
import android.speech.RecognizerIntent
import android.speech.SpeechRecognizer
import android.speech.tts.TextToSpeech
import android.speech.tts.UtteranceProgressListener
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.scaler_task.R
import com.example.scaler_task.common.SingleLiveEvent
import com.example.scaler_task.constants.Constants
import com.example.scaler_task.fragment.*
import com.example.scaler_task.pojo.AnswerStep
import com.example.scaler_task.pojo.FragmentChange
import com.example.scaler_task.pojo.Question
import com.example.scaler_task.pojo.QuestionDemo
import com.example.scaler_task.repository.MainRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.*


class AudioManualViewModel
@ViewModelInject
constructor(private val mainRepository: MainRepository, private val application: Application) :
    ViewModel(), RecognitionListener {

    var selectedLanguageTitle: String = "English"
    var selectedLocale: Locale = Locale("en", "IN")
    private var speechRecognizer: SpeechRecognizer =
        SpeechRecognizer.createSpeechRecognizer(application)
    private lateinit var speechIntent: Intent
    private lateinit var textToSpeech: TextToSpeech
    var partialResult: SingleLiveEvent<String> = SingleLiveEvent<String>()
    var result: SingleLiveEvent<String> = SingleLiveEvent<String>()
    var fragmentChange: SingleLiveEvent<FragmentChange> = SingleLiveEvent<FragmentChange>()
    var fragmentOverLay: SingleLiveEvent<FragmentChange> = SingleLiveEvent()
    var selectedQuestion: Question? = null
    var currentAnswerStepIndex: Int = 0
    private var _currentAnswerStep: MutableLiveData<AnswerStep> = MutableLiveData<AnswerStep>()
    val currentAnswerStep: LiveData<AnswerStep> = _currentAnswerStep
    var isSpeechPlaying: Boolean = false
    var randomQuestion: Question? = null

    init {
        initSpeech()
        initTextToSpeech()
    }

    private fun initSpeech() {
        speechRecognizer.setRecognitionListener(this)
        speechIntent = Intent().apply {
            action = "android.speech.action.RECOGNIZE_SPEECH"
            putExtra("android.speech.extra.LANGUAGE_MODEL", "free_form")
            putExtra("android.speech.extra.MAX_RESULTS", 10)
            putExtra(RecognizerIntent.EXTRA_PARTIAL_RESULTS, true);
            putExtra("calling_package", application.opPackageName)
        }
    }

    private fun initTextToSpeech() {
        textToSpeech = TextToSpeech(
            application
        ) { i ->
            // if No error is found then only it will run
            if (i != TextToSpeech.ERROR) {
                // To Choose language of speech
                textToSpeech.language = selectedLocale
            }
        }
        class SpeechListener : UtteranceProgressListener() {
            override fun onDone(utteranceId: String?) {
                isSpeechPlaying = false
                viewModelScope.launch(Dispatchers.Main) {
                    if (selectedQuestion != null) {
                        showNextStep()
                    }
                }
            }

            override fun onError(utteranceId: String?) {
                isSpeechPlaying = false
                Log.d("zzz", "Speech error")
            }

            override fun onStart(utteranceId: String?) {
                isSpeechPlaying = true
                Log.d("zzz", "Speech started")
            }

        }
        textToSpeech.setOnUtteranceProgressListener(SpeechListener())
    }

    override fun onReadyForSpeech(bundle: Bundle?) {
    }

    override fun onBeginningOfSpeech() {
    }

    override fun onRmsChanged(p0: Float) {
    }

    override fun onBufferReceived(p0: ByteArray?) {
    }

    override fun onEndOfSpeech() {
    }

    override fun onError(error: Int) {
        showStartFragment()
    }

    override fun onResults(bundle: Bundle?) {
        val results = bundle?.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION)
        result.value = results?.last()
        searchInQuestionList(result.value.toString())
    }

    private fun searchInQuestionList(query: String) {
        viewModelScope.launch(Dispatchers.IO) {
            delay(1000) // to show input text to user for some time
            val list = QuestionDemo.getQuestionList()
            var isMatch = false
            for (item in list.indices) {
                if ((Constants.similarity(list[item].question, query) > .7)) {
                    selectedQuestion = list[item]
                    isMatch = true
                    break
                }

            }
            withContext(Dispatchers.Main) {
                if (isMatch) {
                    showAnswerScreen()
                } else {
                    showStartFragment()
                }
            }
        }

    }

    fun showAnswerScreen() {
        selectedQuestion?.let {
            currentAnswerStepIndex = 0
            _currentAnswerStep.value = selectedQuestion!!.answerSteps[currentAnswerStepIndex]
            if (selectedQuestion!!.answerSteps.size > 1) {
                showStepFragment()
            } else {
                // show single step answer which might have different ui
            }
        }
    }

    private fun showNextStep() {
        currentAnswerStepIndex++
        if (currentAnswerStepIndex < selectedQuestion!!.answerSteps.size) {
            _currentAnswerStep.value = (selectedQuestion!!.answerSteps[currentAnswerStepIndex])
            playStep()
        } else if (currentAnswerStepIndex == selectedQuestion!!.answerSteps.size) {
            showStepEndScreen()
        }
    }

    fun pauseStep() {
        textToSpeech.stop()
        isSpeechPlaying = false
    }

    fun playStep() {
        speak("Step ${currentAnswerStepIndex + 1}. ${currentAnswerStep.value?.text}")
    }

    private fun showStepEndScreen() {
        getRandomQuestionFromList()
        speak(selectedQuestion!!.lastScreenTitle)
        fragmentChange(StepEndFragment::class.java, StepEndFragment.TAG)
    }

    private fun showStepFragment() {
        playStep()
        fragmentChange(StepFragment::class.java, StepFragment.TAG)
    }

    override fun onPartialResults(bundle: Bundle?) {
        val results = bundle?.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION)
        partialResult.value = results?.last()
    }

    override fun onEvent(p0: Int, p1: Bundle?) {
    }

    fun startListening() {
        textToSpeech.stop()
        speechRecognizer.startListening(speechIntent)
    }

    fun stopListening() {
        speechRecognizer.cancel()
    }

    fun speak(text: String?) {
        if (text == null) return
        val params: Bundle = Bundle()
        textToSpeech.speak(text, TextToSpeech.QUEUE_FLUSH, params, Math.random().toString())
    }

    override fun onCleared() {
        speechRecognizer.cancel()
        speechRecognizer.destroy()
        textToSpeech.stop()
        super.onCleared()
    }

    fun showStartFragment() {
        fragmentChange(AudioManualStartFragment::class.java, AudioManualStartFragment.TAG)
    }

    fun showListeningFragment() {
        startListening()
        fragmentChange(
            T = AudioListenFragment::class.java,
            tag = AudioListenFragment.TAG
        )
    }

    fun fragmentChange(
        T: Class<out Fragment?>,
        tag: String,
        args: Bundle? = null,
        addToBackStack: Boolean = false,
        startTransition: Int = 0,
        endTransition: Int = 0,
        popEnterTransition: Int = 0,
        popExitTransition: Int = 0
    ) {
        fragmentChange.value = FragmentChange(
            T, tag, args, addToBackStack, startTransition,
            endTransition, popEnterTransition, popExitTransition
        )
    }

    fun overlayChange(T: Class<out Fragment?>, tag: String, args: Bundle? = null) {
        fragmentOverLay.value = FragmentChange(T, tag, args)
    }

    fun getRandomQuestionFromList(): Question? {
        randomQuestion = QuestionDemo.getQuestionList().random()
        return randomQuestion
    }

    fun setSelectedLanguage(selectedLanguage: String) {
        selectedLanguageTitle = selectedLanguage
        when (selectedLanguage) {
            "English" -> {
                speechIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, "en-IN")
                selectedLocale = Locale("en", "IN")
                textToSpeech.language = Locale("en", "IN")
            }
            "Hindi" -> {
                speechIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, "hi-IN")
                selectedLocale = Locale("hi", "IN")
                textToSpeech.language = selectedLocale

            }
        }
    }

    fun showSearchScreen() {
        overlayChange(SearchFragment::class.java, SearchFragment.TAG)
    }

    fun hideSearchScreen() {
        fragmentOverLay.value = FragmentChange(
            T = SearchFragment::class.java,
            tag = SearchFragment.TAG,
            shouldRemove = true
        )
    }

}