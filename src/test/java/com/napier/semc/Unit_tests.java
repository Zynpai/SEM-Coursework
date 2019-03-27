package com.napier.semc;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class Unit_tests
{
    static App app;

    @BeforeAll
    static void init()
    {
        app = new App();
    }

    //printPopulationReport
    @Test
    void nulltestpopreport(){

        app.printPopulationReport(null, null, null, null, false, false, false, false);

    }
    @Test
    void nulltestpopreport2(){

        app.printPopulationReport(null, null, null, null, true, true, true, true);

    }
    @Test
    void nulltestpopreport3(){

        app.printPopulationReport(null, null, null, null, false, true, true, true);

    }
    @Test
    void nulltestpopreport4(){

        app.printPopulationReport(null, null, null, null, false, false, true, true);

    }
    @Test
    void nulltestpopreport5(){

        app.printPopulationReport(null, null, null, null, false, false, false, true);

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


