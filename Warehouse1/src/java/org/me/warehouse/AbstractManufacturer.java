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
public abstract class AbstractManufacturer
{
    public abstract List<Product> getProductInfo(String productType);
    public abstract boolean processPurchasePrder(PurchaseOrder purchaseOrder, int quantity);
    public abstract boolean receivePayment(int orderNumber, float totalPrice);
}