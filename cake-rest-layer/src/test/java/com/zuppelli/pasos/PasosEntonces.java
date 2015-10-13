package com.zuppelli.pasos;

import com.zuppelli.helper.CucumberContext;
import cucumber.api.java.es.Entonces;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.util.EntityUtils;
import org.codehaus.jettison.json.JSONObject;

import static org.junit.Assert.assertEquals;

/**
 * Created by pedro.zuppelli on 13/10/2015.
 */
public class PasosEntonces {
    @Entonces("^debo pagar '(\\d+)'$")
    public void debo_pagar(Double esperado) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        String location = CucumberContext.getInstance().get( CucumberContext.ContentKeys.TORTA_URL );
        HttpResponse response = CucumberContext.getInstance().getClient().execute( new HttpGet( location ) );
        JSONObject result = new JSONObject( EntityUtils.toString( response.getEntity() ) );
        Double actual = result.getDouble( "precio" );
        assertEquals( "Los valores esperado y el corriente son distintos.", esperado, actual );
    }
}
