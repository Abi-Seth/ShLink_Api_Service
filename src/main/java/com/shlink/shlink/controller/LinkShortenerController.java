package com.shlink.shlink.controller;

import com.shlink.shlink.utils.APIResponse;
import com.shlink.shlink.dto.Link;
import com.shlink.shlink.model.ShortenedLink;
import com.shlink.shlink.service.LinkShortenerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import com.shlink.shlink.utils.ValidateLinks;

import javax.validation.Valid;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/shortenedLinks")
public class LinkShortenerController {
    private final LinkShortenerService linkShortenerService;

    @Autowired
    public LinkShortenerController(LinkShortenerService linkShortenerService) {
        this.linkShortenerService = linkShortenerService;
    }

    @GetMapping("")
    public List<ShortenedLink> listShortenedLinks() {
        return linkShortenerService.listShortenedLinks();
    }

    @PostMapping("")
    public ResponseEntity<?> shortenLink(@Valid @RequestBody Link link) {
        try {
            if (!ValidateLinks.validateLink(link))
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(new APIResponse(false,"Cannot shorten an invalid link!"));
        } catch (MalformedURLException | URISyntaxException e) {
            throw new RuntimeException(e);
        }
        return ResponseEntity.ok(linkShortenerService.shortenLink(link.getLink()));
    }

    @GetMapping("{id}")
    public ResponseEntity<?> shortenLink(@PathVariable(name = "id") UUID id) {
        ShortenedLink shortenedlink = linkShortenerService.retrieveShortenedLink(id);
        if (shortenedlink != null) {
            return ResponseEntity.ok(shortenedlink);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new APIResponse(false, "Shortened link not found!"));
    }


}
