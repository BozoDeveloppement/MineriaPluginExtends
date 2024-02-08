package fr.parsival.mineriapluginextends.bank;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class BankResponse {
    private double amount;
    private boolean success;
}
