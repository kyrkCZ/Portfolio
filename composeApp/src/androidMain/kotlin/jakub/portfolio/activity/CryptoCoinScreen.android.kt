package jakub.portfolio.activity

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp

@Composable
actual fun CryptoCoinScreen() {
    val context = LocalContext.current

    Column(modifier = Modifier.padding(16.dp)) {
        Text("Welcome to the Crypto-Coin Screen")
        Button(
            onClick = {
                val intent = Intent(Intent.ACTION_VIEW).apply {
                    data = Uri.parse("https://play.google.com/store/apps/details?id=com.yourapp.package")
                }
                context.startActivity(intent)
            },
            modifier = Modifier.padding(top = 16.dp)
        ) {
            Text("Open in Play Store")
        }
    }
}