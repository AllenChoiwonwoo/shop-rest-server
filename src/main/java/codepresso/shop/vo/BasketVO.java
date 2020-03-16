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
public class BasketVO {
	private int id;
	private int prodId;
	private int userId;
	private int prodVolume;
	private Date createdAt;
}
