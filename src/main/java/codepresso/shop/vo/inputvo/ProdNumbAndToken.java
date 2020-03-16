package codepresso.shop.vo.inputvo;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data
public class ProdNumbAndToken {
	private String accesstoken;
	private int lastProdId;
	private int userid;
	private int prodid;
}
