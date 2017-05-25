package oal.oracle.apps.epm.utils.properties;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Properties;
import java.util.Set;

public class PropertyMessage {
    
    private static HashMap<String,String> propMap=null;

    public  static String getkeyValue(String keyinp,String lang){
        if(propMap==null){
            Properties prop = new Properties();
            InputStream input = null;
            try{
            input = new FileInputStream("C:/JDeveloper/mywork/RestApplication/Utils/src/main/java/oal/oracle/apps/epm/utils/properties/ExceptionProp_"+lang+".properties");
            prop.load(input);
            Set<Object> keys = prop.keySet();
            propMap=new HashMap<String,String>();  
            for(Object k:keys){
                String key = (String)k;
                propMap.put(key,prop.getProperty(key));
            }
            return propMap.get(keyinp);
            }catch(IOException ex){
                return propMap.get("FILE_NOT_FOUND");
            }finally{
                if (input != null) {
                        try {
                                input.close();
                                return propMap.get(keyinp);
                        } catch (IOException e) {
                            return propMap.get("FILE_NOT_FOUND");
                        }
                }
            }
        }
        else{
            return propMap.get(keyinp);
        }
    }
}
