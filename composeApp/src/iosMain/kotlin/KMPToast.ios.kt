import platform.UIKit.UIAlertView

actual class KMPToast {

    actual fun showToast(message: String) {
        val alertView = UIAlertView()
        alertView.title = "Alert"
        alertView.message = message
        alertView.show()
    }
}