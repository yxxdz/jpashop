package jpabook.jpashop.common.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.*;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

    /* 400 BAD_REQUEST */
    INVALID_REFRESH_TOKEN(BAD_REQUEST, "리프레시 토큰이 유효하지 않습니다"),

    /* 401 UNAUTHORIZED */
    UNAUTHORIZED_MEMBER(UNAUTHORIZED, "해당 페이지에 권한이 없는 계정입니다."),

    /* 404 NOT_FOUND */
    MEMBER_NOT_FOUND(NOT_FOUND, "해당 사용자 정보를 찾을 수 없습니다."),

    /* 409 CONFLICT */
    VALIDATE_DUPLICATE_ID(CONFLICT, "이미 존재하는 아이디입니다."),

    /* 500 INTERNAL_SERVER_ERROR */
    NEED_MORE_STOCK(INTERNAL_SERVER_ERROR, "재고 수량이 부족합니다."),

    ;

    private final HttpStatus httpStatus;
    private final String detail;
}
