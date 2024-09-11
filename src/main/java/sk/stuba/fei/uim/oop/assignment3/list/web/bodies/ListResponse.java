package sk.stuba.fei.uim.oop.assignment3.list.web.bodies;

import lombok.Getter;
import sk.stuba.fei.uim.oop.assignment3.book.data.Book;
import sk.stuba.fei.uim.oop.assignment3.book.web.bodies.BookResponse;
import sk.stuba.fei.uim.oop.assignment3.list.data.LendingList;

import java.util.ArrayList;
import java.util.List;

@Getter
public class ListResponse {
    private final Long id;
    private final List<BookResponse> lendingList;
    private final boolean lended;

    public ListResponse(LendingList l) {
        this.id = l.getId();
        this.lendingList = new ArrayList<>();
        for(Book b : l.getLendList()) {
            this.lendingList.add(new BookResponse(b));
        }
        this.lended = l.isLended();
    }
}
