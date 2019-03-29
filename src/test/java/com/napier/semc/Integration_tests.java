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
        app.printPopulationReport(countries, "Europe", "", "Russia", true, false, false, false);

    }
    @Test
    void continentparametertest(){

        ArrayList<Country> countries = new ArrayList<Country>();
        app.printPopulationReport(countries, "Europe", "", "Russia", false, true, false, false);

    }
    @Test
    void regionparametertest(){

        ArrayList<Country> countries = new ArrayList<Country>();
        app.printPopulationReport(countries, "Europe", "Eastern Europe", "Russia", false, false, true, false);

    }
    @Test
    void countryparametertest(){

        ArrayList<Country> countries = new ArrayList<Country>();
        app.printPopulationReport(countries, "Europe", "", "Russia", false, false, false, true);

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
    void getRegionPopulationsReal(){
        ArrayList<Country> pop = app.getRegionPopulations("Eastern Europe");
        assertNotEquals(0,pop.size());
    }


}