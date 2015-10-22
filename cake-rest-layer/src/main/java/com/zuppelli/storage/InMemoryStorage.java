package com.zuppelli.storage;

import com.zuppelli.cake.modelo.Entity;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Created by pedro.com.zuppelli on 07/10/2015.
 */
@Component
public class InMemoryStorage implements Storage{
    private Map<Class<?>, Map<Object, Object>> content = new HashMap<Class<?>, Map<Object, Object>>();
    private final Random random = new Random(  );

    @Override
    public <T> T store( Class<T> clazz, Entity entity ) {
        if ( null == entity.getId() ) {
            entity.setId( Math.abs( random.nextLong() ) );
        }
        Map<Object, Object> map = content.get( clazz );
        if ( null == map  ) {
            map = new HashMap<Object, Object>();
            content.put( clazz, map );
        }
        content.get( clazz ).put( entity.getId(), entity );
        return (T) entity;
    }

    @Override
    public <T, K> T retrieve( Class<T> clazz, K id ) {
        Map<Object, Object> map = content.get( clazz );
        if ( null != map ) {
            return (T) map.get( id );
        }
        return null;
    }

    @Override
    public <T> Collection<T> retrieve( Class<T> clazz) {
        Map<Object, Object> map = content.get( clazz );
        if ( null != map ) {
            return (Collection<T> )map.values();
        }
        return null;
    }

    @Override
    public <T, K> void delete( Class<T> clazz, K id ) {
        Map<Object, Object> map = content.get( clazz );
        if ( null != map ) {
            map.remove( id );
        }
    }
}
