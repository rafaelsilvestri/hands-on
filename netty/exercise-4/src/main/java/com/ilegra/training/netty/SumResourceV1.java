package com.ilegra.training.netty;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("training/netty")
public class SumResourceV1 {


    @GET
    @Path("/sum")
    @Produces(MediaType.TEXT_PLAIN)
    public String sum(@QueryParam("num1") int num1, @QueryParam("num2") int num2) {
        int result = Service.sum(num1, num2);
        return String.valueOf(result);
    }
}
