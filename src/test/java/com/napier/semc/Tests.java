package com.napier.semc;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class Tests {
    static App app;

    @BeforeAll
    static void init() {
        app = new App();
    }


    @Test
    void nulltestpopreport(){
        //app.connect();
        app.printPopulationReport(null, null, null, null, false, false, false, false);
        //app.disconnect();
    }
    @Test
    void worldparametertest(){
        app.connect();
        ArrayList<Country> countries = new ArrayList<Country>();
        app.printPopulationReport(countries, "Europe", "", "Russia", true, false, false, false);
        app.disconnect();
    }



}