package codepresso.shop.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import codepresso.shop.controller.ProdContoller;
import codepresso.shop.vo.ProdDetailVO;
import codepresso.shop.vo.ProdVO;
import codepresso.shop.vo.inputvo.ProdNumbAndToken;

@Repository
public class ProdDAO {
	
	String mapper = "mybatis.mapper.product.";
	public static Logger logger = LoggerFactory.getLogger(ProdDAO.class);
	@Autowired
	ProdDetailVO prodDetailVO;
	@Autowired
	SqlSession sqlsession;
	@Autowired
	ProdVO prodVO;
	public List<ProdVO> getProdListWithUserid(ProdNumbAndToken prodnumbntoken) {
		// TODO Auto-generated method stub
		
		return sqlsession.selectList(mapper+"selectProdListWithBasketDelimiter", prodnumbntoken);
		
//		return null;
	}
	public List<ProdVO> getProdList(int lastProdId) {
		// TODO Auto-generated method stub
		return sqlsession.selectList(mapper+"selectProdList", lastProdId);
//		return null;
	}
//	public List<Object> selectOneProdDetailWithUserid(ProdNumbAndToken prodNumbAndToken) {
//		// TODO Auto-generated method stub
//		return sqlsession.selectList(mapper+"selectOneProdDetailWithUserid", prodNumbAndToken);
//		
//	}
	public List<ProdDetailVO> selectOneProdDetail(int prodid) {
		// TODO Auto-generated method stub
		List<ProdDetailVO> detailList = sqlsession.selectList(mapper+"selectProdDetailList", prodid);
		return detailList;
	}
	
	

}
