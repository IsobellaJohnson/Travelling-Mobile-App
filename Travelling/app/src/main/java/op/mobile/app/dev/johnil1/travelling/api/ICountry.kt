package op.mobile.app.dev.johnil1.travelling.api

import op.mobile.app.dev.johnil1.travelling.models.Country
import retrofit2.http.GET

interface ICountry {
    @GET("raw")
    suspend fun getResponse(): List<Country>
}
