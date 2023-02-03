package de.schosins.AutoConfigureMockMvcError;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpServletRequest;

@SpringBootApplication
public class AutoConfigureMockMvcErrorApplication {

    public static void main(String[] args) {
        SpringApplication.run(AutoConfigureMockMvcErrorApplication.class, args);
    }

}

@Controller
class TestController {

    @GetMapping({ "", "/", "index" })
    public String handle(HttpServletRequest request, Model model) {
        model.addAttribute("requestUri", request.getRequestURI());
        model.addAttribute("requestUrl", request.getRequestURL());
        return "test";
    }

}

@Profile("errorhandling")
@ControllerAdvice(assignableTypes = TestController.class)
class ErrorTestControllerAdvice {

    @ExceptionHandler(Exception.class)
    public String handleException(HttpServletRequest request, Model model) {
        model.addAttribute("requestUri", request.getRequestURI());
        model.addAttribute("requestUrl", request.getRequestURL());
        return "error";
    }

}
