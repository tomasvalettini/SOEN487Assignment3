/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.me.warehouse;

import org.me.manufacturer.Product;

/**
 *
 * @author tomas.valettini
 */
public class ManufacturerFactory
{
    private ManufacturerOne manOne;
    private ManufacturerTwo manTwo;
    private ManufacturerThree manThree;
    
    private final String BRAND_ONE = "Brand1";
    private final String BRAND_TWO = "Brand2";
    private final String BRAND_THREE = "Brand3";
    
    private static ManufacturerFactory instance = null;
    
    protected ManufacturerFactory()
    {
        
    }
    
    public static ManufacturerFactory getInstance()
    {
        if (instance == null)
        {
            instance = new ManufacturerFactory();
        }
        
        return instance;
    }
    
    public AbstractManufacturer getManufacturer(String prodName)
    {
        if (prodName.toLowerCase().contains(BRAND_ONE.toLowerCase()))
        {
            return new ManufacturerOne();
        }
        else if (prodName.toLowerCase().contains(BRAND_TWO.toLowerCase()))
        {
            return new ManufacturerTwo();
        }
        else if (prodName.toLowerCase().contains(BRAND_THREE.toLowerCase()))
        {
            return new ManufacturerThree();
        }
        
        return null;
    }
}
