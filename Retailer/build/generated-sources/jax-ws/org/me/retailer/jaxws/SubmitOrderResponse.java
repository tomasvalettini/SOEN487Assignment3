
package org.me.retailer.jaxws;

import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import org.me.warehouse.OrderItem;

@XmlRootElement(name = "submitOrderResponse", namespace = "http://retailer.me.org/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "submitOrderResponse", namespace = "http://retailer.me.org/")
public class SubmitOrderResponse {

    @XmlElement(name = "return", namespace = "")
    private List<OrderItem> _return;

    /**
     * 
     * @return
     *     returns List<OrderItem>
     */
    public List<OrderItem> getReturn() {
        return this._return;
    }

    /**
     * 
     * @param _return
     *     the value for the _return property
     */
    public void setReturn(List<OrderItem> _return) {
        this._return = _return;
    }

}
