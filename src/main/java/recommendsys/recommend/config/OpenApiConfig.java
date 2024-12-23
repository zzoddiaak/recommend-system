package recommendsys.recommend.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Library API")
                        .version("1.0")
                        .description("API для работы с библиотекой. Здесь вы найдете все доступные рекомендации для товаров и пользователей.")
                        .termsOfService("https://www.example.com/terms")
                        .contact(new Contact()
                                .name("Поддержка API")
                                .url("https://www.example.com/contact")
                                .email("support@example.com"))
                        .license(new License()
                                .name("MIT License")
                                .url("https://opensource.org/licenses/MIT")));
    }
}
