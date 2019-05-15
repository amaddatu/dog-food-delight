package com.thedogcompany.dogfood.repositories;

import com.thedogcompany.dogfood.models.DogFood;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DogFoodRepository extends CrudRepository<DogFood, Long> {
}
