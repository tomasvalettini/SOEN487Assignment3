package org.me.warehouse;

import java.util.ArrayList;

public class ShippedList
{
    public ArrayList<OrderItem> shippedItems;

    public ArrayList<OrderItem> getItems()
    {
        return shippedItems;
    }

    public void setItems(ArrayList<OrderItem> list)
    {
        this.shippedItems = list;
    }
     
    public ShippedList(ArrayList<OrderItem> oi)
    {
        shippedItems = oi;
    }
}