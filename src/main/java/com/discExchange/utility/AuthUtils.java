package com.discExchange.utility;

import com.discExchange.vm.UserVm;

import javax.servlet.ServletRequest;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.Connection;

/**
 * Created by adMin on 09.08.2017.
 */
public class AuthUtils {


    public static final String USER_NAME = "user_name";
    public static final String AUTH_USER = "auth_user";



    /** Store user info in Session
     */
    public static void storeAuthUserInSession(HttpSession session, UserVm userVm) {
        session.setAttribute(AUTH_USER, userVm);
    }


    /**
     *  Get the user information stored in the session.
     */
    public static UserVm getAuthUser(HttpSession session) {
        UserVm userVm = (UserVm) session.getAttribute(AUTH_USER);
        return userVm;
    }


    /** Store info in Cookie
     */
    public static void storeUserNameCookie(HttpServletResponse response, UserVm user) {

        Cookie cookieUserName = new Cookie(USER_NAME, user.getName());
        int oneDayInSeconds = 24 * 60 * 60;
        cookieUserName.setMaxAge(oneDayInSeconds);
        response.addCookie(cookieUserName);
    }

    public static String getUserNameInCookie(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (USER_NAME.equals(cookie.getName())) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }


    /** Delete cookie.*/
    public static void deleteUserCookie(HttpServletResponse response) {
        Cookie cookieUserName = new Cookie(USER_NAME, null);
        // 0 seconds (Expires immediately)
        cookieUserName.setMaxAge(0);
        response.addCookie(cookieUserName);
    }

}


