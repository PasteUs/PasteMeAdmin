package cn.pasteme.admin.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 将表单的 action 放在这里
 *
 * @author Lucien
 * @version 1.0.0
 */
@Slf4j
@Controller
@RequestMapping("/form")
public class FormController {

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
