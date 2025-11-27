# G1 Notícias Scraper - Spring Boot + PostgreSQL

Um web scraper que coleta automaticamente as principais notícias do [G1](https://g1.globo.com/), salva no banco PostgreSQL e exibe em uma interface web simples.

Atualiza a cada 30 minutos ou manualmente com um clique.

## Funcionalidades

- Scraping automático das manchetes do G1
- Salva título, subtítulo, link, imagem e data
- Evita notícias duplicadas (controle por URL)
- Interface web responsiva com Bootstrap 5
- Botão "Atualizar Agora"
- API REST JSON (/api/noticias)
- 100% funcional com Java 21 + Spring Boot 3
- Pronto para Docker

## Tecnologias

- Java 21
- Spring Boot 3.3+
- Spring Data JPA + Hibernate
- PostgreSQL
- Jsoup (HTML parsing)
- Thymeleaf + Bootstrap 5
- Lombok
- Docker & Docker Compose

## Como rodar localmente (5 minutos)

### Pré-requisitos
- Java 21 ou superior
- Maven
- PostgreSQL 15+ rodando localmente

### Passo a passo

1. Clone o repositório
```bash
git clone https://github.com/JucieLima/g1-noticias-scraper-spring
cd g1-noticias-scraper-spring