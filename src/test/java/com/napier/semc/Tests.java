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
        app.connect("localhost:33060");
    }


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


}