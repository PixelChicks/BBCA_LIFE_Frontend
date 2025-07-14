package com.bbcalifeFrontend.bbcalifeFrontend.controller;

import com.bbcalifeFrontend.bbcalifeFrontend.client.AuthClient;
import com.bbcalifeFrontend.bbcalifeFrontend.model.auth.AuthenticationRequest;
import com.bbcalifeFrontend.bbcalifeFrontend.model.auth.AuthenticationResponse;
import com.bbcalifeFrontend.bbcalifeFrontend.model.auth.RegisterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthFrontendController {

    private final AuthClient authClient;

    @GetMapping("/register")
    public String showRegisterPage(Model model) {
        model.addAttribute("registerRequest", new RegisterRequest());
        return "register";
    }

    @GetMapping("/login")
    public String showLoginPage() {
        return "login";
    }

    @PostMapping("/login")
    public String doLogin(@RequestParam String username, @RequestParam String password, Model model) {
        try {
            AuthenticationRequest authRequest = new AuthenticationRequest(username, password);
            AuthenticationResponse response = authClient.authenticate(authRequest);

            model.addAttribute("token", response.getAccessToken());

            return "redirect:/home";
        } catch (Exception e) {
            model.addAttribute("error", "Invalid username or password");
            return "login";
        }
    }

    @PostMapping("/register")
    public String doRegister(@ModelAttribute RegisterRequest registerRequest, Model model) {
        try {
            authClient.register(registerRequest);
            return "redirect:/auth/login?registered";
        } catch (Exception e) {
            model.addAttribute("error", "Registration failed: " + e.getMessage());
            return "register";
        }
    }
}
