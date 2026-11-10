class Library(
    initialBookCollection: List<Book>
) {
    private val bookCollection: MutableList<Book> = initialBookCollection.toMutableList()

    fun getNumberedBooks(): List<String> =
        bookCollection.mapIndexed { index, book -> "${index + 1}: $book" }

    fun getSize() : Int {
        return bookCollection.size
    }

    fun addBook(title: String, author: String, yearOfRelease: Int): OperationResult {
        // Логично добавить trim(), но мне кажется это некоретным, так как пользователь теряет контроль
        if (title.isBlank()) return OperationResult.INVALID_TITLE
        if (author.isBlank()) return OperationResult.INVALID_AUTHOR
        if (yearOfRelease <= 0 || yearOfRelease >= java.time.Year.now().value + 1) return OperationResult.INVALID_YEAR

        //Абсолютно не логично, но не смог найти других применений find
        val duplicate = bookCollection.find {
            it.title.equals(title, ignoreCase = true) &&
                    it.author.equals(author, ignoreCase = true) &&
                        it.yearOfRelease.equals(yearOfRelease)
        }
        if (duplicate != null) return OperationResult.ALREADY_EXISTS
        bookCollection.add(Book(title, author, yearOfRelease))
        return OperationResult.SUCCESS
    }

    fun removeBook(
        index: Int
    ) : OperationResult {
        if (index !in bookCollection.indices) { return OperationResult.INVALID_INDEX }
        bookCollection.removeAt(index)
        return OperationResult.SUCCESS
    }

    fun searchByAuthor(authorName: String) =
        bookCollection.filter { it.author.trim().lowercase() == authorName.trim().lowercase() }

    fun searchByTitle (
        title: String,
    ) = bookCollection.filter {it.title.trim().lowercase() == title.trim().lowercase()}

    fun takeBookFromLibrary(index: Int): OperationResult {
        if (index !in bookCollection.indices) return OperationResult.INVALID_INDEX
        val book = bookCollection[index]
        if (!book.isAvailable) return OperationResult.ALREADY_TAKEN
        bookCollection[index] = book.copy(isAvailable = false)
        return OperationResult.SUCCESS
    }

    fun returnBookToLibrary(index: Int): OperationResult {
        if (index !in bookCollection.indices) return OperationResult.INVALID_INDEX
        val book = bookCollection[index]
        if (book.isAvailable) return OperationResult.NOT_TAKEN
        bookCollection[index] = book.copy(isAvailable = true)
        return OperationResult.SUCCESS
    }

    fun sortByYearOfRelease() {
        bookCollection.sortBy { it.yearOfRelease }
    }
}