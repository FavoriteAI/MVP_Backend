package com.example.backend.service;

import com.example.backend.entity.Fairytale;
import com.example.backend.entity.Picture;
import com.example.backend.repository.FairytaleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class FairytaleService {

    private final FairytaleRepository fairytaleRepository;

    public Fairytale createFairytale(Fairytale fairytale){
        return fairytaleRepository.save(fairytale);
    }
}
