import androidx.compose.ui.window.ComposeUIViewController
import com.akcay.cryptokmm.App
import platform.UIKit.UIViewController

fun mainViewController(): UIViewController {
    return ComposeUIViewController {
        App()
    }
}