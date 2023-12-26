package com.healthIQ.healthIQ.Models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "notification")
public class Notification {
    @Id
    @Column(nullable = false)
    private Long id;

    private String namesender;

    private String emailsender;
    private String content;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNamesender() {
        return namesender;
    }

    public void setNamesender(String namesender) {
        this.namesender = namesender;
    }

    public String getEmailsender() {
        return emailsender;
    }

    public void setEmailsender(String emailsender) {
        this.emailsender = emailsender;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Notification(Long id, String namesender, String emailsender, String content) {
        this.id = id;
        this.namesender = namesender;
        this.emailsender = emailsender;
        this.content = content;
    }

    public Notification() {
    }
}
