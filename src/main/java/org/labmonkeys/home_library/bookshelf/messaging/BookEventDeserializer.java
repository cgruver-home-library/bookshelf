package org.labmonkeys.home_library.bookshelf.messaging;

import io.quarkus.kafka.client.serialization.ObjectMapperDeserializer;

public class BookEventDeserializer extends ObjectMapperDeserializer<BookEvent> {

    public BookEventDeserializer() {
        super(BookEvent.class);
    }
    
}
