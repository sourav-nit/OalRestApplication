package oal.oracle.apps.epm.entities;

import java.io.Serializable;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.SequenceGenerator;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@XmlRootElement(name = "OrderHeader")
@Entity
@EntityListeners({EntityListener_CreatedBy.class,EntityListener_UpdateDate.class,EntityListener_CreationDate.class,EntityListener_LastUpdatedBy.class})
public class Order_Header extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @GeneratedValue(generator="pk_sq")
    @SequenceGenerator(name="pk_sq", sequenceName="pk_seq",initialValue=100, allocationSize=1)
    @Id
    @Column(name = "ORDER_HEADER_ID")
    private Integer orderHeaderId;
    @Column(name = "ORDR_NUMBER")
    private Integer orderNumber;
    @Column(name = "CUSTOMER_ID")
    private Integer customerId;
    @Column(name = "STATUS")
    private String status;
    @Column(name = "BOOKED_DATE")
    private Date bookedDate;
    @Column(name = "ORDER_VALUE")
    private Integer orderValue;
    @Column(name = "Creation_Date")
    private Date creationDate;
    @Column(name = "Created_By")
    private String createdBy;
    @Column(name = "Last_Updated_Date")
    private Date lastUpdatedDate;
    @Column(name = "Last_Updated_By")
    private String lastUpdatedBy;
    @OneToMany(mappedBy="orderheader",cascade={CascadeType.REFRESH,CascadeType.MERGE,CascadeType.PERSIST})
    private List<Order_Lines> orderliness;


    public Order_Header() {
        super();
    }

    public void setOrderHeaderId(Integer orderHeaderId) {
        this.orderHeaderId = orderHeaderId;
    }

    @XmlElement
    public Integer getOrderHeaderId() {
        return orderHeaderId;
    }

    public void setOrderNumber(Integer orderNumber) {
        this.orderNumber = orderNumber;
    }

    @XmlElement
    public Integer getOrderNumber() {
        return orderNumber;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    @XmlElement
    public Integer getCustomerId() {
        return customerId;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @XmlElement
    public String getStatus() {
        return status;
    }

    public void setBookedDate(String bookedDate) throws ParseException {
        SimpleDateFormat df=new SimpleDateFormat("dd/MM/yyyy");
        Date bookedDt = new java.sql.Date(df.parse(bookedDate).getTime());
        this.bookedDate = bookedDt;
    }

    @XmlElement
    public String getBookedDate() {
        if(bookedDate==null)
            return null;
        else{
            SimpleDateFormat df=new SimpleDateFormat("dd/MM/yyyy");
            return df.format(bookedDate);
        }
    }

    public void setOrderValue(Integer orderValue) {
        this.orderValue = orderValue;
    }

    @XmlElement
    public Integer getOrderValue() {
        return orderValue;
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

    public void setCreationDate(Date date){
        //java.sql.Date sqlDate = new java.sql.Date(new java.util.Date().getTime());
        //this.creationDate=sqlDate;
        this.creationDate=date;
    }
    
    
    public void setLastUpdatedDate(Date date){
        //java.sql.Date sqlDate = new java.sql.Date(new java.util.Date().getTime());
        //this.lastUpdatedDate=sqlDate;
        this.lastUpdatedDate=date;
    }
    
    public void setOrderliness(List<Order_Lines> orderliness) {
            for(Order_Lines ol:orderliness){
                ol.setOrderheader(this);
                /*ol.setCreatedBy("Oracle");
                ol.setCreationDate();
                ol.setLastUpdatedDate();
                ol.setLastUpdatedBy("Oracle");*/
            }
        this.orderliness = orderliness;
    }

    public List<Order_Lines> getOrderliness() {
        return orderliness;
    }
}
