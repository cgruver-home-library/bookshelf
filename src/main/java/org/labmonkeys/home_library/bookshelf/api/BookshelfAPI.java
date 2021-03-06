package org.labmonkeys.home_library.bookshelf.api;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.labmonkeys.home_library.bookshelf.dto.BookDTO;

@Path("/bookshelf")
@ApplicationScoped
public interface BookshelfAPI {

    @GET
    @Path("/getBooks/{catalogId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBooks(@PathParam("catalogId") String catalogId);

    @GET
    @Path("/getBook/{bookId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBook(@PathParam("bookId") Long bookId);

    @POST
    @Path("/updateBooks")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateBooks(List<BookDTO> books);
    
    @POST
    @Path("/addBook")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addBook(BookDTO book);

    @DELETE
    @Path("/deleteBook/{bookId}")
    public Response deleteBook(@PathParam("bookId") Long bookId);
}

