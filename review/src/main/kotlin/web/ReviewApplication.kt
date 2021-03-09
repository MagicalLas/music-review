package web

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
open class ReviewApplication

fun main(args: Array<String>) {
    runApplication<ReviewApplication>(*args)
}
