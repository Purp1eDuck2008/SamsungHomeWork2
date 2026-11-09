data class Book(
    val title: String,
    val author: String,
    val yearOfRelease: Int,
) {
    var isAvailable: Boolean = true
        private set

    fun take() : OperationResult {
        if (!isAvailable) return OperationResult.ALREADY_TAKEN
        isAvailable = false
        return OperationResult.SUCCESS
    }

    fun returnBook() : OperationResult {
        if(isAvailable) return OperationResult.NOT_TAKEN
        isAvailable = true
        return OperationResult.SUCCESS
    }

    override fun toString(): String {
        return """
Название: $title
Автор: $author
Год выпуска: $yearOfRelease
Доступность: $isAvailable""".trimIndent()
    }
}