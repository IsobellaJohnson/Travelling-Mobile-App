package op.mobile.app.dev.johnil1.travelling.ui.translate

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import op.mobile.app.dev.johnil1.travelling.models.Country
import op.mobile.app.dev.johnil1.travelling.models.Translate


@Suppress("UNCHECKED_CAST")
class TranslateViewModelFactory(
    private val country: Country
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TranslateViewModel::class.java))
            return TranslateViewModel(country) as T
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
