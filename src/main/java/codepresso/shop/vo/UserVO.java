package codepresso.shop.vo;

import java.sql.Date;


import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Component
public class UserVO {
//	private int id;
//	private int order;
//	private String content;
//	private Date createdAt;
	
	private int id;
	private String email;
	private String username;
	private Date birth;
	private String password;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String passwordConfirm;
	private int gender;
	private String token;
	private Date createdAt;

}
