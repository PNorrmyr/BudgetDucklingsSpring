package com.example.budgetducklingsspring.service;
import com.example.budgetducklingsspring.model.Invoice;
import com.example.budgetducklingsspring.repository.InvoiceRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InvoiceService {

    @Autowired
    private InvoiceRepository invoiceRepository;


    public boolean addInvoice(Invoice invoice) {
        invoiceRepository.save(invoice);
        return true;
    }

    public List<Invoice> getAll(){
        return invoiceRepository.findAll();
    }

    public List<Invoice> findByOwner(String owner){
        return invoiceRepository.findByOwner(owner);
    }


    public void deleteById(Long id){
        invoiceRepository.deleteById(id);
    }

    public void update(Long id, Invoice invoice) {
        Optional<Invoice> optionalInvoice = invoiceRepository.findById(id);

        if (optionalInvoice.isPresent()) {
            Invoice existingInvoice = optionalInvoice.get();
            if (invoice.getTitle() != null) {
                existingInvoice.setTitle(invoice.getTitle());
            }
            if (invoice.getCategory() != null) {
                existingInvoice.setCategory(invoice.getCategory());
            }
            if (invoice.getDate() != null) {
                existingInvoice.setDate(invoice.getDate());
            }
            if (invoice.getDescription() != null) {
                existingInvoice.setDescription(invoice.getDescription());
            }
            invoiceRepository.save(existingInvoice);
        }
    }

}
