package cn.pasteme.admin.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 页面请求
 *
 * @author Lucien
 * @version 1.0.0
 */
@Slf4j
@Controller
@RequestMapping(path = "/")
public class PageController {

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public String index() {
        return "index";
    }

    @RequestMapping(path = "/login", method = RequestMethod.GET)
    public String login() {
        return "login";
    }

    @RequestMapping(path = "/login", method = RequestMethod.POST)
    public String form(
            @RequestParam(name = "username") String username,
            @RequestParam(name = "password") String password) {
        if ("username".equals(username) && "password".equals(password)) {
            return "redirect:/";
        }
        return "login";
    }
}
