package domain.review

import arrow.core.Either
import domain.user.UserId

interface ReviewRepository {
    fun getAllReview(): List<Review>
    fun findByReviewerId(reviewerId: UserId): List<Review>
    fun save(review: Review)
    fun nextId(): ReviewId
    fun findBy(id: ReviewId): Either<ReviewNotFountError, Review>
}
