package com.redhat.training.order;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import io.micrometer.core.annotation.Counted;
import io.micrometer.core.annotation.Timed;

import java.util.Random;

@Path("/")
@ApplicationScoped
public class OrderService {

    @GET
    @Path("/order")
    @Produces(MediaType.TEXT_PLAIN)
    // 1. Add a counter to count the spl50 orders placed
    // 2. Add a simple timer to track the response time
    @Counted(value = "orders_placed", description = "count of spl50 orders placed")
    @Timed(value = "order_process_time", description = "A measure of how long it takes to process an order")
    public String processOrder() {

        try {
            Thread.sleep(getRandom(1, 3)*100); // introduce random delay
        }
        catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        // generate and return a random order id
        return "Thank you for your order! Your order id is " + getRandom(1, 10000) + "\n";
    }

    @GET
    @Path("/rating")
    @Produces(MediaType.TEXT_PLAIN)
    public String getRating() {
        Integer rating = generateRandomRating(); // generate a random rating between 1-5

        return "You rated the order process " + rating + " stars. Thank you for your feedback!\n";
    }
    // Add a counter to track the rating
    @Counted(value = "order_process_rating", description = "Overall customer rating for the order process")
    Integer generateRandomRating() {
        return getRandom(1, 5);
    }

    private Integer getRandom(int min, int max) {
        Random random = new Random();
        Integer number = random.nextInt((max - min) + 1) + min;

        return number;
    }
}
