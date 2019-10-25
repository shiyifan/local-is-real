package cc.goodman

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer

fun main(args: Array<String>) {
    SpringApplication.run(RootContextConfig::class.java, *args)
}


@Configuration
@EnableAutoConfiguration
open class RootContextConfig {
}

@Configuration
@ComponentScan(basePackages = ["cc.goodman.controller"])
open class WebContextConfig {
}

class LocalIsRealServletInitializer : AbstractAnnotationConfigDispatcherServletInitializer() {
    override fun getRootConfigClasses(): Array<Class<*>>? = arrayOf(RootContextConfig::class.java)

    override fun getServletMappings(): Array<String> = arrayOf("/")

    override fun getServletConfigClasses(): Array<Class<*>>? = arrayOf(WebContextConfig::class.java)
}
