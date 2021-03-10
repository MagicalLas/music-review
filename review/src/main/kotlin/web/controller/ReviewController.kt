package web.controller

import application.ReviewApplicationService
import domain.review.MusicId
import domain.user.UserId
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import web.dto.CreateReviewRequestDTO
import web.dto.ReviewDTO

@RestController
@RequestMapping
@CrossOrigin
class ReviewController(
    @Autowired val reviewApplicationService: ReviewApplicationService,
) {
    @GetMapping("/reviews")
    fun getAllReviewList(): List<ReviewDTO> {
        return reviewApplicationService
            .getAllReviewList()
            .map { ReviewDTO.fromModel(it) }
    }

    @PostMapping("/reviews")
    fun createReview(
        @RequestBody request: CreateReviewRequestDTO
    ): ReviewDTO {
        val review = reviewApplicationService.reviewMusic(
            UserId(request.userId),
            MusicId(request.musicId),
            request.text
        )
        return ReviewDTO.fromModel(review)
    }

    @GetMapping("/users/{id}/reviews")
    fun getUserReviewList(@PathVariable id: String): List<ReviewDTO> {
        return reviewApplicationService
            .getMyReviewList(UserId(id))
            .map { ReviewDTO.fromModel(it) }
    }
}
