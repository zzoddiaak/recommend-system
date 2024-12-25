package recommendsys.recommend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // Разрешаем все запросы с указанного источника
        registry.addMapping("/**") // Разрешаем для всех путей API
                .allowedOrigins("http://localhost:63342") // Разрешаем доступ с фронтенд-источника
                .allowedMethods("GET", "POST", "PUT", "DELETE") // Разрешаем нужные HTTP-методы
                .allowedHeaders("*") // Разрешаем любые заголовки
                .allowCredentials(true); // Разрешаем передавать учетные данные (если нужно)
    }
}
