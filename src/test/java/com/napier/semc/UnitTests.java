package com.napier.semc;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class UnitTests
{
    static App app;

    @BeforeAll
    static void init()
    {
        app = new App();
    }

    //getCities
    @Test
    void nulltestgetcities(){
        ArrayList<City> cities = app.getCities(null);
        if(cities == null || cities.size() == 0 ){
            System.out.println("Test sucessful");
        }else{
            assertEquals(1,0);
        }
    }
}


