/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.portfolio.asv.Repository;

import com.portfolio.asv.Entity.Persona;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonaRepositoryInterface extends JpaRepository <Persona,Integer> {
   public Optional<Persona> findByNombre(String nombre);
   public boolean existsByNombre(String nombre); 
}
