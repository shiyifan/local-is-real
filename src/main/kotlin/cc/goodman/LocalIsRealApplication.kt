package cc.goodman

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.ViewResolver
import org.springframework.web.servlet.config.annotation.EnableWebMvc
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer
import org.springframework.web.servlet.view.InternalResourceViewResolver

fun main(args: Array<String>) {
    SpringApplication.run(RootContextConfig::class.java, *args)
}


@Configuration
@EnableAutoConfiguration
open class RootContextConfig {
}

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = ["cc.goodman.controller"])
open class WebContextConfig : WebMvcConfigurer {
//    override fun configureViewResolvers(registry: ViewResolverRegistry) {
//        registry.jsp()
//    }

    @Bean
    open fun viewResolver(): ViewResolver {
        val i = InternalResourceViewResolver();
        i.setPrefix("/")
        i.setSuffix(".jsp")
        return i
    }

//    override fun configureDefaultServletHandling(configurer: DefaultServletHandlerConfigurer) {
//        configurer.enable()
//    }
}

class LocalIsRealServletInitializer : AbstractAnnotationConfigDispatcherServletInitializer() {
    override fun getRootConfigClasses(): Array<Class<*>>? = arrayOf(RootContextConfig::class.java)

    override fun getServletMappings(): Array<String> = arrayOf("/")

    override fun getServletConfigClasses(): Array<Class<*>>? = arrayOf(WebContextConfig::class.java)
}
