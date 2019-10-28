package cc.goodman.controller

import org.apache.logging.log4j.LogManager
import org.springframework.context.ApplicationContext
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import java.util.*

@Controller
class HomeController(private val appContext: ApplicationContext) {
    private val logger = LogManager.getLogger()
    @GetMapping("/hello")
    fun hello(): String {
        logger.info("Hello Hello Hello")
        logger.info(appContext.getMessage("hello", null, Locale.getDefault()))
        return "thymeleafHome";
    }
}