// G1ScraperService.java
package com.include.scraping.service;

import com.include.scraping.model.Noticia;
import com.include.scraping.repository.NoticiaRepository;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class G1ScraperService {

    @Autowired
    private NoticiaRepository repository;

    private static final String G1_URL = "https://g1.globo.com/";

    // Roda a cada 30 minutos
    @Scheduled(fixedDelay = 30 * 60 * 1000, initialDelay = 5000)
    public void scrapearG1() {
        System.out.println("Iniciando scraping do G1 - " + LocalDateTime.now());

        try {
            Document doc = Jsoup.connect(G1_URL)
                    .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36")
                    .timeout(10000)
                    .get();

            // As principais notícias estão nos blocos .feed-post
            Elements noticias = doc.select(".feed-post-body");

            for (Element noticia : noticias) {
                String titulo = noticia.selectFirst(".feed-post-link") != null ?
                        noticia.selectFirst(".feed-post-link").text() : "Sem título";

                String link = noticia.selectFirst(".feed-post-link") != null ?
                        noticia.selectFirst(".feed-post-link").attr("href") : "";

                if (link.isEmpty() || repository.existsByLink(link)) {
                    continue; // evita duplicatas
                }

                String subtitulo = noticia.selectFirst(".feed-post-body-resumo") != null ?
                        noticia.selectFirst(".feed-post-body-resumo").text() : null;

                String imagem = noticia.selectFirst(".bstn-fd-picture-image") != null ?
                        noticia.selectFirst(".bstn-fd-picture-image").attr("src") : null;

                Noticia nova = Noticia.builder()
                        .titulo(titulo)
                        .subtitulo(subtitulo)
                        .link(link)
                        .imagem(imagem)
                        .dataPublicacao(LocalDateTime.now())
                        .build();

                repository.save(nova);
                System.out.println("Notícia salva: " + titulo);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Método para rodar manualmente (opcional)
    public void executarAgora() {
        scrapearG1();
    }
}