package codepresso.shop.dao;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import codepresso.shop.vo.UserVO;

@Repository
public class UserDAO {
	public static Logger logger = LoggerFactory.getLogger(UserDAO.class);

	@Autowired
	SqlSession sqlsession;
	
	private String mapper = "mybatis.mapper.user.";

	public int insertUser(UserVO uservo) {
		return sqlsession.insert(mapper+"insertUser", uservo);
	}

	public boolean checkEmail(String email) {
		String result = sqlsession.selectOne(mapper+"selectOneEmail", email);
		if(result==null) {
			System.out.println("중복없음이다.");
			logger.info("checkEmail, 이메일 중복 X");
			return true;
		}else {
			logger.info("checkEmail, 이메일 중복 O 쓰지마시오)");
			return false;
		}
	}

	public UserVO login(UserVO uservo) {
		UserVO resultUserVO = sqlsession.selectOne(mapper+"checkEmailAndPassword", uservo);
		return resultUserVO;
	}

	public int insertTokenToUser(UserVO uservo) {
		return sqlsession.update(mapper+"insertTokenToUser", uservo);
	}
	
	public int selectOneUserByToken(String token) {
		return sqlsession.selectOne(mapper+"selectOneUserByToken", token);
	}


}
