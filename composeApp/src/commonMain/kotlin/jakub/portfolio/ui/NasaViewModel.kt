import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.statement.HttpResponse
import io.ktor.http.isSuccess
import kotlinx.serialization.Serializable

/**
 * Data model for NASA's Astronomy Picture of the Day (APOD).
 * 
 * @property date The date of the APOD image (format: YYYY-MM-DD)
 * @property explanation A detailed explanation about the image
 * @property hdurl High-definition URL of the image (optional)
 * @property media_type Type of media (image or video) - Note: Uses snake_case as per API response
 * @property service_version API service version - Note: Uses snake_case as per API response
 * @property title Title of the image
 * @property url Standard resolution URL of the image
 */
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

/**
 * Service class for interacting with NASA's APOD API.
 * 
 * This service provides methods to fetch astronomy pictures and their details
 * from NASA's public API.
 * 
 * @property httpClient Ktor HTTP client for making API requests
 * @property apiKey NASA API key for authentication
 */
class NasaApiService(private val httpClient: HttpClient, private val apiKey: String) {

    private val baseUrl = "https://api.nasa.gov/planetary/apod"

    /**
     * Fetches the Astronomy Picture of the Day.
     * 
     * @param date Optional date parameter (format: YYYY-MM-DD). If null, returns today's picture.
     * @return NasaPictureOfTheDay object containing the image and metadata
     * @throws Exception if the API request fails
     */
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
