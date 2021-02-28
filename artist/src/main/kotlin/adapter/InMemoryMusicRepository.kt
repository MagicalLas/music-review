package adapter

import domain.Artist
import domain.Music

class InMemoryMusicRepository {
    private val inMemoryCache: MutableMap<String, Music> = mutableMapOf()
    private var lastId = 1

    fun save(music: Music) {
        inMemoryCache[music.id] = music
    }

    fun findAllMusic(): List<Music> {
        return inMemoryCache.values.toList()
    }

    fun nextId(): String {
        return lastId++.toString()
    }
}