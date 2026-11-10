fun readValidIndex(size: Int, prompt: String): Int? {
    require(size > 0) { "size должен быть больше 0, получено: $size" }
    while (true) {
        val value = readIntOrNull(prompt) ?: return null
        if (value in 1..size) return value
        println("Номер должен быть от 1 до $size")
    }
}

fun listOfBook(library: Library){
    library.getNumberedBooks().forEach { it ->
        println(it)
    }
}

fun readIntOrNull (
    prompt: String
) : Int? {
    while (true) {
        println(prompt)
        val line = readlnOrNull() ?: return null
        val value = (line.trim()).toIntOrNull()
        if (value != null) return value
        println("Нужно ввести Цифру")
    }
}

fun handleAddBook(library : Library){
    while (true) {
        println("Введите название книги:")
        val title: String = readlnOrNull() ?: return

        println("Введите ФИО автора книги:")
        val author: String = readlnOrNull() ?: return

        val yearOfRelease = readIntOrNull("Введите год выпуска книги:") ?: return

        when(library.addBook(title.trim(), author.trim(), yearOfRelease)) {
            OperationResult.SUCCESS -> {
                println("Ваша книга успешно добавлена")
                break
            }
            OperationResult.INVALID_TITLE -> println("Название книги пустое, попробуйте еще раз")
            OperationResult.INVALID_AUTHOR -> println("ФИО автора пустое, попробуйте еще раз")
            OperationResult.INVALID_YEAR -> println("Год введен с ошибкой, попробуйте еще раз")
            OperationResult.ALREADY_EXISTS -> println("Книга уже есть в списке")
            else -> println("Ошибка: неожидаемый результат")
        }
    }
}

fun handleRemoveBook(library : Library){
    listOfBook(library)
    println()
    if (library.getSize() > 0) {
        val index = readValidIndex(library.getSize(),"Выберите номер книги для удаления:") ?: return
        when (library.removeBook(index - 1)) {
            OperationResult.SUCCESS -> println("Книга удалена из библиотеки")
            OperationResult.INVALID_INDEX -> println("Такой книги нет в библиотеке")
            else -> println("Ошибка: неожидаемый результат")
        }
    }
    else {
        println("Список книг пуст")
    }
}

fun handleSearchByAuthor(library : Library){
    println("Введите ФИО автора:")
    val found = library.searchByAuthor(readlnOrNull() ?: return)
    if (found.isNotEmpty()) {
        println("Книги найденные по вашему запросу:")
        found.forEach { println(it) }
    } else {
        println("Книги этого автора не найдены")
    }
}

fun handleSearchByTitle(library : Library) {
    println("Введите название, интересующей вас книги:")
    val found = library.searchByTitle(readlnOrNull() ?: return)
    if (found.isNotEmpty()) {
        println("Книги найденные по вашему запросу:")
        found.forEach { println(it) }
    }
    else {
        println("Книги с таким названием не найдеы")
    }
}

fun handleTakeBook(library : Library){
    listOfBook(library)
    if (library.getSize() > 0) {
        val index = readValidIndex(library.getSize(), "Выберите номер интересующей вас книги:") ?: return
        when (library.takeBookFromLibrary(index - 1)) {
            OperationResult.SUCCESS -> println("Книга выдана")
            OperationResult.ALREADY_TAKEN -> println("Книга уже занята другим пользователем")
            OperationResult.INVALID_INDEX -> println("Такой книги нет в библиотеке")
            else -> println("Ошибка: неожидаемый результат")
        }
    } else {
        println("Список книг пуст")
    }
}

fun handleReturnBook(library : Library){
    listOfBook(library)
    if (library.getSize() > 0) {
        val index = readValidIndex(library.getSize(), "Выберите номер книги которую желаете вернуть:") ?: return
        when (library.returnBookToLibrary(index - 1)) {
            OperationResult.SUCCESS -> println("Книга возвращена")
            OperationResult.INVALID_INDEX -> println("Такой книги нет в библиотеке")
            OperationResult.NOT_TAKEN -> println("Книга уже возращена")
            else -> println("Ошибка: неожидаемый результат")
        }
    } else {
        println("Список книг пуст")
    }
}

fun handleSortByYear(library : Library){
    if (library.getSize() != 0){
        library.sortByYearOfRelease()
        listOfBook(library)
        println("Книги отсортированы")
    }
    else {
        println("Библиотека пуста")
    }
}

fun handleShowAll(library : Library) {
    if (library.getSize() != 0) {
        listOfBook(library)
    }
    else{
        println("Библиотека пуста")
    }
}

fun printMenu() {
    println("""
=== Система управления библиотекой ===
1. Добавить книгу
2. Удалить книгу
3. Поиск книг автора
4. Поиск книги по названию
5. Взять книгу из библиотеки
6. Вернуть книгу в библиотеку
7. Отсортить книги по году выпуска
8. Показать все книги
0. Завершить работу"""
    )
}

fun main() {
    // тестовые значения
    val books = listOf<Book>(
        Book("Война и мир", "Лев Толстой", 1869),
        Book("Анна Каренина", "Лев Толстой", 1877),
        Book("Преступление и наказание", "Фёдор Достоевский", 1866),
        Book("Идиот", "Фёдор Достоевский", 1869),
        Book("Мастер и Маргарита", "Михаил Булгаков", 1967),
        Book("Собачье сердце", "Михаил Булгаков", 1925),
        Book("Евгений Онегин", "Александр Пушкин", 1833),
        Book("1984", "Джордж Оруэлл", 1949),
        Book("Скотный двор", "Джордж Оруэлл", 1945),
        Book("Маленький принц", "Антуан де Сент-Экзюпери", 1943),
        Book("Над пропастью во ржи", "Джером Сэлинджер", 1951),
        Book("Ревизор", "Николай Гоголь", 1836)
    )

    val library = Library(books)

    var input: String = ""

    while( input != "0") {
        printMenu()

        input = readlnOrNull() ?: return

        when (input) {
            "1" -> handleAddBook(library)

            "2" -> handleRemoveBook(library)

            "3" -> handleSearchByAuthor(library)

            "4" -> handleSearchByTitle(library)

            "5" -> handleTakeBook(library)

            "6" -> handleReturnBook(library)

            "7" -> handleSortByYear(library)

            "8" -> handleShowAll(library)

            "0" -> {
                println("Будем рады увидеть вас вновь!")
            }

            else -> {
                println("Нужно ввести цифру в диапозоне от 1 до 8, либо 0, попробуйте еще раз")
            }
        }
    }
}
