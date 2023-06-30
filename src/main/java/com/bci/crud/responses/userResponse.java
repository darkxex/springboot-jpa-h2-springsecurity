package com.bci.crud.responses;

import java.util.Date;
import java.util.Set;

import com.bci.crud.entity.userPhones;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class userResponse {
	private int id;
	private Date created = new Date();
	private Date modified = created;
	private Date last_login = created;
	private String name;
	private String email;
	private String password;
	private boolean isactive = false;
	private Set<userPhones> phones;
	private String token;
}
