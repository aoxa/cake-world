package com.zuppelli.steps;

import com.zuppelli.helper.HttpClientHelper;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumber.api.java.es.Cuando;
import cucumber.api.java.es.Dado;
import cucumber.api.java.es.Entonces;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import java.io.IOException;
import java.util.logging.Logger;

public class InicialSteps {
    private static Logger logger = Logger.getLogger( InicialSteps.class.getName() );

    private JSONObject relleno;
    private JSONObject cobertura;
    private JSONObject torta;
    private HttpClient client;
    private String location;

    @Given( "^I am a user with an empty cart$" )
    @Dado("^que soy un usuario con el carrito vacio$")
    public void empty_cart() {
        client = HttpClients.createDefault();
        logger.info( "Empty cart" );
    }

    @When( "^I order a two stories high cake$" )
    @Cuando( "^quiero ordenar una torta de dos pisos$" )
    public void two_stories_cake() throws JSONException, IOException {
        logger.info( "Two stories cake" );

        HttpResponse response = client.execute( new HttpGet( HttpClientHelper.RECURSO_RELLENO ) );
        // TODO pasar de json a entidad con jackson o jaxb

        JSONArray arr = new JSONArray( EntityUtils.toString( response.getEntity() ) );
        relleno = arr.getJSONObject( 0 );
        response = client.execute( new HttpGet( HttpClientHelper.RECURSO_COBERTURA ) );
        arr = new JSONArray( EntityUtils.toString( response.getEntity() ) );
        cobertura = arr.getJSONObject( 0 );
    }

    @Then( "^I should enter the base filling$" )
    @Entonces("^Debo ingresar el relleno$")
    public void i_should_enter_the_base_filling() throws Throwable {
        JSONObject base = new JSONObject().put( "masa", "Marmolada" ).put( "relleno", relleno ).put( "peso", 3 );
        torta = new JSONObject().put( "base", base ).put( "cobertura", cobertura );
        logger.info( torta.toString() );

        HttpResponse response = client.execute( HttpClientHelper.postStringEntity( HttpClientHelper.RECURSO_TORTA, torta.toString() ) );
        logger.info( "Response: " + response.getStatusLine().getStatusCode() );
        location = response.getFirstHeader( "Location" ).getValue();
        response = client.execute( new HttpGet( location ) );
        logger.info( EntityUtils.toString( response.getEntity() ) );
    }

    @Then( "^I should enter a new floor$" )
    @Entonces("^Debo ingresar un piso$")
    public void i_should_enter_a_new_floor() throws Throwable {
        JSONObject piso = new JSONObject().put( "masa", "Vainilla" ).put( "relleno", relleno ).put( "peso", 2.5 );
        HttpResponse response = client.execute( HttpClientHelper.postStringEntity( location + "/piso", piso.toString() ) );
        logger.info( response.getFirstHeader( "Location" ).getValue() );
    }
}
