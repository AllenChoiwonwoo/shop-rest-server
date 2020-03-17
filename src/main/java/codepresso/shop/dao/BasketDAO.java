package codepresso.shop.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import codepresso.shop.vo.BasketVO;
import codepresso.shop.vo.returnvo.BasketItemReturnVO;

@Repository
public class BasketDAO {
	String mapper = "mybatis.mapper.basket.";
	@Autowired
	SqlSession sqlsession;

	public int insertOneProdToBasket(BasketVO basketvo) {
		return sqlsession.insert(mapper+"insertOneProdToBasket" , basketvo);
		
	}

	public List<BasketItemReturnVO> selectAllProdFromUserBasket(int userid) {
		return sqlsession.selectList(mapper+"selectAllProdFromUserBasket", userid);
		
	}

	public int deleteOneProdFromBasket(BasketVO basketvo) {
		return sqlsession.delete(mapper+"deleteOneProdFromBasket", basketvo);
	}
	
	
	

}
