/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.me.warehouse;

import java.util.List;
import org.me.manufacturer.Product;
import org.me.manufacturer.PurchaseOrder;

/**
 *
 * @author tomas.valettini
 */
class ManufacturerThree extends AbstractManufacturer
{

    @Override
    public Product getProductInfo(String productType) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean processPurchasePrder(PurchaseOrder purchaseOrder) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean receivePayment(int orderNumber, float totalPrice) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
