package tn.esprit.autoloc.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.domain.Employe;
import tn.esprit.autoloc.service.IEmployeService;

import java.util.List;

@Tag(name = "Gestion des Employés", description = "Endpoints de gestion du personnel et des rôles d'employés")
@RestController
@AllArgsConstructor
@RequestMapping("/employe")
public class EmployeRestController {

    private final IEmployeService employeService;

    // http://localhost:8089/gestionprojets/employe/retrieve-all-employes
    @Operation(summary = "Récupérer tous les employés", description = "Retourne la liste complète des employés")
    @GetMapping("/retrieve-all-employes")
    public List<Employe> getEmployes() {
        return employeService.retrieveAllEmployes();
    }

    // http://localhost:8089/gestionprojets/employe/retrieve-employe/1
    @Operation(summary = "Récupérer un employé par ID", description = "Retourne les détails d'un employé selon son identifiant")
    @GetMapping("/retrieve-employe/{employe-id}")
    public Employe retrieveEmploye(@PathVariable("employe-id") Long employeId) {
        return employeService.retrieveEmploye(employeId);
    }

    // http://localhost:8089/gestionprojets/employe/add-employe
    @Operation(summary = "Ajouter un employé", description = "Crée un nouvel employé avec son rôle")
    @PostMapping("/add-employe")
    public Employe addEmploye(@RequestBody Employe employe) {
        return employeService.addEmploye(employe);
    }

    // http://localhost:8089/gestionprojets/employe/modify-employe
    @Operation(summary = "Modifier un employé", description = "Met à jour les données d'un employé")
    @PutMapping("/modify-employe")
    public Employe modifyEmploye(@RequestBody Employe employe) {
        return employeService.modifyEmploye(employe);
    }

    // http://localhost:8089/gestionprojets/employe/remove-employe/1
    @Operation(summary = "Supprimer un employé", description = "Supprime un employé par son identifiant")
    @DeleteMapping("/remove-employe/{employe-id}")
    public void removeEmploye(@PathVariable("employe-id") Long employeId) {
        employeService.removeEmploye(employeId);
    }
}
