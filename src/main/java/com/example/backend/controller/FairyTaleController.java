package com.example.backend.controller;


import com.example.backend.dto.FairytaleRequest;
import com.example.backend.dto.Response;
import com.example.backend.service.FairytaleService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/fairytales")
@RequiredArgsConstructor
public class FairyTaleController {

    private final FairytaleService fairytaleService;

    @PostMapping
    public ResponseEntity<Response> createFairyTale(@RequestBody FairytaleRequest fairytaleRequest) {
        Response response = fairytaleService.createFairytale(fairytaleRequest);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<Response> getFairyTales(Pageable pageable){
        Response response = fairytaleService.getFairytales(pageable);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{fairyTaleId}")
    public ResponseEntity<Response> getFairytale(@PathVariable Long fairyTaleId){
        Response response = fairytaleService.getFairytale(fairyTaleId);
        return ResponseEntity.ok(response);
    }
}