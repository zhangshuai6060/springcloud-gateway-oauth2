package com.example.springcloudalibabaoatuhgatewat.entity;

import lombok.Data;

@Data
public class UserJwtVo {
	private Long id;
	private String name;
	private String permissions;

	/**
	 * token 的过期时间
	 */
	private Long exp;
}
