package xyz.acmer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import xyz.acmer.service.IUserService;

/**
 * Created by hypo on 16-2-20.
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService userService;

    @RequestMapping(method = RequestMethod.GET)
    public String addUser(ModelMap model) {

        model.addAttribute("message", "Hello world!");
        return "hello";
    }
}
