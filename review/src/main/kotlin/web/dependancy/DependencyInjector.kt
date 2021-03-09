package web.dependancy

import adapter.InMemoryReviewRepository
import application.ReviewApplicationService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
open class DependencyInjector {
    @Bean
    open fun reviewApplicationService(): ReviewApplicationService {
        val reviewRepository = InMemoryReviewRepository()
        return ReviewApplicationService(reviewRepository)
    }
}
