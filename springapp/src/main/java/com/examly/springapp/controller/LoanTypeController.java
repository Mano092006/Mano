package com.examly.springapp.controller;

import com.examly.springapp.model.LoanType;
import com.examly.springapp.service.LoanTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/loantypes")
public class LoanTypeController {

    @Autowired
    private LoanTypeService loanTypeService;

    @PostMapping
    public ResponseEntity<LoanType> createLoanType(@RequestBody LoanType loanType) {
        LoanType savedLoanType = loanTypeService.saveLoanType(loanType);
        return new ResponseEntity<>(savedLoanType, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<LoanType>> getAllLoanTypes() {
        List<LoanType> loanTypes = loanTypeService.getAllLoanTypes();
        return ResponseEntity.ok(loanTypes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LoanType> getLoanTypeById(@PathVariable Long id) {
        return loanTypeService.getLoanTypeById(id)
                .map(loanType -> ResponseEntity.ok(loanType))
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<LoanType> updateLoanType(@PathVariable Long id, @RequestBody LoanType loanType) {
        loanType.setLoanTypeId(id);
        LoanType updatedLoanType = loanTypeService.updateLoanType(loanType);
        return ResponseEntity.ok(updatedLoanType);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLoanType(@PathVariable Long id) {
        loanTypeService.deleteLoanType(id);
        return ResponseEntity.noContent().build();
    }
}
