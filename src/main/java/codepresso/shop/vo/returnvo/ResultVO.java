package codepresso.shop.vo.returnvo;

import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Component
public class ResultVO {
	private int code;
	private String message;
	private Object data;

}
