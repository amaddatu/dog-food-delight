package com.thedogcompany.dogfood.services;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.fail;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DogFoodServiceTests {
    @Test
    public void contextLoads() {
    }

    @Test
    public void classTest(){
        try{
            Class.forName("com.thedogcompany.dogfood.services.DogFoodService");
        }
        catch(ClassNotFoundException e){
//            assertTrue(false);
            fail();
        }
    }
}
