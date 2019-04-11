package com.napier.semc;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class IntegrationTests {
    static App app;

    @BeforeAll
    static void init() {
        app = new App();
        app.connect("localhost:33060");

    }

//getCities
    @Test
    void emptytestgetcities(){
        ArrayList<City> cities = app.getCities("");
        assertEquals(0, cities.size());
    }
    @Test
    void datatestgetcities(){
        ArrayList<City> cities = app.getCities("RUS");
        System.out.println(cities.size());

    }
    

//getContinentPopulations
    @Test
    void testcontinentpopulations_garbage(){
        ArrayList<Country> pop = app.getContinentPopulations("jadghashdfklasjh");
        assertEquals(0, pop.size());
    }
    @Test
    void testcontinentpopulations_empty(){
        ArrayList<Country> pop = app.getContinentPopulations("");
        assertEquals(0, pop.size());
    }


//getRegionPopulations

    @Test
    void getRegionPopulationsGarbage(){
        ArrayList<Country> pop = app.getRegionPopulations("fjkhakshdfaslkh");
        assertEquals(0,pop.size());
    }
    @Test
    void getRegionPopulationsEmpty(){
        ArrayList<Country> pop = app.getRegionPopulations("");
        assertEquals(0,pop.size());
    }


//getCountryPopulations

    @Test
    void getCountryPopulationsGarbage(){
        ArrayList<Country> pop = app.getCountryPopulations("adasfewfwdsf");
        assertEquals(0,pop.size());
    }
    @Test
    void getCountryPopulationsEmpty(){
        ArrayList<Country> pop = app.getCountryPopulations("");
        assertEquals(0,pop.size());
    }

//getDistrictPopulations
    @Test
    void getDistrictPopulationsGarbage(){
        ArrayList<City> pop = app.getDistrictPopulations("adasfewfwdsf");
        assertEquals(0,pop.size());
    }
    @Test
    void getDistrictPopulationsEmpty(){
        ArrayList<City> pop = app.getDistrictPopulations("");
        assertEquals(4,pop.size()); //4 districts have error text which counts as ""
    }
    @Test
    void getDistrictPopulationsReal(){
        ArrayList<City> pop = app.getDistrictPopulations("Moscow (City)");
        assertNotEquals(0,pop.size());
    }


//getCityPopulations

    @Test
    void getCityPopulationsGarbage(){
        ArrayList<City> pop = app.getCityPopulations("adasfewfwdsf");
        assertEquals(0,pop.size());
    }
    @Test
    void getCityPopulationsEmpty(){
        ArrayList<City> pop = app.getCityPopulations("");
        assertEquals(0,pop.size());
    }
    @Test
    void getCityPopulationsReal(){
        ArrayList<City> pop = app.getCityPopulations("Moscow");
        assertNotEquals(0,pop.size());
    }

//getWorldTopCapitalCities

    @Test
    void getWorldTopCapitalCitiesReal(){
        app.getWorldTopCapitalCities();
    }


//getWorldTopCountries

    @Test
    void getWorldTopCountriesReal(){
        app.getWorldTopCountries();
    }

//getContinentTopCapitalCities

    @Test
    void getContinentTopCapitalCitiesGarbage(){
        ArrayList<City> pop = app.getContinentTopCapitalCities("jfoiasjfopaskjfpoask");
        assertEquals(0,pop.size());
    }
    @Test
    void getContinentTopCapitalCitiesEmpty(){
        ArrayList<City> pop = app.getContinentTopCapitalCities("");
        assertEquals(0,pop.size());
    }
    @Test
    void getContinentTopCapitalCitiesReal(){
        ArrayList<City> pop = app.getContinentTopCapitalCities("Europe");
        assertNotEquals(0,pop.size());
    }

//getRegionTopCapitalCities

    @Test
    void getRegionTopCapitalCitiesGarbage(){
        ArrayList<City> pop = app.getRegionTopCapitalCities("jfoiasjfopaskjfpoask");
        assertEquals(0,pop.size());
    }
    @Test
    void getRegionTopCapitalCitiesEmpty(){
        ArrayList<City> pop = app.getRegionTopCapitalCities("");
        assertEquals(0,pop.size());
    }
    @Test
    void getRegionTopCapitalCitiesReal(){
        ArrayList<City> pop = app.getRegionTopCapitalCities("Eastern Europe");
        assertNotEquals(0,pop.size());
    }


//getContinentTopCountries

    @Test
    void getContinentTopCountriesGarbage(){
        ArrayList<Country> pop = app.getContinentTopCountries("jfoiasjfopaskjfpoask");
        assertEquals(0,pop.size());
    }
    @Test
    void getContinentTopCountriesEmpty(){
        ArrayList<Country> pop = app.getContinentTopCountries("");
        assertEquals(0,pop.size());
    }
    @Test
    void getContinentTopCountriesReal(){
        ArrayList<Country> pop = app.getContinentTopCountries("Europe");
        assertNotEquals(0,pop.size());
    }

//getRegionTopCountries

    @Test
    void getRegionTopCountriesGarbage(){
        ArrayList<Country> pop = app.getRegionTopCountries("jfoiasjfopaskjfpoask");
        assertEquals(0,pop.size());
    }
    @Test
    void getRegionTopCountriesEmpty(){
        ArrayList<Country> pop = app.getRegionTopCountries("");
        assertEquals(0,pop.size());
    }
    @Test
    void getRegionTopCountriesReal(){
        ArrayList<Country> pop = app.getRegionTopCountries("Eastern Europe");
        assertNotEquals(0,pop.size());
    }

//getCountry

    @Test
    void getCountryGarbage(){
        Country pop = app.getCountry("jfoiasjfopaskjfpoask");
    }
    @Test
    void getCountryEmpty(){
        Country pop = app.getCountry("");
    }
    @Test
    void getCountryReal(){
        Country pop = app.getCountry("FRA");
        assertNotNull(pop);
    }

//getCapitalCity

    @Test
    void getCapitalCity0(){
        City pop = app.getCapitalCity(0);
    }
    @Test
    void getCapitalCityReal(){
        City pop = app.getCapitalCity(2974);
        assertNotNull(pop);
    }

//printTotalPopulationCities

    @Test
    void printTotalPopulationCitiesEmpty(){
        ArrayList<City> pop = new ArrayList<>();
        app.printTotalPopulationCities(pop);
    }
    @Test
    void printTotalPopulationCitiesReal(){
        ArrayList<City> pop = app.getRegionTopCapitalCities("Eastern Europe");
        app.printTotalPopulationCities(pop);
    }


}