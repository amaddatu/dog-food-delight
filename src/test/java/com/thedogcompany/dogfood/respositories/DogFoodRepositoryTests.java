package com.thedogcompany.dogfood.respositories;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.fail;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DogFoodRepositoryTests {
    @Test
    public void contextLoads() {
    }

    @Test
    public void classTest(){
        try{
            Class.forName("com.thedogcompany.dogfood.respositories.DogFoodRepository");
        }
        catch(ClassNotFoundException e){
//            assertTrue(false);
            fail();
        }
    }
}
