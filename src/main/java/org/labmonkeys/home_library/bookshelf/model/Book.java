package org.labmonkeys.home_library.bookshelf.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "book", indexes = {@Index(name = "idx_catalog_id", columnList = "catalog_id")})
public class Book extends PanacheEntityBase {

    @Id()
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private Long bookId;

    @Column(name = "catalog_id")
    private String catalogId;

    @Column()
    private  boolean onShelf;

    @Column()
    private Long bookCaseId;

    @Column()
    private Long bookShelfId;

    public Book findById(Long bookId) {
        return findById(bookId);
    }
    public static List<Book> getBooks(String catalogId) {
    
        return find("catalogId", catalogId).list();
    }
}
