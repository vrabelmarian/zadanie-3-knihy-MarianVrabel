package sk.stuba.fei.uim.oop.assignment3.list.logic;

import sk.stuba.fei.uim.oop.assignment3.exceptions.IllegalOperationException;
import sk.stuba.fei.uim.oop.assignment3.exceptions.NotFoundException;
import sk.stuba.fei.uim.oop.assignment3.list.data.LendingList;
import sk.stuba.fei.uim.oop.assignment3.list.web.bodies.BookId;

import java.util.List;

public interface ILendingListService {
    List<LendingList> getAll();

    LendingList create();

    LendingList getById(Long id) throws NotFoundException;

    void delete(Long id) throws NotFoundException;

    LendingList addBook(Long id, BookId body) throws NotFoundException, IllegalOperationException;

    void removeBook(Long id, BookId body) throws NotFoundException;

    void lendList(Long id) throws NotFoundException, IllegalOperationException;
}
