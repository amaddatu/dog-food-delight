package com.thedogcompany.dogfood.controllers;

import com.thedogcompany.dogfood.models.DogFood;
import com.thedogcompany.dogfood.repositories.DogFoodRepository;
import com.thedogcompany.dogfood.services.DogFoodService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

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

        DogFood dogFood = new DogFood("Kibbles", "Kibbles without the bits");
        DogFood dogFoodExpectedOut = new DogFood(1L, "Kibbles", "Kibbles without the bits");
        //Setup
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
}
