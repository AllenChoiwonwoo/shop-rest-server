package codepresso.shop.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import codepresso.shop.vo.ProdDetailVO;
import codepresso.shop.vo.ProdVO;
import codepresso.shop.vo.inputvo.ProdNumbAndTokenVO;

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
	public List<ProdVO> getProdListWithUserid(ProdNumbAndTokenVO prodnumbntoken) {		
		return sqlsession.selectList(mapper+"selectProdListWithBasketDelimiter", prodnumbntoken);
	}
	public List<ProdVO> getProdList(int lastProdId) {
		return sqlsession.selectList(mapper+"selectProdList", lastProdId);
	}
	public List<ProdDetailVO> selectOneProdDetail(int prodid) {
		List<ProdDetailVO> detailList = sqlsession.selectList(mapper+"selectProdDetailList", prodid);
		return detailList;
	}
	
	

}
