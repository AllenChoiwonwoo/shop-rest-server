package codepresso.shop.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import codepresso.shop.ShopRestServerApplication;
import codepresso.shop.dao.UtilDAO;
import codepresso.shop.vo.AnnounceVO;
import codepresso.shop.vo.returnvo.ResultVO;

@Service
public class UtilService {
	
	public static Logger logger = LoggerFactory.getLogger(UtilService.class);
	@Autowired
	UtilDAO utilDao;

	public ResultVO getAllAnnouces() {
		List<AnnounceVO> resultList = utilDao.selectAnnounces();
		return ShopRestServerApplication.returnSuccess(resultList);
	}

}
