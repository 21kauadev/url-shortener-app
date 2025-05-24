package com.kauadev.url_shortener_app.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kauadev.url_shortener_app.repositories.LinkRepository;

@Service
public class LinkService {

    @Autowired
    private LinkRepository linkRepository;

}
