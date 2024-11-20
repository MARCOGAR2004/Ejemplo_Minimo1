package edu.upc.dsa.services;


import edu.upc.dsa.ProductsManagerImp;
import edu.upc.dsa.ProductsManager;
import edu.upc.dsa.UserManager;
import edu.upc.dsa.UserManagerImpl;
import edu.upc.dsa.models.Products;
import edu.upc.dsa.models.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@Api(value = "/users", description = "Endpoint to User Service")
@Path("/users")
public class UserService {
    private UserManager um;
    private ProductsManager om;

    public UserService() {
        this.um = UserManagerImpl.getInstance();
        this.om = ProductsManagerImp.getInstance();

        if (um.size() == 0) {
            this.um.Register("Dennis", "1234", "dennis@gmail.com");
            this.um.Register("Bob", "1", "bob@gmail.com");
            this.um.Register("Manolo", "miau", "manolo@gmail.com");
        }

    }

    @POST
    @ApiOperation(value = "login", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = User.class),
            @ApiResponse(code = 404, message = "User not found")
    })
    @Path("/login")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(@FormParam("user") String user,
                          @FormParam("password") String password){
        User u = this.um.IniciarSesion(user, password);
        if (u != null)
            return Response.status(201).entity(u).build();
        else
            return Response.status(404).build();
    }

    //register
    @POST
    @ApiOperation(value = "register", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = User.class),
            @ApiResponse(code = 404, message = "User not valid")
    })
    @Path("/register")
    @Produces(MediaType.APPLICATION_JSON)
    public Response register(@FormParam("username") String username,
                             @FormParam("password") String password,
                             @FormParam("email") String email) {
        if (username == null || password == null) return Response.status(404).build();
        else{
            this.um.Register(username, password, email);
            User u = new User(username, password, email);
            return Response.status(201).entity(u).build();
        }
    }

    //Get all products of a user
    @GET
    @ApiOperation(value = "get all products of a user", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = User.class),
            @ApiResponse(code = 404, message = "User not found")
    })
    @Path("/{id}/products")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProductsOfUser(@PathParam("id") String id) {
        User u = this.um.getUser(id);
        if (u == null)
        {
            return Response.status(404).build();
        }
        else {
            List<Products> products = this.um.getProductsOfUser(u);
            GenericEntity<List<Products>> entity = new GenericEntity<List<Products>>(products) {
            };
            return Response.status(201).entity(entity).build();
        }
    }

    //add product to user
    @POST
    @ApiOperation(value = "add product to user", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Products.class),
            @ApiResponse(code = 404, message = "User or Product not found")
    })
    @Path("/{idUser}/products/{idProduct}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response addProductToUser(@PathParam("idUser") String idUser,
                                     @PathParam("idProduct") int idProduct) {
        User u = this.um.getUser(idUser);
        Products p = this.om.findProduct(idProduct);

        if (u == null || p == null) {
            return Response.status(404).build();
        } else {
            this.um.addProductToUser(u, p);
            return Response.status(201).entity(p).build();
        }
    }

    @GET
    @ApiOperation(value = "Get dinero del user", notes = "asdasd")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful" )
    })
    @Path("/{id}/dinero")
    @Produces(MediaType.TEXT_PLAIN)
    public Response getDineroUser(@PathParam("id") String id) {
        User u = this.um.getUser(id);
        int dinero = u.getDinero();
        return Response.status(201).entity(dinero).build();
    }
    @POST
    @Path("/login2")
    @Consumes(MediaType.APPLICATION_JSON)  // Cambiado a JSON
    @Produces(MediaType.APPLICATION_JSON)
    public Response login2(User user) {  // Ahora recibe un objeto User
        if (this.um.IniciarSesion(user.getUsername(), user.getPassword()) != null) {
            return Response.status(201).entity(user).build();
        } else {
            return Response.status(404).entity("User not found or incorrect password").build();
        }
    }

    @POST
    @Path("/register2")
    @Consumes(MediaType.APPLICATION_JSON)  // Cambiado a JSON
    @Produces(MediaType.APPLICATION_JSON)
    public Response register2(User user) {  // Ahora recibe un objeto User
        if (user.getUsername() == null || user.getPassword() == null) {
            return Response.status(400).entity("Invalid username or password").build();
        } else {
            this.um.Register(user.getUsername(), user.getPassword(), user.getEmail());
            return Response.status(201).entity(user).build();
        }
    }

}