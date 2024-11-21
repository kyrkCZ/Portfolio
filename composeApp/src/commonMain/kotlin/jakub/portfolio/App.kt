package jakub.portfolio

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.ui.tooling.preview.Preview

@Preview
@Composable
fun PortfolioApp() {
    MaterialTheme {
        Scaffold(
            modifier = Modifier.fillMaxWidth(),
            topBar = { TopBar() }
        ) { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {
                ProfileSection()
                SkillsSection()
                ProjectsSection()
                ContactSection()
            }
        }
    }
}

@Composable
fun TopBar() {
    TopAppBar(
        title = { Text("My Portfolio", style = TextStyle(color = Color.White)) },
        backgroundColor = MaterialTheme.colors.primary
    )
}

@Composable
fun ProfileSection() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        val profileImage = ColorPainter(Color.Gray) // Placeholder painter for testing
        Image(
            painter = profileImage,
            contentDescription = "Profile Picture",
            modifier = Modifier
                .size(120.dp)
                .clip(CircleShape),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text("Jakub Kramný", style = MaterialTheme.typography.h5)
        Text("Software Engineer", style = MaterialTheme.typography.body1, color = Color.Gray)
    }
}

@Composable
fun SkillsSection() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text("Skills", style = MaterialTheme.typography.h6)
        Spacer(modifier = Modifier.height(8.dp))
        Text("• Kotlin • Java • Compose UI • Android Development", style = MaterialTheme.typography.body1)
        Text("• Multiplatform • REST APIs • Clean Architecture", style = MaterialTheme.typography.body1)
    }
}

@Composable
fun ProjectsSection() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text("Projects", style = MaterialTheme.typography.h6)
        Spacer(modifier = Modifier.height(8.dp))
        ProjectCard("Project 1", "A cool project that does X, Y, Z")
        Spacer(modifier = Modifier.height(8.dp))
        ProjectCard("Project 2", "Another cool project that solves A, B, C")
    }
}

@Composable
fun ProjectCard(title: String, description: String) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = 4.dp
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(title, style = MaterialTheme.typography.subtitle1)
            Spacer(modifier = Modifier.height(4.dp))
            Text(description, style = MaterialTheme.typography.body2, color = Color.Gray)
        }
    }
}

@Composable
fun ContactSection() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Contact", style = MaterialTheme.typography.h6)
        Spacer(modifier = Modifier.height(8.dp))
        ClickableText(
            text = AnnotatedString("Email: johndoe@example.com"),
            style = TextStyle(color = MaterialTheme.colors.primary, fontSize = 14.sp),
            onClick = { /* Handle email click */ }
        )
        Spacer(modifier = Modifier.height(4.dp))
        ClickableText(
            text = AnnotatedString("LinkedIn: linkedin.com/in/johndoe"),
            style = TextStyle(color = MaterialTheme.colors.primary, fontSize = 14.sp),
            onClick = { /* Handle LinkedIn click */ }
        )
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