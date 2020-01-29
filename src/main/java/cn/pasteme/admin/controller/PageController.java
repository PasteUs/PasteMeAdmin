package cn.pasteme.admin.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 这里只负责页面的展示
 *
 * @author Lucien
 * @version 1.1.0
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

}
