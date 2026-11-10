data class Book(
    val title: String,
    val author: String,
    val yearOfRelease: Int,
    val isAvailable: Boolean = true,
) {
    override fun toString(): String = """
        
        Название: $title
        Автор: $author
        Год выпуска: $yearOfRelease
        Доступность: $isAvailable
        
    """.trimIndent()
}