data class Book(
    val title: String,
    val author: String,
    val yearOfRelease: Int,
    var isAvailable: Boolean
) {
    override fun toString(): String {
        return """
Название: $title
Автор: $author
Год выпуска: $yearOfRelease
""".trimIndent()
    }
}