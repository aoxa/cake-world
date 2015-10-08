package com.zuppelli.repository;

import com.zuppelli.cake.modelo.Torta;
import com.zuppelli.storage.Storage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Random;

/**
 * Created by pedro.zuppelli on 07/10/2015.
 */
@Component
public class RepositorioTorta implements Repository<Torta, Long> {
    @Autowired
    private Storage storage;

    private final Random random = new Random(  );

    @Override
    public Torta store( Torta entity ) {
        entity.setId( random.nextLong() );
        storage.store( Torta.class, entity, entity.getId() );

        return entity;
    }

    @Override
    public void remove( Torta entity ) {
        storage.delete( Torta.class, entity );
    }

    @Override
    public Torta retrieve( Long id ) {
        return storage.retrieve( Torta.class, id );
    }
}
