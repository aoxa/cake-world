package com.zuppelli.cake.modelo;

/**
 * Created by pedro.zuppelli on 07/10/2015.
 */

public interface Entity<T> {
    T getId();
    void setId(T id);
}
