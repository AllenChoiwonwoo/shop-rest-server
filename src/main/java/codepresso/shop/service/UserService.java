package codepresso.shop.service;

import java.util.Calendar;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import codepresso.shop.ShopRestServerApplication;
import codepresso.shop.dao.UserDAO;
import codepresso.shop.vo.UserVO;
import codepresso.shop.vo.returnvo.ResultVO;

@Repository
public class UserService {
	
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
	
	

}
