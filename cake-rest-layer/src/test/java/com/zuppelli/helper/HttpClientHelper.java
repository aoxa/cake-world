package com.zuppelli.helper;

import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;

import javax.ws.rs.core.MediaType;
import java.io.UnsupportedEncodingException;

public class HttpClientHelper {
    public static final String RECURSO_COBERTURA = "http://localhost:8080/cake-world-rest/api/cobertura/";
    public static final String RECURSO_RELLENO = "http://localhost:8080/cake-world-rest/api/relleno/";
    public static final String RECURSO_TORTA = "http://localhost:8080/cake-world-rest/api/torta/";
    public static final String RECURSO_TORTA_POR_KILO = "http://localhost:8080/cake-world-rest/api/torta/por_kilo";

    public static HttpPost postStringEntity( String url, Object body ) throws UnsupportedEncodingException {
        return postStringEntity( url, body, MediaType.APPLICATION_JSON );
    }
    public static HttpPost postStringEntity( String url, Object body, String contentType ) throws UnsupportedEncodingException {
        HttpPost post = new HttpPost( url );
        post.setEntity( new StringEntity( body.toString() ) );
        post.setHeader( "Content-Type", contentType );
        return post;
    }
}
