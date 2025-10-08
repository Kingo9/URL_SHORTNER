package com.Asad.url_shortner.model;

// This line declares that our class belongs to the 'model' package.
// This helps in organizing our project, separating data models from controllers, services, etc.

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * This class represents the core data model for our URL Shortener. It is a POJO
 * (Plain Old Java Object), which means it's a simple object not bound by any
 * special restrictions or frameworks at this stage.
 *
 * Each instance of this class will eventually correspond to a single row in our
 * database table, holding the crucial link between an original, long URL and the
 * short code we generate for it.
 *
 * In the upcoming tasks, we will use JPA annotations (like @Entity, @Id, @Column)
 * to transform this simple POJO into a powerful JPA Entity that Spring Data
 * can manage and persist to the database automatically.
 */

@Getter
@Setter
@NoArgsConstructor
@Entity
public class UrlMapping {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Lob
    @Column(nullable = false)
    private String Og_URL;


    @Column(unique = true)
    private String shortcode;


    private LocalDateTime creation_time;



    private long clicks;
}
