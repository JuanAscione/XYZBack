package com.example.xyzhotelddd.http;

import com.example.xyzhotelddd.domain.payment.WalletService;
import com.example.xyzhotelddd.http.dto.DepositDTO;
import com.example.xyzhotelddd.http.dto.DepositResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/wallets")
public class WalletController {

    private final WalletService walletService;

    @Autowired
    public WalletController(WalletService walletService) {
        this.walletService = walletService;
    }

    @PostMapping("/create/{idClient}")
    public ResponseEntity<String> createWallet(@PathVariable Long idClient) {
        try {
            walletService.createWallet(idClient);
            return new ResponseEntity<>("Wallet created successfully", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/deposit")
    public ResponseEntity<DepositResponse> deposit(@RequestBody DepositDTO depositDTO) {
        try {
            BigDecimal amount = depositDTO.getAmount();
            walletService.deposit(depositDTO.getIdClient(), amount, depositDTO.getCurrency());
            DepositResponse response = new DepositResponse("Deposit successful");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            DepositResponse errorResponse = new DepositResponse(e.getMessage());
            return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/balance/{idClient}")
    public ResponseEntity<Map<String, Object>> checkBalance(@PathVariable Long idClient) {
        try {
            BigDecimal balance = walletService.checkBalance(idClient);

            Map<String, Object> response = new HashMap<>();
            response.put("balance", balance);

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("error", e.getMessage());

            return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
        }
    }
}

