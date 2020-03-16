package codepresso.shop.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import codepresso.shop.dao.ProdDAO;
import codepresso.shop.service.ProdService;
import codepresso.shop.vo.inputvo.ProdNumbAndToken;
import codepresso.shop.vo.returnvo.ResultVO;

@RestController
public class ProdContoller {
	
	public static Logger logger = LoggerFactory.getLogger(ProdContoller.class);
	@Autowired
	ProdService prodservice;
	
	// 7) 상품 더보기 
	@RequestMapping(value = "/prod/{lastPordId}", method=RequestMethod.GET)
	public ResultVO getMainProdList(
			@PathVariable("lastPordId") int lastProdId,
			@RequestHeader(value="accesstoken", required=false) String accesstoken,
			HttpServletRequest request, HttpServletResponse response) {
			
		logger.info("getMainProdlist , called ,token = "+accesstoken+", lastpostid = "+lastProdId);
		ProdNumbAndToken prodnumbntoken = new ProdNumbAndToken();
		prodnumbntoken.setAccesstoken(accesstoken);
		prodnumbntoken.setLastProdId(lastProdId);
		return prodservice.getProdList(prodnumbntoken);
	}
	/*
		 * 
		 * // @GetMapping("/prod/{lastPordId}") public ResultVO getMainProdList(
		 * // @CookieValue(value="accessToken", required=false) String accesstoken
		 * // @RequestParam(value = "lastPostId", required = false) int lastPordId,
		 * // @PathVariable(value="lastProdId") String lastProdId,
		 * 
		 * @RequestHeader(value="accesstoken") String accesstoken
		 * 
		 * ) { //
		 * logger.info("getMainProdlist , called ,token = "+accesstoken+"lastpostid = "
		 * +lastProdId); // ProdNumbAndToken prodnumbntoken = new ProdNumbAndToken(); //
		 * prodnumbntoken.setAccesstoken(accesstoken); ////
		 * prodnumbntoken.setLastProdId(lastPordId); return null; }
		 */
	//8) 상품 상세페이지
	@RequestMapping(value = "/prod/detail", method=RequestMethod.GET)
	public ResultVO getProdDetail(
//			@PathVariable("prodid") int prodId,
//			@RequestHeader(value="accesstoken", required=false) String accesstoken,
			@RequestParam int prodId
			) {
		logger.info("getProdDetail, prodId = "+prodId+"prodId" );
		ProdNumbAndToken prodNumbAndToken = new ProdNumbAndToken();
//		prodNumbAndToken.setAccesstoken(accesstoken);
		prodNumbAndToken.setProdid(prodId);
		return prodservice.getProdDetail(prodNumbAndToken);
	}
	
	
	
}
