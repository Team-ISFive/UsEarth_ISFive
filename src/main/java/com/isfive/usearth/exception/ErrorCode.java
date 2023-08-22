package com.isfive.usearth.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;

@Getter
public enum ErrorCode {
	MEMBER_NOT_FOUND(HttpStatus.NOT_FOUND, "M_001", "사용자를 찾을 수 없습니다."),
	BOARD_NOT_FOUND(HttpStatus.NOT_FOUND, "B_001", "게시판을 찾을 수 없습니다."),
	POST_NOT_FOUND(HttpStatus.NOT_FOUND, "P_001", "게시글을 찾을 수 없습니다."),

	POST_WRITER_NOT_ALLOW(HttpStatus.BAD_REQUEST, "PW_001", "작성자는 해당 기능을 사용할 수 없습니다."),

	REFRESH_TOKEN_INVALID(HttpStatus.BAD_REQUEST, "RF_001", "유효하지 않은 토큰입니다.");

	ErrorCode(HttpStatus httpStatus, String code, String message) {
		this.httpStatus = httpStatus;
		this.code = code;
		this.message = message;
	}

	private final HttpStatus httpStatus;
	private final String code;
	private final String message;
}