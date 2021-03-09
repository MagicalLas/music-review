package domain.review

import domain.user.UserId

class Review (
    val id: ReviewId,
    val reviewerId: UserId,
    val musicId: MusicId,
    val reviewText: String) {
}
