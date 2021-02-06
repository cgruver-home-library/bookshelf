package org.labmonkeys.home_library.bookshelf.api;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.labmonkeys.home_library.bookshelf.BookShelfException;
import org.labmonkeys.home_library.bookshelf.dto.BookDTO;
import org.labmonkeys.home_library.bookshelf.mapper.BookMapper;
import org.labmonkeys.home_library.bookshelf.model.Book;

@Path("/bookshelf")
@ApplicationScoped
public class BookshelfService {
    @Inject BookMapper mapper;

    @GET
    @Path("/getBooks/{catalogId}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<BookDTO> getBooks(@PathParam("catalogId") String catalogId) throws BookShelfException {
        
        return mapper.BooksToDtos(Book.getBooks(catalogId));
    }
    // Returns the known list of Books by isbn

    @GET
    @Path("/getBook/{book-id}")
    @Produces(MediaType.APPLICATION_JSON)
    public BookDTO getBook(@PathParam("book-id") Long bookId) throws BookShelfException {
        
        return mapper.BookToDto(Book.findById(bookId));
    }
    // Returns a specific Book by ID

    @POST
    @Path("/updateBook")
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public void updateBook(BookDTO book) throws BookShelfException {
        
        Book.persist(mapper.BookDtoToBook(book));
    }
    // Updates a specific Book

    @POST
    @Path("/updateBooks")
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public void updateBooks(List<BookDTO> books) throws BookShelfException {

        Book.persist(mapper.BookDtosToBooks(books));
    }
    // Updates a group of Books
    
    @POST
    @Path("/addBook")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public BookDTO addBook(BookDTO book) throws BookShelfException {
        
        Book entity = mapper.BookDtoToBook(book);
        Book.persist(entity);
        return mapper.BookToDto(entity);
    }
    // Adds a Book to the BookShelf, Returns the hydrated Book with the assigned Book ID

    @DELETE
    @Path("/deleteBook/{book-id}")
    @Transactional
    public void deleteBook(@PathParam("book-id") Long bookId) throws BookShelfException {
        
        Book.deleteById(bookId);
    }
}

