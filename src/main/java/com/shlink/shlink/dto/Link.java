package com.shlink.shlink.dto;

import javax.validation.constraints.NotNull;

public class Link {
    @NotNull
    private String link;

    public Link() {
        super();
    }

    public Link(@NotNull String link) {
        this.link = link;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
