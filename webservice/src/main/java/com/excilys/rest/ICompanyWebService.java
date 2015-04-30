package com.excilys.rest;

import com.excilys.dto.CompanyDTO;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import java.util.List;

@Path("/company")
@Produces("application/xml")
public interface ICompanyWebService {

    @GET
    @Path("/get")
    List<CompanyDTO> getAll();

    @GET
    @Path("/{id}")
    CompanyDTO getById(@PathParam("id") Long id);

}