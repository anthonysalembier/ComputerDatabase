package com.excilys.service;

import com.excilys.model.Computer;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IComputerService {
    List<Computer> getAll();

    List<Computer> getAll(Pageable page);

    Computer getById(long id);

    List<Computer> getByName(String name, Pageable page);

    void create(Computer computer);

    void update(Computer computer);

    void delete(long id);

    long count();

    int countByName(String name);

}
