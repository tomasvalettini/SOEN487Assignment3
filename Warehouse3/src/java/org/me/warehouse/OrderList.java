package org.me.warehouse;

import java.util.ArrayList;

public class OrderList
{
    public ArrayList<OrderItem> _items;
    
    public OrderList()
    {
        _items = new ArrayList<OrderItem>();
    }
    
    public ArrayList<OrderItem> getItems()
    {
        return _items;
    }
    
    public void addItem (OrderItem item)
    {
        this._items.add(item);
    }
}