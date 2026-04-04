package com.finance_intelligence.domain.watchlist.entity;

import com.finance_intelligence.domain.asset.entity.Asset;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "watchlist_id", nullable = false)
    private Watchlist watchlist;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "asset_id", nullable = false)
    private Asset asset;

    @Column(name = "display_order", nullable = false)
    @Builder.Default
    private Short displayOrder = 0;

    @Column(name = "user_notes", columnDefinition = "TEXT")
    private String userNotes;
}
