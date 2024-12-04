package jakub.portfolio

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
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
                    .verticalScroll(rememberScrollState())
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

    var mDisplayMenu by remember { mutableStateOf(false) }

    TopAppBar(
        title = { Text("My Portfolio", style = TextStyle(color = Color.White)) },
        backgroundColor = MaterialTheme.colors.primary,
        actions = {
            IconButton(onClick = { mDisplayMenu = !mDisplayMenu }) {
                Icon(Icons.Default.MoreVert, contentDescription = "More")
            }
            DropdownMenu(
                expanded = mDisplayMenu,
                onDismissRequest = { mDisplayMenu = false },
                content = {
                    DropdownMenuItem(onClick = { /* handle item click */ }) {
                        Text("Settings")
                    }
                    DropdownMenuItem(onClick = { /* handle item click */ }) {
                        Text("About")
                    }
                    // Add more menu items as needed
                }
            )
        }
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
        ProjectCard("Crypto-Coin", "A cryptocurrency tracker app that shows the latest prices")
        Spacer(modifier = Modifier.height(8.dp))
        ProjectCard(
            "Kryptography",
            "A collection of cryptographic algorithms implemented in Kotlin"
        )
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
            text = AnnotatedString("Email: jakubkramny93@gmail.com"),
            style = TextStyle(color = MaterialTheme.colors.primary, fontSize = 14.sp),
            onClick = { /* Handle email click */ }
        )
        Spacer(modifier = Modifier.height(4.dp))
        ClickableText(
            text = AnnotatedString("LinkedIn"),
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