package org.me.warehouse;

import java.util.ArrayList;
import org.me.manufacturer.Product;

public class ProductList
{
    private ArrayList<Product> _products;

    public ProductList()
    {
        
    }
    
    public ArrayList<Product> getProducts() {
        return _products;
    }

    public void setProducts(ArrayList<Product> tempProds) {
        this._products = tempProds;
    }
}