package com.volt.vest.model;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Data
public class MutualFund {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String fundName;
    private String fundCode;
    private BigDecimal currentValue;
    private BigDecimal units;
    private BigDecimal cagr; // Compounded Annual Growth Rate
    private LocalDate purchaseDate;
    
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
} 