class Library(
    private val bookCollection: MutableList<Book>
) {
    //Вывод списка всех книг с индексом
    fun getNumberedBooks(): List<String> =
        bookCollection.mapIndexed { index, book -> "${index + 1}: $book" }

    //Получение размера библиотеки
    fun getSize() : Int {
        return bookCollection.size
    }

    //Добавление новой книги в библиотеку
    fun addBook(
        newBook: Book
    ) {
        bookCollection.add(newBook)
    }

    //Удаление книги из библиотеки
    fun removeBook(
        index: Int
    ) : OperationResult {
        if (index !in bookCollection.indices) { return OperationResult.INVALID_INDEX }
        bookCollection.removeAt(index)
        return OperationResult.SUCCESS
    }

    //Поиск книги в библиотеке по автору
    fun searchByAuthor(authorName: String) =
        bookCollection.filter { it.author.lowercase() == authorName.lowercase() }

    //Поиск книги в библиотеке по названию
    fun searchByTitle (
        title: String,
    ) = bookCollection.filter {it.title.lowercase() == title.lowercase()}

    //Выдача книги по индексу
    fun takeBookFromLibrary(
        index: Int
    ) : OperationResult {
        if (index !in bookCollection.indices) { return OperationResult.INVALID_INDEX }
        return bookCollection[index].take()
    }

    //Возврат книги по индексу
    fun returnBookToLibrary(
        index: Int
    ) : OperationResult {
        if (index !in bookCollection.indices) {return OperationResult.INVALID_INDEX}
        return bookCollection[index].returnBook()
    }

    //Дополнительное задание (Сортировка книг по году выпуска)
    fun sortByYearOfRelease() {
        bookCollection.sortBy { it.yearOfRelease }
    }
}