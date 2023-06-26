/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.portfolio.asv.Controller;

import com.portfolio.asv.Dto.dtoSkills;
import com.portfolio.asv.Entity.Skills;
import com.portfolio.asv.Security.Controller.Mensaje;
import com.portfolio.asv.Service.Sskills;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = {"https://my-proyect-final-ap.web.app","http://localhost:4200"})
@RequestMapping("/skill")

public class CSkills {
    
@Autowired
    Sskills sskills;

    @GetMapping("/lista")
    public ResponseEntity<List<Skills>> list() {
        List<Skills> list = sskills.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<Skills> getById(@PathVariable("id") int id) {
        if (!sskills.existsById(id)) {
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        }
        Skills skills = sskills.getOne(id).get();
        return new ResponseEntity(skills, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id) {
        if (!sskills.existsById(id)) {
            return new ResponseEntity(new Mensaje("El ID no existe"), HttpStatus.NOT_FOUND);
        }
        sskills.delete(id);
        return new ResponseEntity(new Mensaje("Skill eliminado"), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody dtoSkills dtoskills) {
        if (StringUtils.isBlank(dtoskills.getNombre())) {
            return new ResponseEntity(new Mensaje("El campo no puede estar vacio"), HttpStatus.BAD_REQUEST);
        }
        if (sskills.existsByNombre(dtoskills.getNombre())) {
            return new ResponseEntity(new Mensaje("Ya existe la Skill"), HttpStatus.BAD_REQUEST);
        }

        Skills skills = new Skills(dtoskills.getNombre(), dtoskills.getPorcentaje());
        sskills.save(skills);

        return new ResponseEntity(new Mensaje("Skill agregada"), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody dtoSkills dtoskills) {
        //Validamos si existe el ID
        if (!sskills.existsById(id)) {
            return new ResponseEntity(new Mensaje("El ID no existe"), HttpStatus.BAD_REQUEST);
        }
        //Compara nombre de skills
        if (sskills.existsByNombre(dtoskills.getNombre()) && sskills.getByNombre(dtoskills.getNombre()).get()
                .getId() != id) {
            return new ResponseEntity(new Mensaje("Ya existe la Skill"), HttpStatus.BAD_REQUEST);
        }
        //No puede estar vacio
        if (StringUtils.isBlank(dtoskills.getNombre())) {
            return new ResponseEntity(new Mensaje("El campo no puede estar vacio"), HttpStatus.BAD_REQUEST);
        }

        Skills skills = sskills.getOne(id).get();
        skills.setNombre(dtoskills.getNombre());
        skills.setPorcentaje(dtoskills.getPorcentaje());

        sskills.save(skills);
        return new ResponseEntity(new Mensaje("Skill actualizada"), HttpStatus.OK);

    }
}



