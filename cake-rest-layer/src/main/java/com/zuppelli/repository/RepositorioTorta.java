package com.zuppelli.repository;

import com.zuppelli.cake.modelo.Torta;
import com.zuppelli.storage.Storage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;

/**
 * Created by pedro.com.zuppelli on 07/10/2015.
 */
@Component
@Deprecated
public class RepositorioTorta implements Repository<Torta, Long> {
    @Autowired private Storage storage;

    @Override
    public Torta store( Torta entity ) {
        storage.store( Torta.class, entity);

        return entity;
    }

    @Override
    public void remove( Long id ) {
        storage.delete( Torta.class, id );
    }

    @Override
    public Torta retrieve( Long id ) {
        return storage.retrieve( Torta.class, id );
    }

    @Override
    public Collection<Torta> retrieve() {
        return storage.retrieve( Torta.class );
    }
}
