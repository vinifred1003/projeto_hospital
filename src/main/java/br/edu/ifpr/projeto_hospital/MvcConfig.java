package br.edu.ifpr.projeto_hospital;

import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MvcConfig implements WebMvcConfigurer {
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/pharmaceutical-page").setViewName("PharmaceuticalHome");
        registry.addViewController("/").setViewName("login");
        registry.addViewController("/doctor-page").setViewName("DoctorHome");
        registry.addViewController("/login").setViewName("login");
    }
}
