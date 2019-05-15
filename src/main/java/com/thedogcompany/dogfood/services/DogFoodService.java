package com.thedogcompany.dogfood.services;

import com.thedogcompany.dogfood.models.DogFood;
import com.thedogcompany.dogfood.repositories.DogFoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DogFoodService {

    @Autowired
    private DogFoodRepository dogFoodRepository;

    public DogFoodService(DogFoodRepository dogFoodRepository){
        this.dogFoodRepository = dogFoodRepository;
    }

    public DogFood save(DogFood dogFood){
        return dogFoodRepository.save(dogFood);
    }
    public Optional<DogFood> findById(Long id){
        return dogFoodRepository.findById(id);
    }
}
