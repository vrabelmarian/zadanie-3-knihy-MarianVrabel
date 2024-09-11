package sk.stuba.fei.uim.oop.assignment3.list.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sk.stuba.fei.uim.oop.assignment3.book.data.Book;
import sk.stuba.fei.uim.oop.assignment3.exceptions.IllegalOperationException;
import sk.stuba.fei.uim.oop.assignment3.exceptions.NotFoundException;
import sk.stuba.fei.uim.oop.assignment3.list.logic.ILendingListService;
import sk.stuba.fei.uim.oop.assignment3.list.web.bodies.BookId;
import sk.stuba.fei.uim.oop.assignment3.list.web.bodies.ListResponse;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/list")
public class LendingListController {
    @Autowired
    private ILendingListService service;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ListResponse> getAllLists() {
        return this.service.getAll().stream().map(ListResponse::new).collect(Collectors.toList());
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ListResponse> createList() {
        return new ResponseEntity<>(new ListResponse(this.service.create()), HttpStatus.CREATED);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ListResponse getList(@PathVariable("id") Long listId) throws NotFoundException {
        return new ListResponse(this.service.getById(listId));
    }

    @DeleteMapping(value = "/{id}")
    public void deleteList(@PathVariable("id") Long listId) throws NotFoundException {
        this.service.delete(listId);
    }

    @PostMapping(value = "/{id}/add", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ListResponse addBookToList(@PathVariable("id") Long listId, @RequestBody BookId bookToAdd) throws NotFoundException, IllegalOperationException {
        return new ListResponse(this.service.addBook(listId, bookToAdd));
    }

    @DeleteMapping(value = "/{id}/remove")
    public void removeBookFromList(@PathVariable("id") Long listId, @RequestBody BookId bookToRemove) throws NotFoundException {
        this.service.removeBook(listId, bookToRemove);
    }

    @GetMapping(value = "/{id}/lend", produces = MediaType.TEXT_PLAIN_VALUE)
    public void lendTheList(@PathVariable("id") Long listId) throws NotFoundException, IllegalOperationException {
        this.service.lendList(listId);
    }
}
