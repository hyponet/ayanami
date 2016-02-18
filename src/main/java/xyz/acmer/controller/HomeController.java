package xyz.acmer.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import xyz.acmer.dao.IUserDao;
import xyz.acmer.dao.impl.UserDaoImpl;
import xyz.acmer.entity.user.User;
import xyz.acmer.service.impl.UserServiceImpl;

@Controller
@RequestMapping("/")
public class HomeController {
	@RequestMapping(method = RequestMethod.GET)
	public String printWelcome(ModelMap model) {

		User user = new UserServiceImpl().addNewUser("hypo", "hypo", "i@ihypo.net", "123");
		model.addAttribute("message", "Hello world!");
		return "hello";
	}
}