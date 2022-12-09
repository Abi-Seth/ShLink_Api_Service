package com.shlink.shlink.model;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.StringJoiner;
import java.util.UUID;

@Entity(name="ShortenedLinks")
@Table(name="ShortenedLinks")
public class ShortenedLink {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "uuid", columnDefinition = "char(36)")
    @Type(type = "org.hibernate.type.UUIDCharType")
    private UUID uuid;
    @Column(name = "link", updatable = true, nullable = false)
    private String link;
    @Column(name = "shortLink", updatable = true, nullable = false)
    private String shortLink;

    public ShortenedLink() {}

    public ShortenedLink(String link, String shortLink) {
        this.link = link;
        this.shortLink = shortLink;
    }

    public ShortenedLink(UUID uuid, String link, String shortLink) {
        this.uuid = uuid;
        this.link = link;
        this.shortLink = shortLink;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getShortLink() {
        return shortLink;
    }

    public void setShortLink(String shortLink) {
        this.shortLink = shortLink;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", ShortenedLink.class.getSimpleName() + "[", "]")
                .add(String.format("uuid=%s", uuid))
                .toString();
    }
}
