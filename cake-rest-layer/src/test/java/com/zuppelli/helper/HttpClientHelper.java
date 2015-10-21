package com.zuppelli.helper;

import com.zuppelli.cake.modelo.Entity;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.annotation.Immutable;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;

import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class HttpClientHelper {
    /* TODO: Host y port deben ser configurables en ejecucion. */
    private static final String HOST = "localhost";
    private static final String PORT = "5723";

    public static final String RECURSO_COBERTURA = "http://"+HOST+":"+PORT+"/cake-world-rest/api/cobertura/";
    public static final String RECURSO_RELLENO = "http://"+HOST+":"+PORT+"/cake-world-rest/api/relleno/";
    public static final String RECURSO_TORTA = "http://"+HOST+":"+PORT+"/cake-world-rest/api/torta/";
    public static final String RECURSO_TORTA_POR_KILO = "http://"+HOST+":"+PORT+"/cake-world-rest/api/torta/por_kilo";
    public static final String RECURSO_USUARIO = "http://"+HOST+":"+PORT+"/cake-world-rest/api/commerce/user";
    public static final String RECURSO_CARRITO_USUARIO = "http://"+HOST+":"+PORT+"/cake-world-rest/api/commerce/user/%s/carrito";

    public static HttpPost postStringEntity( String url, Entity body ) throws UnsupportedEncodingException {
        try {
            return postStringEntity( url, CucumberContext.getInstance().getObjectMapper().writeValueAsString( body ) );
        } catch ( Exception e ) {
            throw new UnsupportedEncodingException( e.getMessage() );
        }

    }

    public static HttpPost postStringEntity( String url, String body ) throws UnsupportedEncodingException {
        return postStringEntity( url, body, MediaType.APPLICATION_JSON );
    }

    public static HttpPost postStringEntity( String url, Object body, String contentType ) throws UnsupportedEncodingException {
        HttpPost post = new HttpPost( url );
        post.setEntity( new StringEntity( body.toString() ) );
        post.setHeader( "Content-Type", contentType );
        return post;
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
