fun readValidIndex(library: Library, prompt: String): Int? {
    val size = library.getSize()
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

fun main() {
    // тестовые значения
    val books = mutableListOf(
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
        input = readlnOrNull() ?: return

        when (input) {
            "1" -> {
                println("Введите название книги:")
                val title: String = readlnOrNull() ?: return

                println("Введите ФИО автора книги:")
                val author: String = readlnOrNull() ?: return

                val yearOfRelease = readIntOrNull("Введите год выпуска книги:") ?: return

                library.addBook(Book(title, author, yearOfRelease))
            }

            "2" -> {
                listOfBook(library)

                println()

                if (library.getSize() > 0) {
                    val index = readValidIndex(library,"Выберите номер книги для удаления:") ?: return
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

            "3" -> {
                println("Введите ФИО автора:")
                val found = library.searchByAuthor(readlnOrNull() ?: return)
                if (found.isNotEmpty()) {
                    println("Книги найденные по вашему запросу:")
                    found.forEach { println(it) }
                } else {
                    println("Книги этого автора не найдены")
                }
            }

            "4" -> {
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

            "5" -> {
                listOfBook(library)
                if (library.getSize() > 0) {
                    val index = readValidIndex(library, "Выберите номер интересующей вас книги:") ?: return
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

            "6" -> {
                listOfBook(library)
                if (library.getSize() > 0) {
                    val index = readValidIndex(library, "Выберите номер книги которую желаете вернуть:") ?: return
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

            "7" -> {
                if (library.getSize() != 0){
                    library.sortByYearOfRelease()
                    listOfBook(library)
                    println("Книги отсортированы")
                }
                else {
                    println("Библиотека пуста")
                }
            }

            "8" -> {
                if (library.getSize() != 0) {
                    listOfBook(library)
                }
                else{
                    println("Библиотека пуста")
                }
            }

            "0" -> {
                println("Будем рады увидеть вас вновь!")
            }

            else -> {
                println("Нужно ввести цифру в диапозоне от 1 до 8, либо 0, попробуйте еще раз")
            }
        }
    }
}
