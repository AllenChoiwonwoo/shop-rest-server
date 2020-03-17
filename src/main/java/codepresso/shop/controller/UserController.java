package codepresso.shop.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import codepresso.shop.dao.UserDAO;
import codepresso.shop.service.UserService;
import codepresso.shop.vo.UserVO;
import codepresso.shop.vo.inputvo.ProdNumbAndTokenVO;
import codepresso.shop.vo.returnvo.ResultVO;

@RestController
public class UserController {
	
	@Autowired
	UserService userService;
	@Autowired
	UserDAO userDao;
	@Autowired
	ResultVO resultVo;
	
	public static Logger logger = LoggerFactory.getLogger(UserController.class);
	
	// 2) 회원가입 api
	@PostMapping("/user/join")
	public ResultVO joinUser(
			@RequestBody UserVO uservo) throws Exception {
		logger.info("joinUser, 호출됨");
		return userService.addUser(uservo);
	}

	//3) 아이디 중복 확인api
	@GetMapping("/user/emailConfirm")
	public ResultVO emailConfirm(
			@RequestParam String email) throws Exception{
			logger.info("emailConfirm, 호출됨");
			ResultVO resultvo = userService.checkEmail(email);
		return resultvo;
	}
	
	//4) 로그인 api ..
	@PostMapping("/user/login")
	public ResultVO login(
			@RequestBody UserVO uservo,HttpServletRequest request, HttpServletResponse response
			) throws Exception{
		logger.info("login, 호출됨");
		return userService.login(uservo);
	}

}
