package jakub.portfolio

import NasaApiService
import NasaPictureOfTheDay
import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Divider
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.launch
import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import kotlinx.serialization.json.Json
import jakub.portfolio.activity.CryptoCoinScreen

@Composable
fun PortfolioApp() {
    val scaffoldState = rememberScaffoldState()
    val coroutineScope = rememberCoroutineScope()
    var mDisplayMenu by remember { mutableStateOf(false) }
    val drawerItems = listOf("Home", "NASA", "Crypto-Coin", "Kryptography")
    var selectedItem by remember { mutableStateOf("Home") }
    var showSettings by remember { mutableStateOf(false) }
    var showAbout by remember { mutableStateOf(false) }

    Scaffold(
        scaffoldState = scaffoldState,
        drawerContent = {
            Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
                Text("Menu", style = MaterialTheme.typography.h6)
                Divider(modifier = Modifier.padding(vertical = 8.dp))
                drawerItems.forEach { item ->
                    TextButton(
                        onClick = {
                            selectedItem = item
                            coroutineScope.launch { scaffoldState.drawerState.close() }
                        },
                        modifier = Modifier.fillMaxWidth().padding(4.dp)
                    ) {
                        Text(text = item)
                    }
                }
            }
        },
        topBar = {
            TopAppBar(
                title = { Text("My Portfolio", style = TextStyle(color = Color.White)) },
                backgroundColor = MaterialTheme.colors.primary,
                navigationIcon = {
                    IconButton(onClick = {
                        showAbout = false
                        showSettings = false
                        coroutineScope.launch { scaffoldState.drawerState.open() }
                    }) {
                        Icon(
                            Icons.Default.Menu,
                            contentDescription = "Menu Icon",
                            tint = Color.White
                        )
                    }
                },
                actions = {
                    IconButton(onClick = { mDisplayMenu = !mDisplayMenu }) {
                        Icon(Icons.Default.MoreVert, contentDescription = "More")
                    }
                    DropdownMenu(
                        expanded = mDisplayMenu,
                        onDismissRequest = { mDisplayMenu = false },
                        content = {
                            DropdownMenuItem(onClick = {
                                showAbout = false
                                showSettings = true
                                mDisplayMenu = false
                            }) {
                                Text("Settings")
                            }
                            DropdownMenuItem(onClick = {
                                showAbout = true
                                showSettings = false
                                mDisplayMenu = false
                            }) {
                                Text("About")
                            }
                        }
                    )
                }
            )
        },
        content = { paddingValues ->
            Box(modifier = Modifier.padding(paddingValues)) {
                when {
                    showSettings -> SettingsView()
                    showAbout -> AboutView()
                    else -> when (selectedItem) {
                        "Home" -> HomeScreen()
                        "NASA" -> NasaScreen("yEDjQUgqIe4ZTbEkVwHU1ZkdfeK4pj5DfBfMTK22")
                        "Crypto-Coin" -> CryptoCoinScreen()
                        "Kryptography" -> KryptographyScreen()
                    }
                }
            }
        }
    )
}

@Composable
fun HomeScreen() {
    val scrollState = remember { ScrollState(0) }

    Column(
        modifier = Modifier
            .fillMaxSize().verticalScroll(scrollState)
            .padding(16.dp), horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Welcome to the Home Screen")
        Spacer(modifier = Modifier.height(8.dp))
        Text("Today's date is: ${todaysDate()}")
        Spacer(modifier = Modifier.height(8.dp))
        ProfileSection()
        Spacer(modifier = Modifier.height(16.dp))
        SkillsSection()
        Spacer(modifier = Modifier.height(16.dp))
        ProjectsSection()
        Spacer(modifier = Modifier.height(16.dp))
        ContactSection()
    }
}

@Composable
fun NasaScreen(apiKey: String) {
    // Create an HttpClient instance
    val httpClient = remember {
        HttpClient {
            install(ContentNegotiation) {
                json(Json {
                    ignoreUnknownKeys = true
                    isLenient = true
                })
            }
        }
    }

    val nasaApiService = remember { NasaApiService(httpClient, apiKey) }
    val coroutineScope = rememberCoroutineScope()

    var pictureOfTheDay by remember { mutableStateOf<NasaPictureOfTheDay?>(null) }
    var errorMessage by remember { mutableStateOf<String?>(null) }
    var isLoading by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        isLoading = true
        try {
            pictureOfTheDay = nasaApiService.getPictureOfTheDay()
        } catch (e: Exception) {
            errorMessage = e.message ?: "Unknown error"
        } finally {
            isLoading = false
        }
    }

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        when {
            isLoading -> {
                CircularProgressIndicator()
            }

            errorMessage != null -> {
                Text(
                    text = "Error: $errorMessage",
                    color = MaterialTheme.colors.error,
                    modifier = Modifier.padding(16.dp),
                    textAlign = TextAlign.Center
                )
            }

            pictureOfTheDay != null -> {
                PictureOfTheDayContent(pictureOfTheDay!!)
            }
        }
    }
}

@Composable
fun PictureOfTheDayContent(picture: NasaPictureOfTheDay) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = picture.title,
            style = MaterialTheme.typography.h6,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        Text(
            text = picture.date,
            style = MaterialTheme.typography.caption,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        picture.hdurl?.let {
            AsyncImage(
                imageUrl = it,
                contentDescription = picture.title,
                modifier = Modifier.fillMaxWidth().aspectRatio(1.5f)
            )
        }
        Text(
            text = picture.explanation,
            style = MaterialTheme.typography.body1,
            modifier = Modifier.padding(top = 16.dp)
        )
    }
}

@Composable
fun AsyncImage(imageUrl: String, contentDescription: String, modifier: Modifier = Modifier) {
    // Placeholder for your image loading solution
    // Replace this with Coil or other libraries for image loading in Compose Multiplatform
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        Text("Image loading placeholder")
    }
}



@Composable
fun KryptographyScreen() {
    Column(modifier = Modifier.padding(16.dp)) {
        Text("Welcome to the Kryptography Screen")
        // Add more content for the Kryptography screen
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

@Composable
fun SettingsView() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Settings", style = MaterialTheme.typography.h6)
        // Add more settings content here
    }
}

@Composable
fun AboutView() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("About", style = MaterialTheme.typography.h6)
        Text("This is a portfolio app created by Jakub Kramný.")
        // Add more about content here
    }
}