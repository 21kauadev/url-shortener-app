package com.kauadev.url_shortener_app.domain.link;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import com.kauadev.url_shortener_app.domain.user.User;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Link {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // é uma boa prática converter o padrão camelCase (em código, na classe)
    // pro padrão snake_case (pro nome da coluna, no banco de dados)

    @Column(name = "original_url")
    private String originalUrl;

    @Column(name = "short_code")
    private String shortCode;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "expires_at")
    private LocalDateTime expiresAt;

    @Column(name = "click_count")
    private Integer clickCount = 0;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public Link() {
    }

    public Link(String originalUrl, String shortCode, LocalDateTime expiresAt, Integer clickCount, User user) {
        this.originalUrl = originalUrl;
        this.shortCode = shortCode;
        this.expiresAt = LocalDateTime.now().plusDays(7);
        this.clickCount = clickCount;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public String getOriginalUrl() {
        return originalUrl;
    }

    public void setOriginalUrl(String originalUrl) {
        this.originalUrl = originalUrl;
    }

    public String getShortCode() {
        return shortCode;
    }

    public void setShortCode(String shortCode) {
        this.shortCode = shortCode;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getExpiresAt() {
        return expiresAt;
    }

    public Integer getClickCount() {
        return clickCount;
    }

    public void setClickCount(Integer clickCount) {
        this.clickCount = clickCount;
    }

}
