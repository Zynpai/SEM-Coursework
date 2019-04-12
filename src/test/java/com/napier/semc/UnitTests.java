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

    //getContinentPopulations
    @Test
    void nulltestcontinentpopulations(){
        app.getContinentPopulations(null);
    }


    //getRegionPopulations

    @Test
    void getRegionPopulationsNull(){
        app.getRegionPopulations(null);
    }

    //getCountryPopulations

    @Test
    void getCountryPopulationsNull(){
        app.getCountryPopulations(null);
    }

    //getDistrictPopulations

    @Test
    void getDistrictPopulationsNull(){
        app.getDistrictPopulations(null);
    }



    //getCityPopulations

    @Test
    void getCityPopulationsNull(){
        app.getCityPopulations(null);
    }


    //getWorldTopCapitalCities

    @Test
    void getWorldTopCapitalCitiesNull(){
        app.getWorldTopCapitalCities();
    }


//getWorldTopCountries

    @Test
    void getWorldTopCountriesNull(){
        app.getWorldTopCountries();
    }

    //getContinentTopCapitalCities

    @Test
    void getContinentTopCapitalCitiesNull(){
        app.getContinentTopCapitalCities(null);
    }

//getRegionTopCapitalCities

    @Test
    void getRegionTopCapitalCitiesNull(){
       app.getRegionTopCapitalCities(null);
    }
//getContinentTopCountries

    @Test
    void getContinentTopCountriesNull(){
        app.getContinentTopCountries(null);
    }
    //getRegionTopCountries

    @Test
    void getRegionTopCountriesNull(){
        ArrayList<Country> pop = app.getRegionTopCountries(null);
    }
}


