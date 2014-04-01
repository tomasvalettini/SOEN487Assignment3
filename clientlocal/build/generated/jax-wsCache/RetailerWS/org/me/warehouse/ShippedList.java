
package org.me.warehouse;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for shippedList complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="shippedList">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="shippedItems" type="{http://warehouse.me.org/}orderItem" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="items" type="{http://warehouse.me.org/}orderItem" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "shippedList", propOrder = {
    "shippedItems",
    "items"
})
public class ShippedList {

    @XmlElement(nillable = true)
    protected List<OrderItem> shippedItems;
    @XmlElement(nillable = true)
    protected List<OrderItem> items;

    /**
     * Gets the value of the shippedItems property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the shippedItems property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getShippedItems().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link OrderItem }
     * 
     * 
     */
    public List<OrderItem> getShippedItems() {
        if (shippedItems == null) {
            shippedItems = new ArrayList<OrderItem>();
        }
        return this.shippedItems;
    }

    /**
     * Gets the value of the items property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the items property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getItems().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link OrderItem }
     * 
     * 
     */
    public List<OrderItem> getItems() {
        if (items == null) {
            items = new ArrayList<OrderItem>();
        }
        return this.items;
    }

}
