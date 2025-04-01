package com.example.__backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Configuration class to enable Cross-Origin Resource Sharing (CORS) for the frontend application.
 * This configuration allows the frontend to communicate with the backend hosted on a different port.
 */
@Configuration // Indicates this is a configuration class for Spring
public class FrontendConfiguration {
    @Bean // Marks this method as a Spring Bean to be managed by the Spring container
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/")  // Apply CORS settings to all paths
                        .allowedOrigins("http://localhost:3000") // Allow frontend on localhost:3000
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Allow specific HTTP methods
                        .allowedHeaders("*"); // Allow all headers in the request
            }
    };
}

}
