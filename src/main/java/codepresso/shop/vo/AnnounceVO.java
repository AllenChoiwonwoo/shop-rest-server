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
public class AnnounceVO {
	private int id;
	private int order;
	private String content;
	private Date createdAt;

}
