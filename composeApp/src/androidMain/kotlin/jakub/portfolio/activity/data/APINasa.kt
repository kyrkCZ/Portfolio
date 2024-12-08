package jakub.portfolio.data

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.statement.HttpResponse
import io.ktor.http.isSuccess
import kotlinx.serialization.Serializable

@Serializable
data class NasaPictureOfTheDay(
    val date: String,
    val explanation: String,
    val hdurl: String?,
    val media_type: String,
    val service_version: String,
    val title: String,
    val url: String
)

class NasaApiService(private val httpClient: HttpClient, private val apiKey: String) {

    private val baseUrl = "https://api.nasa.gov/planetary/apod"

    suspend fun getPictureOfTheDay(date: String? = null): NasaPictureOfTheDay {
        val response: HttpResponse = httpClient.get(baseUrl) {
            url {
                parameters.append("api_key", apiKey)
                if (date != null) {
                    parameters.append("date", date)
                }
            }
        }

        if (response.status.isSuccess()) {
            return response.body()
        } else {
            throw Exception("Failed to fetch data: ${response.status}")
        }
    }
}