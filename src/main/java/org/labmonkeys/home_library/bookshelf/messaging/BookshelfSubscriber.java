package org.labmonkeys.home_library.bookshelf.messaging;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.eclipse.microprofile.reactive.messaging.Acknowledgment;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.labmonkeys.home_library.bookshelf.mapper.BookMapper;
import org.labmonkeys.home_library.bookshelf.service.BookshelfService;

@ApplicationScoped
public class BookshelfSubscriber {

    @Inject BookshelfService bookshelf;

    @Inject BookMapper mapper;

    @Incoming("book-event")
    @Acknowledgment(Acknowledgment.Strategy.PRE_PROCESSING)
    public void bookshelfEvents(List<BookEvent> bookEvents) {
        bookshelf.updateBooks(mapper.BookEventsToDtos(bookEvents));
    }
}
