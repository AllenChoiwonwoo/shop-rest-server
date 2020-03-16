package codepresso.shop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import codepresso.shop.ShopRestServerApplication;
import codepresso.shop.dao.BasketDAO;
import codepresso.shop.dao.UserDAO;
import codepresso.shop.vo.BasketVO;
import codepresso.shop.vo.returnvo.BasketItemReturnVO;
import codepresso.shop.vo.returnvo.ResultVO;

@Service
public class BasketService {
	
	@Autowired
	BasketDAO basketdao;
	@Autowired
	UserDAO userdao;

	public ResultVO addOneProdToBasket(String accesstoken, BasketVO basketvo) {
		// TODO Auto-generated method stub
		int userid = userdao.selectOneUserByToken(accesstoken);
		System.out.println( "userid = "+userid);
		basketvo.setUserId(userid);
		int result = basketdao.insertOneProdToBasket(basketvo);
		System.out.println("result = "+result);
		if(result > 0) {
			return ShopRestServerApplication.returnSuccess(basketvo.getProdId());
		}else {
			return ShopRestServerApplication.returnError(basketvo.getProdId());
		}
	}

	public ResultVO getAllFromBasket(String accesstoken) {
		// TODO Auto-generated method stub
		int userid = userdao.selectOneUserByToken(accesstoken);
		List<BasketItemReturnVO> resultList = basketdao.selectAllProdFromUserBasket(userid);
		return ShopRestServerApplication.returnSuccess(resultList);
	}

	public ResultVO deleteOneProdFromBasket(String accesstoken, BasketVO basketvo) {
		// TODO Auto-generated method stub
		int userid = userdao.selectOneUserByToken(accesstoken);
		basketvo.setUserId(userid);
		System.out.println("bstovo  "+basketvo.toString());
		if(userid != basketvo.getUserId()) {
			return ShopRestServerApplication.returnError("아이디가 일치하지 않아 장바구니 상품을 삭제할 수 없습니다.");
		}
		int result = basketdao.deleteOneProdFromBasket(basketvo);
		if(result > 0 ) {// ok
			return ShopRestServerApplication.returnSuccess(basketvo.getProdId());
		}else {
			return ShopRestServerApplication.returnError(basketvo.getProdId());
		}
	}
	

}
