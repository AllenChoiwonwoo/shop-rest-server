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
public class ProdDetailVO {
	private int prodDetail;
	private int prodId;
	private int order;
	private String img;
	private String desc;
	private Date createdAt;
}
