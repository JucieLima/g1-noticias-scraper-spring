package com.include.scraping.repository;

import com.include.scraping.model.Noticia;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface NoticiaRepository extends JpaRepository<Noticia, Long> {
    Optional<Noticia> findByLink(String link);
    boolean existsByLink(String link);

    List<Noticia> findAllByOrderByDataScrapingDesc();
}