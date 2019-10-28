package cc.goodman

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.context.ApplicationContext
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.context.support.ReloadableResourceBundleMessageSource
import org.springframework.web.servlet.ViewResolver
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer
import org.springframework.web.servlet.config.annotation.EnableWebMvc
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer
import org.springframework.web.servlet.view.InternalResourceViewResolver
import org.thymeleaf.spring5.SpringTemplateEngine
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver
import org.thymeleaf.spring5.view.ThymeleafViewResolver
import org.thymeleaf.templatemode.TemplateMode

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
open class WebContextConfig(private val appContext: ApplicationContext) : WebMvcConfigurer {
    override fun configureDefaultServletHandling(configurer: DefaultServletHandlerConfigurer): Unit =
        configurer.enable()

    override fun addResourceHandlers(registry: ResourceHandlerRegistry) {
        registry.addResourceHandler("/img/**").addResourceLocations("/img/")
    }

    @Bean
    open fun jspViewResolver(): ViewResolver = InternalResourceViewResolver().apply {
        setPrefix("/")
        setSuffix(".jsp")
        order = Int.MAX_VALUE
    }

    @Bean
    open fun messageSource(): ReloadableResourceBundleMessageSource = ReloadableResourceBundleMessageSource().apply {
        setBasename("i18n/label")
    }

    @Bean
    open fun thymeleafTemplateResolver(): SpringResourceTemplateResolver = SpringResourceTemplateResolver().apply {
        setApplicationContext(appContext)
        prefix = "/template/"
        suffix = ".html"
        templateMode = TemplateMode.HTML
        isCacheable = false
    }

    @Bean
    open fun thymeleafTemplateEngine(): SpringTemplateEngine = SpringTemplateEngine().apply {
        setTemplateResolver(thymeleafTemplateResolver())
        enableSpringELCompiler = true
    }

    @Bean
    open fun thymeleafViewResolver(): ThymeleafViewResolver = ThymeleafViewResolver().apply {
        templateEngine = thymeleafTemplateEngine()
        order = 1
    }
}

class LocalIsRealServletInitializer : AbstractAnnotationConfigDispatcherServletInitializer() {
    override fun getRootConfigClasses(): Array<Class<*>>? = arrayOf(RootContextConfig::class.java)

    override fun getServletMappings(): Array<String> = arrayOf("/")

    override fun getServletConfigClasses(): Array<Class<*>>? = arrayOf(WebContextConfig::class.java)
}
