package codepresso.shop.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import codepresso.shop.ShopRestServerApplication;
import codepresso.shop.dao.ProdDAO;
import codepresso.shop.dao.UserDAO;
import codepresso.shop.vo.ProdDetailVO;
import codepresso.shop.vo.ProdVO;
import codepresso.shop.vo.inputvo.ProdNumbAndToken;
import codepresso.shop.vo.returnvo.ResultVO;

@Service
public class ProdService {
	public static Logger logger = LoggerFactory.getLogger(ProdDAO.class);
	
	@Autowired
	UserDAO userdao;
	@Autowired
	ProdDAO proddao;
	@Autowired
	ProdDetailVO prodDetailVO;
	public ResultVO getProdList(ProdNumbAndToken prodnumbntoken) {
		// TODO Auto-generated method stub
		// lastprodid 부터 6개의 상품을 정보를 받아서 보내준다.
		//  장바구니에 담은 상품이면 담기 가 아닌 보기로 화면에 보여질 수 있도록 해야한다.
		List<ProdVO> prodlist =new ArrayList<ProdVO>();
		if(prodnumbntoken.getAccesstoken() != null) {
			int userid = userdao.selectOneUserByToken(prodnumbntoken.getAccesstoken());
			prodnumbntoken.setUserid(userid);
			prodlist= proddao.getProdListWithUserid(prodnumbntoken);
		}else {
			prodlist = proddao.getProdList(prodnumbntoken.getLastProdId());
			prodnumbntoken.setUserid(0);
		}
		// 유저토큰과 함께 돌려보낸다. ( 그럼 프로트에서 유저 토큰과 함게오면 로그인 중인거로 인식 )
		HashMap hashMap =new HashMap();
		hashMap.put("userid", prodnumbntoken.getUserid());
		hashMap.put("data", prodlist);
		return ShopRestServerApplication.returnSuccess(prodlist);
	}
	
	

}
