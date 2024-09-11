package sk.stuba.fei.uim.oop.assignment3.book.logic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sk.stuba.fei.uim.oop.assignment3.author.data.Author;
import sk.stuba.fei.uim.oop.assignment3.author.logic.AuthorService;
import sk.stuba.fei.uim.oop.assignment3.book.data.Book;
import sk.stuba.fei.uim.oop.assignment3.book.data.BookRepository;
import sk.stuba.fei.uim.oop.assignment3.book.web.bodies.BookRequest;
import sk.stuba.fei.uim.oop.assignment3.book.web.bodies.BookUpdateRequest;
import sk.stuba.fei.uim.oop.assignment3.exceptions.IllegalOperationException;
import sk.stuba.fei.uim.oop.assignment3.exceptions.NotFoundException;

import java.util.List;

@Service
public class BookService implements IBookService {
    @Autowired
    private BookRepository repository;

    @Autowired
    private AuthorService authorService;


    @Override
    public List<Book> getAll() {
        return this.repository.findAll();
    }

    @Override
    public Book create(BookRequest request) throws NotFoundException {
        Author author = authorService.getById(request.getAuthor());
        return this.repository.save(new Book(request, author));
    }

    @Override
    public Book getById(Long id) throws NotFoundException {
        Book b =  this.repository.findBookById(id);
        if (b == null) {
            throw new NotFoundException();
        }
        return b;
    }

    @Override
    public Book update(Long id, BookUpdateRequest request) throws NotFoundException {
        Book b = this.getById(id);
        if (request.getName() != null) {
            b.setName(request.getName());
        }
        if (request.getDescription() != null) {
            b.setDescription(request.getDescription());
        }
        if (request.getAuthor() != null && request.getAuthor() != 0) {
            Author a = authorService.getById(request.getAuthor());
            b.setAuthor(a);
        }
        if (request.getPages() != 0) {
            b.setPages(request.getPages());
        }
        return this.repository.save(b);
    }

    @Override
    public void delete(Long id) throws NotFoundException {
        this.repository.delete(this.getById(id));
    }

    @Override
    public long getAmount(Long id) throws NotFoundException {
        return this.getById(id).getAmount();
    }

    @Override
    public long addAmount(Long id, long increment) throws NotFoundException {
        Book b = this.getById(id);
        b.setAmount(b.getAmount() + increment);
        this.repository.save(b);
        return b.getAmount();
    }

    @Override
    public long getLendCount(Long id) throws NotFoundException {
        return this.getById(id).getLendCount();
    }

    @Override
    public void decAmount(Long id, long dec) throws NotFoundException, IllegalOperationException {
        Book b = this.getById(id);
        if (b.getAmount() < dec) {
            throw new IllegalOperationException();
        }
        b.setAmount(b.getAmount() - dec);
        this.repository.save(b);
    }
}
