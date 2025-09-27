package br.com.erudio.controllers.docs;

import br.com.erudio.data.dto.BookVO;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface BookControllerDocs {

    @Operation(summary = "Find All Books",
        description = "Finds All Books",
        tags = {"Books"},
        responses = {
            @ApiResponse(description = "No Content",
                    responseCode = "204",
                    content = {
                    @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            array = @ArraySchema(schema = @Schema(implementation = BookVO.class))
                    )
            }),
            @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
            @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
            @ApiResponse(description = "Internal Server error", responseCode = "500", content = @Content)
        }
    )
    List<BookVO> findsAllBooks();

    @Operation(summary = "Find One Book",
        description = "Find a book by their id",
        responses = {
            @ApiResponse(description = "success",
                responseCode = "200",
                content = {
                    @Content(
                        schema = @Schema(implementation = BookVO.class)
                    )
            }),
            @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
            @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
            @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
        }
    )
    BookVO findBookById(@PathVariable("id") Long id);

    @Operation(summary = "Creating One Book",
                description = "Creating a new book",
            responses = {
            @ApiResponse(description = "Created",
                responseCode = "201",
                content = @Content(
                    schema = @Schema(implementation = BookVO.class)
                )
            ),
            @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
            @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
            @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
            }
    )
    BookVO createBook(@RequestBody BookVO vo);

    @Operation(summary = "Update One Book",
        description = "Updates one book",
        responses = {
            @ApiResponse(description = "Success",
                responseCode = "200",
                content = @Content(
                    schema = @Schema(implementation = BookVO.class)
                )
            ),
            @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
            @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
            @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
        }
    )
    BookVO updateBook(@RequestBody BookVO vo);

    @Operation(summary = "Delete One Book",
            description = "Deletes one book by their id",
            responses = {
                @ApiResponse(description = "No Content", responseCode = "201")
            }
    )
    ResponseEntity<?> deleteBook(@PathVariable("id") Long id);

}
