package oal.oracle.apps.epm.utils.properties;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import java.util.HashMap;
import java.util.Properties;
import java.util.Set;

public class TestFile {
    public TestFile() {
        super();
    }
    private static HashMap<String,String> propMap=null;

    public  static void showProperty() throws FileNotFoundException, IOException {
            Properties prop = new Properties();
            InputStream input = null;
        File directory = new File("src/main/");
           System.out.println(directory.getAbsolutePath());
            //input = new FileInputStream("C:/JDeveloper/mywork/RestApplication/Utils/src/main/java/oal/oracle/apps/epm/utils/properties/ExceptionProp_en-US.properties");
            input = TestFile.class.getResourceAsStream("ExceptionProp_en-US.properties");
            prop.load(input);
            Set<Object> keys = prop.keySet();
            propMap=new HashMap<String,String>();  
            for(Object k:keys){
                String key = (String)k;
                propMap.put(key,prop.getProperty(key));
                System.out.println(propMap.get(key));
            }
    }
    
    public  static void main(String[] args) throws FileNotFoundException, IOException {
        showProperty();


    }

}
