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
public abstract class AbstractManufacturer
{
    public abstract Product getProductInfo(String productType);
    public abstract boolean processPurchasePrder(PurchaseOrder purchaseOrder);
    public abstract boolean receivePayment(int orderNumber, float totalPrice);
}