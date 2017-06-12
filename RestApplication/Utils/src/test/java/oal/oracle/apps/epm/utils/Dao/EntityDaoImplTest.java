package oal.oracle.apps.epm.utils.Dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Date;

import java.util.List;

import javax.naming.NamingException;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;

import javax.transaction.UserTransaction;

import javax.ws.rs.core.MultivaluedHashMap;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.UriInfo;

import oal.oracle.apps.epm.entities.BaseEntity;
import oal.oracle.apps.epm.entities.Employees;
import oal.oracle.apps.epm.utils.Dao.EntityDao;
import oal.oracle.apps.epm.utils.Dao.EntityDaoImpl;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.mockito.Mockito;
import static org.mockito.Mockito.mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class EntityDaoImplTest {
    
    private Employees emp1;
    private Employees emp2;
    private EntityDao employeedao;
    private List<BaseEntity> beList=new ArrayList<BaseEntity>();
    private MultivaluedMap<String,String> map;
    
    @Before
    public void setup() throws NamingException, ParseException {
        
        //Dummy Implemetation of EmployeeDao class to check whether we have correct output or not for business class
        EntityManager em=mock(EntityManager.class);
        Query mockedQuery = mock(Query.class);
        UserTransaction transaction=mock(UserTransaction.class);
        
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date2=dateFormat.parse("03/06/1993");
        
        map=new MultivaluedHashMap<String,String>();
        map.putSingle("offset","0");
        map.putSingle("limit","2");
        
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
        beList.add(emp2);
        
        //Injecting the Dummy implementation of EmployeeDao to get business class output
        employeedao=new EntityDaoImpl(em,transaction);
        employeedao.setEntity(Employees.class);

        //Mocking the Employee Dao Methods
        Mockito.when(em.find(Employees.class,1001)).thenReturn(emp1);
        Mockito.when(em.find(Employees.class,1003)).thenReturn(emp2);
        Mockito.when(em.createQuery("SELECT e FROM Employees e")).thenReturn(mockedQuery);
        Mockito.when(mockedQuery.setFirstResult(0)).thenReturn(mockedQuery);
        Mockito.when(mockedQuery.setMaxResults(2)).thenReturn(mockedQuery);
        Mockito.when(mockedQuery.getResultList()).thenReturn(beList);
        Mockito.when(em.merge((BaseEntity)emp1)).thenReturn(emp1);

    }
    
    @Test
    public void testFind(){  
        Assert.assertEquals(employeedao.find(1001),emp1);
        Assert.assertEquals(employeedao.find(1004),null);
    }
    
    @Test
    public void testUpdate() throws NotSupportedException, SystemException, RollbackException, HeuristicMixedException,
                                    HeuristicRollbackException {  
        Assert.assertEquals(employeedao.update((BaseEntity)emp1),emp1);
        Assert.assertNotEquals(employeedao.update(emp1),emp2);
    }
    
    @Test
    public void testCreate() throws NotSupportedException, SystemException, RollbackException, HeuristicMixedException,
                                    HeuristicRollbackException {  
        Assert.assertEquals(employeedao.create((BaseEntity)emp1),emp1);
        Assert.assertNotEquals(employeedao.create((BaseEntity)emp1),emp2);
    }
    
    @Test
    public void testGetData() throws NotSupportedException, SystemException, RollbackException, HeuristicMixedException,
                                    HeuristicRollbackException {  
        Assert.assertEquals(employeedao.getData(0,2,map),beList);
    }

}

