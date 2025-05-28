package com.kauadev.url_shortener_app.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kauadev.url_shortener_app.domain.link.Link;
import com.kauadev.url_shortener_app.repositories.LinkRepository;

@Service
public class LinkService {

    @Autowired
    private LinkRepository linkRepository;

    public List<Link> getAllLinks() {
        List<Link> links = linkRepository.findAll();

        return links;
    }

    // public Link shorten(String url) {
    // // com base na url recebida,
    // // gerar um shortCode, que seria a url curta a passar na url da applicaçao
    // // a logica de interceptaçao e redirecionamento da url é com uso do traefik

    // // processo:
    // // vou salvar a entidade Link
    // // pegar o id dela
    // // com base no id, gerar um shortCode (que será salvo posteriormente em Link)
    // // gerar a url curta e retornar a url original e a curta

    // }

}
