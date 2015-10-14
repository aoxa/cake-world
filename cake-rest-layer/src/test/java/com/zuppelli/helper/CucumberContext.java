package com.zuppelli.helper;

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.codehaus.jackson.map.ObjectMapper;

import javax.ws.rs.core.MediaType;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

public class CucumberContext {
    private static CucumberContext instance;
    private final Map<String, Object> content;
    private HttpClient client;
    private final ObjectMapper objectMapper;

    private CucumberContext() {
        content = new HashMap<>();
        client = HttpClients.createDefault();
        objectMapper = new ObjectMapper();
    }

    public static CucumberContext getInstance() {
        if ( null == instance )
            instance = new CucumberContext();
        return instance;
    }

    public <T> void add( String key, T value ) {
        this.content.put( key, value );
    }

    public <T> T get( String key ) {
        return ( T ) this.content.get( key );
    }

    public void reset() {
        this.content.clear();
        this.client = HttpClients.createDefault();
    }

    public HttpClient getClient() {
        return client;
    }

    public ObjectMapper getObjectMapper() {
        return objectMapper;
    }

    private HttpPost postStringEntity( String url, Object body ) throws UnsupportedEncodingException {
        HttpPost post = new HttpPost( url );
        post.setEntity( new StringEntity( body.toString() ) );
        post.setHeader( "Content-Type", MediaType.APPLICATION_JSON );
        return post;
    }

    public interface ContentKeys {
        String RELLENO_BASE = "relleno-base";
        String COBERTURA_BASE = "cobertura-base";
        String TORTA_URL = "torta-url";
        String LOGIN = "login";
        String USER_ID = "user-id";
        String CARRITO_URL = "carrito-url";
    }
}
