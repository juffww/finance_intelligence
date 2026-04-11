package com.watchlist.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Watchlist {

    private UUID id;
    private UUID userId;
    private String name;
    private String description;
    @Builder.Default
    private boolean isDefault = true;
    @Builder.Default
    private List<Item> itemList = new ArrayList<>();

    public void addItem(Item item)
    {
        if (this.itemList == null) {
            this.itemList = new ArrayList<>();
        }

        itemList.add(item);
    }

    public void changeDefault(boolean newDefault)
    {
        this.isDefault = newDefault;
    }
}