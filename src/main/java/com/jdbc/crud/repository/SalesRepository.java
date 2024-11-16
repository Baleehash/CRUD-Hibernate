package com.jdbc.crud.repository;

import com.jdbc.crud.model.Sales;
import org.springframework.data.repository.CrudRepository;

public interface SalesRepository  extends CrudRepository<Sales, Integer> {
}
