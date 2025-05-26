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

}
