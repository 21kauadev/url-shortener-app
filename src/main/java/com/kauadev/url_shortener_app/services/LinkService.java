package com.kauadev.url_shortener_app.services;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.kauadev.url_shortener_app.domain.link.Link;
import com.kauadev.url_shortener_app.domain.link.LinkResponse;
import com.kauadev.url_shortener_app.domain.link.exceptions.ExpiredLinkException;
import com.kauadev.url_shortener_app.domain.user.User;
import com.kauadev.url_shortener_app.repositories.LinkRepository;

@Service
public class LinkService {

    @Autowired
    private LinkRepository linkRepository;

    private static final String baseUrl = "http://localhost:8080/link/";

    public List<Link> getAllLinks() {
        List<Link> links = linkRepository.findAll();

        return links;
    }

    public LinkResponse shorten(String url) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User loggedUser = (User) auth.getPrincipal();

        // processo:
        // vou salvar a entidade Link
        // pegar o id dela
        // com base no id, gerar um shortCode (que será salvo posteriormente em Link)
        // gerar a url curta e retornar a url original e a curta

        // inicializando sem tempo de expiração e shortCode gerado
        Link link = new Link(url, null, null, 0, loggedUser);
        Link savedLink = linkRepository.save(link);

        String shortCode = ShortCodeGeneratorService.genShortCode(savedLink.getId());

        savedLink.setShortCode(shortCode);
        linkRepository.save(savedLink);

        String shortUrl = baseUrl + shortCode;
        LinkResponse linkResponse = new LinkResponse(url, shortUrl);

        return linkResponse;
    }

    public Link redirectToOriginaLUrl(String shortCode) {
        System.out.println("tamo aqui.");
        Link foundLink = linkRepository.findByShortCode(shortCode);

        // verifica se expirou
        if (foundLink.getExpiresAt().isBefore(LocalDateTime.now())) {
            throw new ExpiredLinkException();
        }

        // incrementando a contagem de cliques
        Integer clickCount = foundLink.getClickCount();
        foundLink.setClickCount(clickCount + 1);

        linkRepository.save(foundLink);

        return foundLink;
    }

}
