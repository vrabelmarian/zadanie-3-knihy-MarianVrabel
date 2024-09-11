package sk.stuba.fei.uim.oop.assignment3.book.web.bodies;

import lombok.Getter;

@Getter
public class BookUpdateRequest {
    private Long id;
    private String name;
    private String description;
    private Long author;
    private int pages;
}
