/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Lu
 */

import java.io.*;
import javax.annotation.PostConstruct;

import javax.faces.bean.ManagedBean;


/**
 *
 * @author Lu
 */
@ManagedBean(name="PizzaBean")

public class PizzaBean {
    private String[] topping = {"olives","pepper","tomato","ham","chees","mushroom","bacon"};
    private String base = "Regular";
    private String selectSize = "Medium";
    private String delivery = "Delivery";
    private String name;
    private String address;
    private String deliveryResult = "Delivered to ";
    private double priceDelivery = 2, priceTopping = 0.20, priceSmall = 3, priceMedium = 5, priceLarge = 7;

    public double getPriceDelivery() {
        return priceDelivery;
    }

    public void setPriceDelivery(double priceDelivery) {
        this.priceDelivery = priceDelivery;
    }
    
    public String[] getTopping() {
        return topping;
    }

    public void setTopping(String[] topping) {
        this.topping = topping;
    }

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public String getSelectSize() {
        return selectSize;
    }

    public void setSelectSize(String selectSize) {
        this.selectSize = selectSize;
    }

    public String getDelivery() {
        return delivery;
    }

    public void setDelivery(String delivery) {
        this.delivery = delivery;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDeliveryResult() {
        return deliveryResult;
    }

    public void setDeliveryResult(String deliveryResult) {
        this.deliveryResult = deliveryResult;
    }

    public double getPriceTopping() {
        return priceTopping;
    }

    public void setPriceTopping(double priceTopping) {
        this.priceTopping = priceTopping;
    }

    public double getPriceSmall() {
        return priceSmall;
    }

    public void setPriceSmall(double priceSmall) {
        this.priceSmall = priceSmall;
    }

    public double getPriceMedium() {
        return priceMedium;
    }

    public void setPriceMedium(double priceMedium) {
        this.priceMedium = priceMedium;
    }

    public double getPriceLarge() {
        return priceLarge;
    }

    public void setPriceLarge(double priceLarge) {
        this.priceLarge = priceLarge;
    }
    
              
    public PizzaBean(){
        
    }
    
    @PostConstruct
    public void loadPrice(){
        try(BufferedReader br = new BufferedReader(new FileReader("E:\\Netbeans\\Projects\\Pizzabuilder\\web\\resources\\price.txt"))) {
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();

            while (line != null) {
                sb.append(line);
                sb.append(System.lineSeparator());
                line = br.readLine();
            }
            String everything = sb.toString();
            String[] split = everything.split(" ");
            if(Double.parseDouble(split[0])!=0)this.priceDelivery = Double.parseDouble(split[0]);    
            if(Double.parseDouble(split[1])!=0)this.priceLarge = Double.parseDouble(split[1]);
            if(Double.parseDouble(split[2])!=0)this.priceMedium = Double.parseDouble(split[2]);
            if(Double.parseDouble(split[3])!=0)this.priceSmall = Double.parseDouble(split[3]);
            if(Double.parseDouble(split[4])!=0)this.priceTopping = Double.parseDouble(split[4]);        
        }catch (IOException ex){
        
        }
    }
    
    public double total(){
      double price=0.00;   
      
      if ("Small".equals(this.selectSize)) price = this.getPriceSmall();
      if ("Medium".equals(this.selectSize)) price = this.getPriceMedium();
      if ("Large".equals(this.selectSize)) price = this.priceLarge;
      if ("Delivery".equals(this.delivery))  price = price+this.priceDelivery;   
      price=price+this.topping.length*this.priceTopping;       
      return price;        
    }
    
    public String order(){
      if("Pick up".equals(this.delivery)){
          this.deliveryResult = "Pick up by";
          this.address = " ";
      }      
      return "order";
    }
    
    public void saveChanges(){
        
        try (Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("E:\\Netbeans\\Projects\\Pizzabuilder\\web\\resources\\price.txt"), "utf-8"))) {
            writer.write(String.valueOf(this.getPriceDelivery())+" ");
            writer.write(String.valueOf(this.getPriceLarge())+" ");
            writer.write(String.valueOf(this.getPriceMedium())+" ");
            writer.write(String.valueOf(this.getPriceSmall())+" ");
            writer.write(String.valueOf(this.getPriceTopping())+" ");
        }catch (IOException ex){
            
        }
        
    }

    
}