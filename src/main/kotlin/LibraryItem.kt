abstract class LibraryItem(
    val title: String,
    val isbn: String,
    val publication: String,
    val numberOfPages: Int
) {
    var isAvailable: Boolean = true
        private set

    fun isReturned() {
        isAvailable = true
    }

    fun isBorrowed() {
        isAvailable = false
    }
}