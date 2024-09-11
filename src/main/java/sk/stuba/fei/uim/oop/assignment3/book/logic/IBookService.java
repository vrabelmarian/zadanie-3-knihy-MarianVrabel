package sk.stuba.fei.uim.oop.assignment3.book.logic;

import sk.stuba.fei.uim.oop.assignment3.book.data.Book;
import sk.stuba.fei.uim.oop.assignment3.book.web.bodies.BookRequest;
import sk.stuba.fei.uim.oop.assignment3.book.web.bodies.BookUpdateRequest;
import sk.stuba.fei.uim.oop.assignment3.exceptions.IllegalOperationException;
import sk.stuba.fei.uim.oop.assignment3.exceptions.NotFoundException;

import java.util.List;

public interface IBookService {
    List<Book> getAll();

    Book create(BookRequest request) throws NotFoundException;

    Book getById(Long id) throws NotFoundException;

    Book update(Long id, BookUpdateRequest request) throws NotFoundException;

    void delete(Long id) throws NotFoundException;

    long getAmount(Long id) throws NotFoundException;

    long addAmount(Long id,long add) throws NotFoundException;

    long getLendCount(Long id) throws NotFoundException;

    void decAmount(Long id, long dec) throws NotFoundException, IllegalOperationException;
}
