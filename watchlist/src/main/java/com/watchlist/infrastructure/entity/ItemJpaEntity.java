package com.watchlist.infrastructure.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.UUID;

@Entity
@Table(
        name = "watchlist_items",
        uniqueConstraints = {
                @UniqueConstraint(name = "uq_watchlist_items", columnNames = {"watchlist_id", "asset_id"})
        }
)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ItemJpaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "watchlist_id", nullable = false)
    private WatchlistJpaEntity watchlist;

    // CHỈ LƯU ID CỦA ASSET, KHÔNG LƯU OBJECT ASSET
    @Column(name = "asset_id", nullable = false)
    private UUID assetId;

    @Column(name = "display_order", nullable = false)
    @Builder.Default
    private Short displayOrder = 0;

    @Column(name = "user_notes", columnDefinition = "TEXT")
    private String userNotes;
}