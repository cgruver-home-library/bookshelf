package org.labmonkeys.home_library.bookshelf.messaging;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.labmonkeys.home_library.bookshelf.dto.BookDTO;
import org.labmonkeys.home_library.bookshelf.service.BookshelfService;

@ApplicationScoped
public class BookshelfSubscriber {

    @Inject BookshelfService bookshelf;

    @Incoming("book-event")
    public void bookshelfEvents(List<BookDTO> books) {
        bookshelf.updateBooks(books);
    }
}
