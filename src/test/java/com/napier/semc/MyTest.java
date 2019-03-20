package com.napier.semc;

import com.napier.semc.App;
import org.junit.jupiter.api.*;

class MyTest
{
    static App app;
    static String Asia = "Asia";

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


