package org.labmonkeys.home_library.bookshelf.messaging;

import java.awt.print.Book;

import org.labmonkeys.home_library.bookshelf.dto.BookDTO;

import io.quarkus.kafka.client.serialization.ObjectMapperDeserializer;

public class BookDeserializer extends ObjectMapperDeserializer<BookDTO> {

    public BookDeserializer() {
        super(BookDTO.class);
    }
    
}
