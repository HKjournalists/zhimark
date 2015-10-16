package com.spring.mvc.utils;


import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by liluoqi on 15/8/29.
 */
public class CookieUtils {

    public static void addCookis(HttpServletRequest request, HttpServletResponse response, String name, String value,
                                 int maxAge) {
        Cookie cookie = new Cookie(name, value);
        cookie.setMaxAge(maxAge);
        cookie.setPath("/");
        response.addCookie(cookie);
    }

    public static String getValueByName(HttpServletRequest request, HttpServletResponse response, String name) {
        Cookie cookie = getCookieByName(request, response, name);
        return cookie != null ? cookie.getValue() : StringUtils.emptyString();
    }

    public static Cookie getCookieByName(HttpServletRequest request, HttpServletResponse response, String name) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null && cookies.length > 0) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(name)) {
                    return cookie;
                }
            }
        }
        return null;
    }

    public static void deleteCookieByName(HttpServletRequest request, HttpServletResponse response, String name) {
        Cookie cookie = getCookieByName(request, response, name);
        if (cookie != null) {
            cookie.setMaxAge(0);
        }
        response.addCookie(cookie);
    }

    public static void deleteCookies(HttpServletRequest request, HttpServletResponse response) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null && cookies.length > 0) {
            for (Cookie cookie : cookies) {
                cookie.setMaxAge(0);
                response.addCookie(cookie);
            }
        }
    }
}
