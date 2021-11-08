package com.flameshine.app.util;

import org.springframework.data.domain.Page;

import com.flameshine.app.entity.Persistable;

/**
 * An auxiliary utility for items that need pagination.
 */

public record Pager(Page<? extends Persistable> items) {

    public boolean hasNext() {
        return items.hasNext();
    }

    public boolean hasPrevious() {
        return items.hasPrevious();
    }

    public int getTotalPages() {
        return items.getTotalPages();
    }
}