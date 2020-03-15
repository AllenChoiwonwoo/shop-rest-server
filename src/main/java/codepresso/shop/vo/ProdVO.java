package codepresso.shop.vo;

import java.sql.Date;

import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Component
public class ProdVO {
	private int id;
	private String name;
	private String mainImg;
	private String desc;
	private int price;
	private int salePrice;
	private Date createdAt;
	
}
