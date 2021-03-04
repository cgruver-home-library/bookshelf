package org.labmonkeys.home_library.bookshelf.dto;

import io.quarkus.runtime.annotations.RegisterForReflection;
import lombok.Data;

@Data
@RegisterForReflection
public class BookDTO {
    public enum BookStatusEnum{ON_SHELF,CHECKED_OUT,CHECKED_IN,LOST,DAMAGED};
    private String catalogId;
    private Long bookId;
    private BookStatusEnum status;
    private Long bookCaseId;
    private Long bookShelfId;
}
