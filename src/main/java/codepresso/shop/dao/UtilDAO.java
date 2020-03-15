package codepresso.shop.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import codepresso.shop.vo.AnnounceVO;

@Repository
public class UtilDAO {
	
	String mapper = "mybatis.mapper.util.";
	@Autowired
	SqlSession sqlsession;

	public List<AnnounceVO> selectAnnounces() {
		// TODO Auto-generated method stub
		List<AnnounceVO> volist = sqlsession.selectList(mapper+"selectAnnounces");
		return volist;
	}

}
