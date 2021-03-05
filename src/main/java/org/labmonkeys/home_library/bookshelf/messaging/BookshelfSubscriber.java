package org.labmonkeys.home_library.bookshelf.messaging;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.eclipse.microprofile.reactive.messaging.Acknowledgment;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.labmonkeys.home_library.bookshelf.dto.BookDTO;
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
    public void bookshelfEvents(BookEvent bookEvent) {
        List<BookDTO> bookEvents = new ArrayList<BookDTO>();
        bookEvents.add(mapper.BookEventToDto(bookEvent));
        bookshelf.updateBooks(bookEvents);
    }
}
