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
class ManufacturerThree extends AbstractManufacturer
{
    @Override
    public Product getProductInfo(String productType)
    {
        org.me.manufacturer.Manufacturer3WS_Service service = new org.me.manufacturer.Manufacturer3WS_Service();
        org.me.manufacturer.Manufacturer3WS port = service.getManufacturer3WSPort();
        return port.getProductInfo(productType);
    }

    @Override
    public boolean processPurchasePrder(PurchaseOrder purchaseOrder)
    {
        org.me.manufacturer.Manufacturer3WS_Service service = new org.me.manufacturer.Manufacturer3WS_Service();
        org.me.manufacturer.Manufacturer3WS port = service.getManufacturer3WSPort();
        return port.processPurchasePrder(purchaseOrder);
    }

    @Override
    public boolean receivePayment(int orderNumber, float totalPrice)
    {
        org.me.manufacturer.Manufacturer3WS_Service service = new org.me.manufacturer.Manufacturer3WS_Service();
        org.me.manufacturer.Manufacturer3WS port = service.getManufacturer3WSPort();
        return port.receivePayment(orderNumber, totalPrice);
    }
}