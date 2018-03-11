package com.alexzahv.business.persistance.domain;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "book")
public class Book {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO, generator="book_generator")
    @SequenceGenerator(name="book_generator", sequenceName="seq_book_id")
    @Column(name = "id")
    private Long id;
    
    @Column(name = "file_id")
    private String fileId;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "author")
    private String author;

    @Column(name = "size")
    private Long size;

    @Column(name = "mime_type")
    private String mimeType;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "create_date")
    private LocalDateTime createDate;
}
