package br.edu.ifpr.projeto_hospital.Controllers;

import java.time.LocalDateTime;

import java.util.List;

import java.util.Optional;
import java.util.stream.Collectors;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ifpr.projeto_hospital.Models.ControlledDrugsSupplied;

import br.edu.ifpr.projeto_hospital.Models.Drug;
import br.edu.ifpr.projeto_hospital.Models.DrugSupplied;

import br.edu.ifpr.projeto_hospital.Models.Pharmaceutical;
import br.edu.ifpr.projeto_hospital.Repository.DrugRepository;
import br.edu.ifpr.projeto_hospital.Repository.DrugsSuppliedRepository;
import br.edu.ifpr.projeto_hospital.Repository.PharmaceuticalRepository;


@RestController
@RequestMapping("/pharmaceutical-page")
public class DrugSuppliedController {
    
    @Autowired
    private DrugsSuppliedRepository drugsSuppliedRepository;

    @Autowired
    private PharmaceuticalRepository pharmaceuticalRepository;

    @Autowired
    private DrugRepository drugRepository;

    
    @GetMapping
    @CrossOrigin(origins = "http://localhost:8080")
    public @ResponseBody Iterable<ControlledDrugsSupplied> getAllControlledDrugsSupplied() {

        return drugsSuppliedRepository.findAll();
    }

    @PostMapping
    @CrossOrigin(origins = "http://localhost:8080")
    public @ResponseBody String addNewControlledDrugSupplied(@RequestBody ControlledDrugsSupplied cds) {
        // Validar a farmacêutica
        Optional<Pharmaceutical> pharmaceutical = pharmaceuticalRepository
                .findById(cds.getPharmaceutical().getPharmaceuticalId());
        if (pharmaceutical.isEmpty()) {
            return "Pharmaceutical not found";
        }

        // Criar a entidade principal
        ControlledDrugsSupplied CDS = new ControlledDrugsSupplied();
        CDS.setIssuedDate(LocalDateTime.now());
        CDS.setPharmaceutical(pharmaceutical.get());

        // Processar os remédios e suas quantidades
        List<DrugSupplied> drugSuppliedList = cds.getDrugSuppliedList().stream().map(drugSupplied -> {
            Optional<Drug> drug = drugRepository.findById(drugSupplied.getDrug().getDrugId());
            if (drug.isEmpty()) {
                throw new RuntimeException("Drug not found: " + drugSupplied.getDrug().getDrugId());
            }

            DrugSupplied ds = new DrugSupplied();
            ds.setControlledDrugsSupplied(CDS);
            ds.setDrug(drug.get());
            ds.setQuantity(drugSupplied.getQuantity());
            return ds;
        }).collect(Collectors.toList());

        CDS.setDrugSuppliedList(drugSuppliedList);

        // Salvar no banco
        drugsSuppliedRepository.save(CDS);

        return "Inserted with success";
    }

    @PutMapping("/{id}")
    @CrossOrigin(origins = "http://localhost:8080")
    public @ResponseBody String updateControlledDrugSupplied(@PathVariable Long id,
            @RequestBody ControlledDrugsSupplied cds) {
        // Verificar se o registro existe
        Optional<ControlledDrugsSupplied> existingCDSOptional = drugsSuppliedRepository.findById(id);
        if (existingCDSOptional.isEmpty()) {
            return "ControlledDrugsSupplied not found";
        }

        ControlledDrugsSupplied existingCDS = existingCDSOptional.get();

        // Validar a farmacêutica
        Optional<Pharmaceutical> pharmaceutical = pharmaceuticalRepository
                .findById(cds.getPharmaceutical().getPharmaceuticalId());
        if (pharmaceutical.isEmpty()) {
            return "Pharmaceutical not found";
        }

        // Atualizar os campos principais
        existingCDS.setIssuedDate(LocalDateTime.now()); // Atualizar a data/hora
        existingCDS.setPharmaceutical(pharmaceutical.get());

        // Atualizar os remédios e suas quantidades
        List<DrugSupplied> updatedDrugSuppliedList = cds.getDrugSuppliedList().stream().map(drugSupplied -> {
            Optional<Drug> drug = drugRepository.findById(drugSupplied.getDrug().getDrugId());
            if (drug.isEmpty()) {
                throw new RuntimeException("Drug not found: " + drugSupplied.getDrug().getDrugId());
            }

            DrugSupplied ds = new DrugSupplied();
            ds.setControlledDrugsSupplied(existingCDS);
            ds.setDrug(drug.get());
            ds.setQuantity(drugSupplied.getQuantity());
            return ds;
        }).collect(Collectors.toList());

        // Limpar os remédios antigos e adicionar os novos
        existingCDS.getDrugSuppliedList().clear();
        existingCDS.getDrugSuppliedList().addAll(updatedDrugSuppliedList);

        // Salvar no banco
        drugsSuppliedRepository.save(existingCDS);

        return "Updated with success";
    }

    @DeleteMapping("/{id}")
    @CrossOrigin(origins = "http://localhost:8080")
    public @ResponseBody String deleteControlledDrugSupplied(@PathVariable Long id) {
        // Verificar se o registro existe
        Optional<ControlledDrugsSupplied> existingCDSOptional = drugsSuppliedRepository.findById(id);
        if (existingCDSOptional.isEmpty()) {
            return "ControlledDrugsSupplied not found";
        }

        // Deletar o registro
        drugsSuppliedRepository.deleteById(id);

        return "Deleted with success";
    }

}
