fun checkForIndex(
    library: Library,
    stringRequest1: String,
) : Int {
    var index: Int
        while (true) {
            println(stringRequest1)
            try {
                index = readln().trim().toInt()
                if (index in 1..library.getSize()) {
                    break
                }
                println("Номер должен быть от 1 до ${library.getSize()}")
            } catch (e: NumberFormatException) {
                println("Нужно ввести целое число")
            }
        }
        return index
}

fun main() {
    // тестовые значения
    val books = mutableListOf(
        Book("Война и мир", "Лев Толстой", 1869, true),
        Book("Анна Каренина", "Лев Толстой", 1877, true),
        Book("Преступление и наказание", "Фёдор Достоевский", 1866, true),
        Book("Идиот", "Фёдор Достоевский", 1869, false),
        Book("Мастер и Маргарита", "Михаил Булгаков", 1967, true),
        Book("Собачье сердце", "Михаил Булгаков", 1925, true),
        Book("Евгений Онегин", "Александр Пушкин", 1833, true),
        Book("1984", "Джордж Оруэлл", 1949, false),
        Book("Скотный двор", "Джордж Оруэлл", 1945, true),
        Book("Маленький принц", "Антуан де Сент-Экзюпери", 1943, true),
        Book("Над пропастью во ржи", "Джером Сэлинджер", 1951, true),
        Book("Ревизор", "Николай Гоголь", 1836, true)
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

                var yearOfRelease = 0
                while (true) {
                    println("Введите год выпуска книги:")
                    try {
                        yearOfRelease = (readlnOrNull() ?: return).trim().toInt()
                        break
                    } catch (e: NumberFormatException) {
                        println("Нужно ввести целое число")
                    }
                }

                println("Эту книгу можно выдавать посителям? [Y/n]")
                var isAvailable: Boolean = true
                if (readlnOrNull() == "n"){
                    isAvailable = false
                }
                library.addBook(Book(title, author, yearOfRelease, isAvailable))
            }

            "2" -> {
                library.listOfBook()
                println()

                if (library.getSize() > 0) {
                    val index: Int = checkForIndex(library,"Выберите номер книги для удаления:")
                    when(library.removeBook(index - 1)) {
                        OperationResult.SUCCESS -> println("Книга удалена из библиотеки")
                        OperationResult.FAILURE -> println("Не поулчилось удалить книгу из библиотеки")
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
                library.listOfBook()
                if (library.getSize() > 0) {
                    val index = checkForIndex(library, "Выберите номер интересующей вас книги:")
                    when (library.takeBookFromLibrary(index - 1)) {
                        OperationResult.SUCCESS -> println("Книга выдана")
                        OperationResult.FAILURE -> println("Книга не выдана")
                    }

                } else {
                    println("Список книг пуст")
                }
            }

            "6" -> {
                library.listOfBook()
                if (library.getSize() > 0) {
                    val index = checkForIndex(library, "Выберите номер книги которую желаете вернуть:")
                    when (library.returnBookToLibrary(index - 1)) {
                        OperationResult.FAILURE -> println("Не получилось вернуть книгу")
                        OperationResult.SUCCESS -> println("Книгу вернулась")
                    }
                } else {
                    println("Список книг пуст")
                }
            }

            "7" -> {
                library.sortByYearOfRelease()
                library.listOfBook()
                println("Книги отсортированы!")
            }

            "8" -> {
                library.listOfBook()
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
