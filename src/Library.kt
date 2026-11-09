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
    ) {
        if (index in 0 until bookCollection.size) {
            bookCollection.removeAt(index)
        }
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
    ) {
        if (bookCollection[index].isAvailable) {
            bookCollection[index].isAvailable = false
        }
        else {
            println("Книга занята другим пользователем, у нас нет возможности ее выдать")
        }
    }

    //Возврат книги по индексу
    fun returnBookToLibrary(
        index: Int
    ) {
        bookCollection[index].isAvailable = true
    }

    //Дополнительное задание (Сортировка книг по году выпуска)
    fun sortByYearOfRelease() {
        bookCollection.sortBy { it.yearOfRelease }
    }
}