package com.volt.vest.model;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class MutualFund {
    private String fundName;
    private String fundCode;
    private BigDecimal currentValue;
    private BigDecimal units;
    private BigDecimal cagr; // Compounded Annual Growth Rate
    private LocalDate purchaseDate;
} 