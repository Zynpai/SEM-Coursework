package com.napier.semc;

import java.sql.*;
import java.util.ArrayList;

public class App
{
    /**
     * Connection to MySQL database.
     */
    private Connection con = null;

    /**
     * Connect to the MySQL database.
     */
    public void connect()
    {
        try
        {
            //Load Database driver
            Class.forName("com.mysql.jdbc.Driver");
        }
        catch (ClassNotFoundException e)
        {
            System.out.println("Could not load SQL driver");
            System.exit(-1);
        }

        int retries = 10;
        for (int i = 0; i < retries; ++i)
        {
            System.out.println("Connecting to database...");
            try
            {
                //Wait a bit for db to start
                Thread.sleep(30000);
                //Connect to database
                con = DriverManager.getConnection("jdbc:mysql://db:3306/world?useSSL=false", "root", "example");
                System.out.println("Successfully connected");
                break;
            }
            catch (SQLException sqle)
            {
                System.out.println("Failed to connect to database attempt " + Integer.toString(i));
                System.out.println(sqle.getMessage());
            }
            catch (InterruptedException ie)
            {
                System.out.println("Thread interrupted? Should not happen.");
            }
        }
    }

    /**
     * Disconnect from the MySQL database.
     */
    public void disconnect()
    {
        if (con != null)
        {
            try
            {
                //Close connection
                con.close();
            }
            catch (Exception e)
            {
                System.out.println("Error closing connection to database");
            }
        }
    }

    /**
     * Gets world populations
     * @return A list of countries and populations, or null if there is an error.
     */
    public ArrayList<Country> getWorldPopulations()
    {
        try
        {
            //Create an SQL statement
            Statement stmt = con.createStatement();
            //Create string for SQL statement
            String strSelect =
                    "SELECT country.Code, country.Name, country.Population "
                            + "FROM country";
            //Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            //Extract country information
            ArrayList<Country> countries = new ArrayList<Country>();
            while (rset.next())
            {
                Country country = new Country();
                country.code = rset.getString("country.Code");
                country.name = rset.getString("country.Name");
                country.population = rset.getInt("country.Population");
                countries.add(country);
            }
            return countries;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get population details");
            return null;
        }
    }

    /**
     * Gets continent populations
     * @param continent The continent from which to take the countries.
     * @return A list of countries and populations, or null if there is an error.
     */
    public ArrayList<Country> getContinentPopulations(String continent)
    {
        try
        {
            //Create an SQL statement
            Statement stmt = con.createStatement();
            //Create string for SQL statement
            String strSelect =
                    "SELECT country.Code, country.Name, country.Population "
                            + "FROM country "
                            + "WHERE country.Continent = '" + continent + "' ";
            //Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            //Extract country information
            ArrayList<Country> countries = new ArrayList<Country>();
            while (rset.next())
            {
                Country country = new Country();
                country.code = rset.getString("country.Code");
                country.name = rset.getString("country.Name");
                country.population = rset.getInt("country.Population");
                countries.add(country);
            }
            return countries;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get population details");
            return null;
        }
    }

    /**
     * Gets region populations
     * @param region The region from which to take the countries.
     * @return A list of countries and populations, or null if there is an error.
     */
    public ArrayList<Country> getRegionPopulations(String region)
    {
        try
        {
            //Create an SQL statement
            Statement stmt = con.createStatement();
            //Create string for SQL statement
            String strSelect =
                    "SELECT country.Code, country.Name, country.Population "
                            + "FROM country "
                            + "WHERE country.Region = '" + region + "' ";
            //Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            //Extract country information
            ArrayList<Country> countries = new ArrayList<Country>();
            while (rset.next())
            {
                Country country = new Country();
                country.code = rset.getString("country.Code");
                country.name = rset.getString("country.Name");
                country.population = rset.getInt("country.Population");
                countries.add(country);
            }
            return countries;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get population details");
            return null;
        }
    }

    /**
     * Gets country populations
     * @param countryName The country.
     * @return A list of countries and populations, or null if there is an error.
     */
    public ArrayList<Country> getCountryPopulations(String countryName)
    {
        try
        {
            //Create an SQL statement
            Statement stmt = con.createStatement();
            //Create string for SQL statement
            String strSelect =
                    "SELECT country.Code, country.Name, country.Population "
                            + "FROM country "
                            + "WHERE country.Name = '" + countryName + "' ";
            //Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            //Extract country information
            ArrayList<Country> countries = new ArrayList<Country>();
            while (rset.next())
            {
                Country country = new Country();
                country.code = rset.getString("country.Code");
                country.name = rset.getString("country.Name");
                country.population = rset.getInt("country.Population");
                countries.add(country);
            }
            return countries;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get population details");
            return null;
        }
    }

    /**
     * Gets district populations
     * @param district The district from which to take the countries.
     * @return A list of cities and populations, or null if there is an error.
     */
    public ArrayList<City> getDistrictPopulations(String district)
    {
        try
        {
            //Create an SQL statement
            Statement stmt = con.createStatement();
            //Create string for SQL statement
            String strSelect =
                    "SELECT city.ID, city.Name, city.Population "
                            + "FROM city "
                            + "WHERE city.District = '" + district + "' ";
            //Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            //Extract city information
            ArrayList<City> cities = new ArrayList<City>();
            while (rset.next())
            {
                City city = new City();
                city.ID = rset.getInt("city.ID");
                city.name = rset.getString("city.Name");
                city.population = rset.getInt("city.Population");
                cities.add(city);
            }
            return cities;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get population details");
            return null;
        }
    }

    /**
     * Gets city populations
     * @param cityName The city.
     * @return A list of cities and populations, or null if there is an error.
     */
    public ArrayList<City> getCityPopulations(String cityName)
    {
        try
        {
            //Create an SQL statement
            Statement stmt = con.createStatement();
            //Create string for SQL statement
            String strSelect =
                    "SELECT city.ID, city.Name, city.Population "
                            + "FROM city "
                            + "WHERE city.Name = '" + cityName + "' ";
            //Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            //Extract city information
            ArrayList<City> cities = new ArrayList<City>();
            while (rset.next())
            {
                City city = new City();
                city.ID = rset.getInt("city.ID");
                city.name = rset.getString("city.Name");
                city.population = rset.getInt("city.Population");
                cities.add(city);
            }
            return cities;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get population details");
            return null;
        }
    }

    /**
     * Gets top capital cities in largest to smallest in the world
     * @return A list of capital cities and their populations, or null if there is an error.
     */
    public ArrayList<City> getWorldTopCapitalCities()
    {
        try
        {
            //Create an SQL statement
            Statement stmt = con.createStatement();
            //Create string for SQL statement
            String strSelect =
                    "SELECT city.ID, city.Name, city.CountryCode, city.Population "
                            + "FROM city "
                            + "INNER JOIN country ON city.ID = country.capital "
                            + "ORDER BY city.Population DESC";
            //Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            //Extract city information
            ArrayList<City> cities = new ArrayList<City>();
            while (rset.next())
            {
                City city = new City();
                city.ID = rset.getInt("city.ID");
                city.name = rset.getString("city.Name");
                city.countryCode = rset.getString("city.CountryCode");
                city.population = rset.getInt("city.Population");
                cities.add(city);
            }
            return cities;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get top country details");
            return null;
        }
    }

    /**
     * Gets top capital cities in largest to smallest in the continent
     * @param continent The continent.
     * @return A list of capital cities and their populations, or null if there is an error.
     */
    public ArrayList<City> getContinentTopCapitalCities(String continent)
    {
        try
        {
            //Create an SQL statement
            Statement stmt = con.createStatement();
            //Create string for SQL statement
            String strSelect =
                    "SELECT city.ID, city.Name, city.CountryCode, city.Population "
                            + "FROM city "
                            + "INNER JOIN country ON city.ID = country.capital "
                            + "WHERE country.continent = '" + continent + "'"
                            + "ORDER BY city.Population DESC";
            //Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            //Extract city information
            ArrayList<City> cities = new ArrayList<City>();
            while (rset.next())
            {
                City city = new City();
                city.ID = rset.getInt("city.ID");
                city.name = rset.getString("city.Name");
                city.countryCode = rset.getString("city.CountryCode");
                city.population = rset.getInt("city.Population");
                cities.add(city);
            }
            return cities;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get top country details");
            return null;
        }
    }

    /**
     * Gets top capital cities in largest to smallest in the region
     * @param region The region.
     * @return A list of capital cities and their populations, or null if there is an error.
     */
    public ArrayList<City> getRegionTopCapitalCities(String region)
    {
        try
        {
            //Create an SQL statement
            Statement stmt = con.createStatement();
            //Create string for SQL statement
            String strSelect =
                    "SELECT city.ID, city.Name, city.CountryCode, city.Population "
                            + "FROM city "
                            + "INNER JOIN country ON city.ID = country.capital "
                            + "WHERE country.Region = '" + region + "'"
                            + "ORDER BY city.Population DESC";
            //Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            //Extract city information
            ArrayList<City> cities = new ArrayList<City>();
            while (rset.next())
            {
                City city = new City();
                city.ID = rset.getInt("city.ID");
                city.name = rset.getString("city.Name");
                city.countryCode = rset.getString("city.CountryCode");
                city.population = rset.getInt("city.Population");
                cities.add(city);
            }
            return cities;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get top country details");
            return null;
        }
    }

    /**
     * Gets country name
     * @param countryCode How the city calls the country
     * @return A country's details, or null if there is an error.
     */
    public Country getCountry(String countryCode)
    {
        try
        {
            Statement stmt = con.createStatement();
            //Create string for SQL statement
            String strSelect =
                    "SELECT country.Name, country.Code "
                        +"FROM country "
                        +"WHERE country.Code = '" + countryCode + "'";
            //Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            //Extract city information
            if (rset.next())
            {
                Country country = new Country();
                country.code = rset.getString("country.Code");
                country.name = rset.getString("country.Name");
                return country;
            }
            else
            {
                return null;
            }
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get Country");
            return null;
        }
    }

    /**
     * Gets cities in a country
     * @param countryCode How the city calls the country
     * @return All the cities in a country
     */
    public ArrayList<City> getCities(String countryCode)
    {
        try
        {
            Statement stmt = con.createStatement();
            //Create string for SQL statement
            String strSelect =
                    "SELECT city.ID, city.Name, city.CountryCode, city.District, city.Population "
                            +"FROM city "
                            +"WHERE city.CountryCode = '" + countryCode + "'";
            //Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            //Extract city information
            ArrayList<City> cities = new ArrayList<City>();
            while (rset.next())
            {
                City city = new City();
                city.ID = rset.getInt("city.ID");
                city.name = rset.getString("city.Name");
                city.countryCode = rset.getString("city.CountryCode");
                city.district = rset.getString("city.District");
                city.population = rset.getInt("city.Population");
                cities.add(city);
            }
            return cities;
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get cities");
            return null;
        }
    }

    /**
     * Prints a population report
     * @param countries The list of countries
     * @param continentName The continent where to search
     * @param regionName  The region where to search
     * @param countryName The country where to search
     * @param worldBool The boolean that sees if the user is searching in the world
     * @param continentBool The boolean that sees if the user is searching in the continent
     * @param regionBool The boolean that sees if the user is searching in the region
     * @param countryBool The boolean that sees if the user is searching in the country
     */
    public void printPopulationReport(ArrayList<Country> countries, String continentName, String regionName, String countryName,  boolean worldBool, boolean continentBool, boolean regionBool, boolean countryBool)
    {
        long totalPopulation = 0; //Total population
        long cityPopulation = 0;  //Population living in cities
        long ruralPopulation = 0; //Population living out of cities
        String name = null;
        ArrayList<City> cities;

        if (worldBool)
        {
            countries = getWorldPopulations();
            name = "World";
        }
        if (continentBool)
        {
            countries = getContinentPopulations(continentName);
            name = continentName;
        }
        if (regionBool)
        {
            countries = getRegionPopulations(regionName);
            name = regionName;
        }
        if (countryBool)
        {
            countries = getCountryPopulations(countryName);
            name = countryName;
        }
        for (Country country : countries)
        {
            totalPopulation += country.population;
            cities = getCities(country.code);
            for (City city : cities)
            {
                cityPopulation += city.population;
            }
            ruralPopulation = totalPopulation - cityPopulation;
        }
        System.out.println("Name: " + name);
        System.out.println("Total population: " + totalPopulation );
        System.out.println("Population in cities: " + cityPopulation + "(" + (100f / totalPopulation * cityPopulation) + "%)");
        System.out.println("Population not in cities: " + ruralPopulation + "(" + (100f / totalPopulation * ruralPopulation) + "%)");
    }

    /**
     * Prints total population of given cities
     * @param cities The list of cities
     */
    public void printTotalPopulationCities(ArrayList<City> cities)
    {
        //Total population
        long totalPopulation = 0;
        //Loop over all countries in the list
        for (City city : cities)
        {
            totalPopulation = totalPopulation + city.population;
        }
        System.out.println("Total population is " + totalPopulation);
    }


    /**
     * Prints top N capital cities of given n
     * @param cities The list of cities
     * @param continent The continent
     * @param region The region
     * @param topNumber The top N given by user
     * @param worldBool The boolean that sees if the user is searching in the world
     * @param continentBool The boolean that sees if the user is searching in the continent
     * @param regionBool The boolean that sees if the user is searching in the region
     */
    public void printTopCapitalCities(ArrayList<City> cities, String continent, String region, int topNumber, boolean worldBool, boolean continentBool, boolean regionBool)
    {
        Country country;
        if (worldBool)
        {
            cities = getWorldTopCapitalCities();
        }
        if (continentBool)
        {
            cities = getContinentTopCapitalCities(continent);
        }
        if (regionBool)
        {
            cities = getRegionTopCapitalCities(region);
        }
        for (City city : cities)
        {
            if (topNumber > 0) {
                if (worldBool)
                {
                    country = getCountry(city.countryCode);
                    printCapitalCities(city, country);
                    topNumber--;
                }
                if (continentBool)
                {
                    country = getCountry(city.countryCode);
                    printCapitalCities(city, country);
                    topNumber--;
                }
                if (regionBool)
                {
                    country = getCountry(city.countryCode);
                    printCapitalCities(city, country);
                    topNumber--;
                }
            }
        }
    }

    /**
     * Prints the capital cities
     * @param city The city
     * @param country The country
     */
    public void printCapitalCities(City city, Country country)
    {
        System.out.println("Name:" + city.name);
        System.out.println("Population:" + city.population);
        System.out.println("Country:" + country.name);
        System.out.println("------------------------");
    }

    /**
     * Main method
     */
    public static void main(String[] args)
    {
        //Create new application
        App a = new App();

        //Connect to database
        a.connect();

        //Initialize variables
        boolean worldBool = false;
        boolean continentBool = false;
        boolean regionBool = false;
        boolean countryBool = false;
        String continent = null;
        String region = null;
        String country = null;
        ArrayList<City> cities = new ArrayList<>();
        ArrayList<Country> countries = new ArrayList<>();

        System.out.println("Top 10 Capitals in the World");
        //Get all top 10 capital cities in the world
        worldBool = true;
        a.printTopCapitalCities(cities, continent, region,10, worldBool, continentBool, regionBool);
        //Clear cities
        worldBool = false;
        cities.clear();

        System.out.println("Top 10 Capitals in Europe");
        //Get all top 10 capital cities in Europe
        continent = "Europe";
        continentBool = true;
        a.printTopCapitalCities(cities, continent, region,10, worldBool, continentBool, regionBool);
        //Clear cities
        continentBool = false;
        continent = null;
        cities.clear();

        System.out.println("Top 10 Capitals in Carribean");
        //Get all top 10 capital cities in the Carribean
        region = "Caribbean";
        regionBool = true;
        a.printTopCapitalCities(cities, continent, region, 10, worldBool, continentBool, regionBool);
        //Clear cities
        regionBool = false;
        region = null;
        cities.clear();

        //Population reports
        //World report
        worldBool = true;
        a.printPopulationReport(countries, continent, region, country, worldBool, continentBool, regionBool, countryBool);
        //Reset values
        worldBool = false;
        countries.clear();

        //Continent report
        continent = "Europe";
        continentBool = true;
        a.printPopulationReport(countries, continent, region, country, worldBool, continentBool, regionBool, countryBool);
        //Reset values
        continentBool = false;
        continent = null;
        countries.clear();

        //Region report
        region = "Caribbean";
        regionBool = true;
        a.printPopulationReport(countries, continent, region, country, worldBool, continentBool, regionBool, countryBool);
        //Clear cities
        regionBool = false;
        region = null;
        countries.clear();

        //Region report
        country = "Lithuania";
        countryBool = true;
        a.printPopulationReport(countries, continent, region, country, worldBool, continentBool, regionBool, countryBool);
        //Clear cities
        countryBool = false;
        country = null;
        countries.clear();

        //Disconnect from database
        a.disconnect();
    }
}
