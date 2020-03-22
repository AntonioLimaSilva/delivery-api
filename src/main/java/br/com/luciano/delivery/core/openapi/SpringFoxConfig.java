package br.com.luciano.delivery.core.openapi;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
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
public class SpringFoxConfig implements WebMvcConfigurer {


    @Bean
    public Docket apiDocket() {
        return new Docket(DocumentationType.SWAGGER_2)
            .select()
                .apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
                .build()
            .apiInfo(apiInfo())
            .tags(new Tag("Cidades", "Gerencia as cidades"), tags());
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
                new Tag("Cozinhas", "Gerencia as cozinhas"),
                new Tag("Estados", "Gerencia os estados"),
                new Tag("Formas de pagamento", "Gerencia as formas de pagamentos"),
                new Tag("Grupos", "Gerencia os grupos"),
                new Tag("Restaurantes", "Gerencia os restaurantes")
        };
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Delivery API")
                .description("API aberta para clientes de restaurante")
                .version("1.0.0")
                .contact(new Contact("Luciano Lima", "www.umlima.com.br", "luclimasilva23@gmail.com"))
                .build();
    }
}
