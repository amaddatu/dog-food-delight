package com.thedogcompany.dogfood.controllers;

import com.thedogcompany.dogfood.models.DogFood;
import com.thedogcompany.dogfood.services.DogFoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/dog-food")
public class DogFoodController {

    @Autowired
    private DogFoodService dogFoodService;

    public DogFoodController(DogFoodService dogFoodService){
        this.dogFoodService = dogFoodService;
    }

    @PostMapping("") // POST at the url "/dog-food"
    public DogFood createDogFood(@RequestBody DogFood dogFood){
        return dogFoodService.save(dogFood);
    }

    @GetMapping("/{id}")
    public DogFood readDogFood(@PathVariable Long id){
        return null;

    }

    @PutMapping("/{id}")
    public DogFood updateDogFood(@PathVariable Long id, @RequestBody DogFood dogBreed) {
        return null;
    }

    @DeleteMapping("/{id}")
    public boolean deleteDogFood(@PathVariable Long id){
        return false;
    }
}
