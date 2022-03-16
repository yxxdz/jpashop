package jpabook.jpashop.controller;

import jpabook.jpashop.domain.Role;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter @Setter
public class MemberForm {

    @NotEmpty(message = "필수 입력 항목입니다.")
    private String realId;

    @NotEmpty(message = "필수 입력 항목입니다.")
    private String name;

    @NotEmpty(message = "필수 입력 항목입니다.")
    private String password;

    @NotEmpty(message = "필수 입력 항목입니다.")
    private String phone;

    @NotEmpty(message = "필수 입력 항목입니다.")
    private String city;

    @NotEmpty(message = "필수 입력 항목입니다.")
    private String street;

    @NotEmpty(message = "필수 입력 항목입니다.")
    private String zipcode;

    private Role role;
}
