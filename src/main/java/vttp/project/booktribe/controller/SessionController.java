package vttp.project.booktribe.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import vttp.project.booktribe.model.User;
import vttp.project.booktribe.service.UserService;

@Controller
public class SessionController {

	@Autowired
	private UserService userSvc;



	// @GetMapping(path = "/{id}")
	// public String getHomePage(Model model, @PathVariable String id, HttpSession session) {
	// 	List<User> userDetails = userSvc.userProfile(id);
	// 	// List<String> userInfo = (List<String>) session.getAttribute("USER_SESSSION");

	// 	// if (userInfo == null) {
	// 	// 	userInfo = new ArrayList<>();
	// 	// }

	// 	model.addAttribute("userDetails", userDetails);
	// 	// model.addAttribute("sessionId", session.getId());
    //     // System.out.println(userDetails);
    //     return "/home";
	// }

}
