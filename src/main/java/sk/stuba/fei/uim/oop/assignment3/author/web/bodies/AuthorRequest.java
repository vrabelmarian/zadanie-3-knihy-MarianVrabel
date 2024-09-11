package sk.stuba.fei.uim.oop.assignment3.author.web.bodies;

import lombok.Getter;
import lombok.Setter;
import sk.stuba.fei.uim.oop.assignment3.book.data.Book;
import java.util.List;


@Getter
@Setter
public class AuthorRequest {
    private String name;
    private String surname;
    private List<Book> books;
}
