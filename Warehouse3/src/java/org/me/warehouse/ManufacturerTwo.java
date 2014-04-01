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
class ManufacturerTwo extends AbstractManufacturer
{
    @Override
    public Product getProductInfo(String productType)
    {
	org.me.manufacturer.Manufacturer2WS_Service service = new org.me.manufacturer.Manufacturer2WS_Service();
	org.me.manufacturer.Manufacturer2WS port = service.getManufacturer2WSPort();
        return port.getProductInfo(productType);
    }

    @Override
    public boolean processPurchasePrder(PurchaseOrder purchaseOrder)
    {
        org.me.manufacturer.Manufacturer2WS_Service service = new org.me.manufacturer.Manufacturer2WS_Service();
        org.me.manufacturer.Manufacturer2WS port = service.getManufacturer2WSPort();
        return port.processPurchasePrder(purchaseOrder);
    }

    @Override
    public boolean receivePayment(int orderNumber, float totalPrice)
    {
        org.me.manufacturer.Manufacturer2WS_Service service = new org.me.manufacturer.Manufacturer2WS_Service();
        org.me.manufacturer.Manufacturer2WS port = service.getManufacturer2WSPort();
        return port.receivePayment(orderNumber, totalPrice);
    }
}