package domain

class Artist(val id: String, val name: String, val description: String) {
    override fun equals(other: Any?): Boolean {
        return hashCode() == other.hashCode()
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }
}
