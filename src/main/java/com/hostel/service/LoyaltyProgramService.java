package com.hostel.service;

import com.hostel.domain.LoyaltyProgram;
import com.hostel.exception.LoyaltyNotFoundException;
import com.hostel.repository.LoyaltyProgramRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class LoyaltyProgramService {
    private final LoyaltyProgramRepository loyaltyProgramRepository;

    public List<LoyaltyProgram> getAll() {
        return loyaltyProgramRepository.findAll();
    }

    public Optional<LoyaltyProgram> getLoyalty(Long id){
        if (loyaltyProgramRepository.findById(id).isPresent()) {
            return loyaltyProgramRepository.findById(id);
        }
        throw new LoyaltyNotFoundException();
    }

    public Boolean changeLoyaltyLevel(LoyaltyProgram loyaltyProgram) {
        try {
            loyaltyProgramRepository.saveAndFlush(loyaltyProgram);
            log.info(String.format("level of loyalty %s was change", loyaltyProgram.getId()));
        } catch (Exception e){
            log.warn(String.format("have problem with changing level of loyalty %s have error %s", loyaltyProgram.getId(), e));
            return false;
        }
        return true;
    }
}
