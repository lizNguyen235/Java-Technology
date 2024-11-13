package org.example.ex02.Controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class CustomErrorController implements ErrorController {

    @RequestMapping("/error")
    @ResponseBody
    public String handleError(HttpServletRequest request) {
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        if (statusCode != null) {
            if (statusCode == HttpStatus.NOT_FOUND.value()) {
                return "Page not found. Please check the URL.";
            } else if (statusCode == HttpStatus.METHOD_NOT_ALLOWED.value()) {
                return "HTTP method not supported for this URL.";
            }
            // Add other specific status codes as needed
        }
        return "An unexpected error occurred.";
    }
}
