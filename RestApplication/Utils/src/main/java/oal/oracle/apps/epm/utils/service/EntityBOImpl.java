package oal.oracle.apps.epm.utils.service;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.naming.NamingException;

import javax.persistence.Id;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;


import javax.ws.rs.core.Context;

import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.UriInfo;

import oal.oracle.apps.epm.entities.BaseEntity;
import oal.oracle.apps.epm.entities.CustomResponse;
import oal.oracle.apps.epm.entities.ErrorMessage;
import oal.oracle.apps.epm.entities.Meta;
import oal.oracle.apps.epm.utils.Dao.EntityDao;
import oal.oracle.apps.epm.utils.Dao.EntityDaoImpl;
import oal.oracle.apps.epm.utils.Exception.MyLocalizedThrowable;



//This is the main business class which will be run by rest api
@Path("/{Entity}")
public class EntityBOImpl {
    
    private Class entity;
    private final EntityDao dao;
        
    public EntityBOImpl() throws NamingException {
        dao=new EntityDaoImpl();
    }
    
    public EntityBOImpl(EntityDao dao) throws NamingException {
        this.dao=dao;
    }
    private  void setEntityDao(String en) throws ClassNotFoundException {
            entity=Class.forName("oal.oracle.apps.epm.entities."+en);
            dao.setEntity(entity);
    }

    @GET
    @Path("/{id}")
    @Produces("application/json")
    public CustomResponse getDataById(@PathParam("Entity") String ent,@PathParam("id") Integer pk,@HeaderParam("Accept-Language") String lang){
        CustomResponse cust;
        try{
            setEntityDao(ent);
            List<BaseEntity> beList=new ArrayList<BaseEntity>();
            BaseEntity be=dao.find(pk);
            if(be==null){
                    //GenericException e=new GenericException("ID_NOT_FOUND");
                    MyLocalizedThrowable ex= new MyLocalizedThrowable("ID_NOT_FOUND");
                    ex.setLang(lang);
                    throw ex;
            }
            beList.add(be);
            cust=new CustomResponse("Success",null,null,beList);
        }catch(MyLocalizedThrowable e){
            ErrorMessage err=new ErrorMessage(e.getLocalizedMessage(),e.getMessage());
            cust=new CustomResponse("Error",err,null,null); 
        }catch(Exception e){
            ErrorMessage err=new ErrorMessage(e.toString(),e.getMessage());
            cust=new CustomResponse("Error",err,null,null);  
        }finally{
            dao.closeEntityManager();
        }
        return cust;
    }
    
    /*
    @GET
    @Path("/")
    @Produces("application/json")
    public CustomResponse getData(@PathParam("Entity") String ent,@DefaultValue("0")@QueryParam("offset") int offset,
                                  @DefaultValue("10")@QueryParam("limit") int limit,
                                  @DefaultValue("null")@QueryParam("OrderNumber") String ORDR_NUMBER,
                                  @DefaultValue("null")@QueryParam("status") String STATUS,
                                  @HeaderParam("Accept-Language") String lang) {
        CustomResponse cust;
        Meta meta=null;
        try{
            HashMap<String,String> queryData=new HashMap<String,String>();
            if(offset<0 || limit<0){
                MyLocalizedThrowable ex= new MyLocalizedThrowable("OFFSET_ERROR");
                ex.setLang(lang);
                throw ex;
            }
            meta=new Meta(offset,limit);
            setEntityDao(ent);
            if(!ORDR_NUMBER.equals("null")){
                if(!ent.equals("Order_Header")){
                        MyLocalizedThrowable ex= new MyLocalizedThrowable("INVALID_FIELD");
                        ex.setLang(lang);
                        throw ex;
                }
                queryData.put("orderNumber",ORDR_NUMBER);
            }
            if(!STATUS.equals("null")){
                if(!ent.equals("Order_Header")){
                        MyLocalizedThrowable ex= new MyLocalizedThrowable("INVALID_FIELD");
                        ex.setLang(lang);
                        throw ex;
                }
                queryData.put("status",STATUS);
            }
            if(STATUS.equals("null") && ORDR_NUMBER.equals("null")){
                queryData=null;
            }
            List<BaseEntity> beList=dao.getData(offset,limit,queryData);
            if(beList.isEmpty()){
                MyLocalizedThrowable ex= new MyLocalizedThrowable("INVALID_FIELD");
                ex.setLang(lang);
                throw ex;
            }
            cust=new CustomResponse("Success",null,meta,beList);
        }catch(MyLocalizedThrowable e){
            ErrorMessage err=new ErrorMessage(e.getLocalizedMessage(),e.getMessage());
            cust=new CustomResponse("Error",err,null,null); 
        }catch(Exception e){
            ErrorMessage err=new ErrorMessage(e.toString(),e.getMessage());
            cust=new CustomResponse("Error",err,null,null);  
        }finally{
            dao.closeEntityManager();
        }
          return cust;
    }*/
    
    /*
    @GET
    @Path("/{id}/{Child}")
    @Produces("application/json")
    public CustomResponse getChildData(@PathParam("Entity") String ent,@PathParam("Child") String child,@PathParam("id") Integer pk,@HeaderParam("Accept-Language") String lang){
        CustomResponse cust;
        Class temp;
        try{
            setEntityDao(ent);
            Method getMethod=entity.getMethod("get"+child,null);
            List<BaseEntity> beList=new ArrayList<BaseEntity>();
            temp=getMethod.getReturnType();
            BaseEntity be=dao.find(pk);
            if(be == null){
                MyLocalizedThrowable ex= new MyLocalizedThrowable("ID_NOT_FOUND");
                ex.setLang(lang);
                throw ex;
                
            }
            if(temp.getTypeName().equals("java.util.List")){
                beList=(List<BaseEntity>)getMethod.invoke(be);
            }
            else{
                beList.add((BaseEntity)getMethod.invoke(be));
            }
            if(beList.get(0)==null){
                MyLocalizedThrowable ex= new MyLocalizedThrowable("CHILD_NOT_FOUND");
                ex.setLang(lang);
                throw ex;
                
            }
            cust=new CustomResponse("Success",null,null,beList);              
       }catch(MyLocalizedThrowable e){
            ErrorMessage err=new ErrorMessage(e.getLocalizedMessage(),e.getMessage());
            cust=new CustomResponse("Error",err,null,null); 
        }catch(Exception e){
            ErrorMessage err=new ErrorMessage(e.toString(),e.getMessage());
            cust=new CustomResponse("Error",err,null,null);  
        }finally{
            dao.closeEntityManager();
        }
        return cust;
    }*/
    
    /*
    @POST
    @Path("/")
    @Consumes("application/json")
    @Produces("application/json")
    public CustomResponse setData(@PathParam("Entity") String ent,String t,@HeaderParam("Accept-Language") String lang){
        CustomResponse cust;
        String idName=null;
        Object idValue=null;
        Object dbObj=null;
        System.out.println(t);
        try{
            setEntityDao(ent);
            BaseEntity be=null;
            ObjectMapper mapper = new ObjectMapper();
            Object obj=mapper.readValue(t,entity);
            for (Field field : entity.getDeclaredFields()) {
                Id id = field.getAnnotation(Id.class);
                if(id!=null){
                    idName=field.getName();
                    idName=idName.substring(0, 1).toUpperCase() + idName.substring(1);
                    Method getMethod=entity.getMethod("get"+idName,null);
                    System.out.println("get"+idName);
                    idValue=getMethod.invoke(obj,null); 
                    break;
                }
            }
            if(idValue==null){
                   be=dao.create((BaseEntity)obj);
            }
            else{
                dbObj=dao.find(idValue);
                Object fieldValue;
                
                for (Field field : entity.getDeclaredFields()) {                   
                    String str=field.getName();
                    if(str!="serialVersionUID"){
                        str=str.substring(0, 1).toUpperCase() + str.substring(1);
                        Method getMethod=entity.getMethod("get"+str,null);
                        System.out.println("get"+str);
                        fieldValue=getMethod.invoke(obj,null); 
                    
                        if(fieldValue!=null){
                            Class temp=getMethod.getReturnType(); 
                            Method setMethod=entity.getMethod("set"+str,temp);
                            setMethod.invoke(dbObj,fieldValue);                        
                        }
                    }
                }
                be=dao.update((BaseEntity)dbObj);
            } 

            List<BaseEntity> beList=new ArrayList<BaseEntity>();
            beList.add(be);
            cust=new CustomResponse("Success",null,null,beList);              
           }catch(Exception  e){
               ErrorMessage err=new ErrorMessage(e.toString(),e.getMessage());
               cust=new CustomResponse("Error",err,null,null);  
           }finally{
            dao.closeEntityManager();
        }
        return cust;
    }*/
    
    
    @POST
    @Path("/")
    @Consumes("application/json")
    @Produces("application/json")
    public CustomResponse setData(@PathParam("Entity") String ent,Object postObject,@HeaderParam("Accept-Language") String lang){
        CustomResponse cust;
        Map<String, Object> postHashMap = (LinkedHashMap<String, Object>) postObject;
        String idName=null;
        Object idValue=null;
        Object dbObj=null;
        try{
            setEntityDao(ent);
            BaseEntity baseEntity=(BaseEntity)entity.newInstance();
            BaseEntity be=null;
            ObjectMapper mapper = new ObjectMapper();
            baseEntity = (BaseEntity) mapper.convertValue(postHashMap,entity);
            for (Field field : entity.getDeclaredFields()) {
                Id id = field.getAnnotation(Id.class);
                if(id!=null){
                    idName=field.getName();
                    idValue=postHashMap.get(idName);
                    break;
                }
            }
            if(idValue==null){
                   be=dao.create(baseEntity);
            }
            else{
                dbObj=dao.find(idValue);
                String methodName;
                for(String str:postHashMap.keySet()){
                    methodName=str.substring(0, 1).toUpperCase() + str.substring(1);
                    Method setMethod=entity.getMethod("set"+methodName,postHashMap.get(str).getClass());
                    setMethod.invoke(dbObj,postHashMap.get(str));                                           
                }
                be=dao.update((BaseEntity)dbObj);
            } 

            List<BaseEntity> beList=new ArrayList<BaseEntity>();
            beList.add(be);
            cust=new CustomResponse("Success",null,null,beList);              
           }catch(Exception  e){
               ErrorMessage err=new ErrorMessage(e.toString(),e.getMessage());
               cust=new CustomResponse("Error",err,null,null);  
           }finally{
            dao.closeEntityManager();
        }
        return cust;
    }
 /*   
    public static HashMap<String,String> getQueryData(String INPUT){
        String REGEX = "\\w+[=]";    
        Pattern p = Pattern.compile(REGEX);
        Matcher m = p.matcher(INPUT);
        int count = 0,end=0;
        String temp=null;
        HashMap<String,String> hm=new HashMap<String,String>();
        while(m.find()) {
            if(count>=1){
                hm.put(temp,INPUT.substring(end,m.start()-1));
            }
            count++;
            end=m.end();
            temp=INPUT.substring(m.start(),m.end()-1);
            hm.put(temp,null);
        }
        hm.put(temp,INPUT.substring(end));
        return hm;     
    }*/  
    
    //Get data with the help of query parameter
    
    @GET
    @Path("/")
    @Produces("application/json")
    public CustomResponse getData(@PathParam("Entity") String ent,
                                  @Context UriInfo ui,
                                  @HeaderParam("Accept-Language") String lang) {
        CustomResponse cust;
        Meta meta=null;
        int offset=0,limit=10;

        try{
            setEntityDao(ent);
            MultivaluedMap<String, String> QueryData= ui.getQueryParameters();    
            if(QueryData.containsKey("offset")){
                offset=Integer.parseInt(QueryData.getFirst("offset"));
            }
            if(QueryData.containsKey("limit")){
                limit=Integer.parseInt(QueryData.getFirst("limit"));
            }
            if(offset<0 || limit<0){
                MyLocalizedThrowable ex= new MyLocalizedThrowable("OFFSET_ERROR");
                ex.setLang(lang);
                throw ex;
            }
            for(String keys:QueryData.keySet()){
                int err=0;
                for(Field field:entity.getDeclaredFields()){
                    if(field.getName().equals(keys) || keys.equals("offset") || keys.equals("limit")){
                        err=1;
                        break;
                    }                    
                }
                if(err==0){
                    MyLocalizedThrowable ex= new MyLocalizedThrowable("INVALID_FIELD");
                    ex.setLang(lang);
                    throw ex;
                }
            }            
            meta=new Meta(offset,limit);
            List<BaseEntity> beList=dao.getData(offset,limit,QueryData);
            if(beList.isEmpty()){
                MyLocalizedThrowable ex= new MyLocalizedThrowable("INVALID_FIELD");
                ex.setLang(lang);
                throw ex;
            }
            cust=new CustomResponse("Success",null,meta,beList);
        }catch(MyLocalizedThrowable e){
            ErrorMessage err=new ErrorMessage(e.getLocalizedMessage(),e.getMessage());
            cust=new CustomResponse("Error",err,null,null); 
        }catch(Exception e){
            ErrorMessage err=new ErrorMessage(e.toString(),e.getMessage());
            cust=new CustomResponse("Error",err,null,null);  
        }finally{
            dao.closeEntityManager();
        }
          return cust;
    }
}

