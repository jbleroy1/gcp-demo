package com.google.cloud;

import java.util.Base64;
import java.util.concurrent.TimeUnit;

import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.google.cloud.data.EncodingOperation;

@Path("/b64")
public class Base64Resource {

    @POST
    @Path("/encode")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public EncodingOperation encode(EncodingOperation op) {
        waitDelay(op.getDelay());
        op.setResult(Base64.getEncoder().encodeToString(op.getValue().getBytes()));
        return op;

    }

    @POST
    @Path("/decode")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public EncodingOperation decode(EncodingOperation op) {
        waitDelay(op.getDelay());
        op.setResult(new String(Base64.getDecoder().decode(op.getValue())));
        return op;

    }

  

    @GET
    @Path("/decode")
    @Produces(MediaType.APPLICATION_JSON)
    public EncodingOperation decodeByParam(
            @DefaultValue("No-Value") @QueryParam("value") String value,
            @DefaultValue("0") @QueryParam("delay") int delay) {
                waitDelay(delay);
        return EncodingOperation.builder()
                .value(value)
                .result(new String(Base64.getDecoder().decode(value)))
                .delay(delay)
                .build();

    }

    @GET
    @Path("/encode")
    @Produces(MediaType.APPLICATION_JSON)
    public EncodingOperation encodeByParam(
            @DefaultValue("No-Value") @QueryParam("value") String value,
            @DefaultValue("0") @QueryParam("delay") int delay) {
                waitDelay(delay);
        return EncodingOperation.builder()
                .value(value)
                .result(Base64.getEncoder().encodeToString(value.getBytes()))
                .delay(delay)
                .build();

    }

    private void waitDelay(int delay){
        try {
            TimeUnit.SECONDS.sleep(delay);
        } catch (InterruptedException ie) {
            Thread.currentThread().interrupt();
        }
    }

}