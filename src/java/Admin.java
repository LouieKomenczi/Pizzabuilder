
import javax.faces.bean.ManagedBean;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Lu
 */

@ManagedBean(name="AdminBean")

public class Admin {
    private String password;
    

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Admin() {
    }
    
    public String validatePswd(){
        String result;
        if(this.password.equals("pepperoni")){
            result = "price";
        }else
            result = "fail";
        return result;
    } 
    
       
}
