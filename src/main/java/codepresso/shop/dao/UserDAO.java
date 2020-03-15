package codepresso.shop.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import codepresso.shop.vo.UserVO;

@Repository
public class UserDAO {
	
	@Autowired
	SqlSession sqlsession;
	
	private String mapper = "mybatis.mapper.user.";

	public int insertUser(UserVO uservo) {
		// TODO Auto-generated method stub
		return sqlsession.insert(mapper+"insertUser", uservo);
	}

	public boolean checkEmail(String email) {
		// TODO Auto-generated method stub
		String result = sqlsession.selectOne(mapper+"selectOneEmail", email);
		if(result==null) {
			System.out.println("중복없음이다.");
			return true;
		}else {
			System.out.println("!중복 !! 이다. 쓰지마ㅏㄹ");
			return false;
		}
		
	}

	public UserVO login(UserVO uservo) {
		// TODO Auto-generated method stub
		UserVO resultUserVO = sqlsession.selectOne(mapper+"checkEmailAndPassword", uservo);
		return resultUserVO;
	}

	public int insertTokenToUser(UserVO uservo) {
		// TODO Auto-generated method stub
		return sqlsession.update(mapper+"insertTokenToUser", uservo);
		
	}
	


}
