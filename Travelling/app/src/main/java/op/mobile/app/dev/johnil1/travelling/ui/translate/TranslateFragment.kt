package op.mobile.app.dev.johnil1.travelling.ui.translate

import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import op.mobile.app.dev.johnil1.travelling.R
import op.mobile.app.dev.johnil1.travelling.databinding.FragmentTranslateBinding
//import kotlinx.android.synthetic.main.fragment_translate.*
import java.util.*


class TranslateFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        lateinit var mTTS: TextToSpeech
        mTTS = TextToSpeech(activity, TextToSpeech.OnInitListener { status ->
            if (status != TextToSpeech.ERROR) {
                //if there is no error then set language
                mTTS.language = Locale.UK
            }
        })
        val binding: FragmentTranslateBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_translate,
            container,
            false

        )

        val viewModelFactory =
            TranslateViewModelFactory(
                TranslateFragmentArgs.fromBundle(requireArguments()).country
            )

        val viewModel = ViewModelProvider(
            this,
            viewModelFactory
        ).get(TranslateViewModel::class.java)

        binding.apply {
            lifecycleOwner = viewLifecycleOwner
            translateViewModel = viewModel

            translateBtn.setOnClickListener {
                val txt = translation.text.toString()
                viewModel.getResponse(txt)
            }

            txt2speechBtn.setOnClickListener {
                val toSpeak = translation.text.toString()
                if (toSpeak == "") {
                    //if there is no text in edit text
                    Toast.makeText(activity, "Enter text", Toast.LENGTH_SHORT).show()
                } else {
                    //if there is text in edit text
                    Toast.makeText(activity, toSpeak, Toast.LENGTH_SHORT).show()
                    mTTS.speak(toSpeak, TextToSpeech.QUEUE_FLUSH, null)
                }
            }
        }
        return binding.root
    }
}


