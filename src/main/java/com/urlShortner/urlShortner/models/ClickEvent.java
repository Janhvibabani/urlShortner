package com.urlShortner.urlShortner.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "clickEvent")
public class ClickEvent {
    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime clickDate;

    @ManyToOne
    @JoinColumn(name = "url_mapping_id")
    private UrlMapping urlMapping;
}
