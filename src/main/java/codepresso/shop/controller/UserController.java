package codepresso.shop.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import codepresso.shop.ShopRestServerApplication;
import codepresso.shop.dao.UserDAO;
import codepresso.shop.service.UserService;
import codepresso.shop.vo.UserVO;
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
	
//	@RequestMapping(value = "/user", method = RequsetMethod.POST)
	// 2) 회원가입 api
	@PostMapping("/user/join")
	public ResultVO joinUser(
			@RequestBody UserVO uservo) throws Exception {
		logger.info("joinUser, 호출됨");
//		System.out.println(uservo.getPasswordConfirm());
//		if(!(uservo.getPasswordConfirm()).equals(uservo.getPassword())){
//			logger.info("joinUser, passwordconfirm = "+uservo.getPasswordConfirm()+
//					", password = "+ uservo.getPassword());
//			ShopRestServerApplication.errorResultVO.setMessage("비밀번호가 일치하지 않습니다");
//			return ShopRestServerApplication.errorResultVO;//에러
//		}
		return userService.addUser(uservo);

	}
	/*
	 * @GetMapping("/post") public ResponseResult
	 * findPostsAndUser(@CookieValue(value = "accesstoken", required = false) String
	 * accesstoken)
	 */
	
	//3) 아이디 중복 확인api
	@GetMapping("/user/emailConfirm")
	public ResultVO emailConfirm(
			@RequestParam String email) throws Exception{
			logger.info("emailConfirm, 호출됨");
			ResultVO resultvo = userService.checkEmail(email);
		return resultvo;
		
	}
	
			
	

}
