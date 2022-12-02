package com.example.application.repositories;

import com.example.application.entities.InfoEntity;
import org.springframework.data.repository.CrudRepository;

public interface InfoRepository extends CrudRepository<InfoEntity, Long> {
}
