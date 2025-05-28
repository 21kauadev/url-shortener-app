package com.kauadev.url_shortener_app.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kauadev.url_shortener_app.domain.link.Link;
import com.kauadev.url_shortener_app.domain.link.LinkResponse;
import com.kauadev.url_shortener_app.infra.ApiResponse;
import com.kauadev.url_shortener_app.services.LinkService;

@RestController
@RequestMapping("/link")
public class LinkController {

    @Autowired
    private LinkService linkService;

    @GetMapping("/")
    private ResponseEntity<ApiResponse<List<Link>>> getAllLinks() {
        List<Link> links = linkService.getAllLinks();
        ApiResponse<List<Link>> response = new ApiResponse<List<Link>>(HttpStatus.OK.value(), true,
                "Links retornados: ", links);

        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/shorten")
    private ResponseEntity<LinkResponse> shorten(@RequestBody String url) {
        LinkResponse response = linkService.shorten(url);

        return ResponseEntity.ok().body(response);
    }
}
