package jpabook.jpashop.admin.controller;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ItemForm {

    private Long id;

    private String name;
    private int price;
    private int stockQuantity;

    // Album
    private String artist;
    private String etc;

    //  Book
    private String author;
    private String isbn;

    // Movie
    private String director;
    private String actor;
}
