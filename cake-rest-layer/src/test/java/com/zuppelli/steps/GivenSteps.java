package com.zuppelli.steps;

import com.zuppelli.cake.modelo.Cobertura;
import com.zuppelli.cake.modelo.Relleno;
import com.zuppelli.helper.CucumberContext;
import com.zuppelli.helper.HttpClientHelper;
import cucumber.api.java.en.Given;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.util.EntityUtils;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;

import javax.ws.rs.core.MediaType;
import java.util.logging.Logger;

public class GivenSteps
{
    private static Logger logger = Logger.getLogger( GivenSteps.class.getName() );

    @Given("^I am a user using '(.+)' price '(\\d+)' and '(.+)' price '(\\d+)' and '(\\d+)'$")
    public void i_am_a_user_using_cobertura_and_relleno(String cobertura, Long coberturaPrecio, String relleno, Long rellenoPrecio, Double porKilo) throws Throwable {
        CucumberContext context = CucumberContext.getInstance();
        HttpClient client = context.getClient();

        Cobertura cbt = new Cobertura();
        cbt.setTipo( cobertura );
        cbt.setPrecio( coberturaPrecio );

        Relleno rel = new Relleno();
        rel.setTipo( relleno );
        rel.setPrecio( rellenoPrecio );

        HttpResponse response = client.execute( HttpClientHelper.postStringEntity( HttpClientHelper.RECURSO_TORTA+"por_kilo", porKilo, MediaType.TEXT_PLAIN ) );
        logger.info( ""+response.getStatusLine().getStatusCode() );
        response = client.execute( HttpClientHelper.postStringEntity( HttpClientHelper.RECURSO_COBERTURA,
                context.getObjectMapper().writeValueAsString( cbt ) ) );
        logger.info( ""+response.getStatusLine().getStatusCode() );
        response = client.execute( HttpClientHelper.postStringEntity( HttpClientHelper.RECURSO_RELLENO,
                context.getObjectMapper().writeValueAsString( rel ) ) );
        logger.info( ""+response.getStatusLine().getStatusCode() );

    }
    //@Given("^I am a user using '(.+)' and '(.+)'$")
    public void i_am_a_user_using_cobertura_and_relleno(String cobertura, String relleno) throws Throwable {
        CucumberContext context = CucumberContext.getInstance();
        HttpClient client = context.getClient();
        HttpResponse response = client.execute( new HttpGet( HttpClientHelper.RECURSO_COBERTURA ) );
        JSONArray array = new JSONArray( EntityUtils.toString( response.getEntity() ) );
        boolean found = false;

        for (int i =0; i < array.length() && !found; i++ ) {
            JSONObject object = array.getJSONObject( i );

            if( object.getString( "tipo" ).equalsIgnoreCase( cobertura )) {
                context.add( "cobetura", context.getObjectMapper().readValue( object.toString(), Cobertura.class ) );
                found=true;
            }
        }

        response = client.execute( new HttpGet( HttpClientHelper.RECURSO_RELLENO ) );
        array = new JSONArray( EntityUtils.toString( response.getEntity() ) );
        found = false;

        for (int i =0; i < array.length() && !found; i++ ) {
            JSONObject object = array.getJSONObject( i );
            if( object.getString( "tipo" ).equalsIgnoreCase( relleno )) {
                found = true;
                CucumberContext.getInstance().add( "relleno", object );
            }
        }
    }
}
