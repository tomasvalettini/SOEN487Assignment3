/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.me.warehouse;

import org.me.manufacturer.Product;
import org.me.manufacturer.PurchaseOrder;

/**
 *
 * @author tomas.valettini
 */
class ManufacturerOne extends AbstractManufacturer
{
    @Override
    public Product getProductInfo(String productType)
    {
        org.me.manufacturer.ManufacturerWS_Service service = new org.me.manufacturer.ManufacturerWS_Service();
        org.me.manufacturer.ManufacturerWS port = service.getManufacturerWSPort();
        return port.getProductInfo(productType);
    }

    @Override
    public boolean receivePayment(int orderNumber, float totalPrice)
    {
        org.me.manufacturer.ManufacturerWS_Service service = new org.me.manufacturer.ManufacturerWS_Service();
        org.me.manufacturer.ManufacturerWS port = service.getManufacturerWSPort();
        return port.receivePayment(orderNumber, totalPrice);
    }    

    @Override
    public boolean processPurchasePrder(PurchaseOrder purchaseOrder)
    {
        org.me.manufacturer.ManufacturerWS_Service service = new org.me.manufacturer.ManufacturerWS_Service();
        org.me.manufacturer.ManufacturerWS port = service.getManufacturerWSPort();
        return port.processPurchasePrder(purchaseOrder);
    }
}