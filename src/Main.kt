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
                val title: String = readln()

                println("Введите ФИО автора книги:")
                val author: String = readln()

                var yearOfRelease = 0
                while (true) {
                    println("Введите год выпуска книги:")
                    try {
                        yearOfRelease = readln().trim().toInt()
                        break
                    } catch (e: NumberFormatException) {
                        println("Нужно ввести целое число")
                    }
                }

                library.addBook(Book(title, author, yearOfRelease, true))
            }

            "2" -> {
                library.listOfBook()
                println()

                if (library.getSize() > 0) {
                    var index: Int

                    while (true) {
                        println("Выберите номер книги для удаления:")
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

                    library.removeBook(index - 1)
                }
                else {
                    println("Список книг пуст, удаление невозможно")
                }
            }

            //Сделано
            "3" -> {
                println("Введите ФИО автора:")
                println("Книги найденные по вашему запросу:${library.searchByAuthor(readln())}")
            }

            //Сделано
            "4" -> {
                println("Введите название, интересующей вас книги:")
                println("Книги найденные по вашему запросу:${library.searchByTitle(readln())}")
            }

            "5" -> {
                library.listOfBook()
                var index: Int = 0
                if (library.getSize() > 0) {
                    while (true) {
                        println("Выберите номер интересующей вас книги:")
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
                    library.takeBookFromLibrary(index - 1)
                }
                else {
                    println("Список книг пуст")
                }
            }

            "6" -> {
                library.listOfBook()
                var index: Int = 0
                if (library.getSize() > 0) {
                    while (true) {
                        println("Выберите номер книги которую желаете вернуть:")
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
                    library.returnBookToLibrary(index - 1)
                }
                else {
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
