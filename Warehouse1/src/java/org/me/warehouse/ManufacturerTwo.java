/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.me.warehouse;

import java.util.List;

/**
 *
 * @author tomas.valettini
 */
class ManufacturerTwo extends AbstractManufacturer
{
    @Override
    public List<Product> getProductInfo(String productType) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean processPurchasePrder(PurchaseOrder purchaseOrder, int quantity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean receivePayment(int orderNumber, float totalPrice) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
