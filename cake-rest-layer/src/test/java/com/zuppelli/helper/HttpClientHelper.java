package com.zuppelli.helper;

import com.zuppelli.cake.modelo.Entity;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.annotation.Immutable;
import org.apache.http.client.methods.*;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;

import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class HttpClientHelper {
    /* TODO: Host y port deben ser configurables en ejecucion. */
    private static final String HOST = "localhost";
    private static final String PORT = "5723";
    // private static final String APP = "cake-world-rest/";
    private static final String APP = "";

    public static final String RECURSO_COBERTURA = "http://HOST:PORT/" + APP + "api/cobertura/";
    public static final String RECURSO_RELLENO = "http://HOST:PORT/" + APP + "api/relleno/";
    public static final String RECURSO_TORTA = "http://HOST:PORT/" + APP + "api/torta/";
    public static final String RECURSO_TORTA_PISO = "http://HOST:PORT/" + APP + "api/torta/%s/piso";
    public static final String RECURSO_TORTA_POR_KILO = "http://HOST:PORT/" + APP + "api/torta/por_kilo";
    public static final String RECURSO_USUARIO = "http://HOST:PORT/" + APP + "api/commerce/user";
    public static final String RECURSO_CARRITO_USUARIO = "http://HOST:PORT/" + APP + "api/commerce/user/%s/carrito";
    public static final String RECURSO_CARRITO_USUARIO_TORTA = "http://HOST:PORT/" + APP + "api/commerce/user/%s/carrito/%s/torta";

    private static String buildUrl(String url ) {
        return url.replace( "HOST", getHost() ).replace( "PORT", getPort() );
    }

    private static String getHost() {
        String prop = System.getProperty( "server.host" );
        return StringUtils.isBlank( prop ) ? HOST : prop;
    }

    private static String getPort() {
        String prop = System.getProperty( "server.port" );
        return StringUtils.isBlank( prop ) ? PORT : prop;
    }

    public static HttpEntityEnclosingRequestBase postStringEntity( String url, Entity body, boolean update ) throws UnsupportedEncodingException {
        try {
            return postStringEntity( url, CucumberContext.getInstance().getObjectMapper().writeValueAsString( body ),
                    update );
        } catch ( Exception e ) {
            throw new UnsupportedEncodingException( e.getMessage() );
        }
    }

    public static HttpEntityEnclosingRequestBase postStringEntity( String url, Entity body ) throws UnsupportedEncodingException {
        try {
            return postStringEntity( url, body , null != body.getId() );
        } catch ( Exception e ) {
            throw new UnsupportedEncodingException( e.getMessage() );
        }

    }

    public static HttpEntityEnclosingRequestBase postStringEntity( String url, String body ) throws UnsupportedEncodingException {
        return postStringEntity( url, body, false );
    }

    public static HttpEntityEnclosingRequestBase postStringEntity( String url, String body, boolean update ) throws UnsupportedEncodingException {
        return postStringEntity( url, body, MediaType.APPLICATION_JSON, update );
    }

    public static HttpEntityEnclosingRequestBase postStringEntity( String url, Object body, String contentType ) throws UnsupportedEncodingException {
        return postStringEntity( url, body, contentType, false );
    }

    public static HttpEntityEnclosingRequestBase postStringEntity( String url, Object body, String contentType, boolean update ) throws UnsupportedEncodingException {
        HttpEntityEnclosingRequestBase post = update ? new HttpPut( buildUrl( url ) ) : new HttpPost( buildUrl( url ) );

        post.setEntity( new StringEntity( body.toString() ) );
        post.setHeader( "Content-Type", contentType );
        return post;
    }

    public static HttpGet getEntity( String url ) {
        return new HttpGet( buildUrl( url ) );
    }

    public static Response execute( HttpRequestBase request ) throws IOException {
        try{
            HttpResponse response = CucumberContext.getInstance().getClient().execute( request );
            return new Response( response, response.getEntity() );
        } finally {
            request.releaseConnection();
        }
    }

    @Immutable
    public static class Response {
        final private HttpResponse closedResponse;
        final private String entity;
        Response(HttpResponse response, HttpEntity entity) throws IOException {
            closedResponse = response;
            this.entity = null != entity? EntityUtils.toString( entity ) : null;
        }

        public String getEntity() {
            return entity;
        }

        public HttpResponse getClosedResponse() {
            return closedResponse;
        }
    }
}
