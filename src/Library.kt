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

    //добавления
    fun addBook(
        newBook: Book
    ) {
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
    }

    //Дополнительно
    fun sortByYearOfRelease() {
        bookCollection.sortBy { it.yearOfRelease }
    }
}