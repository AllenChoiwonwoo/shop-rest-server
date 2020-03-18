package codepresso.shop.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import codepresso.shop.ShopRestServerApplication;
import codepresso.shop.service.ProdService;
import codepresso.shop.vo.inputvo.ProdNumbAndTokenVO;
import codepresso.shop.vo.returnvo.ResultVO;

@RestController
public class ProdContoller {
	
	public static Logger logger = LoggerFactory.getLogger(ProdContoller.class);
	@Autowired
	ProdService prodservice;
	
	// 7) 상품 더보기 
	@RequestMapping(value = {"/product/list/{lastPordId}"}, method=RequestMethod.GET)
	public ResultVO getProdListForMain(
			@PathVariable("lastPordId") int lastProdId,
			@RequestHeader(value="accesstoken", required=false) String accesstoken,
			HttpServletRequest request, HttpServletResponse response) {
			
		logger.info("getProdListForMain , called ,token = "+accesstoken+", lastpostid = "+lastProdId);
		ProdNumbAndTokenVO prodnumbntoken = new ProdNumbAndTokenVO();
		prodnumbntoken.setAccesstoken(accesstoken);
		prodnumbntoken.setLastProdId(lastProdId);
		return prodservice.getProdList(prodnumbntoken);
	}
	// 8) 상품 상세페이지 - 메인정보
	@RequestMapping(value="/product/detail/{prodId}/main", method=RequestMethod.GET)
	public ResultVO getMainProdDetailInfo(
			@PathVariable("prodId") int prodId,
			@RequestHeader(value="accesstoken", required=false) String accesstoken
			) {
		logger.info("getMainProdDetailInfo , prodId ="+prodId);
		ProdNumbAndTokenVO prodnumbntoken = new ProdNumbAndTokenVO();
		prodnumbntoken.setAccesstoken(accesstoken);
		prodnumbntoken.setProdid(prodId);
		return prodservice.getMainProdDetailInfo(prodnumbntoken);
//		return null;
	}

	//9) 상품 상세페이지 - 상세정보
	@RequestMapping(value = "/product/detail/{prodId}/info", method=RequestMethod.GET)
	public ResultVO getProdDetail(
			@RequestParam int prodId
			) {
		logger.info("getProdDetail, prodId = "+prodId+"prodId" );
		if(0 == prodId) {
			ShopRestServerApplication.returnError("prodId : 0 은 존제하지 않는 상품입니다.");
		}
		ProdNumbAndTokenVO prodNumbAndTokenVO = new ProdNumbAndTokenVO();
		prodNumbAndTokenVO.setProdid(prodId);
		return prodservice.getProdDetail(prodNumbAndTokenVO);
	}
	
	
	
}
