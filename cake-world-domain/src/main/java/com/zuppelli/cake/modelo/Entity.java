package com.zuppelli.cake.modelo;

public abstract class Entity {
    private Long id;

    public final Long getId() {
        return this.id;
    }

    public final void setId( Long id ) {
        this.id = id;
    }
}
