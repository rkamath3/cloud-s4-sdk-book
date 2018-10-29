package com.sap.cloud.s4hana.examples.addressmgr;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import com.sap.cloud.sdk.cloudplatform.connectivity.ProxyConfiguration;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import java.net.URL;

import com.sap.cloud.sdk.testutil.MockUtil;

import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.*;

@RunWith( Arquillian.class )
public class BusinessPartnerServletTest
{
    private static final MockUtil mockUtil = new MockUtil();
    public static final String BUPA_ID = "1003764";

    @ArquillianResource
    private URL baseUrl;

    @Deployment
    public static WebArchive createDeployment()
    {
        return TestUtil.createDeployment(BusinessPartnerServlet.class);
    }

    @BeforeClass
    public static void beforeClass()
    {
        mockUtil.mockDefaults();
    }

    @Before
    public void before()
    {
        RestAssured.baseURI = baseUrl.toExternalForm();
    }

    @Test
    public void testGetAll()
    {
        when()
                .get("/api/business-partners")
        .then().body(not(isEmptyOrNullString()));
    }

    @Test
    public void testGetSingle() {
        when()
                .get("/api/business-partners?id={id}", BUPA_ID)
        .then().body(not(isEmptyOrNullString()));
    }
}
