package org.example;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class PhoneCall {

    private int id;
    private String type;
    private LocalDate date;
    private int customer;
    private int manager;
}
