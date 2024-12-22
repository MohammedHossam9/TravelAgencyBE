package com.travelagency.event.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Invoice {
    private String invoiceId;
    private double amount;
    private String details;
    private String status;
} 