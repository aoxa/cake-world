package com.zuppelli.cake.config;

import java.util.HashMap;
import java.util.Map;

public class ConfigHelper {
    interface ConfigKey {
        String getKey();
    }

    public enum Keys implements ConfigKey {
        PRECIO_POR_KILO( "PRECIO_POR_KILO" );
        String key;

        Keys( String key ) {
            this.key = key;
        }

        @Override
        public String getKey() {
            return key;
        }

    }

    private final Map<ConfigKey, Object> configs;

    private static ConfigHelper instance;

    private ConfigHelper() {
        this.configs = new HashMap<ConfigKey, Object>();
        configs.put( Keys.PRECIO_POR_KILO, 107d );
    }

    /**
     * Devuelve una instancia de la configuracion.
     * @return
     *          - La configuracion.
     */
    public static ConfigHelper getInstance() {
        if ( null == instance ) {
            synchronized ( ConfigHelper.class ) {
                if ( null == instance ) {
                    instance = new ConfigHelper();
                }
            }
        }
        return instance;
    }

    /**
     * Agrega la configuracion.
     * @param key - la llave de la configuracion.
     * @param value - el valor de la configuracion.
     * @param <T> - El tipo de configuracion.
     */
    public <T> void put( ConfigKey key, T value ) {
        this.configs.put( key, value );
    }

    /**
     * Devuelve el valor de una configuracion.
     * @param key - la llave de la configuracion.
     * @param <T> - El tipo de configuracion.
     * @return El valor configurado.
     */
    public <T> T get( ConfigKey key ) {
        try {
            T value = ( T ) configs.get( key );
            if ( null == value ) {
                throw new MissconfigurationException( "not configured." );
            }
            return value;
        } catch ( ClassCastException ex ) {
            throw new MissconfigurationException();
        }
    }
}
