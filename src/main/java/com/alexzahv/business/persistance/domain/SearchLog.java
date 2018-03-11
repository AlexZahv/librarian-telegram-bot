package com.alexzahv.business.persistance.domain;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "search_log")
public class SearchLog {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO, generator="search_generator")
    @SequenceGenerator(name="search_generator", sequenceName="seq_search_id")
    @Column(name = "fileId")
    private Long id;

    @Column(name = "command")
    private String command;

    @Column(name = "search_date")
    private LocalDateTime searchDate;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
