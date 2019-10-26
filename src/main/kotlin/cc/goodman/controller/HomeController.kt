package cc.goodman.controller

import org.apache.logging.log4j.LogManager
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping

@Controller
class HomeController {
    private val logger = LogManager.getLogger()
    @GetMapping("/hello")
    fun hello(): String {
        logger.info("hello mapping")
        return "home";
    }
}