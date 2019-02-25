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
     * Prints total population of given countries
     * @param countries The list of countries.
     */
    public void printTotalPopulationCountries(ArrayList<Country> countries)
    {
        //Total population
        long totalPopulation = 0;
        //Loop over all countries in the list
        for (Country country : countries)
        {
            totalPopulation = totalPopulation + country.population;
        }
        System.out.println("Total population is " + totalPopulation);
    }

    /**
     * Prints total population of given cities
     * @param cities The list of cities.
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
     * Main method.
     */
    public static void main(String[] args)
    {
        //Create new application
        App a = new App();

        //Connect to database
        a.connect();

        System.out.println("World");
        //Get all countries in the world
        ArrayList<Country> countries = a.getWorldPopulations();

        //Print total population
        a.printTotalPopulationCountries(countries);

        //Clear countries
        countries.clear();

        System.out.println("Continent: Asia");
        //Get all countries in the continent
        countries = a.getContinentPopulations("Asia");

        //Print total population
        a.printTotalPopulationCountries(countries);

        //Clear countries
        countries.clear();

        System.out.println("Region: Eastern Europe");
        //Get all countries in the region
        countries = a.getRegionPopulations("Eastern Europe");

        //Print total population
        a.printTotalPopulationCountries(countries);

        //Clear countries
        countries.clear();

        System.out.println("Country: Lithuania");
        //Get all countries in the country
        countries = a.getCountryPopulations("Lithuania");

        //Print total population
        a.printTotalPopulationCountries(countries);

        //Clear countries
        countries.clear();

        System.out.println("District: Scotland");
        //Get all cities in the district
        ArrayList<City> cities = a.getDistrictPopulations("Scotland");

        //Print total population
        a.printTotalPopulationCities(cities);

        //Clear cities
        cities.clear();

        System.out.println("City: Klaipeda");
        //Get all cities in the district
        cities = a.getDistrictPopulations("Klaipeda");

        //Print total population
        a.printTotalPopulationCities(cities);

        //Clear cities
        cities.clear();

        //Disconnect from database
        a.disconnect();
    }
}
