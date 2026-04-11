package com.watchlist.infrastructure.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "watchlists")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class WatchlistJpaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    // CHỈ LƯU ID CỦA USER, KHÔNG LƯU OBJECT USER
    @Column(name = "user_id", nullable = false)
    private UUID userId;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Builder.Default
    @Column(name = "is_default", nullable = false)
    private boolean isDefault = true;

    @OneToMany(mappedBy = "watchlist", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ItemJpaEntity> itemJpaEntityList;
}