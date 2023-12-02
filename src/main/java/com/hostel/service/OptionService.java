package com.hostel.service;

import com.hostel.domain.LoyaltyProgram;
import com.hostel.domain.Option;
import com.hostel.exception.LoyaltyNotFoundException;
import com.hostel.repository.OptionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service

public class OptionService {
    private final OptionRepository optionRepository;

    public List<Option> getAll() {
        return optionRepository.findAll();
    }

    public Optional<Option> getLoyalty(Long id){
        if (optionRepository.findById(id).isPresent()) {
            return optionRepository.findById(id);
        }
        throw new LoyaltyNotFoundException();
    }

    public Boolean changeOption(Option option) {
        try {
            optionRepository.saveAndFlush(option);
            log.info(String.format("option %s was change", option.getId()));
        } catch (Exception e){
            log.warn(String.format("have problem with changing option %s have error %s", option.getId(), e));
            return false;
        }
        return true;
    }
}
