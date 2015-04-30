package com.excilys.rest;

import com.excilys.dto.ComputerDTO;

import javax.ws.rs.*;
import java.util.List;

@Path("/computer")
@Produces("application/xml")
public interface IComputerWebService {

    @GET
    @Path("/get")
    List<ComputerDTO> getAll();

    @GET
    @Path("/{id}")
    ComputerDTO getById(@PathParam("id") Long id);

    @PUT
    @Path("/add")
    void add(ComputerDTO computer);

    @POST
    @Path("/update")
    void update(ComputerDTO computer);

    @DELETE
    @Path("/{id}")
    void delete(@PathParam("id") Long id);
}

