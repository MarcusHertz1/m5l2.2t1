package ru.netology

data class Comments(
    val count: Int = 0,
    val canPost: Boolean = true,
    val groupsCanPost: Boolean = false
)

data class Likes(
    val count: Int = 0,
    val userLikes: Boolean = false,
    val canLike: Boolean = true
)

data class Post(
    val id: Int = 0,
    val date: Int? = null,
    val text: String? = null,
    val friendsOnly: Boolean = false,
    val canPin: Boolean = false,
    val canDelete: Boolean = false,
    val canEdit: Boolean = false,
    val isPinned: Boolean = false,
    val markedAsAds: Boolean = false,
    val isFavorite: Boolean = false,
    val comments: Comments? = Comments(),
    val likes: Likes? = Likes()
)

object WallService {
    private var posts = mutableListOf<Post>()
    private var nextId = 1

    fun add(post: Post): Post {
        val postWithId = post.copy(id = nextId++)
        posts += postWithId
        return postWithId
    }

    fun update(post: Post): Boolean {
        for ((index, current) in posts.withIndex()) {
            if (current.id == post.id) {

                posts[index] = post.copy(
                    date = current.date,
                    comments = current.comments,
                    likes = current.likes
                )
                return true
            }
        }
        return false
    }

    fun clear() {
        posts.clear()
        nextId = 1
    }

    fun getAll(): List<Post> = posts.toList()
}

fun main() {

    val post1 = Post(
        date = 1680000000,
        text = "Первый пост!",
        friendsOnly = true,
        canPin = true,
        canDelete = true,
        canEdit = true,
        isPinned = false,
        markedAsAds = false,
        isFavorite = true
    )

    val addedPost1 = WallService.add(post1)
    println("Добавлен пост: $addedPost1")

    val post2 = Post(
        date = 1680000010,
        text = "Второй пост для всех!",
        friendsOnly = false,
        canPin = true,
        canDelete = false,
        canEdit = true,
        isPinned = true,
        markedAsAds = true,
        isFavorite = false
    )

    val addedPost2 = WallService.add(post2)
    println("Добавлен пост: $addedPost2")

    val updated = WallService.update(
        addedPost1.copy(text = "Первый пост (обновлено)")
    )
    println("Пост обновлён: $updated")

    println("\nВсе посты:")
    for (post in WallService.getAll()) {
        println(post)
    }
}