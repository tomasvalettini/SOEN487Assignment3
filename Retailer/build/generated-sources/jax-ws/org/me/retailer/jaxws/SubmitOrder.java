
package org.me.retailer.jaxws;

import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import org.me.warehouse.OrderItem;

@XmlRootElement(name = "submitOrder", namespace = "http://retailer.me.org/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "submitOrder", namespace = "http://retailer.me.org/")
public class SubmitOrder {

    @XmlElement(name = "orderItemList", namespace = "")
    private List<OrderItem> orderItemList;

    /**
     * 
     * @return
     *     returns List<OrderItem>
     */
    public List<OrderItem> getOrderItemList() {
        return this.orderItemList;
    }

    /**
     * 
     * @param orderItemList
     *     the value for the orderItemList property
     */
    public void setOrderItemList(List<OrderItem> orderItemList) {
        this.orderItemList = orderItemList;
    }

}
