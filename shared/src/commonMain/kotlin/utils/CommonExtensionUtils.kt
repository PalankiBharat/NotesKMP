package utils

fun Boolean.ifTrue(callback: () -> Unit) {
    if (this) {
        callback()
    }
}