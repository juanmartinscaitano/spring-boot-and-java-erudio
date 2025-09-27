package br.com.erudio.controllers;

import br.com.erudio.controllers.docs.BookControllerDocs;
import br.com.erudio.data.dto.BookVO;
import br.com.erudio.services.BookServices;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books/v1")
@Tag(name = "Book", description = "End Points for Books")
public class BookController implements BookControllerDocs {

    @Autowired
    private BookServices bookServices;


    @GetMapping(produces = {
        MediaType.APPLICATION_JSON_VALUE,
        MediaType.APPLICATION_YAML_VALUE,
        MediaType.APPLICATION_XML_VALUE
    })
    @Override
    public List<BookVO> findsAllBooks() {
        return bookServices.findsAllBooks();
    }
    @Override
    @GetMapping(value = "/{id}",
        produces = {
            MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_YAML_VALUE,
            MediaType.APPLICATION_XML_VALUE
    })
    public BookVO findBookById(@PathVariable("id") Long id){
        return bookServices.findBookById(id);
    }

    @PostMapping(
        produces = {
            MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_YAML_VALUE,
            MediaType.APPLICATION_XML_VALUE
        },
        consumes = {
            MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_YAML_VALUE,
            MediaType.APPLICATION_XML_VALUE
        }
    )
    @Override
    public BookVO createBook(@RequestBody BookVO book) {
        return bookServices.createBook(book);
    }

    @PutMapping(
        produces = {
            MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_YAML_VALUE,
            MediaType.APPLICATION_XML_VALUE
        },
        consumes = {
            MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_YAML_VALUE,
            MediaType.APPLICATION_XML_VALUE
        }
    )
    @Override
    public BookVO updateBook(@RequestBody BookVO book) {
        return bookServices.updateBook(book);
    }

    @DeleteMapping(value = "/{id}")
    @Override
    public ResponseEntity<?> deleteBook(@PathVariable("id") Long id) {
         bookServices.deleteBook(id);
         return ResponseEntity.noContent().build();
    }
}
