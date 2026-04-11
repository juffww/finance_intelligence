package com.watchlist.domain.entity;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Item {
    private UUID id;
    private UUID assetId;
    private UUID watchlistId;
    @Builder.Default
    private Short displayOrder = 0;
    private String userNotes;
}