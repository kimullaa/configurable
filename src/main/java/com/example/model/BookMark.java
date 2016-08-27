package com.example.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;

@Entity
@Configurable
public class BookMark {
    @Id
    @GeneratedValue
    private Long id;
    private String link;

    BookMark() {
    }

    public BookMark(String link) {
        this.link = link;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    @Transient
    @Autowired
    private RestTemplate restTemplate;

    public boolean isExist() {
        ResponseEntity<String> result;
        try {
            result = restTemplate.getForEntity(link, String.class);
        } catch (ResourceAccessException e) {
            return false;
        }
        if (result.getStatusCode() == HttpStatus.OK) {
            return true;
        } else {
            return false;
        }
    }
}
