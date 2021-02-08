package org.labmonkeys.home_library.bookshelf.mapper;

import java.util.List;

import org.labmonkeys.home_library.bookshelf.dto.BookDTO;
import org.labmonkeys.home_library.bookshelf.model.Book;
import org.mapstruct.Mapper;

@Mapper(componentModel = "cdi")
public interface BookMapper {

    Book BookDtoToBook(BookDTO book);

    BookDTO BookToDto(Book book);

    List<Book> BookDtosToBooks(List<BookDTO> books);

    List<BookDTO> BooksToDtos(List<Book> books);
}
