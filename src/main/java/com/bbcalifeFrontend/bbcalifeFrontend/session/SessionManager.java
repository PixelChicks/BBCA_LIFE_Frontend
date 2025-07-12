package com.bbcalifeFrontend.bbcalifeFrontend.session;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Component;

@Component
public class SessionManager {
    public void setSessionToken(HttpServletRequest request, String sessionToken, String role) {
        String token = "Bearer " + sessionToken;

        HttpSession session = request.getSession(true);
        session.setAttribute("sessionToken", token);
        session.setAttribute("sessionRole", role);
    }

    public void invalidateSession(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
    }
}
