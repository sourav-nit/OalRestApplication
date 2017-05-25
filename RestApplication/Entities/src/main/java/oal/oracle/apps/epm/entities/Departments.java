package oal.oracle.apps.epm.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.io.Serializable;

import java.lang.reflect.Field;

import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import javax.persistence.SequenceGenerator;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@XmlRootElement(name="Departments")
@Entity
public class Departments extends BaseEntity implements Serializable {
    private static final long serialVersionUID = -1246294097529286468L;

    @SequenceGenerator(name="pk_sq", sequenceName="pk_seq",initialValue=100, allocationSize=1)
    @GeneratedValue(generator="pk_sq")
    @Id
    @Column(name = "DEPARTMENT_ID")
    private Integer departmentId;
    @Column(name = "DEPARTMENT_NAME")
    private String departmentName;
    @Column(name = "LOCATION_ID")
    private Integer locationId;
    @Column(name = "MANAGER_ID")
    private Integer managerId;
    @Column(name = "Creation_Date")
    private Date creationDate;
    @Column(name = "Created_By")
    private String createdBy;
    @Column(name = "Last_Updated_Date")
    private Date lastUpdatedDate;
    @Column(name = "Last_Updated_By")
    private String lastUpdatedBy;
    @OneToMany(mappedBy="dept",cascade={CascadeType.REFRESH,CascadeType.MERGE,CascadeType.PERSIST})
    private List<Employees> empList;
    
    public Departments(){
        
    }
    
    public Departments(int departmentId,String departmentName,int locationId,int managerId){
        this.departmentId=departmentId;
        this.departmentName=departmentName;
        this.locationId=locationId;
        this.managerId=managerId;
    }

    @XmlElement
    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }
    @XmlElement
    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }
    @XmlElement
    public Integer getLocationId() {
        return locationId;
    }

    public void setLocationId(Integer locationId) {
        this.locationId = locationId;
    }
    @XmlElement
    public Integer getManagerId() {
        return managerId;
    }

    public void setManagerId(Integer managerId) {
        this.managerId = managerId;
    }
    

    public List<Employees> getEmpList(){
        return this.empList;
    }

    
    public void setEmpList(List<Employees> empList) {
        this.empList = empList;
    }
    
    public void setCreationDate(){
        java.sql.Date sqlDate = new java.sql.Date(new java.util.Date().getTime());
        this.creationDate=sqlDate;
    }
    
    public void setLastUpdatedDate(){
        java.sql.Date sqlDate = new java.sql.Date(new java.util.Date().getTime());
        this.lastUpdatedDate=sqlDate;
    }
    
    public void setCreatedBy(String name){
        this.createdBy=name;
    }
    
    public void setLastUpdatedBy(String name){
        this.lastUpdatedBy=name;
    }
    
    @XmlTransient
    public String getCreationDate(){
        if(creationDate==null)
            return null;
        else{
            SimpleDateFormat df=new SimpleDateFormat("dd/MM/yyyy");
            return df.format(creationDate);
        }       
    }
    
    @XmlTransient
    public String getLastUpdatedDate(){
        if(lastUpdatedDate==null)
            return null;
        else{
            SimpleDateFormat df=new SimpleDateFormat("dd/MM/yyyy");
            return df.format(lastUpdatedDate);
        }
    }
    
    @XmlTransient
    public String getCreatedBy(){
        return this.createdBy;
    }
    @XmlTransient
    public String getLastUpdatedBy(){
        return this.lastUpdatedBy;
    }    

    /*
    public static List<String> getFieldList(){
        int temp=0;
        Field[] fields=Employees.class.getFields();
        List<String> fieldList=new ArrayList<String>();
        while(temp!=fields.length){
            fieldList.add(fields[0].getName());
            ++temp;
        }
        return fieldList;
    }*/
}

