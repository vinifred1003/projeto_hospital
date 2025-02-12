package br.edu.ifpr.projeto_hospital.Controllers;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ifpr.projeto_hospital.Models.ControlledDrugsSupplied;
import br.edu.ifpr.projeto_hospital.Models.Doctor;
import br.edu.ifpr.projeto_hospital.Models.Drug;
import br.edu.ifpr.projeto_hospital.Models.Patient;
import br.edu.ifpr.projeto_hospital.Models.Pharmaceutical;
import br.edu.ifpr.projeto_hospital.Repository.DrugRepository;
import br.edu.ifpr.projeto_hospital.Repository.DrugsSuppliedRepository;
import br.edu.ifpr.projeto_hospital.Repository.PharmaceuticalRepository;
import br.edu.ifpr.projeto_hospital.Repository.PrescriptionRepository;


@RestController
@RequestMapping("/pharmaceutical-page")
public class DrugSuppliedController {
    
    @Autowired
    private DrugsSuppliedRepository drugsSuppliedRepository;

    @Autowired
    private PharmaceuticalRepository pharmaceuticalRepository;

    @Autowired
    private DrugRepository drugRepository;

    @Autowired
    private PrescriptionRepository prescriptionRepository;

    
    @GetMapping
    @CrossOrigin(origins = "http://localhost:8080")
    public @ResponseBody Iterable<ControlledDrugsSupplied> getAllControlledDrugsSupplied() {

        return drugsSuppliedRepository.findAll();
    }

    @PostMapping
    @CrossOrigin(origins = "http://localhost:8080")
    public @ResponseBody String addNewPatient(@RequestBody ControlledDrugsSupplied cds){
        
        List<Long> drugIds = cds.getDrugs().stream().map(Drug::getDrugId).toList();
        Iterable<Drug> drugsIterable = drugRepository.findAllById(drugIds);
        List<Drug> drugs = StreamSupport.stream(drugsIterable.spliterator(), false)
                                  .collect(Collectors.toList());
                                  

        Optional<Pharmaceutical> pharmaceutical = pharmaceuticalRepository.findById(cds.getPharmaceutical().getPharmaceuticalId());

        return"ControlledDrugsSupplied inserted with success";
    }
}
