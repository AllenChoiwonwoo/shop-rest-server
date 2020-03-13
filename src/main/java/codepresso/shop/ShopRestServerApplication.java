package codepresso.shop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import codepresso.shop.vo.returnvo.ResultVO;

@SpringBootApplication
public class ShopRestServerApplication {
	
	public static ResultVO errorResultVO = new ResultVO();

	public static void main(String[] args) {
		errorResultVO.setCode(500);
		errorResultVO.setMessage("fail");
		errorResultVO.setData(null);
		SpringApplication.run(ShopRestServerApplication.class, args);
	}
	public static ResultVO returnSuccess(Object obj) {
		ResultVO rvo = new ResultVO();
		rvo.setCode(200);
		rvo.setMessage("Success");
		rvo.setData(obj);
		return rvo;
	}
	public static ResultVO returnError(Object obj) {
		ResultVO rvo = new ResultVO();
		rvo.setCode(200);
		rvo.setMessage("Success");
		rvo.setData(obj);
		return rvo;
	}

}
