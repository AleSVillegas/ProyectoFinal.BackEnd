/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.portfolio.asv.Repository;

import com.portfolio.asv.Entity.Skills;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RSkills extends JpaRepository<Skills, Integer>{
  Optional<Skills> findByNombre(String nombre);
  public boolean existsByNombre(String nombre);
}    

