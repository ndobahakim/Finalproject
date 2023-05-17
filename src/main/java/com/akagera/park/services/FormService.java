package com.akagera.park.services;

import com.akagera.park.model.Form;

import java.util.List;
import java.util.Optional;

public interface FormService {
    List<Form> getAllForms();
    Optional<Form> getFormById(int id);
    void saveForm(Form form);
    void deleteFormById(int id);
    void updateForm(Form form);
}
