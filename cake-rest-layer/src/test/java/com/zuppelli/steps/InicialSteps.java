package com.zuppelli.steps;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.logging.Logger;

public class InicialSteps {
    private static Logger logger = Logger.getLogger( InicialSteps.class.getName() );
    public static final String RECURSO_COBERTURA = "http://localhost:8080/cake-world-rest/api/cobertura/";
    public static final String RECURSO_RELLENO = "http://localhost:8080/cake-world-rest/api/relleno/";
    public static final String RECURSO_TORTA = "http://localhost:8080/cake-world-rest/api/torta/";

    private JSONObject relleno;
    private JSONObject cobertura;
    private JSONObject torta;
    private HttpClient client;
    private String location;

    @Given( "^I am a user with an empty cart$" )
    public void empty_cart() {
        client = HttpClients.createDefault();
        logger.info( "Empty cart" );
    }

    @When( "^I order a two stories high cake$" )
    public void two_stories_cake() throws JSONException, IOException {
        logger.info( "Two stories cake" );

        HttpResponse response = client.execute( new HttpGet( RECURSO_RELLENO ) );
        // TODO pasar de json a entidad con jackson o jaxb

        JSONArray arr = new JSONArray( EntityUtils.toString( response.getEntity() ) );
        relleno = (JSONObject) arr.get( 0 );
        response = client.execute( new HttpGet( RECURSO_COBERTURA ) );
        arr = new JSONArray( EntityUtils.toString( response.getEntity() ) );
        cobertura = (JSONObject) arr.get( 0 );
    }

    @Then( "^I should enter the base filling$" )
    public void i_should_enter_the_base_filling() throws Throwable {
        JSONObject base = new JSONObject().put( "masa", "Marmolada" ).put( "relleno", relleno ).put( "peso", 3 );
        torta = new JSONObject().put( "base", base ).put( "cobertura", cobertura );
        logger.info( torta.toString() );

        HttpResponse response = client.execute( postStringEntity( RECURSO_TORTA, torta.toString() ) );
        logger.info( "Response: " + response.getStatusLine().getStatusCode() );
        location = response.getFirstHeader( "Location" ).getValue();
        response = client.execute( new HttpGet( location ) );
        logger.info( EntityUtils.toString( response.getEntity() ) );
    }

    @Then( "^I should enter a new floor$" )
    public void i_should_enter_a_new_floor() throws Throwable {
        JSONObject piso = new JSONObject().put( "masa", "Vainilla" ).put( "relleno", relleno ).put( "peso", 2.5 );
        HttpResponse response = client.execute( postStringEntity( location + "/piso", piso ) );
        logger.info( response.getFirstHeader( "Location" ).getValue() );
    }

    private HttpPost postStringEntity( String url, Object body ) throws UnsupportedEncodingException {
        HttpPost post = new HttpPost( url );
        post.setEntity( new StringEntity( body.toString() ) );
        post.setHeader( "Content-Type", MediaType.APPLICATION_JSON );
        return post;
    }
}
