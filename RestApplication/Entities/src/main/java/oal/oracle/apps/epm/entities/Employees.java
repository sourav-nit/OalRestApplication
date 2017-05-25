package oal.oracle.apps.epm.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

import java.lang.reflect.Field;

import java.text.ParseException;
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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@XmlRootElement(name = "Employees")
@Entity
public class Employees extends BaseEntity implements Serializable {
    private static final long serialVersionUID = -2506140104403524623L;
    
    @Column(name = "COMMISSION_PCT")
    private Integer commissionPct;
    @Column(name = "email")
    private String email;
    @Column(name = "employee_id")
    @SequenceGenerator(name="pk_sq", sequenceName="pk_seq",initialValue=100, allocationSize=1)
    @GeneratedValue(generator="pk_sq")
    @Id
    private Integer employeeId;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "hire_date")
    private Date hireDate;
    @Column(name = "job_id")
    private String jobId;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "phone_number")
    private String phoneNumber;
    @Column(name = "salary")
    private Integer salary;
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
    /*@Column(name = "DEPARTMENT_ID")
    private Integer departmentId;*/
    @JsonBackReference
    @ManyToOne(cascade={CascadeType.REFRESH,CascadeType.MERGE,CascadeType.PERSIST})
    @JoinColumn(name = "DEPARTMENT_ID")
    private Departments dept;

    
    public Employees() {
    }
    public Employees(int commissionPct,String email,int employeeId,String firstName,Date hireDate,String jobId,
                     String lastName,String phoneNumber,int salary,int managerId){
        this.commissionPct=commissionPct;
        this.email=email;
        this.employeeId=employeeId;
        this.firstName=firstName;
        this.hireDate=hireDate;
        this.jobId=jobId;
        this.lastName=lastName;
        this.phoneNumber=phoneNumber;
        this.salary=salary;
        this.managerId=managerId;
        //this.departmentId=departmentId;
    }
    @XmlElement    
    public Integer getCommissionPct() {
        return commissionPct;
    }

    public void setCommissionPct(Integer commissionPct) {
        this.commissionPct = commissionPct;
    }

    @XmlElement
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    @XmlElement
    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }
    @XmlElement
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    @XmlElement
    public String getHireDate() {
        if(hireDate==null)
            return null;
        else{
            SimpleDateFormat df=new SimpleDateFormat("dd/MM/yyyy");
            return df.format(hireDate);
        }
    }

    public void setHireDate(String hireDate) throws ParseException {
            SimpleDateFormat df=new SimpleDateFormat("dd/MM/yyyy");
            Date hireDt = new java.sql.Date(df.parse(hireDate).getTime());
            this.hireDate = hireDt;
    }
    @XmlElement
    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }
    @XmlElement
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @XmlElement
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    @XmlElement
    public Integer getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }
    
    @XmlElement
    public Integer getManagerId() {
        return managerId;
    }

    public void setManagerId(Integer salary) {
        this.managerId = managerId;
    }

    @JsonBackReference
    public Departments getDept(){
        return this.dept;
    }
    
    public void setDept(Departments dept) {
        this.dept = dept;
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
}

