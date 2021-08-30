package com.github.rebue.third.party.demo.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

public class CookieUtil {

    public static Optional<String> getFirstCookieValue(HttpServletRequest request, String cookieName)
    {
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if (cookieName.equals(cookie.getName())) {
                return Optional.of(cookie.getValue());
            }
        }
        return Optional.empty();
    }

}
