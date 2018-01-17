package com.glima.moneywise.resource;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Guilherme on 17/01/2018.
 *
 * The application does not store the token, therefore for logout
 * I remove the refresh Token cookie and the client application must remove the access token
 */
@RestController
@RequestMapping("/tokens")
public class TokenResource {


    @DeleteMapping("/revoke")
    public void revoke(HttpServletRequest req, HttpServletResponse resp){
        Cookie cookie = new Cookie("refreshToken", null);
        cookie.setHttpOnly(true);
        cookie.setPath(req.getContextPath() + "/oauth/token");
        cookie.setSecure(false); // TODO: change for production
        cookie.setMaxAge(0);

        resp.addCookie(cookie);
        resp.setStatus(HttpStatus.NO_CONTENT.value());

    }
}
