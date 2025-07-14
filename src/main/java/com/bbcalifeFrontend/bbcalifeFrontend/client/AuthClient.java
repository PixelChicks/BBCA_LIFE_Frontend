package com.bbcalifeFrontend.bbcalifeFrontend.client;


import com.bbcalifeFrontend.bbcalifeFrontend.config.FeignClientConfiguration;
import com.bbcalifeFrontend.bbcalifeFrontend.model.auth.AuthenticationRequest;
import com.bbcalifeFrontend.bbcalifeFrontend.model.auth.AuthenticationResponse;
import com.bbcalifeFrontend.bbcalifeFrontend.model.auth.RegisterRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "life-article-authClient", url = "${backend.base-url}/auth", configuration = FeignClientConfiguration.class)
public interface AuthClient {

    @PostMapping("/register")
    AuthenticationResponse register(@RequestBody RegisterRequest request);

    @PostMapping("/authenticate")
    AuthenticationResponse authenticate(@RequestBody AuthenticationRequest request);
}

