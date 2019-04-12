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

//getWorldPopulations

    @Test
    void getWorldPopulationsTest(){
        app.getWorldPopulations();
    }


//getWorldTopCities

    @Test
    void getWorldTopCitiesTest(){
        app.getWorldTopCities();
    }


//getContinentTopCities

    @Test
    void getContinentTopCitiesEmpty(){
        app.getContinentTopCities("");
    }
    @Test
    void getContinentTopCitiesGarbage(){
        app.getContinentTopCities("jkdfhakjsdh");
    }
    @Test
    void getContinentTopCitiesReal(){
        app.getContinentTopCities("Europe");
    }


//getRegionTopCities


    @Test
    void getRegionTopCitiesEmpty(){
        app.getRegionTopCities("");
    }
    @Test
    void getRegionTopCitiesGarbage(){
        app.getRegionTopCities("jkdfhakjsdh");
    }
    @Test
    void getRegionTopCitiesReal(){
        app.getRegionTopCities("Eastern Europe");
    }

//getCountryTopCities


    @Test
    void getCountryTopCitiesEmpty(){
        app.getCountryTopCities("");
    }
    @Test
    void getCountryTopCitiesGarbage(){
        app.getCountryTopCities("jkdfhakjsdh");
    }
    @Test
    void getCountryTopCitiesReal(){
        app.getCountryTopCities("France");
    }

//getDistrictTopCities


    @Test
    void getDistrictTopCitiesEmpty(){
        app.getDistrictTopCities("");
    }
    @Test
    void getDistrictTopCitiesGarbage(){
        app.getDistrictTopCities("jkdfhakjsdh");
    }
    @Test
    void getDistrictTopCitiesReal(){
        app.getDistrictTopCities("Moscow (City)");
    }


//getCountryLanguages

    @Test
    void getCountryLanguagesEmpty(){
        app.getCountryLanguages("");
    }
    @Test
    void getCountryLanguagesGarbage(){
        app.getCountryLanguages("jkdfhakjsdh");
    }
    @Test
    void getCountryLanguagesReal(){
        app.getCountryLanguages("French");
    }


//printLanguageReport

    @Test
    void printLanguageReportEmpty(){
        ArrayList<CountryLanguage> lang = new ArrayList<CountryLanguage>();
        app.printLanguageReport(lang);
    }

    @Test
    void printLanguageReportReal(){
        ArrayList<CountryLanguage> lang = app.getCountryLanguages("French");
        app.printLanguageReport(lang);
    }

//printMajorLanguages

    @Test
    void printMajorLanguagesTest(){
        app.printMajorLanguages();
    }

//printCountriesIn

    @Test
    void printCountriesInEmpty(){
        app.printCountriesIn("","");
    }
    @Test
    void printCountriesInGarbage1(){
        app.printCountriesIn("ikfhkash","");
    }
    @Test
    void printCountriesInGarbage2(){
        app.printCountriesIn("","afasoudf");
    }
    @Test
    void printCountriesInGarbage3(){
        app.printCountriesIn("kljdsl","afasoudf");
    }
    @Test
    void printCountriesInGarbage4(){
        app.printCountriesIn("world","afasoudf");
    }
    @Test
    void printCountriesInGarbage5(){
        app.printCountriesIn("region","afasoudf");
    }
    @Test
    void printCountriesInGarbage6(){
        app.printCountriesIn("continent","afasoudf");
    }
    @Test
    void printCountriesInEmpty2(){
        app.printCountriesIn("continent","");
    }
    @Test
    void printCountriesInEmpty3(){
        app.printCountriesIn("region","");
    }
    @Test
    void printCountriesInReal1(){
        app.printCountriesIn("world","");
    }
    @Test
    void printCountriesInReal2(){
        app.printCountriesIn("continent","Europe");
    }
    @Test
    void printCountriesInReal3(){
        app.printCountriesIn("region","Eastern Europe");
    }

//printTopCountries

    @Test
    void printTopCountriesEmpty(){
        app.printTopCountries("" ,"","");
    }
    @Test
    void printTopCountriesGarbage1(){
        app.printTopCountries("","ikfhkash","");
    }
    @Test
    void printTopCountriesGarbage2(){
        app.printTopCountries("","","afasoudf");
    }
    @Test
    void printTopCountriesGarbage3(){
        app.printTopCountries("","kljdsl","afasoudf");
    }
    @Test
    void printTopCountriesGarbage4(){
        app.printTopCountries("","world","afasoudf");
    }
    @Test
    void printTopCountriesGarbage5(){
        app.printTopCountries("","region","afasoudf");
    }
    @Test
    void printTopCountriesGarbage6(){
        app.printTopCountries("","continent","afasoudf");
    }
    @Test
    void printTopCountriesEmpty2(){
        app.printTopCountries("","continent","");
    }
    @Test
    void printTopCountriesEmpty3(){
        app.printTopCountries("","region","");
    }
    @Test
    void printTopCountriesReal1(){
        app.printTopCountries("3","world","");
    }
    @Test
    void printTopCountriesReal2(){
        app.printTopCountries("3","continent","Europe");
    }
    @Test
    void printTopCountriesReal3(){
        app.printTopCountries("3","region","Eastern Europe");
    }


}