package com.thedogcompany.dogfood.controllers;

import com.thedogcompany.dogfood.models.DogFood;
import com.thedogcompany.dogfood.repositories.DogFoodRepository;
import com.thedogcompany.dogfood.services.DogFoodService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
//import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DogFoodControllerTests {

    @Mock
    private DogFoodRepository dogFoodRepository;

    @Test
    public void contextLoads() {
    }

    @Test
    public void classTest(){
        try{
            Class.forName("com.thedogcompany.dogfood.controllers.DogFoodController");
        }
        catch(ClassNotFoundException e){
//            assertTrue(false);
            fail();
        }
    }

    // Legacy Function Test
    @Test
    public void controllerEndPointTest(){
        DogFoodService dbs = new DogFoodService(dogFoodRepository);
        DogFoodController dc = new DogFoodController(dbs);
        try{
            dc.getClass().getDeclaredMethod("createDogFood", DogFood.class);
        }
        catch(NoSuchMethodException e){
            fail();
        }

        try{
            dc.getClass().getDeclaredMethod("readDogFood", Long.class);
        }
        catch(NoSuchMethodException e){
            fail();
        }

        try{
            dc.getClass().getDeclaredMethod("updateDogFood", Long.class, DogFood.class);
        }
        catch(NoSuchMethodException e){
            fail();
        }

        try{
            dc.getClass().getDeclaredMethod("deleteDogFood", Long.class);
        }
        catch(NoSuchMethodException e){
            fail();
        }
    }

    @Test
    public void postTest(){
        //Setup
        DogFood dogFood = new DogFood("Kibbles", "Kibbles without the bits");
        DogFood dogFoodExpectedOut = new DogFood(1L, "Kibbles", "Kibbles without the bits");

        given(dogFoodRepository.save( dogFood )).willReturn( dogFoodExpectedOut );
        DogFoodService dbs = new DogFoodService(dogFoodRepository);
        DogFoodController dbc = new DogFoodController(dbs);

        //Execute
        DogFood dogFoodOut = dbc.createDogFood(dogFood);


        //Assert
        then(dogFoodRepository).should(times(1)).save(dogFood);
        assertEquals(dogFoodOut.getId(), dogFoodExpectedOut.getId());
        assertEquals(dogFoodOut.getName(), dogFoodExpectedOut.getName());
        assertEquals(dogFoodOut.getDescription(), dogFoodExpectedOut.getDescription());

        //Teardown
    }

    @Test
    public void getTest(){
        DogFood dogFood = new DogFood(1L, "Kibbles", "Kibbles without the bits");
        Optional<DogFood> dfOptional = Optional.of(dogFood);
        given(dogFoodRepository.findById( 1L )).willReturn(dfOptional);
        DogFoodService dbs = new DogFoodService(dogFoodRepository);
        DogFoodController dbc = new DogFoodController(dbs);

        //Execute
        Optional<DogFood> dogFoodOut = dbc.readDogFood( 1L );

        //Assert
        then(dogFoodRepository).should(times(1)).findById(1L);
        assertEquals(dogFoodOut.get().getId(), dogFood.getId());
        assertEquals(dogFoodOut.get().getName(), dogFood.getName());
        assertEquals(dogFoodOut.get().getDescription(), dogFood.getDescription());

        //Teardown
    }

    @Test
    public void deleteTest(){
        DogFood dogFood = new DogFood(1L, "Kibbles", "Kibbles without the bits");
        Optional<DogFood> dfOptional = Optional.of(dogFood);
        Optional<DogFood> dfOptional2 = Optional.empty();
        given(dogFoodRepository.findById(1L)).willReturn(dfOptional);
        given(dogFoodRepository.findById(2L)).willReturn(dfOptional2);

        DogFoodService dbs = new DogFoodService(dogFoodRepository);
        DogFoodController dbc = new DogFoodController(dbs);

        //Execute
        boolean bool = dbc.deleteDogFood( 1L );
        boolean bool2 = dbc.deleteDogFood( 2L );

        //Assert
        then(dogFoodRepository).should(times(1)).deleteById(1L);
        then(dogFoodRepository).should(times(1)).findById(1L);
        then(dogFoodRepository).should(times(1)).findById(2L);
        assertEquals(bool, true);
        assertEquals(bool2, false);

        //Teardown
    }
}
