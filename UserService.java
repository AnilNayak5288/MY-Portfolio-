package in.anil.service;

import in.anil.binding.LoginForm;
import in.anil.binding.RegisterForm;
import in.anil.entity.User;

public interface UserService {

		public String saveUser(User c);

		public User loginCheck(String email, String pwd);

		public boolean recoverPwd(String email);

//		public DashboardResponse getDashboardInfo(Integer cid);

	}
