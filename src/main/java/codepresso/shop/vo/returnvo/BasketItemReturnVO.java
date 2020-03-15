package codepresso.shop.vo.returnvo;

import java.sql.Date;

import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Component
public class BasketItemReturnVO {
	private int id;
	private int prodId;
	private int userId;
	private String prodName;
	private String prodMainImg;
	private int prodPrice;
	private int prodSalePrice;
	private int prodVolume;
	private Date createdAt;
}
