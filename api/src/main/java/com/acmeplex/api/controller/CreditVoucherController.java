package com.acmeplex.api.controller;

import com.acmeplex.api.dto.CreditVoucherDto;
import com.acmeplex.api.service.CreditVoucherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/credits")
@CrossOrigin(origins = "http://localhost:4200")
public class CreditVoucherController {

    @Autowired
    private final CreditVoucherService creditVoucherService;

    public CreditVoucherController(CreditVoucherService creditVoucherService) {
        this.creditVoucherService = creditVoucherService;
    }

    @GetMapping("/by-email")
    public List<CreditVoucherDto> getUnusedCreditsByCustomerEmail(@RequestParam String email){
        return creditVoucherService.getUnusedCreditVouchersByCustomerEmail(email);
    }
}
