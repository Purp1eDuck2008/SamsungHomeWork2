class Library(
    private val bookCollection: MutableList<Book>
) {
    //Вывод списка всех книг с индексом
    fun listOfBook() {
        if (bookCollection.isNotEmpty()) {
            var numberOfBook: Int = 1
            bookCollection.forEach { it ->
                println("$numberOfBook: ${it.toString()}")
                numberOfBook++
            }
        }
        else {
            println("Список книг пуст")
        }
    }

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
        bookCollection.filter { it.author == authorName }

    //Поиск книги в библиотеке по названию
    fun searchByTitle (
        title: String,
    ) = bookCollection.filter {it.title == title}

    //Выдача книги по индексу
    fun takeBookFromLibrary(
        index: Int
    ) : OperationResult {
        if (index !in bookCollection.indices) { return OperationResult.INVALID_INDEX }
        if (!bookCollection[index].isAvailable) {return OperationResult.ALREADY_TAKEN }
        bookCollection[index].isAvailable = false
        return OperationResult.SUCCESS
    }

    //Возврат книги по индексу
    fun returnBookToLibrary(
        index: Int
    ) : OperationResult {
        if (index !in bookCollection.indices) {return OperationResult.INVALID_INDEX}
        bookCollection[index].isAvailable = true
        return OperationResult.SUCCESS
    }

    //Дополнительное задание (Сортировка книг по году выпуска)
    fun sortByYearOfRelease() {
        bookCollection.sortBy { it.yearOfRelease }
    }
}