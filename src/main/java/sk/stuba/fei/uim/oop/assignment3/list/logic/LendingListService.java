package sk.stuba.fei.uim.oop.assignment3.list.logic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sk.stuba.fei.uim.oop.assignment3.book.data.Book;
import sk.stuba.fei.uim.oop.assignment3.book.logic.IBookService;
import sk.stuba.fei.uim.oop.assignment3.exceptions.IllegalOperationException;
import sk.stuba.fei.uim.oop.assignment3.exceptions.NotFoundException;
import sk.stuba.fei.uim.oop.assignment3.list.data.LendingList;
import sk.stuba.fei.uim.oop.assignment3.list.data.LendingListRepository;
import sk.stuba.fei.uim.oop.assignment3.list.web.bodies.BookId;

import java.util.List;

@Service
public class LendingListService implements ILendingListService {
    @Autowired
    private LendingListRepository repository;

    @Autowired
    private IBookService bookService;

    @Override
    public List<LendingList> getAll() {
        return this.repository.findAll();
    }

    @Override
    public LendingList create() {
        return this.repository.save(new LendingList());
    }

    @Override
    public LendingList getById(Long id) throws NotFoundException {
        LendingList list = this.repository.findLendingListById(id);
        if (list == null) {
            throw new NotFoundException();
        }
        return list;
    }

    @Override
    public void delete(Long id) throws NotFoundException {
        for (Book b : this.getById(id).getLendList()) {
            b.setAmount(b.getAmount() + 1);
        }
        this.repository.delete(this.getById(id));
    }

    @Override
    public LendingList addBook(Long id, BookId body) throws NotFoundException, IllegalOperationException {
        LendingList list = this.getNotLended(id);
        Book b = bookService.getById(body.getId());
        if (list.getLendList().contains(b)) {
            throw new IllegalOperationException();
        }
        b.setAmount(b.getAmount() - 1);
        list.getLendList().add(b);
        return this.repository.save(list);
    }

    @Override
    public void removeBook(Long id, BookId body) throws NotFoundException {
        LendingList list = this.getById(id);
        Book b = bookService.getById(body.getId());
        list.getLendList().remove(b);
        b.setAmount(b.getAmount() + 1);
        this.repository.save(list);
    }

    @Override
    public void lendList(Long id) throws NotFoundException, IllegalOperationException {
        LendingList list = this.getNotLended(id);
        list.setLended(true);
        for(Book b : list.getLendList()) {
            b.setLendCount(b.getLendCount() + 1);
        }
        this.repository.save(list);
    }

    private LendingList getNotLended(Long id) throws NotFoundException, IllegalOperationException {
        LendingList list = this.getById(id);
        if (list.isLended()) {
            throw new IllegalOperationException();
        }
        return list;
    }

}
