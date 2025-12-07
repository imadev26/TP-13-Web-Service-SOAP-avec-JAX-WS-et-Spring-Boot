package com.example.demo.ws;

import com.example.demo.entities.Compte;
import com.example.demo.entities.TypeCompte;
import com.example.demo.repositories.CompteRepository;

import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

/**
 * Service SOAP pour la gestion des comptes bancaires
 * Utilise JAX-WS avec Apache CXF
 */
@Component
@WebService(serviceName = "BanqueWS")
public class CompteSoapService {

    @Autowired
    private CompteRepository compteRepository;

    /**
     * Récupère tous les comptes
     * 
     * @return Liste de tous les comptes
     */
    @WebMethod
    public List<Compte> getComptes() {
        return compteRepository.findAll();
    }

    /**
     * Récupère un compte par son ID
     * 
     * @param id Identifiant du compte
     * @return Le compte correspondant ou null
     */
    @WebMethod
    public Compte getCompteById(@WebParam(name = "id") Long id) {
        return compteRepository.findById(id).orElse(null);
    }

    /**
     * Crée un nouveau compte
     * 
     * @param solde Solde initial
     * @param type  Type de compte (COURANT ou EPARGNE)
     * @return Le compte créé
     */
    @WebMethod
    public Compte createCompte(@WebParam(name = "solde") double solde,
            @WebParam(name = "type") TypeCompte type) {
        Compte compte = new Compte(null, solde, new Date(), type);
        return compteRepository.save(compte);
    }

    /**
     * Supprime un compte par son ID
     * 
     * @param id Identifiant du compte à supprimer
     * @return true si suppression réussie, false sinon
     */
    @WebMethod
    public boolean deleteCompte(@WebParam(name = "id") Long id) {
        if (compteRepository.existsById(id)) {
            compteRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
