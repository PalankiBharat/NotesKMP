package expect_actuals

import javax.swing.JOptionPane

actual class KMPToast {
    actual fun showToast(message: String) {
        JOptionPane.showMessageDialog(null, message)
    }
}
