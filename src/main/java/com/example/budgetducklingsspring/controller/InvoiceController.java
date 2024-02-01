package com.example.budgetducklingsspring.controller;

import com.example.budgetducklingsspring.model.Invoice;
import com.example.budgetducklingsspring.model.User;
import com.example.budgetducklingsspring.service.InvoiceService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/budgetduckling")
public class InvoiceController {

    @Autowired
    private InvoiceService invoiceService;


    @GetMapping("/")
    public List<Invoice> getAll(HttpServletRequest req){
        String owner = (String) req.getSession().getAttribute("username");
        return invoiceService.findByOwner(owner);
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<String> updateInvoice(@PathVariable Long id, @RequestBody Invoice invoice){
        invoiceService.update(id, invoice);
        return ResponseEntity.status(201).body("Update successful");
    }

    @PostMapping("/")
    public ResponseEntity<String> addInvoice(HttpServletRequest req, @RequestBody Invoice invoice){
        HttpSession session = req.getSession(false);
        if (session == null){
            return ResponseEntity.status(401).body("Can't add invoice, not logged in");
        } else {
            String owner = (String) session.getAttribute("username");
            invoice.setOwner(owner);
            if (invoiceService.addInvoice(invoice)){
                return ResponseEntity.status(201).body("Invoice Created");
            }
            return ResponseEntity.status(400).body("Something went wrong");
        }
    }

    @DeleteMapping("/search/{index}")
    public void deleteInvoice(@PathVariable Long index){
        invoiceService.deleteById(index);
    }
}
