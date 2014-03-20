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
class ManufacturerOne extends AbstractManufacturer
{
    @Override
    public List<Product> getProductInfo(String productType)
    {
        org.me.manufacturer.ManufacturerWS_Service service = new org.me.manufacturer.ManufacturerWS_Service();
        org.me.manufacturer.ManufacturerWS port = service.getManufacturerWSPort();
        return port.getProductInfo(productType);
    }

    @Override
    public boolean processPurchasePrder(PurchaseOrder purchaseOrder, int quantity)
    {
        org.me.manufacturer.ManufacturerWS_Service service = new org.me.manufacturer.ManufacturerWS_Service();
        org.me.manufacturer.ManufacturerWS port = service.getManufacturerWSPort();
        return port.processPurchasePrder(purchaseOrder, quantity);
    }

    @Override
    public boolean receivePayment(int orderNumber, float totalPrice)
    {
        org.me.manufacturer.ManufacturerWS_Service service = new org.me.manufacturer.ManufacturerWS_Service();
        org.me.manufacturer.ManufacturerWS port = service.getManufacturerWSPort();
        return port.receivePayment(orderNumber, totalPrice);
    }    
}
