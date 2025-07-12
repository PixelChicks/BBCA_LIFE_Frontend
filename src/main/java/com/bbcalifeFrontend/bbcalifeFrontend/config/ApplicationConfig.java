package com.bbcalifeFrontend.bbcalifeFrontend.config;

import com.bbcalifeFrontend.bbcalifeFrontend.session.SessionListener;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {
    @Bean
    public ServletListenerRegistrationBean<SessionListener> sessionListener() {
        ServletListenerRegistrationBean<SessionListener> listenerBean =
                new ServletListenerRegistrationBean<>();

        listenerBean.setListener(new SessionListener());
        return listenerBean;
    }
}