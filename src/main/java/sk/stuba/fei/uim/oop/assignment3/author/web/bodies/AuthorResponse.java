package sk.stuba.fei.uim.oop.assignment3.author.web.bodies;

import lombok.Getter;
import sk.stuba.fei.uim.oop.assignment3.author.data.Author;
import sk.stuba.fei.uim.oop.assignment3.book.data.Book;

import java.util.ArrayList;
import java.util.List;

@Getter
public class AuthorResponse {
    private final long id;
    private final String name;
    private final String surname;
    private final List<Long> books;

    public AuthorResponse(Author a) {
        this.id = a.getId();
        this.name = a.getName();
        this.surname = a.getSurname();
        this.books = new ArrayList<>();
        if (a.getBooks() != null) {
            for (Book book : a.getBooks()) {
                this.books.add(book.getId());
            }
        }
    }
}
