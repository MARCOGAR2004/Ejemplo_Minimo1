package edu.upc.dsa.models;

import edu.upc.dsa.models.Products;
import java.util.LinkedList;
import java.util.List;

public class User {
    String username;
    String password;
    List<Products> productos;


    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.productos = new LinkedList<>();
    }
    public User() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Products> getProductos(){return productos; }

    public void setProductos(List<Products> productos){this.productos = productos; }


}
