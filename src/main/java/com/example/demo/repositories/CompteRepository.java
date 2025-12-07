package com.example.demo.repositories;

import com.example.demo.entities.Compte;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository JPA pour l'entité Compte
 * Fournit les méthodes CRUD automatiques
 */
@Repository
public interface CompteRepository extends JpaRepository<Compte, Long> {
}
