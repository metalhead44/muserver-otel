package com.nitin.example;



import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.muserver.MuServer;
import io.muserver.MuServerBuilder;
import io.muserver.rest.RestHandlerBuilder;

public class JaxRSExample {
	
	static Logger logger = LoggerFactory.getLogger(JaxRSExample.class);

    public static void main(String[] args) {
    	
    	logger.info("Helo World");

        Map<Integer, String> users = new HashMap<>();
        users.put(1, "Mike");
        users.put(2, "Sam");
        users.put(3, "Dan");
        UserResource userResource = new UserResource(users);

        MuServer server = MuServerBuilder.httpServer()
            .addHandler(RestHandlerBuilder.restHandler(userResource))
            .start();

        System.out.println("API example: " + server.uri().resolve("/jaxrsexample/users/1"));

    }

    @Path("/jaxrsexample/users")
    public static class UserResource {

        private final Map<Integer, String> users;

        public UserResource(Map<Integer, String> users) {
            this.users = users;
        }

        @GET
        @Path("/{id}")
        @Produces("application/json")
        public String get(@PathParam("id") int id) {
            String name = users.get(id);
            if (name == null) {
                throw new NotFoundException("No user with id " + id);
            }
            return new JSONObject()
                .put("id", id)
                .put("name", name)
                .toString(4);
        }

    }
}
