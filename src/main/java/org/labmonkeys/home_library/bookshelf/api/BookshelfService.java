package org.labmonkeys.home_library.bookshelf.api;

import java.util.ArrayList;
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
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.jboss.logging.Logger;
import org.labmonkeys.home_library.bookshelf.dto.BookDTO;
import org.labmonkeys.home_library.bookshelf.mapper.BookMapper;
import org.labmonkeys.home_library.bookshelf.model.Book;

@Path("/bookshelf")
@ApplicationScoped
public class BookshelfService {
    final Logger LOG = Logger.getLogger(BookshelfService.class);
    @Inject BookMapper mapper;

    @GET
    @Path("/getBooks/{catalogId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBooks(@PathParam("catalogId") String catalogId) {
        List<BookDTO> books = null;
        try {
            books = mapper.BooksToDtos(Book.getBooks(catalogId));
        } catch (Exception e) {
            LOG.error(e.getMessage());
            return Response.status(Status.INTERNAL_SERVER_ERROR).build();
        }
        return Response.ok(books).build();
    }

    @GET
    @Path("/getBook/{bookId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBook(@PathParam("bookId") Long bookId) {
        BookDTO book = null;
        try {
            book = mapper.BookToDto(Book.findById(bookId));
        } catch (Exception e) {
            LOG.error(e.getMessage());
            return Response.status(Status.INTERNAL_SERVER_ERROR).build();
        }
        return Response.ok(book).build();
    }

    @POST
    @Path("/updateBooks")
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response updateBooks(List<BookDTO> books) {
        List<Book> booksToUpdate = new ArrayList<Book>();
        try {
            for (BookDTO bookDTO : books) {
                Book book = Book.findById(bookDTO.getBookId());
                book.setBookCaseId(bookDTO.getBookCaseId());
                book.setBookShelfId(bookDTO.getBookShelfId());
                book.setOnShelf(bookDTO.isOnShelf());
                booksToUpdate.add(book);
                book.persist();
            }
            //Book.persist(booksToUpdate);
        } catch (Exception e) {
            e.printStackTrace();
            LOG.error(e.getMessage());
            return Response.status(Status.INTERNAL_SERVER_ERROR).build();
        }
        return Response.ok().build();
    }
    
    @POST
    @Path("/addBook")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Response addBook(BookDTO book) {
        Book entity = mapper.BookDtoToBook(book);
        try {
            Book.persist(entity);
        } catch (Exception e) {
            LOG.error(e.getMessage());
            return Response.status(Status.INTERNAL_SERVER_ERROR).build();
        }
        return Response.ok(mapper.BookToDto(entity)).build();
    }
    // Adds a Book to the BookShelf, Returns the hydrated Book with the assigned Book ID

    @DELETE
    @Path("/deleteBook/{bookId}")
    @Transactional
    public Response deleteBook(@PathParam("bookId") Long bookId) {
        try {
            if (!Book.deleteById(bookId)) {
                return Response.status(Status.NOT_FOUND).build();
            }
        } catch (Exception e) {
            LOG.error(e.getMessage());
            return Response.status(Status.INTERNAL_SERVER_ERROR).build();
        }
        return Response.ok().build();
    }
}

