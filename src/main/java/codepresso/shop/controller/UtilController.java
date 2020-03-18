package codepresso.shop.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import codepresso.shop.service.UtilService;
import codepresso.shop.vo.returnvo.ResultVO;

@RestController
public class UtilController {
	
	public static Logger logger = LoggerFactory.getLogger(UtilController.class);
	@Autowired
	UtilService utilService;
	
	//6) 공지 가져오기
	@GetMapping("/announce")
	public ResultVO getAnnounce() throws Exception{
			logger.info("getAnnounce, 호출됨");
			return utilService.getAllAnnouces();
	}
}
