package com.example.kisi.controller;

import com.example.kisi.entity.Kisi;
import com.example.kisi.repository.KisiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/kisiler")
@CrossOrigin(origins = "http://localhost:4200")
public class KisiController {

    @Autowired
    private KisiRepository kisiRepository;

    @GetMapping
    public List<Kisi> getAllKisiler() {
        return kisiRepository.findAll();
    }

    @PostMapping
    public Kisi createKisi(@RequestBody Kisi kisi) {
        return kisiRepository.save(kisi);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Kisi> getKisiById(@PathVariable Long id) {
        Kisi kisi = kisiRepository.findById(id).orElse(null);
        if (kisi == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(kisi);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Kisi> updateKisi(@PathVariable Long id, @RequestBody Kisi kisiDetails) {
        Kisi kisi = kisiRepository.findById(id).orElse(null);
        if (kisi == null) {
            return ResponseEntity.notFound().build();
        }

        kisi.setName(kisiDetails.getName());
        kisi.setSurname(kisiDetails.getSurname());
        kisi.setGender(kisiDetails.getGender());
        kisi.setAddress(kisiDetails.getAddress());

        Kisi updatedKisi = kisiRepository.save(kisi);
        return ResponseEntity.ok(updatedKisi);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteKisi(@PathVariable Long id) {
        Kisi kisi = kisiRepository.findById(id).orElse(null);
        if (kisi == null) {
            return ResponseEntity.notFound().build();
        }

        kisiRepository.delete(kisi);
        return ResponseEntity.noContent().build();
    }
}
