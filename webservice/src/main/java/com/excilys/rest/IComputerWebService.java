package com.excilys.rest;

import com.excilys.model.Computer;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import java.util.List;

@Path("/computer")
@Produces("application/json")
public interface IComputerWebService {

	@GET
	@Path("/all")
	List<Computer> getAll();

    @GET
    @Path("/{id}")
    Computer getById(@PathParam("id") Long id);
}
