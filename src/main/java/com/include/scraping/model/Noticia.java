package com.include.scraping.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "noticias")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Noticia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 1000)
    private String titulo;

    @Column(length = 2000)
    private String subtitulo;

    @Column(unique = true, nullable = false, length = 1000)
    private String link;

    @Column(length = 1000)
    private String imagem;

    private LocalDateTime dataPublicacao;

    @Column(nullable = false)
    @CreationTimestamp
    private LocalDateTime dataScraping = LocalDateTime.now();
}