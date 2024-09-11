package sk.stuba.fei.uim.oop.assignment3.list.data;

import lombok.Data;
import sk.stuba.fei.uim.oop.assignment3.book.data.Book;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class LendingList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany
    private List<Book> lendList;

    private boolean isLended;

    public LendingList() {
        this.lendList = new ArrayList<>();
        this.isLended = false;
    }
}
