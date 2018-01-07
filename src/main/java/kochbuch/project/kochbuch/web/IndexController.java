package kochbuch.project.kochbuch.web;

import kochbuch.project.kochbuch.Benutzer.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by kgrif on 18.11.2017.
 */

/*
TODO COMMENT: controller - IndexController

    This controller handles the URL - requests for the index.html site.
    If a mapping to the login.html view is requested, Spring Security is utilized to check if the user is anonymous,
    if so, the controller maps the URL request to the login.html view, else the user is redirected to the index.html view.
    Meaning a user cannot login twice.
 */
@Controller
public class IndexController
{
    @Autowired
    private final UserService userService;

    public IndexController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String index()
    {
        return "index";
    }

    @RequestMapping("/login")
    public String login() {
        org.springframework.security.core.Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth instanceof AnonymousAuthenticationToken ? "login" : "redirect:/";
    }
}
