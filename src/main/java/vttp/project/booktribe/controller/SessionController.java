package vttp.project.booktribe.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import vttp.project.booktribe.model.User;
import vttp.project.booktribe.service.UserService;

@Controller
@SuppressWarnings("unchecked")
public class SessionController {

	@Autowired
	private UserService userSvc;

    @GetMapping(path="/")
    public String getLoginPage() {
		// List<User> userLogin = userSvc.userProfile(id);
	
        return "login";
    }

    @GetMapping(path="/register")
    public String getRegisterPage(Model model) { 
		User user = new User();
		model.addAttribute("user", user);
		// System.out.println(user);
        return "register";
    }

    @GetMapping(path="/goals")
    public String getGoalsPage() { 
        return "goals";
    }

	@GetMapping(path="/tribe")
    public String getBookTribe() { 
        return "tribe";
    }


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

    @PostMapping("/register")
    public String register() { 
        return "register";
    }

    @PostMapping("/")
    public String registerAccount() { 
        return "redirect:/";
    }

	@PostMapping("/home")
	public String home(@RequestParam("username") String msg, HttpServletRequest request) {
		List<String> msgs = (List<String>) request.getSession().getAttribute("USER_SESSSION");
		if (msgs == null) {
			msgs = new ArrayList<>();
			request.getSession().setAttribute("USER_SESSSION", msgs);
		}
		msgs.add(msg);
		request.getSession().setAttribute("USER_SESSSION", msgs);
		System.out.println(msgs);
		return "redirect:/home";
	}

	@PostMapping("/logout")
	public String logout(HttpServletRequest request) {
		request.getSession().invalidate();
		System.out.println("User has been logged out");
		return "redirect:/";
	}

}
