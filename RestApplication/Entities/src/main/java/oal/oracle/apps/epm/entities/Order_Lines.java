package oal.oracle.apps.epm.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@XmlRootElement(name = "OrderLines")
@Entity
@EntityListeners({EntityListener_CreatedBy.class,EntityListener_UpdateDate.class,EntityListener_CreationDate.class,EntityListener_LastUpdatedBy.class})
public class Order_Lines extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @GeneratedValue(generator="pk_sq")
    @SequenceGenerator(name="pk_sq", sequenceName="pk_seq",initialValue=100, allocationSize=1)
    @Id
    @Column(name = "ORDER_LINE_ID")
    private Integer orderLineId;
    @Column(name = "LINE_NUMBER")
    private Integer LineNumber;
    @Column(name = "PRODUCT_ID")
    private Integer productId;
    @Column(name = "PRODUCT_NAME")
    private String productName;
    @Column(name = "START_DATE")
    private Date startDate;
    @Column(name = "END_DATE")
    private Date endDate;
    @Column(name = "SELLING_PRICE")
    private Integer sellingPrice;
    @Column(name = "Quantity")
    private Integer quantity;
    @Column(name = "Creation_Date")
    private Date creationDate;
    @Column(name = "Created_By")
    private String createdBy;
    @Column(name = "Last_Updated_Date")
    private Date lastUpdatedDate;
    @Column(name = "Last_Updated_By")
    private String lastUpdatedBy;
    
    @JsonBackReference
    @ManyToOne(cascade={CascadeType.REFRESH,CascadeType.MERGE,CascadeType.PERSIST})
    @JoinColumn(name = "ORDER_HEADER_ID")
    private Order_Header orderheader;
    
    public Order_Lines() {
        super();
    }

    public void setOrderLineId(Integer orderLineId) {
        this.orderLineId = orderLineId;
    }

    @XmlElement
    public Integer getOrderLineId() {
        return orderLineId;
    }

    public void setLineNumber(Integer LineNumber) {
        this.LineNumber = LineNumber;
    }

    @XmlElement
    public Integer getLineNumber() {
        return LineNumber;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    @XmlElement
    public Integer getProductId() {
        return productId;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    @XmlElement
    public String getProductName() {
        return productName;
    }

    public void setStartDate(String startDate) throws ParseException {
        SimpleDateFormat df=new SimpleDateFormat("dd/MM/yyyy");
        Date startDt = new java.sql.Date(df.parse(startDate).getTime());
        this.startDate = startDt;
    }

    @XmlElement
    public String getStartDate() {
        if(startDate==null)
            return null;
        else{
            SimpleDateFormat df=new SimpleDateFormat("dd/MM/yyyy");
            return df.format(startDate);
        }
    }

    public void setEndDate(String endDate) throws ParseException {
        SimpleDateFormat df=new SimpleDateFormat("dd/MM/yyyy");
        Date endDt = new java.sql.Date(df.parse(endDate).getTime());
        this.endDate = endDt;
    }

    @XmlElement
    public String getEndDate() {
        if(endDate==null)
            return null;
        else{
            SimpleDateFormat df=new SimpleDateFormat("dd/MM/yyyy");
            return df.format(endDate);
        }
    }

    public void setSellingPrice(Integer sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    @XmlElement
    public Integer getSellingPrice() {
        return sellingPrice;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @XmlElement
    public Integer getQuantity() {
        return quantity;
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


    public void setOrderheader(Order_Header orderheader) {
        this.orderheader = orderheader;
    }
    
    @JsonBackReference
    public Order_Header getOrderheader() {
        return orderheader;
    }
}
