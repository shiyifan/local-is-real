package cc.goodman.controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import java.io.PrintWriter

@Controller
class HomeController {
    @GetMapping("/hello")
    fun hello(out: PrintWriter): String {
        return "home";
    }
}