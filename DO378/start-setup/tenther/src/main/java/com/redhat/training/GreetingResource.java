package com.redhat.training;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/hello")
public class GreetingResource {

/**    @GET
    @Produces(MediaType.TEXT_PLAIN)
public String hello() {
    return "Hello RESTEasy";
}
**/
    @GET
    @Path("/{number}")
    @Produces(MediaType.TEXT_PLAIN)
    public String hello(@PathParam("number") Integer number) {
        return "Hello RESTEasy"+String.valueOf(number*10);
    }

}
