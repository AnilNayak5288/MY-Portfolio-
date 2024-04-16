package in.anil.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import in.anil.binding.LoginForm;
import in.anil.binding.RegisterForm;
import in.anil.entity.User;
import in.anil.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class UserController {
	
	@Autowired
	private UserService userService;

	@GetMapping("/")
	public String index(Model model) {	
	model.addAttribute("user",new User());
	return "index";
		
	}
	
	@PostMapping("/login")
	public String handleLogin(User c, HttpServletRequest req, Model model) {
	
		User obj = userService.loginCheck(c.getEmail(),c.getPwd());
		
		if(obj == null) {
			model.addAttribute("errMgs", "Invalid Credentials");
			return "index";
		}
		return "dashBoard";
	}
	
	

	
	@GetMapping("/register")
	public String regPage(Model model) {
		model.addAttribute("user",new User());
		return "register";
	}
	
	@PostMapping("/register")
	public String handleRegistration(User c, Model model) {
		String mgs=userService.saveUser(c);
		model.addAttribute("mgs",mgs);
		return "register";
	}

	@GetMapping("/forgot-pwd")
	public String recoverPwdPage(Model model) {
		return "resetPwd";
	}
	@GetMapping("/recover-pwd")
	public String recoverPwd(@RequestParam String email, Model model) {
		boolean status = userService.recoverPwd(email);
		if(status) {
			model.addAttribute("smgs","Password sent to Your Email");
		}else {
			model.addAttribute("errMgs","Invalid Email");
		}
		return "resetPwd";
	}
	

}
