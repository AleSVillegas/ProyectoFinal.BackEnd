/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.portfolio.asv.Service;

import com.portfolio.asv.Entity.Persona;
import com.portfolio.asv.Repository.PersonaRepositoryInterface;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class PersonaService {
    @Autowired PersonaRepositoryInterface personaRepositoryInterface;
    
     public List<Persona> list(){
         return personaRepositoryInterface.findAll();
     }
     
     public Optional<Persona> getOne(int id){
         return personaRepositoryInterface.findById(id);
     }
     
     public Optional<Persona> getByNombre(String nombre){
         return personaRepositoryInterface.findByNombre(nombre);
     }
     
     public void save(Persona persona){
         personaRepositoryInterface.save(persona);
     }
     
     public void delete(int id){
         personaRepositoryInterface.deleteById(id);
     }
     
     public boolean existsById(int id){
         return personaRepositoryInterface.existsById(id);
     }
     
     public boolean existsByNombre(String nombre){
         return personaRepositoryInterface.existsByNombre(nombre);
     }

}    
