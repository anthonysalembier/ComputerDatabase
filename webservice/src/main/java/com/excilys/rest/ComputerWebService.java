package com.excilys.rest;

import com.excilys.dto.ComputerDTO;
import com.excilys.mapper.ComputerDTOMapper;
import com.excilys.service.IComputerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComputerWebService implements IComputerWebService {
	
	@Autowired
	private IComputerService computerService;
    @Autowired
    private ComputerDTOMapper computerDTOMapper;

    @Override
	public List<ComputerDTO> getAll() {
		return computerDTOMapper.getComputerListDTO(computerService.getAll());
	}

    @Override
    public ComputerDTO getById(Long id) {
        return computerDTOMapper.getComputerDTO(computerService.getById(id));
    }

    @Override
    public void add(ComputerDTO computer) {
        // TODO
    }

    @Override
    public void update(ComputerDTO computer) {
        // TODO
    }

    @Override
    public void delete(Long id) {
        // TODO
    }

}
