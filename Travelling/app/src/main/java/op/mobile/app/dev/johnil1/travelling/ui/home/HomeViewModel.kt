package op.mobile.app.dev.johnil1.travelling.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import op.mobile.app.dev.johnil1.travelling.api.RetrofitInstance
import op.mobile.app.dev.johnil1.travelling.api.ServiceStatus
import op.mobile.app.dev.johnil1.travelling.models.Country
import op.mobile.app.dev.johnil1.travelling.models.Translate

class HomeViewModel : ViewModel() {
    private val baseUrl =
        "https://gist.github.com/IsobellaJohnson/b7da9dfdcfdb95f1c9cf6da63f61ec85/"

    private val _status = MutableLiveData<ServiceStatus>()
    val status: LiveData<ServiceStatus> get() = _status

    private val _response = MutableLiveData<List<Country>>()
    val response: LiveData<List<Country>> get() = _response

    init {
        viewModelScope.launch {
            _status.value = ServiceStatus.LOADING
            try {
                _response.value = RetrofitInstance(baseUrl).retrofitCountryService.getResponse()
                _status.value = ServiceStatus.COMPLETE
            } catch (e: Exception) {
                _status.value = ServiceStatus.ERROR
            }
        }

    }
}
