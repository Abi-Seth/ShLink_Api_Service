package com.shlink.shlink.utils;

import com.shlink.shlink.dto.Link;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;

public class ValidateLinks {
    public static boolean validateLink(Link link) throws MalformedURLException, URISyntaxException {
        try {
            new URL(link.getLink()).toURI();
            return true;
        } catch (MalformedURLException | URISyntaxException err) {
            return false;
        }
    }
}
