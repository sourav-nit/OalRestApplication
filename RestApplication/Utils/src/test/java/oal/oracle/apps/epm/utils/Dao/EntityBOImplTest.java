package oal.oracle.apps.epm.utils.Dao;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;

import java.io.IOException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import java.util.Map;

import javax.naming.NamingException;

import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;

import javax.ws.rs.core.MultivaluedHashMap;
import javax.ws.rs.core.MultivaluedMap;

import javax.ws.rs.core.UriInfo;

import oal.oracle.apps.epm.entities.BaseEntity;
import oal.oracle.apps.epm.entities.Employees;
import oal.oracle.apps.epm.entities.Order_Header;
import oal.oracle.apps.epm.entities.Order_Lines;
import oal.oracle.apps.epm.utils.Dao.EntityDao;
import oal.oracle.apps.epm.utils.service.EntityBOImpl;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import static org.mockito.Mockito.mock;
import static org.mockito.Matchers.any;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class EntityBOImplTest {
    
    private EntityBOImpl entitybo_impl;
    private Employees emp1;
    private Employees emp2;
    private List<BaseEntity> beList=new ArrayList<BaseEntity>();
    private List<BaseEntity> beList1=new ArrayList<BaseEntity>();
    private List<BaseEntity> beList2=new ArrayList<BaseEntity>();
    List<Order_Lines> olList=new ArrayList<>();
    private Order_Lines ol_out=new Order_Lines();
    private Order_Header oh_out=new Order_Header();
    private UriInfo mockUriInfo;

    @Before
    public void setup() throws NamingException, ParseException, NotSupportedException, SystemException,
                               RollbackException, HeuristicMixedException, HeuristicRollbackException {


        EntityDao entdao=mock(EntityDao.class);
        mockUriInfo = mock(UriInfo.class);
        
        MultivaluedMap<String,String> map=new MultivaluedHashMap<String,String>();
        map.putSingle("offset","0");
        map.putSingle("limit","2");

        
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date2=dateFormat.parse("03/06/1993");        
        //Employees 1st Object
        emp1=new Employees(0,"Test",1001,"Hello",date2,"AD_VP","End","590.423.4567",1000,100);
        emp1.setCreationDate(date2);
        emp1.setLastUpdatedDate(date2);
        emp1.setLastUpdatedBy("Oracle");
        emp1.setCreatedBy("Oracle");
        
        //Employees 2nd Object
        emp2=new Employees(0,"Test2",1003,"Hi",date2,"AD_VP","End","590.423.4567",1000,100);
        emp2.setCreationDate(date2);
        emp2.setLastUpdatedDate(date2);
        emp2.setLastUpdatedBy("Oracle");
        emp2.setCreatedBy("Oracle");
        
        beList.add(emp1);
        beList1.add(emp1);
        beList.add(emp2);
        
        
        //Order_lines Object
        ol_out.setOrderLineId(201);
        ol_out.setLineNumber(903);
        ol_out.setQuantity(75);
        ol_out.setProductId(85);
        ol_out.setProductName("Kurkure");
        ol_out.setSellingPrice(20);
        ol_out.setEndDate("02/10/2017");
        ol_out.setStartDate("02/09/2017");
        ol_out.setCreationDate(date2);
        ol_out.setLastUpdatedDate(date2);
        ol_out.setLastUpdatedBy("Oracle");
        ol_out.setCreatedBy("Oracle");
        
        olList.add(ol_out);
        //Order_Header Object
        oh_out.setOrderHeaderId(200);
        oh_out.setOrderNumber(207);
        oh_out.setBookedDate("01/07/2017");
        oh_out.setOrderValue(1300);
        oh_out.setStatus("OPEN");
        oh_out.setCustomerId(12);
        oh_out.setOrderliness(olList);
        oh_out.setCreationDate(date2);
        oh_out.setLastUpdatedDate(date2);
        oh_out.setLastUpdatedBy("Oracle");
        oh_out.setCreatedBy("Oracle");
        
        beList2.add(oh_out);
        
        //Injecting the Dummy implementation of EmployeeDao to get business class output
        entitybo_impl=new EntityBOImpl(entdao);


        //Mocking the Employee Dao Methods
        Mockito.when(mockUriInfo.getQueryParameters()).thenReturn(map);
        Mockito.when(entdao.find(1001)).thenReturn(emp1);
        Mockito.when(entdao.find(1003)).thenReturn(emp2);
        Mockito.when(entdao.getData(0,2,map)).thenReturn(beList);
        Mockito.when(entdao.create(any(BaseEntity.class))).thenReturn(oh_out);

    }
    
    @Test
    public void testGetDataById(){  
        
        Assert.assertEquals(entitybo_impl.getDataById("Employees", 1001,"en-US").getData(),beList1);
        Assert.assertEquals(entitybo_impl.getDataById("Employees", 1002,"en-US").getError().getErrorMessage(),"Incorrect id for given entity type.");
        Assert.assertEquals(entitybo_impl.getDataById("Employees", 1001,"en-US").getStatus(),"Success");
        Assert.assertEquals(entitybo_impl.getDataById("Employees", 1002,"en-US").getStatus(),"Error");
        Assert.assertNotEquals(entitybo_impl.getDataById("Employees",1002,"en-US").getData(),beList1);
        Assert.assertEquals(entitybo_impl.getDataById("Employees",null,"en-US").getData(),null);
    }

    @Test
    public void testGetData(){
       
        Assert.assertEquals(entitybo_impl.getData("Employees",mockUriInfo,"en-US").getData(),beList);
        Assert.assertEquals(entitybo_impl.getData("Employees",mockUriInfo,"en-US").getStatus(),"Success");
        //Assert.assertEquals(entitybo_impl.getData("Employees","offset=0&limit=-5","en-US").getStatus(),"Error");
        //Assert.assertEquals(entitybo_impl.getData("Employees","offset=0&limit=-5","en-US").getError().getErrorMessage(),"Offset or Limit value is less than 0.");
        
    }
    
    
    @Test
    public void testSetData() throws IOException {
       //Files.lines(Paths.get(System.getProperty("user.dir")+"//src//main//java//oal//oracle//apps//epm//utils//MockitoTest//jsondata.json")).forEach(s->{str.append(s);str.append("\n");});
        ObjectMapper mapper = new ObjectMapper();
        Map<String,Object> dataMap = mapper.readValue(new File(System.getProperty("user.dir")+"//src//main//java//oal//oracle//apps//epm//utils//MockitoTest//jsondata.json"), Map.class);
        List<BaseEntity> res=entitybo_impl.setData("Order_Header",dataMap,"en-US").getData();
        Assert.assertEquals(res,beList2);
    }    
}
