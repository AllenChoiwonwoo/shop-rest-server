package codepresso.shop.vo.returnvo;

import java.util.List;

import org.springframework.stereotype.Component;

import codepresso.shop.vo.ProdVO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Component
public class ProdListReturnVO {
	private int userId;
	private List<ProdVO> prodListData;

}
