package org.labmonkeys.home_library.bookshelf.dto;

import io.quarkus.runtime.annotations.RegisterForReflection;
import lombok.Data;

@Data
@RegisterForReflection
public class BookDTO {

    
    private String catalogId;
    private Long bookId;
    private boolean onShelf;
    private Long bookCaseId;
    private Long bookShelfId;
}
