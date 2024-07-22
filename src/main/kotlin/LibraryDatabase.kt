class LibraryDatabase(
    var currentLibrarian: Librarian,
) {

    private val availableItems: MutableList<LibraryItem> = mutableListOf()
    private val borrowedItems: MutableMap<LibraryItem, User> = mutableMapOf()

    fun addLibraryItem(item: LibraryItem) {
        availableItems.add(item)
    }

    fun lendLibraryItem(item: LibraryItem, user: User) {
        item.isBorrowed()
        borrowedItems[item] = user
        availableItems.remove(item)

    }

    fun viewAvailableItems() {
        if (availableItems.isEmpty()) {
            println("No available items.")
        } else {
            availableItems.forEach { item ->
                println(
                    "${item.title} by ${item.publication}," +
                            " ISBN: ${item.isbn}, Number Of Pages: ${item.numberOfPages}," +
                            " Available: ${item.isAvailable}"
                )
            }
        }
    }

    fun searchForLibraryItem(title: String): Boolean {
        return availableItems.find { it.title == title } != null
    }

    fun getLibraryItem(title: String): LibraryItem {
        return availableItems.find { it.title == title }!!
    }

    fun getBorrowedLibraryItem(title: String): LibraryItem? {
        return borrowedItems.keys.find { it.title == title }
    }

    fun receiveItemFromBorrower(item: LibraryItem) {
        item.isReturned()
        availableItems.add(item)
        borrowedItems.remove(item)

    }
}