package oal.oracle.apps.epm.entities;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@XmlRootElement(name = "Customer")
@Entity
public class Customer extends BaseEntity implements Serializable{
    private static final long serialVersionUID = 1L;
    
    @SequenceGenerator(name="pk_sq", sequenceName="pk_seq",initialValue=100, allocationSize=1)
    @GeneratedValue(generator="pk_sq")
    @Id
    @Column(name = "CUSTOMER_ID")
    private Integer customerId;
    @Column(name = "CUSTOMER_NUMBER")
    private Integer customerNumber;
    @Column(name = "CUSTOMER_NAME")
    private String customerName;
    @Column(name = "STATUS")
    private String status;
    @Column(name = "Creation_Date")
    private Date creationDate;
    @Column(name = "Created_By")
    private String createdBy;
    @Column(name = "Last_Updated_Date")
    private Date lastUpdatedDate;
    @Column(name = "Last_Updated_By")
    private String lastUpdatedBy;
    
    public Customer() {
        super();
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    @XmlElement
    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerNumber(Integer customerNumber) {
        this.customerNumber = customerNumber;
    }

    @XmlElement
    public Integer getCustomerNumber() {
        return customerNumber;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    @XmlElement
    public String getCustomerName() {
        return customerName;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @XmlElement
    public String getStatus() {
        return status;
    }
    
    public void setCreatedBy(String CreatedBy) {
        this.createdBy = CreatedBy;
    }

    @XmlTransient
    public String getCreatedBy() {
        return createdBy;
    }

    public void setLastUpdatedBy(String LastUpdatedBy) {
        this.lastUpdatedBy = LastUpdatedBy;
    }

    @XmlTransient
    public String getLastUpdatedBy() {
        return lastUpdatedBy;
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
    

    public void setCreationDate(){
        java.sql.Date sqlDate = new java.sql.Date(new java.util.Date().getTime());
        this.creationDate=sqlDate;
    }
    

    public void setLastUpdatedDate(){
        java.sql.Date sqlDate = new java.sql.Date(new java.util.Date().getTime());
        this.lastUpdatedDate=sqlDate;
    }

}
