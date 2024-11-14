package jakub.portfolio

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import org.jetbrains.compose.ui.tooling.preview.Preview


@Preview
@Composable
fun MainPage() {
    // Your UI elements here
    Column {
        Text("Welcome to my Portfolio!")
        // Add more content: profile picture, about me, skills, projects, etc.
    }
}

fun todaysDate(): String {
    fun LocalDateTime.format() = toString().substringBefore('T')

    val now = Clock.System.now()
    val zone = TimeZone.currentSystemDefault()
    return now.toLocalDateTime(zone).format()
}

fun affineCipherDemo() {
    val cipher = AffineCipher(alphabet = ('a'..'z').joinToString("") + ('0'..'9').joinToString(""))
    val ciphertext = cipher.encode("Hello", 5, 8)
    val plaintext = cipher.decode(ciphertext, 5, 8)
}

fun adfgvxCipherDemo() {
    val cipher = ADFGVXCipher("HELLO", useExtendedAlphabet = true)
    val ciphertext = cipher.encode("Hello")
    val plaintext = cipher.decode(ciphertext)
}