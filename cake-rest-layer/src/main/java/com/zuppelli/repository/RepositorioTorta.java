package com.zuppelli.repository;

import com.zuppelli.cake.modelo.Torta;
import com.zuppelli.storage.InMemoryStorage;
import com.zuppelli.storage.Storage;

import java.util.Random;

/**
 * Created by pedro.zuppelli on 07/10/2015.
 */
public class RepositorioTorta implements Repository<Torta, Long> {
    private Storage storage = new InMemoryStorage();
    private Random random = new Random(  );

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
