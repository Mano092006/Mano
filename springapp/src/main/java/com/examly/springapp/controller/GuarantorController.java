package com.examly.springapp.controller;

import com.examly.springapp.model.Guarantor;
import com.examly.springapp.service.GuarantorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/guarantors")
public class GuarantorController {

    @Autowired
    private GuarantorService guarantorService;

    @PostMapping
    public ResponseEntity<Guarantor> createGuarantor(@RequestBody Guarantor guarantor) {
        Guarantor savedGuarantor = guarantorService.saveGuarantor(guarantor);
        return new ResponseEntity<>(savedGuarantor, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Guarantor>> getAllGuarantors() {
        List<Guarantor> guarantors = guarantorService.getAllGuarantors();
        return ResponseEntity.ok(guarantors);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Guarantor> getGuarantorById(@PathVariable Long id) {
        return guarantorService.getGuarantorById(id)
                .map(guarantor -> ResponseEntity.ok(guarantor))
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Guarantor> updateGuarantor(@PathVariable Long id, @RequestBody Guarantor guarantor) {
        guarantor.setGuarantorId(id);
        Guarantor updatedGuarantor = guarantorService.updateGuarantor(guarantor);
        return ResponseEntity.ok(updatedGuarantor);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGuarantor(@PathVariable Long id) {
        guarantorService.deleteGuarantor(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/loan/{loanId}")
    public ResponseEntity<List<Guarantor>> getGuarantorsByLoan(@PathVariable Long loanId) {
        List<Guarantor> guarantors = guarantorService.getGuarantorsByLoan(loanId);
        return ResponseEntity.ok(guarantors);
    }
}
