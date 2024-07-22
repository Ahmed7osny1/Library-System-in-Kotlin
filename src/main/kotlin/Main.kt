fun loadBooks(number: Int, libraryDatabase: LibraryDatabase) {

    var title: String
    var isbn: String
    var publication: String
    var numberOfPages: Int

    for (i in 1..number) {

        println("Please Enter Your Title of Book: ")
        title = readln()
        println("Please Enter Your isbn of Book: ")
        isbn = readln()
        println("Please Enter Your publication of Book: ")
        publication = readln()
        println("Please Enter Your Number Of Pages of Book: ")
        numberOfPages = readln().toInt()

        libraryDatabase.addLibraryItem(
            Book(
                title,
                isbn,
                publication,
                numberOfPages
            )
        )
    }
}

fun loadMagazine(number: Int, libraryDatabase: LibraryDatabase) {

    var title: String
    var isbn: String
    var publication: String
    var numberOfPages: Int

    for (i in 1..number) {

        println("Please Enter Your Title of Magazine: ")
        title = readln()
        println("Please Enter Your isbn of Magazine: ")
        isbn = readln()
        println("Please Enter Your publication of Magazine: ")
        publication = readln()
        println("Please Enter Your Number Of Pages of Magazine: ")
        numberOfPages = readln().toInt()

        libraryDatabase.addLibraryItem(
            Magazine(
                title,
                isbn,
                publication,
                numberOfPages
            )
        )
    }
}

fun loadJournal(number: Int, libraryDatabase: LibraryDatabase) {

    var title: String
    var isbn: String
    var publication: String
    var numberOfPages: Int

    for (i in 1..number) {

        println("Please Enter Your Title of Journal: ")
        title = readln()
        println("Please Enter Your isbn of Journal: ")
        isbn = readln()
        println("Please Enter Your publication of Journal: ")
        publication = readln()
        println("Please Enter Your Number Of Pages of Journal: ")
        numberOfPages = readln().toInt()

        libraryDatabase.addLibraryItem(
            Journal(
                title,
                isbn,
                publication,
                numberOfPages
            )
        )
    }
}

fun typeChoice(): Int {
    println("1- Item is Book: ")
    println("2- Item is Magazine: ")
    println("3- Item isJournal: ")

    println("Enter choice: ")
    val choice = readln().toInt()
    return choice
}

fun userData(): User {
    println("Please, Enter Your Name of user: ")
    val userName = readln()
    println("Please, Enter Your Id: ")
    val userId = readln()

    return User(userName, userId)
}

fun systemServices(libraryDatabase: LibraryDatabase) {

    println("\n\n-------- System Services --------\n\n")

    println("1- You can Add Library Item: ")
    println("2- You can lend Library Item: ")
    println("3- You can view Available Library Item: ")
    println("4- You can search For Library Item: ")
    println("5- You can receive Item From Borrower: ")
    println("6- Don't need Operation Again")

    println("\n\nPlease Enter your operation: ")
    val operation = readln().toInt()

    when (operation) {
        1 -> {

            val number: Int

            when (typeChoice()) {
                1 -> {
                    println("Enter Number of Books: ")
                    number = readln().toInt()
                    loadBooks(number, libraryDatabase)
                    println("Books Added Successfully")
                }

                2 -> {
                    println("Enter Number of Magazines: ")
                    number = readln().toInt()
                    loadMagazine(number, libraryDatabase)
                    println("Magazines Added Successfully")
                }

                3 -> {
                    println("Enter Number of Journals: ")
                    number = readln().toInt()
                    loadJournal(number, libraryDatabase)
                    println("Journals Added Successfully")
                }

                else -> {
                    println("PLease Enter 1, 2, 3")
                    systemServices(libraryDatabase)
                }
            }
            systemServices(libraryDatabase)
        }

        2 -> {
            println("Enter Title: ")
            val title = readln()
            if (libraryDatabase.searchForLibraryItem(title)) {
                val user = userData()
                libraryDatabase.lendLibraryItem(
                    libraryDatabase.getLibraryItem(title), user
                )
                println("Lent $title to ${user.name}")

            } else {
                println("Item is Not Available")
            }
            systemServices(libraryDatabase)
        }

        3 -> {
            println("Available Items: ")
            libraryDatabase.viewAvailableItems()
            systemServices(libraryDatabase)
        }

        4 -> {
            println("Enter Title: ")
            val title = readln()
            if (libraryDatabase.searchForLibraryItem(title)) {

                println("Item is Available\n")

                println("You want to Lend? ")
                val borrow = readln().single()
                if (borrow == 'y') {
                    val user = userData()
                    libraryDatabase.lendLibraryItem(
                        libraryDatabase.getLibraryItem(title), user
                    )
                    println("Lent $title to ${user.name}")
                } else {
                    println("Ok, We thank you for your presence")
                }

            } else {
                println("Item is Not Available")
            }
            systemServices(libraryDatabase)
        }

        5 -> {

            println("Please Enter Your Title of item: ")
            val title: String = readln()

            if (libraryDatabase.getBorrowedLibraryItem(title) != null) {
                libraryDatabase.receiveItemFromBorrower(
                    libraryDatabase.getBorrowedLibraryItem(title)!!
                )
                println("Ite is received")
                println("Ok, We thank you for your presence")
            } else {
                println("Borrowed Item not Found")
            }
            systemServices(libraryDatabase)
        }

        6 -> println("Ok, We thank you for your presence")

        else -> {
            println("PLease Enter 1, 2, 3, 4, 5")
            systemServices(libraryDatabase)
        }
    }

}

fun main() {

    println("Welcome in Library System\n\n")

    println("Please, Enter Your Name: ")
    val librarianName = readln()
    println("Please, Enter Your Id: ")
    val librarianId = readln()

    val librarian = Librarian(librarianName, librarianId)

    val libraryDatabase = LibraryDatabase(currentLibrarian = librarian)

    val availableBook = Book("Kotlin", "K1547", "Head First", 735)
    val availableMagazine = Magazine("kotlin Magazine", "M1543", "Magazine", 30)
    val availableJournal = Journal("Kotlin Journal", "J6434", "Journal", 150)

    libraryDatabase.addLibraryItem(availableBook)
    libraryDatabase.addLibraryItem(availableMagazine)
    libraryDatabase.addLibraryItem(availableJournal)

    systemServices(libraryDatabase)

}