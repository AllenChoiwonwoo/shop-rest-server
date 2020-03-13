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
public class ProdMainInfoResultVO {
	private int id;
	private String name;
	private String desc;
	private int price;
	private int salePrice;
	private Date createdAt;

}
