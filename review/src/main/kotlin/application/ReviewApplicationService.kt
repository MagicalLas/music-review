package application

import arrow.core.Either
import domain.review.MusicId
import domain.review.Review
import domain.review.ReviewId
import domain.review.ReviewNotFountError
import domain.review.ReviewRepository
import domain.user.UserId

class ReviewApplicationService(
    private val reviewRepository: ReviewRepository,
) {
    fun reviewMusic(
        authorId: UserId,
        musicId: MusicId,
        reviewText: String
    ): Review {
        val id = reviewRepository.nextId()
        val review = Review(id, authorId, musicId, reviewText)
        reviewRepository.save(review)
        return review
    }

    fun getMyReviewList(
        userId: UserId
    ): List<Review> {
        return reviewRepository.findByReviewerId(userId)
    }

    fun getAllReviewList(): List<Review> {
        return reviewRepository.getAllReview()
    }

    fun getSpecificReview(
        reviewId: ReviewId
    ): Either<ReviewNotFountError, Review> {
        return reviewRepository.findBy(reviewId)
    }
}
