package oal.oracle.apps.epm.utils.IntegrationTest;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.client.Client;

import javax.ws.rs.client.ClientBuilder;

import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;

import javax.ws.rs.core.MultivaluedHashMap;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;


import oal.oracle.apps.epm.entities.CustomResponse;
import oal.oracle.apps.epm.entities.Employees;

import oal.oracle.apps.epm.entities.Order_Header;

import oal.oracle.apps.epm.entities.Order_Lines;

import org.eclipse.persistence.jpa.jpql.Assert;

import org.glassfish.jersey.client.ClientConfig;

import sun.misc.BASE64Encoder;

import org.junit.Test;


public class EntityBOImplIntTest {
        
    static {
    javax.net.ssl.HttpsURLConnection.setDefaultHostnameVerifier(
    new javax.net.ssl.HostnameVerifier(){

        public boolean verify(String hostname,
                javax.net.ssl.SSLSession sslSession) {
            if (hostname.equals("localhost")) {
                return true;
            }
            return false;
        }
    });
    }
    
    private String url;
    private Client restClient;
    private MultivaluedMap<String,Object> map;

//@Before    
    public EntityBOImplIntTest(){
        url ="https://localhost:7102/Utils/rest"; 
        String name = "sourav";
        String password = "souravP5@";
        String authString = name + ":" + password;
        String authStringEnc = new BASE64Encoder().encode(authString.getBytes());
        map=new MultivaluedHashMap<String,Object>();
        map.putSingle("Authorization","Basic " + authStringEnc);
        map.putSingle("Accept-Language","en-US");
        map.putSingle("Content-Type","application/json");
        restClient=ClientBuilder.newClient(new ClientConfig());
    }
//    @Test
    public void testGetDataById() throws Exception{

    WebTarget webTarget = restClient.target(url).path("/Order_Header").path("/284");
    Response response =webTarget.request().accept("application/json").headers(map).get();
    //Assert.assertEquals(response.getStatus(),200);
    System.out.println("Status:"+response.getStatus()); 
    MultivaluedMap<String,Object> headers=response.getHeaders();
    for(String s:headers.keySet()){
        System.out.println(s+":"+headers.get(s));
    }
    String resObj=response.readEntity(String.class);
    System.out.println(resObj);
    }
    
//    @Test
    public void testGetData() throws Exception{
        WebTarget webTarget = restClient.target(url).path("/Order_Header").queryParam("orderNumber",207);
        Response response =webTarget.request().accept("application/json").headers(map).get();
        //Assert.assertEquals(response.getStatus(),200);
        System.out.println("Status:"+response.getStatus()); 
        MultivaluedMap<String,Object> headers=response.getHeaders();
        for(String s:headers.keySet()){
            System.out.println(s+":"+headers.get(s));
        }
        String resObj=response.readEntity(String.class);
        System.out.println(resObj);
    }
//    @Test
    public void testSetData() throws Exception{

        WebTarget webTarget = restClient.target(url).path("/Order_Lines");
        ObjectMapper mapperObj = new ObjectMapper();
        Map<String, Object> inputMap = new HashMap<String, Object>();
        inputMap.put("orderLineId", 105);
        inputMap.put("quantity", 100);
        String payload = mapperObj.writeValueAsString(inputMap);
        Response response =webTarget.request().accept("application/json").headers(map).post(Entity.entity(payload, "application/json"));
        //Assert.assertEquals(response.getStatus(),200);
        System.out.println("Status:"+response.getStatus()); 
        MultivaluedMap<String,Object> headers=response.getHeaders();
        for(String s:headers.keySet()){
            System.out.println(s+":"+headers.get(s));
        }
        String resObj=response.readEntity(String.class);
        System.out.println(resObj);
    }
    
    
    public static void main(String[] args) throws Exception {
        EntityBOImplIntTest t1=new EntityBOImplIntTest();
        t1.testGetDataById();
        t1.testGetData();
        t1.testSetData();
    }
}
