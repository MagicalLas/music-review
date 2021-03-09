package adapter

import arrow.core.Either
import arrow.core.rightIfNotNull
import domain.review.Review
import domain.review.ReviewId
import domain.review.ReviewNotFountError
import domain.review.ReviewRepository
import domain.user.UserId

class InMemoryReviewRepository : ReviewRepository {
    private val inMemoryCache: MutableMap<ReviewId, Review> = mutableMapOf()
    private var lastId = 1

    override fun getAllReview(): List<Review> {
        return inMemoryCache.values.toList()
    }

    override fun findByReviewerId(reviewerId: UserId): List<Review> {
        return inMemoryCache.values.filter {
            it.reviewerId == reviewerId
        }
    }

    override fun save(review: Review) {
        inMemoryCache[review.id] = review
    }

    override fun nextId(): ReviewId {
        return ReviewId(lastId++)
    }

    override fun findBy(id: ReviewId): Either<ReviewNotFountError, Review> {
        return inMemoryCache[id]
            .rightIfNotNull { ReviewNotFountError(id) }
    }
}
