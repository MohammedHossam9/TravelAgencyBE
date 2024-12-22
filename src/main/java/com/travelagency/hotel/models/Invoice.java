package com.travelagency.hotel.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class Invoice {
    private Long invoiceId;
    private String details;
    private Double amount;
    private String status;
} 