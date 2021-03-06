package br.com.luciano.delivery.core.openapi;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@Import(BeanValidatorPluginsConfiguration.class) // integração do springfox com o bean validation para adicionar required
public class SpringFoxConfig implements WebMvcConfigurer {

    @Bean
    public Docket apiDocket() {
        return new Docket(DocumentationType.SWAGGER_2)
            .select()
                .apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
                .build()
            .apiInfo(apiInfo())
            .tags(new Tag("Cities", "Manage the cities"), tags());
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");

        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

    private Tag [] tags() {
        return new Tag[] {
                new Tag("Kitchens", "Manage the kitchens"),
                new Tag("States", "Manage the states"),
                new Tag("Payments", "Manage the payments"),
                new Tag("Permissions", "Manage the permissions"),
                new Tag("Restaurants", "Manage the restaurants"),
                new Tag("Orders", "Manage the orders")
        };
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Delivery API")
                .description("API open for restaurant customers")
                .version("1.0.0")
                .contact(new Contact("Luciano Lima", "www.umlima.com.br", "luclimasilva23@gmail.com"))
                .build();
    }
}
