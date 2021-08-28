package org.labmonkeys.home_library.bookshelf.messaging;


import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.eclipse.microprofile.reactive.messaging.Acknowledgment;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.labmonkeys.home_library.bookshelf.mapper.BookMapper;
import org.labmonkeys.home_library.bookshelf.service.BookshelfService;
import io.smallrye.reactive.messaging.annotations.Blocking;

@ApplicationScoped
public class BookshelfSubscriber {

    @Inject BookshelfService bookshelf;

    @Inject BookMapper mapper;

    @Incoming("book-event")
    @Blocking
    @Acknowledgment(Acknowledgment.Strategy.PRE_PROCESSING)
    public void bookshelfEvents(BorrowedBookEvent bookEvent) {
        bookshelf.updateBooks(mapper.BookStatesToDtos(bookEvent.getBookList()));
    }
}
