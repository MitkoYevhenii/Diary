package com.example.diary.model

import com.github.javafaker.Faker
import com.github.javafaker.Lorem
import java.util.*

typealias UsersListener = (users: List<User>) -> Unit

class UserService {

    private var users = mutableListOf<User>()
    private val listeners = mutableSetOf<UsersListener>()

    init {
        val faker = Faker.instance()
        val generatedUsers = (1..100).map { User(
            id = it.toLong(),
            photo = null,
            title = faker.name().title(),
            content = faker.book().title(),
            epigraph =  faker.book().genre(),
            data = faker.date().birthday().toString(),
            editData = faker.date().birthday().toString(),
        ) }.toMutableList()
    }

    fun getUsers(): List<User> {
        return users
    }

    fun deleteUser(user: User) {
        val indexToDelete = users.indexOfFirst { it.id == user.id }
        if (indexToDelete != -1) {
            users.removeAt(indexToDelete)
        }
    }

    fun moveUser(user: User, moveBy: Int) {
        val oldIndex = users.indexOfFirst { it.id == user.id }
        if (oldIndex == -1) return
        val newIndex = oldIndex + moveBy
        if (newIndex < 0 || newIndex >= users.size) return
        Collections.swap(users, oldIndex, newIndex)
    }

    fun addListeners(listener: UsersListener) {
        listeners.add(listener)
        listener.invoke(users)
    }

    fun removeListener(listener: UsersListener) {
        listeners.remove(listener)
    }

    private fun notifyChanges() {
        listeners.forEach { it.invoke(users) }
    }
}