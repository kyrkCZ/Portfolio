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
    Column {
        Text("Welcome to my Portfolio!")
        Text("Today's date is: ${todaysDate()}")
        Text("Here you can find information about me, my skills, and projects.")
        // About Me
        Text("About Me")
        Text("I am a software developer with a passion for creating innovative solutions...")
        // Skills
        Text("Skills")
        Text("Kotlin, Java, Android Development, Compose, etc.")
        // Projects
        Text("Projects")
        Text("1. CryptoCoin - Simple cryptocurrency tracker")
        Text("2. Cryptátor - Cryptography app")
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