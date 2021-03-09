package web.dto

import domain.review.Review

class ReviewDTO(
    val id: Int,
    val musicId: String,
    val reviewerId: String,
    val text: String,
) {
    companion object {
        fun fromModel(review: Review): ReviewDTO {
            return ReviewDTO(
                id = review.id.id,
                musicId = review.musicId.id,
                reviewerId = review.reviewerId.id,
                text = review.reviewText,
            )
        }
    }
}
