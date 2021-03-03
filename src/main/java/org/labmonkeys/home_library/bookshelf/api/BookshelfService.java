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

    @GET
    @Path("/getBook/{bookId}")
    @Produces(MediaType.APPLICATION_JSON)
    public BookDTO getBook(@PathParam("bookId") Long bookId) throws BookShelfException {
        
        return mapper.BookToDto(Book.findById(bookId));
    }

    @POST
    @Path("/updateBooks")
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public void updateBooks(List<BookDTO> books) throws BookShelfException {

        Book.persist(mapper.BookDtosToBooks(books));
    }
    
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
    @Path("/deleteBook/{bookId}")
    @Transactional
    public void deleteBook(@PathParam("bookId") Long bookId) throws BookShelfException {
        
        Book.deleteById(bookId);
    }
}

