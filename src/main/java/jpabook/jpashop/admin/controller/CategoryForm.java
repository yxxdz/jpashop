package jpabook.jpashop.admin.controller;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CategoryForm {

    private Long id;

    private String name;
    private String koName;
    private Long parentId;
}
