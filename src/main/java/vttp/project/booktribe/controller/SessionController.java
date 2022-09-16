package vttp.project.booktribe.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SessionController {

    @GetMapping(path="/")
    public String getLoginPage() {
        return "login";
    }

    @GetMapping(path="/register")
    public String getRegisterPage() { 
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


	@GetMapping("/home")
	public String getHomePage(Model model, HttpSession session) {
		@SuppressWarnings("unchecked")
		List<String> userInfo = (List<String>) session.getAttribute("USER_SESSSION");

		if (userInfo == null) {
			userInfo = new ArrayList<>();
		}

		model.addAttribute("userInfo", userInfo);
		model.addAttribute("sessionId", session.getId());
        System.out.println(userInfo);

		return "home";
	}

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
		@SuppressWarnings("unchecked")
		List<String> msgs = (List<String>) request.getSession().getAttribute("USER_SESSSION");
		if (msgs == null) {
			msgs = new ArrayList<>();
			request.getSession().setAttribute("USER_SESSSION", msgs);
		}
		msgs.add(msg);
		request.getSession().setAttribute("USER_SESSSION", msgs);
		return "redirect:/home";
	}

	@PostMapping("/logout")
	public String logout(HttpServletRequest request) {
		request.getSession().invalidate();
		System.out.println("User has been logged out");
		return "redirect:/";
	}

}
