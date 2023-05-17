package com.akagera.park.services;


        import com.akagera.park.model.Form;
        import com.akagera.park.repository.FormRepo;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.stereotype.Service;

        import java.util.List;
        import java.util.Optional;
@Service
public class FormServiceImpl implements FormService {
    private final FormRepo formRepo;
    @Autowired
    public FormServiceImpl(FormRepo formRepo) {
        this.formRepo = formRepo;
    }


    public List<Form> getAllForms() {
        return formRepo.findAll();
    }

    @Override
    public Optional<Form> getFormById(int id) {
        return formRepo.findById(id);
    }
    @Override
    public void saveForm(Form form) {
        formRepo.save(form);

    }

    @Override
    public void deleteFormById(int id) {
        formRepo.deleteById(id);
    }

    @Override
    public void updateForm(Form form) {
        Optional<Form> existingForm = formRepo.findById(form.getId());

        if (existingForm.isPresent()) {
            Form updatedForm = new Form();
            updatedForm.setId(existingForm.get().getId()); // set the same ID as the existing portfolio
            formRepo.delete(existingForm.get());// delete the existing portfolio
            System.out.println("and im here");
            formRepo.save(updatedForm); // save the updated portfolio with the same ID
        } else {
            // handle the case where the portfolio with the given ID doesn't exist
        }
    }
}

