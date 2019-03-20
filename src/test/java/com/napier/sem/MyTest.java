package com.napier.sem;

import com.napier.semc.App;
import org.junit.jupiter.api.*;

class MyTest
{
    static App app;
    static String Asia = "Asian";

    @BeforeAll
    static void init()
    {
        app = new App();
    }

    @Test
    void getContinentPopulationsNull()
    {
        app.getContinentPopulations(Asia);
    }
}


