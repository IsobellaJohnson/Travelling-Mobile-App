package op.mobile.app.dev.johnil1.travelling.api

import op.mobile.app.dev.johnil1.travelling.models.Translate
import retrofit2.http.GET
import retrofit2.http.Query

interface ITranslate {
    @GET("translate?")
    suspend fun getResponse(
        @Query("key") key: String,
        @Query("text") text: String,
        @Query("lang") lang: String
    ): Translate
}
