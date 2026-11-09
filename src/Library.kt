class Library(
    val bookCollection: MutableList<Book>
) {
    //Тест
    private fun requireValidIndex(index: Int) {
        require(index in bookCollection.indices) {
            "Индекс $index вне диапазона 0..${bookCollection.lastIndex}"
        }
    }

    //вывод списка всех книг с индексом
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

    //размер
    fun getSize() : Int {
        return bookCollection.size
    }

    //возращать элемент по индексу
    operator fun get(index: Int): Book {
        return bookCollection[index]
    }

    //добавления
    fun addBook(
        newBook: Book
    ) {
        newBook.isAvailable = true
        bookCollection.add(newBook)
    }

    //удаления
    fun removeBook(
        index: Int
    ) {
        if (index in 0 until bookCollection.size) {
            bookCollection.removeAt(index)
        }
    }

    //поиска книг по автору
    fun searchByAuthor(authorName: String) =
        bookCollection.filter { it.author == authorName }

    //поиска книг по названию
    fun searchByTitle (
        title: String,
    ) = bookCollection.filter {it.title == title}

    //выдачи
    fun takeBookFromLibrary(
        index: Int
    ) {
        if (bookCollection[index].isAvailable) {
            println("Книга выдана")
            bookCollection[index].isAvailable = false
        }
        else {
            println("Книга занята другим пользователем, у нас нет возможности ее выдать")
        }
    }

    //возврата
    fun returnBookToLibrary(
        index: Int
    ) {
        bookCollection[index].isAvailable = true
        println("Спасибо, что остаетесь с нами")
    }

    //Дополнительно
    fun sortByYearOfRelease() {
        bookCollection.sortBy { it.yearOfRelease }
    }
}