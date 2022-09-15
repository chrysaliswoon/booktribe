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

    @GetMapping("/")
    public String getLoginPage() {
        return "loginPage";
    }

    @GetMapping(path="/goals")
    public String getGoalsPage() { 
        return "goalsPage";
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

		return "homePage";
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
		return "redirect:/";
	}

    // @GetMapping("/")
    // public String loginPage() {
    //     return "loginPage";
    // }

    // @PostMapping("/homePage")
    // public String login(@RequestParam("email") String user, HttpServletRequest request) {
    //     //? Get the user from request session
    //     List<String> userDetails = (List<String>) request.getSession().getAttribute("USER_SESSION");

    //     //? Check if user is present in session
    //     if (user == null) {
    //         userDetails = new ArrayList<>();

    //         //? if user is not present in session, set user details in the request session
    //         request.getSession().setAttribute("USER_SESSION", userDetails);
    //     }
    //     userDetails.add(user);
    //     request.getSession().setAttribute("USER_SESSION", userDetails);
    //     return "redirect:/homePage";
    // }
    
    // @GetMapping("/homePage")
    // public String homePage(Model model, HttpSession session) {
    //     List<String> user = (List<String>) session.getAttribute("USER_SESSION");
    //     model.addAttribute("userSession", user!=null? user:new ArrayList<>());
    //     return "homePage";
    // }

    // @PostMapping("/logout")
    // public String logout(HttpServletRequest request) {
    //     //? invalidate the session and clear the data from Redis database
    //     request.getSession().invalidate();

    //     return "redirect:/loginPage";
        
    // }

}
