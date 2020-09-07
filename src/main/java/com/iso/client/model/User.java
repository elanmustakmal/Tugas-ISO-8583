package com.iso.client.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class User {
    private String sourceAccountName;
    private String sourceAccountNumber;
    private String destinationAccountNumber;
    private String destinationAccountName;
    private String encryptedPinBlock;
    private String pan;
    private String amount;
    private String bankCode;

}
