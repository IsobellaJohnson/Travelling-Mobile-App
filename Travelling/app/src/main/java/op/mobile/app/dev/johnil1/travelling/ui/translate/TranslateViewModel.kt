package op.mobile.app.dev.johnil1.travelling.ui.translate

import android.speech.tts.TextToSpeech
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import op.mobile.app.dev.johnil1.travelling.api.RetrofitInstance
import op.mobile.app.dev.johnil1.travelling.api.ServiceStatus
import op.mobile.app.dev.johnil1.travelling.models.Country
import op.mobile.app.dev.johnil1.travelling.models.Translate
import java.util.*

class TranslateViewModel(_country: Country) : ViewModel() {
    var country: Country = _country
    private val baseUrl =
        "https://translate.yandex.net/api/v1.5/tr.json/"

    private val _status = MutableLiveData<ServiceStatus>()
    val status: LiveData<ServiceStatus> get() = _status

    private val _response = MutableLiveData<Translate>()
    val response: LiveData<Translate> get() = _response

    fun getResponse(text: String) {
        viewModelScope.launch {
            _status.value = ServiceStatus.LOADING
            try {
                _response.value = RetrofitInstance(baseUrl).retrofitTranslateService.getResponse(
                    "trnsl.1.1.20200329T025311Z.37f6897b8a99dbd9.bb42d876c007fde0812c365015625fde8c0f0163",
                    text,
                    "en-" + country.langCode
                )
                _status.value = ServiceStatus.COMPLETE
            } catch (e: Exception) {
                _status.value = ServiceStatus.ERROR
            }
        }

    }
}