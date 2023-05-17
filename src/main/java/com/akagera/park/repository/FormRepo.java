package com.akagera.park.repository;

import com.akagera.park.model.Form;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

    @Repository
    public interface FormRepo extends JpaRepository<Form, Integer> {
    }



