package com.napier.semc;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class Integration_tests {
    static App app;

    @BeforeAll
    static void init() {
        app = new App();
        app.connect("localhost:33060");

    }

// printPopulationReport
    @Test
    void emptytestpopreport(){

        app.printPopulationReport(null, "", "", "", false, true, true, true);

    }
    @Test
    void emptytestpopreport2(){

        app.printPopulationReport(null, "", "", "", false, false, true, true);

    }
    @Test
    void emptytestpopreport3(){

        app.printPopulationReport(null, "", "", "", false, false, false, true);

    }

    @Test
    void worldparametertest(){

        ArrayList<Country> countries = new ArrayList<Country>();
        app.printPopulationReport(countries, "Europe", "", "France", true, false, false, false);

    }
    @Test
    void continentparametertest(){

        ArrayList<Country> countries = new ArrayList<Country>();
        app.printPopulationReport(countries, "Europe", "", "France", false, true, false, false);

    }
    @Test
    void regionparametertest(){

        ArrayList<Country> countries = new ArrayList<Country>();
        app.printPopulationReport(countries, "Europe", "Eastern Europe", "France", false, false, true, false);

    }
    @Test
    void countryparametertest(){

        ArrayList<Country> countries = new ArrayList<Country>();
        app.printPopulationReport(countries, "Europe", "", "France", false, false, false, true);

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

//getWorldPopulations
    @Test
    void testworldpopulations(){
        ArrayList<Country> pop =  app.getWorldPopulations();
        assertNotEquals(0, pop.size());
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
    @Test
    void testcontinentpopulations_real(){
        ArrayList<Country> pop = app.getContinentPopulations("Europe");
        assertNotEquals(0, pop.size());
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
    @Test
    void getRegionPopulationsReal(){
        ArrayList<Country> pop = app.getRegionPopulations("Eastern Europe");
        assertNotEquals(0,pop.size());
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
    @Test
    void getCountryPopulationsReal(){
        ArrayList<Country> pop = app.getCountryPopulations("France");
        assertNotEquals(0,pop.size());
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

//printCountriesIn

    @Test
    void printCountriesInFalse(){
        ArrayList<Country> pop = new ArrayList<>();
        app.printCountriesIn(pop,"","",false,false,false);
    }
    @Test
    void printCountriesInEmpty1(){
        ArrayList<Country> pop = new ArrayList<>();
        app.printCountriesIn(pop,"","",true,false,false);
    }
    @Test
    void printCountriesInEmpty2(){
        ArrayList<Country> pop = new ArrayList<>();
        app.printCountriesIn(pop,"","",false,true,false);
    }
    @Test
    void printCountriesInEmpty3(){
        ArrayList<Country> pop = new ArrayList<>();
        app.printCountriesIn(pop,"","",false,false,true);
    }
    @Test
    void printCountriesInAllTrueEmpty(){
        ArrayList<Country> pop = new ArrayList<>();
        app.printCountriesIn(pop,"","",true,true,true);
    }
    @Test
    void printCountriesInAllTrueFull(){
        ArrayList<Country> pop = new ArrayList<>();
        app.printCountriesIn(pop,"Europe","Eastern Europe",true,true,true);
    }
    @Test
    void printCountriesInCorrect1(){
        ArrayList<Country> pop = new ArrayList<>();
        app.printCountriesIn(pop,"Europe","",false,true,false);
    }
    @Test
    void printCountriesInCorrect2(){
        ArrayList<Country> pop = new ArrayList<>();
        app.printCountriesIn(pop,"","Eastern Europe",false,false,true);
    }


//printTopCountries

    @Test
    void printTopCountriesFalse(){
        ArrayList<Country> pop = new ArrayList<>();
        app.printTopCountries(pop,"","",0,false,false,false);
    }
    @Test
    void printTopCountriesEmpty1(){
        ArrayList<Country> pop = new ArrayList<>();
        app.printTopCountries(pop,"","",0,true,false,false);
    }
    @Test
    void printTopCountriesEmpty2(){
        ArrayList<Country> pop = new ArrayList<>();
        app.printTopCountries(pop,"","",0,false,true,false);
    }
    @Test
    void printTopCountriesEmpty3(){
        ArrayList<Country> pop = new ArrayList<>();
        app.printTopCountries(pop,"","",0,false,false,true);
    }
    @Test
    void printTopCountriesAllTrueEmpty(){
        ArrayList<Country> pop = new ArrayList<>();
        app.printTopCountries(pop,"","",0,true,true,true);
    }
    @Test
    void printTopCountriesAllTrueFull(){
        ArrayList<Country> pop = new ArrayList<>();
        app.printTopCountries(pop,"Europe","Eastern Europe",3,true,true,true);
    }
    @Test
    void printTopCountriesCorrect1(){
        ArrayList<Country> pop = new ArrayList<>();
        app.printTopCountries(pop,"","",3,true,false,false);
    }
    @Test
    void printTopCountriesCorrect2(){
        ArrayList<Country> pop = new ArrayList<>();
        app.printTopCountries(pop,"Europe","",3,false,true,false);
    }
    @Test
    void printTopCountriesCorrect3(){
        ArrayList<Country> pop = new ArrayList<>();
        app.printTopCountries(pop,"","Eastern Europe",3,false,false,true);
    }
}