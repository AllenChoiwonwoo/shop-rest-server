package codepresso.shop.service;

import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import codepresso.shop.ShopRestServerApplication;
import codepresso.shop.controller.UserController;
import codepresso.shop.dao.UserDAO;
import codepresso.shop.vo.UserVO;
import codepresso.shop.vo.returnvo.ResultVO;
import codepresso.shop.vo.returnvo.TokenVO;

@Repository
public class UserService {
	public static Logger logger = LoggerFactory.getLogger(UserService.class);
	@Autowired
	UserDAO userDao;
	@Autowired
	ResultVO resultVo;

	public ResultVO addUser(UserVO uservo) {
		// TODO Auto-generated method stub
		ResultVO checkedResult = checkJoinable(uservo);
		if(checkedResult.getCode()!=200) {
			return checkedResult;
		}else {
			if(1 != userDao.insertUser(uservo)) {
				ShopRestServerApplication.errorResultVO.setData("db서버에서의 에러");
				checkedResult = ShopRestServerApplication.errorResultVO;
			}
		}
		
		return checkedResult;
	}

	public ResultVO checkEmail(String email) {
		// TODO Auto-generated method stub
		boolean isAvailableEmail = userDao.checkEmail(email);
		if(isAvailableEmail) {
			resultVo = ShopRestServerApplication.returnSuccess(email);
			return resultVo;
		}else {
			ShopRestServerApplication.errorResultVO.setData("중복된 이메일 입니다.");
			return ShopRestServerApplication.errorResultVO;
		}
	}
	
	public ResultVO checkJoinable(UserVO uservo) {

		if(!uservo.getPassword().equals(uservo.getPasswordConfirm())) {
			//비밀번호 불일치 - 회원가입 불가
			ShopRestServerApplication.errorResultVO.setData("비밀번호가 일치하지 않습니다.확인해주세요");
			return ShopRestServerApplication.errorResultVO;
		}
		
		// 오늘 날짜를 구해서 7살 이하를 구분해내기 위한 코드
		java.util.Calendar cal = Calendar.getInstance();
		java.util.Date utilDate = new java.util.Date(); // your util date
		cal.setTime(utilDate);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		cal.add(Calendar.YEAR, -7);
		java.sql.Date sqlDate = new java.sql.Date(cal.getTime().getTime()); // your sql date
		System.out.println("sqlDate:" + sqlDate);
		
		int compare = uservo.getBirth().compareTo(sqlDate);
		if(compare < 0 ) {
			System.out.println(" 7세 이상");
		}else {
			System.out.println(" 7세 이하"); //- 회원가입 불가
			ShopRestServerApplication.errorResultVO.setData( "7세 이상부터 가입할 수 있습니다.");
			return ShopRestServerApplication.errorResultVO;
		}
		if(!userDao.checkEmail(uservo.getEmail())) {
			ShopRestServerApplication.errorResultVO.setData("Email 이 중복되었습니다. 다른 이메일 주소를 적어주세요");
			return ShopRestServerApplication.errorResultVO;
		}
		
		ResultVO joinResult = ShopRestServerApplication.returnSuccess("ok");
		return joinResult;// 회원가입 가능
		
		
	}

	public ResultVO login(UserVO uservo) throws Exception{
		// TODO Auto-generated method stub
		// 로그인후, 토큰 발행하는 메서드 실행후 토큰 받아서 db에 넣는다 ㅡ, 정상입력되면 토큰값을 넣어서 return 한다.
		UserVO resultUserVO;
		int userid;
		try { // 아이디 비번 체크
			resultUserVO = userDao.login(uservo);
			logger.info("login , userid = "+resultUserVO.getId());
		} catch (Exception e) { // 아이디 비번 불일치
			logger.info("login, 로그인 실페 - email & password 불일치 ");
			e.printStackTrace();
			return ShopRestServerApplication.returnError("아이디와 비밀번호 불일치");
		}
		if(resultUserVO.getToken() != null) {// 이미 토큰이 있을 경우 있는 토큰 반환
			logger.info("login, 이미 토큰이 있을 경우 있는 토큰 반환");
			return ShopRestServerApplication.returnSuccess(new TokenVO(resultUserVO.getToken()));
		}
		// 토큰 없음, 발행 
		logger.info("login, 토큰 없음");
		String newToken = makeToken(resultUserVO.getId());
		logger.info("login, newToken = "+newToken);
		uservo.setToken(newToken);
		int insertResult = userDao.insertTokenToUser(uservo);
		if(insertResult == 1) {// 토큰 저장 성공, 반환
			logger.info("login, 발행 후 저장 후 토큰 반환 ");
			return ShopRestServerApplication.returnSuccess(new TokenVO(newToken));
		}else { // 토큰 저장 실패 
			logger.info("login, 토큰 저장 실패 ");
			return ShopRestServerApplication.returnError("로그인 실패 - 토큰 저장 실패");
		}
	}
	
	public static String makeToken(int id){
		int length = 8;
	  StringBuffer buffer = new StringBuffer();
	  buffer.append(id+"");
	  Random random = new Random();
	  String chars[] =
	    "a,b,c,d,e,f,g,h,i,j,k,l,m,n,o,p,q,r,s,t,u,v,w,x,y,z".split(",");
	 
	  for (int i=0 ; i<length ; i++)
	  {
	    buffer.append(chars[random.nextInt(chars.length)]);
	  }
	  return buffer.toString();
	}
	
	

}
