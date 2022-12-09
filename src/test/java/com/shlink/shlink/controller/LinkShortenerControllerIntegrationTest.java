package com.shlink.shlink.controller;

import com.shlink.shlink.utils.APIResponse;
import org.json.JSONException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.shlink.shlink.model.ShortenedLink;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
public class LinkShortenerControllerIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void getAll_success() throws JSONException {
        String response = this.restTemplate.getForObject("/api/v1/shortenedLinks", String.class);

        JSONAssert.assertEquals("[{uuid:1d354731-f69c-4875-9caf-ee1b15f21f38},{uuid:bafc0491-0e19-4ec1-b4cb-ac5a25fdf4c1},{uuid:dd941532-9ca3-4207-aa2f-4decfb1ba078}]", response, false);
    }

    @Test
    public void getByUuid_successObject() {
        ShortenedLink link = this.restTemplate.getForObject("/api/v1/shortenedLinks/1d354731-f69c-4875-9caf-ee1b15f21f38", ShortenedLink.class);

        assertEquals("http://127.0.0.1:8080/GFatM6", link.getShortLink());
        assertEquals("https://trello.com/b/nmdD7m0m/ackviz-backend", link.getLink());
    }

    @Test
    public void getByUuid_success() {
        ResponseEntity<ShortenedLink> link = this.restTemplate.getForEntity("/api/v1/shortenedLinks/1d354731-f69c-4875-9caf-ee1b15f21f38", ShortenedLink.class);

        assertTrue(link.getStatusCode().is2xxSuccessful());
        assertEquals("https://trello.com/b/nmdD7m0m/ackviz-backend", Objects.requireNonNull(link.getBody()).getLink());
        assertEquals("http://127.0.0.1:8080/GFatM6", link.getBody().getShortLink());
    }

    @Test
    public void getByUuid_404() {
        ResponseEntity<APIResponse> link =
            this.restTemplate.getForEntity("/api/v1/shortenedLinks/1d354731-f69c-4875-9caf-ee1b15f21f37", APIResponse.class);

        assertEquals(404, link.getStatusCodeValue());
        assertFalse(Objects.requireNonNull(link.getBody()).isStatus());
        assertEquals("Shortened link not found!", link.getBody().getMessage());
    }
}
