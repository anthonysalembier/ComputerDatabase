package com.excilys.rest;

import com.excilys.dto.ComputerDTO;
import com.excilys.mapper.ComputerDTOMapper;
import com.excilys.service.IComputerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/computer")

public class ComputerWebService {
	
	@Autowired
	private IComputerService computerService;
    @Autowired
    private ComputerDTOMapper computerDTOMapper;

    @RequestMapping(value="/get", produces="application/json")
	public List<ComputerDTO> getAll() {
		return computerDTOMapper.getComputerListDTO(computerService.getAll());
	}

    @RequestMapping(value="/{id}", produces="application/json")
    public ComputerDTO getById(@PathVariable Long id) {
        return computerDTOMapper.getComputerDTO(computerService.getById(id));
    }

    @RequestMapping(value="/add", method= RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.OK)
    public void add(ComputerDTO computer) {
        // TODO
    }

    @RequestMapping(value="/{id}", method= RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    public void update(ComputerDTO computer) {
        // TODO
    }

    @RequestMapping(value="/{id}", method= RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.OK)
    public void delete(Long id) {
        // TODO
    }

}
