package in.anil.service;

import java.util.Map;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.anil.binding.LoginForm;
import in.anil.binding.RegisterForm;
import in.anil.entity.User;
import in.anil.repo.UserRepo;
import in.anil.util.EmailUtils;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private EmailUtils emailUtils;
	
	@Override
	public String saveUser(User c) {
		
		User obj = userRepo.findByEmail(c.getEmail());
		
		if(obj != null) {
			return "Duplicate Email";
		}
		
		User savedObj = userRepo.save(c);
		if(savedObj.getUserId() != null) {
			return "Registration Success";
		}
		return "Registration Failed";
	}

	@Override
	public User loginCheck(String email, String pwd) {
		
		return userRepo.findByEmailAndPwd(email, pwd);
	}

	@Override
	public boolean recoverPwd(String email) {
     
		 User c = userRepo.findByEmail(email);
		
		 if(c == null) {
			 return false; 
		 }
		 String subject = "Recover Password Anil Profile";
		 String body="<h1>Your Password : " + c.getPwd()+"</h1>";
		 
		 
		 return emailUtils.sendEmail(subject, body, email);
		
	}
}