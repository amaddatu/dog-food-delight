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

    public boolean delete(Long id){
        if(dogFoodRepository.findById(id).isPresent()){
            dogFoodRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public Optional<DogFood> update(Long id, DogFood dogFood){
        Optional<DogFood> dogFoodOptional = dogFoodRepository.findById(id);
        if(dogFoodOptional.isPresent()){
            DogFood dogFoodItem = dogFoodOptional.get();
            if(dogFood.getName() != null){
                dogFoodItem.setName(dogFood.getName());
            }
            if(dogFood.getDescription() != null){
                dogFoodItem.setDescription(dogFood.getDescription());
            }
            dogFoodRepository.save(dogFoodItem);
            return Optional.of(dogFoodItem);
        }
        return Optional.empty();
    }
}
