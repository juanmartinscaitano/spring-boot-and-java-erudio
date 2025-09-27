package br.com.erudio.services;

import br.com.erudio.controllers.BookController;
import br.com.erudio.data.dto.BookVO;
import br.com.erudio.exception.RequiredObjectIsNullException;
import br.com.erudio.exception.ResourceNotFoundException;
import br.com.erudio.model.Book;
import br.com.erudio.repository.BookRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static br.com.erudio.mapper.ObjectMapper.parseListObjects;
import static br.com.erudio.mapper.ObjectMapper.parseObject;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class BookServices {

    private Logger loggerBook = LoggerFactory.getLogger(BookServices.class.getName());

    @Autowired
    BookRepository repositoryBook;

    public List<BookVO> findsAllBooks() {

        loggerBook.info("Finding All books!");

        List<BookVO> books = parseListObjects(repositoryBook.findAll(), BookVO.class);
        books.forEach(this::addHateoasBookLinks);
        return books;
    }

    public BookVO findBookById(Long id) {

        loggerBook.info("Finding one book");

        Book bookEntity = repositoryBook.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book ID not found"));
        BookVO vo = parseObject(bookEntity, BookVO.class);
        addHateoasBookLinks(vo);
        return vo;
    }

    public BookVO createBook(BookVO book) {
        if (book == null) throw new RequiredObjectIsNullException();

        loggerBook.info("Creating a book");
        Book bookEntity = parseObject(book, Book.class);

        BookVO vo = parseObject(repositoryBook.save(bookEntity), BookVO.class);
        addHateoasBookLinks(vo);
        return vo;
    }

    public BookVO updateBook(BookVO book) {

        if (book == null) throw new RequiredObjectIsNullException();


        loggerBook.info("Updating a book");
        Book bookEntity = repositoryBook.findById(book.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Id not found!"));

        bookEntity.setAuthor(book.getAuthor());
        bookEntity.setPrice(book.getPrice());
        bookEntity.setLaunchDate(book.getLaunchDate());
        bookEntity.setTitle(book.getTitle());

        BookVO vo = parseObject(repositoryBook.save(bookEntity), BookVO.class);
        addHateoasBookLinks(vo);
        return vo;
    }

    public void deleteBook(Long id) {

        loggerBook.info("Deleting one book");

        Book entityBook = repositoryBook.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("There's no such ID!"));
        repositoryBook.delete(entityBook);
    }

    private void addHateoasBookLinks(BookVO vo) {
        vo.add(linkTo(methodOn(BookController.class).findBookById(vo.getId())).withSelfRel().withType("GET"));
        vo.add(linkTo(methodOn(BookController.class).findsAllBooks()).withRel("Find All").withType("GET"));
        vo.add(linkTo(methodOn(BookController.class).createBook(vo)).withRel("Create").withType("POST"));
        vo.add(linkTo(methodOn(BookController.class).updateBook(vo)).withRel("Update").withType("PUT"));
        vo.add(linkTo(methodOn(BookController.class).deleteBook(vo.getId())).withRel("Delete").withType("DELETE"));
    }


}
