package com.bbcalifeFrontend.bbcalifeFrontend.config;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class GlobalModelAttributes {

    @ModelAttribute("currentUrl")
    public String populateCurrentUrl(HttpServletRequest request) {
        return request.getRequestURI(); // Get the current URL
    }
}