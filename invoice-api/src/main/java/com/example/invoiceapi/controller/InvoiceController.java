package com.example.invoiceapi.controller;

import com.example.invoiceapi.service.InvoiceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/invoices")
public class InvoiceController {

    private final InvoiceService invoiceService;

    public InvoiceController(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    @PostMapping
    public ResponseEntity<?> createInvoice(@RequestBody Map<String, String> request) {
        String base64xml = request.get("base64xml");
        if (base64xml == null || base64xml.isEmpty()) {
            return ResponseEntity.badRequest().body(Map.of("error", "base64xml is required"));
        }

        try {
            invoiceService.processInvoice(base64xml);
            return ResponseEntity.status(HttpStatus.CREATED).body(Map.of("message", "Invoice saved successfully"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("error", "Internal server error", "details", e.getMessage()));
        }
    }
}
