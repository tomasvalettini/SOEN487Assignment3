/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.me.warehouse;

import java.util.ArrayList;

/**
 *
 * @author steve.bilogan
 */
public class OrderList {
    
    
    public ArrayList<OrderItem> _items;



    
    public OrderList()
    {
    }
    
    
    public ArrayList<OrderItem> getItems() {
        return _items;
    }
    
    public void addItem (OrderItem item) {
        this._items.add(item);
    }

}
