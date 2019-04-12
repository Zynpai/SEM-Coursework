package com.napier.semc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.*;
import java.util.*;

@SpringBootApplication
@RestController
public class App
{
    /**
     * Connection to MySQL database.
     */
    private static Connection con = null;

    /**
     * Connect to the MySQL database.
     */
    public static void connect(String location)
    {
        try
        {
            // Load Database driver
            Class.forName("com.mysql.cj.jdbc.Driver");
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
                // Wait a bit for db to start
                Thread.sleep(30000);
                // Connect to database
                con = DriverManager.getConnection("jdbc:mysql://" + location + "/world?allowPublicKeyRetrieval=true&useSSL=false", "root", "example");
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
    public static void disconnect()
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
    @RequestMapping("population_world")
    public ArrayList<Country> getWorldPopulations()
    {
        try
        {
            //Create an SQL statement
            Statement stmt = con.createStatement();
            //Create string for SQL statement
            String strSelect =
                    "SELECT country.Code, country.Name, country.Population, country.Continent, country.Region, " +
                            "country.SurfaceArea, country.IndepYear, country.LifeExpectancy, country.GNP, country.GNPOld," +
                            "country.LocalName, country.GovernmentForm, country.HeadOfState, country.Capital, country.Code2 "
                            + "FROM country "
                            + "ORDER BY country.Population DESC";
            //Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            //Extract country information
            ArrayList<Country> countries = new ArrayList<Country>();
            while (rset.next())
            {
                Country country = new Country();
                country.code = rset.getString("country.Code");
                country.name = rset.getString("country.Name");
                country.continent = rset.getString("country.Continent");
                country.region = rset.getString("country.Region");
                country.population = rset.getInt("country.Population");
                country.surfaceArea = rset.getDouble("country.SurfaceArea");
                country.indYear = rset.getInt("country.IndepYear");
                country.lifeExpectancy = rset.getDouble("country.LifeExpectancy");
                country.GNP = rset.getDouble("country.GNP");
                country.oldGNP = rset.getDouble("country.GNPOld");
                country.localName = rset.getString("country.LocalName");
                country.govForm = rset.getString("country.GovernmentForm");
                country.headOfState = rset.getString("country.HeadOfState");
                country.capital = rset.getInt("country.Capital");
                country.code2 = rset.getString("country.Code2");
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
    @RequestMapping("population_continent")
    public ArrayList<Country> getContinentPopulations(@RequestParam(value = "continent") String continent)
    {
        try
        {
            //Create an SQL statement
            Statement stmt = con.createStatement();
            //Create string for SQL statement
            String strSelect =
                    "SELECT country.Code, country.Name, country.Population, country.Continent, country.Region, " +
                            "country.SurfaceArea, country.IndepYear, country.LifeExpectancy, country.GNP, country.GNPOld," +
                            "country.LocalName, country.GovernmentForm, country.HeadOfState, country.Capital, country.Code2 "
                            + "FROM country "
                            + "WHERE country.Continent = '" + continent + "' "
                            + "ORDER BY country.Population DESC";
            //Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            //Extract country information
            ArrayList<Country> countries = new ArrayList<Country>();
            while (rset.next())
            {
                Country country = new Country();
                country.code = rset.getString("country.Code");
                country.name = rset.getString("country.Name");
                country.continent = rset.getString("country.Continent");
                country.region = rset.getString("country.Region");
                country.population = rset.getInt("country.Population");
                country.surfaceArea = rset.getDouble("country.SurfaceArea");
                country.indYear = rset.getInt("country.IndepYear");
                country.lifeExpectancy = rset.getDouble("country.LifeExpectancy");
                country.GNP = rset.getDouble("country.GNP");
                country.oldGNP = rset.getDouble("country.GNPOld");
                country.localName = rset.getString("country.LocalName");
                country.govForm = rset.getString("country.GovernmentForm");
                country.headOfState = rset.getString("country.HeadOfState");
                country.capital = rset.getInt("country.Capital");
                country.code2 = rset.getString("country.Code2");
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
    @RequestMapping("population_region")
    public ArrayList<Country> getRegionPopulations(@RequestParam(value = "region") String region)
    {
        try
        {
            //Create an SQL statement
            Statement stmt = con.createStatement();
            //Create string for SQL statement
            String strSelect =
                    "SELECT country.Code, country.Name, country.Population, country.Continent, country.Region, " +
                            "country.SurfaceArea, country.IndepYear, country.LifeExpectancy, country.GNP, country.GNPOld," +
                            "country.LocalName, country.GovernmentForm, country.HeadOfState, country.Capital, country.Code2 "
                            + "FROM country "
                            + "WHERE country.Region = '" + region + "' "
                            + "ORDER BY country.Population DESC";
            //Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            //Extract country information
            ArrayList<Country> countries = new ArrayList<Country>();
            while (rset.next())
            {
                Country country = new Country();
                country.code = rset.getString("country.Code");
                country.name = rset.getString("country.Name");
                country.continent = rset.getString("country.Continent");
                country.region = rset.getString("country.Region");
                country.population = rset.getInt("country.Population");
                country.surfaceArea = rset.getDouble("country.SurfaceArea");
                country.indYear = rset.getInt("country.IndepYear");
                country.lifeExpectancy = rset.getDouble("country.LifeExpectancy");
                country.GNP = rset.getDouble("country.GNP");
                country.oldGNP = rset.getDouble("country.GNPOld");
                country.localName = rset.getString("country.LocalName");
                country.govForm = rset.getString("country.GovernmentForm");
                country.headOfState = rset.getString("country.HeadOfState");
                country.capital = rset.getInt("country.Capital");
                country.code2 = rset.getString("country.Code2");
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
    @RequestMapping("population_country")
    public ArrayList<Country> getCountryPopulations(@RequestParam(value = "country") String countryName)
    {
        try
        {
            //Create an SQL statement
            Statement stmt = con.createStatement();
            //Create string for SQL statement
            String strSelect =
                    "SELECT country.Code, country.Name, country.Population, country.Continent, country.Region, " +
                            "country.SurfaceArea, country.IndepYear, country.LifeExpectancy, country.GNP, country.GNPOld," +
                            "country.LocalName, country.GovernmentForm, country.HeadOfState, country.Capital, country.Code2 "
                            + "FROM country "
                            + "WHERE country.Name = '" + countryName + "' "
                            + "ORDER BY country.Population DESC";
            //Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            //Extract country information
            ArrayList<Country> countries = new ArrayList<Country>();
            while (rset.next())
            {
                Country country = new Country();
                country.code = rset.getString("country.Code");
                country.name = rset.getString("country.Name");
                country.continent = rset.getString("country.Continent");
                country.region = rset.getString("country.Region");
                country.population = rset.getInt("country.Population");
                country.surfaceArea = rset.getDouble("country.SurfaceArea");
                country.indYear = rset.getInt("country.IndepYear");
                country.lifeExpectancy = rset.getDouble("country.LifeExpectancy");
                country.GNP = rset.getDouble("country.GNP");
                country.oldGNP = rset.getDouble("country.GNPOld");
                country.localName = rset.getString("country.LocalName");
                country.govForm = rset.getString("country.GovernmentForm");
                country.headOfState = rset.getString("country.HeadOfState");
                country.capital = rset.getInt("country.Capital");
                country.code2 = rset.getString("country.Code2");
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
    @RequestMapping("population_district")
    public ArrayList<City> getDistrictPopulations(@RequestParam(value = "district") String district)
    {
        try
        {
            //Create an SQL statement
            Statement stmt = con.createStatement();
            //Create string for SQL statement
            String strSelect =
                    "SELECT city.ID, city.Name, city.Population, city.CountryCode, city.District "
                            + "FROM city "
                            + "WHERE city.District = '" + district + "' "
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
                city.population = rset.getInt("city.Population");
                city.countryCode = rset.getString("city.CountryCode");
                city.district = rset.getString("city.District");
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
    @RequestMapping("population_city")
    public ArrayList<City> getCityPopulations(@RequestParam(value = "city") String cityName)
    {
        try
        {
            //Create an SQL statement
            Statement stmt = con.createStatement();
            //Create string for SQL statement
            String strSelect =
                    "SELECT city.ID, city.Name, city.Population, city.CountryCode, city.District "
                            + "FROM city "
                            + "WHERE city.Name = '" + cityName + "' "
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
                city.population = rset.getInt("city.Population");
                city.countryCode = rset.getString("city.CountryCode");
                city.district = rset.getString("city.District");
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
    @RequestMapping("world_top_capitals")
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
    @RequestMapping("continent_top_capitals")
    public ArrayList<City> getContinentTopCapitalCities(@RequestParam(value = "continent") String continent)
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
    @RequestMapping("region_top_capitals")
    public ArrayList<City> getRegionTopCapitalCities(@RequestParam(value = "region") String region)
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
     * Gets the countries and puts it to largest to smallest based on their population
     * @return A list of countries and their details, or null if theres is an error
     */
    @RequestMapping("countries_world")
    public ArrayList<Country> getWorldTopCountries()
    {
        try
        {
            //Create an SQL statement
            Statement stmt = con.createStatement();
            //Create string for SQL statement
            String strSelect =
                    "SELECT country.Code, country.Name, country.Continent, country.Region, country.Population, country.Capital "
                            + "FROM country "
                            + "ORDER BY country.Population DESC";
            //Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            //Extract city information
            ArrayList<Country> countries = new ArrayList<Country>();
            while (rset.next())
            {
                Country country = new Country();
                country.code = rset.getString("country.Code");
                country.name = rset.getString("country.Name");
                country.continent = rset.getString("country.Continent");
                country.region = rset.getString("country.Region");
                country.population = rset.getInt("country.Population");
                country.capital = rset.getInt("country.Capital");
                countries.add(country);
            }
            return countries;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get top country details");
            return null;
        }
    }
    @RequestMapping("countries_continent")
    public ArrayList<Country> getContinentTopCountries(@RequestParam(value = "continent") String continent)
    {
        try
        {
            //Create an SQL statement
            Statement stmt = con.createStatement();
            //Create string for SQL statement
            String strSelect =
                    "SELECT country.Code, country.Name, country.Continent, country.Region, country.Population, country.Capital "
                            + "FROM country "
                            + "WHERE country.Continent = '" + continent + "'"
                            + "ORDER BY country.Population DESC";
            //Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            //Extract city information
            ArrayList<Country> countries = new ArrayList<Country>();
            while (rset.next())
            {
                Country country = new Country();
                country.code = rset.getString("country.Code");
                country.name = rset.getString("country.Name");
                country.continent = rset.getString("country.Continent");
                country.region = rset.getString("country.Region");
                country.population = rset.getInt("country.Population");
                country.capital = rset.getInt("country.Capital");
                countries.add(country);
            }
            return countries;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get top country details");
            return null;
        }
    }
    @RequestMapping("countries_region")
    public ArrayList<Country> getRegionTopCountries(@RequestParam(value = "region") String region)
    {
        try
        {
            //Create an SQL statement
            Statement stmt = con.createStatement();
            //Create string for SQL statement
            String strSelect =
                    "SELECT country.Code, country.Name, country.Continent, country.Region, country.Population, country.Capital "
                            + "FROM country "
                            + "WHERE country.Region = '" + region + "'"
                            + "ORDER BY country.Population DESC";
            //Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            //Extract city information
            ArrayList<Country> countries = new ArrayList<Country>();
            while (rset.next())
            {
                Country country = new Country();
                country.code = rset.getString("country.Code");
                country.name = rset.getString("country.Name");
                country.continent = rset.getString("country.Continent");
                country.region = rset.getString("country.Region");
                country.population = rset.getInt("country.Population");
                country.capital = rset.getInt("country.Capital");
                countries.add(country);
            }
            return countries;
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
    @RequestMapping("country")
    public Country getCountry(@RequestParam(value = "countryCode") String countryCode)
    {
        try
        {
            Statement stmt = con.createStatement();
            //Create string for SQL statement
            String strSelect =
                    "SELECT country.Name, country.Code, country.Population, country.Capital "
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
                country.population = rset.getInt("country.Population");
                country.capital = rset.getInt("country.Capital");
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
     * Gets city details and orders them by population
     * @return A city's details, or null if there is an error.
     */
    @RequestMapping("cities_world")
    public ArrayList<City> getWorldTopCities()
    {
        try
        {
            Statement stmt = con.createStatement();
            //Create string for SQL statement
            String strSelect =
                    "SELECT city.ID, city.name, city.district, city.countryCode, city.population "
                            +"FROM city "
                            +"ORDER BY city.population DESC";
            //Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            ArrayList<City> cities = new ArrayList<City>();
            //Extract city information
            while (rset.next())
            {
                City city = new City();
                city.ID = rset.getInt("city.ID");
                city.name = rset.getString("city.Name");
                city.population = rset.getInt("city.Population");
                city.district = rset.getString("city.district");
                city.countryCode = rset.getString("city.countryCode");
                cities.add(city);
            }
            return cities;
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get City");
            return null;
        }
    }
    @RequestMapping("cities_continent")
    public ArrayList<City> getContinentTopCities(@RequestParam(value = "continent") String continent)
    {
        try
        {
            Statement stmt = con.createStatement();
            //Create string for SQL statement
            String strSelect =
                    "SELECT city.ID, city.Name, city.District, city.CountryCode, city.Population "
                            +"FROM city "
                            +"INNER JOIN country ON country.code = city.countryCode "
                            +"WHERE country.Continent ='" +  continent + "'"
                            +"ORDER BY city.Population DESC";
            //Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            ArrayList<City> cities = new ArrayList<City>();
            //Extract city information
            while (rset.next())
            {
                City city = new City();
                city.ID = rset.getInt("city.ID");
                city.name = rset.getString("city.Name");
                city.population = rset.getInt("city.Population");
                city.district = rset.getString("city.district");
                city.countryCode = rset.getString("city.countryCode");
                cities.add(city);
            }
            return cities;
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get City");
            return null;
        }
    }
    @RequestMapping("cities_region")
    public ArrayList<City> getRegionTopCities(@RequestParam(value = "region") String region)
    {
        try
        {
            Statement stmt = con.createStatement();
            //Create string for SQL statement
            String strSelect =
                    "SELECT city.ID, city.name, city.district, city.countryCode, city.population "
                            +"FROM city "
                            +"INNER JOIN country ON country.code = city.countryCode "
                            +"WHERE country.Region ='" +  region + "'"
                            +"ORDER BY city.population DESC";
            //Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            ArrayList<City> cities = new ArrayList<City>();
            //Extract city information
            while (rset.next())
            {
                City city = new City();
                city.ID = rset.getInt("city.ID");
                city.name = rset.getString("city.Name");
                city.population = rset.getInt("city.Population");
                city.district = rset.getString("city.district");
                city.countryCode = rset.getString("city.countryCode");
                cities.add(city);
            }
            return cities;
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get City");
            return null;
        }
    }
    @RequestMapping("cities_country")
    public ArrayList<City> getCountryTopCities(@RequestParam(value = "country") String country)
    {
        try
        {
            Statement stmt = con.createStatement();
            //Create string for SQL statement
            String strSelect =
                    "SELECT city.ID, city.name, city.district, city.countryCode, city.population "
                            +"FROM city "
                            +"INNER JOIN country ON country.code = city.countryCode "
                            +"WHERE country.Name ='" +  country + "'"
                            +"ORDER BY city.population DESC";
            //Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            ArrayList<City> cities = new ArrayList<City>();
            //Extract city information
            while (rset.next())
            {
                City city = new City();
                city.ID = rset.getInt("city.ID");
                city.name = rset.getString("city.Name");
                city.population = rset.getInt("city.Population");
                city.district = rset.getString("city.district");
                city.countryCode = rset.getString("city.countryCode");
                cities.add(city);
            }
            return cities;
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get City");
            return null;
        }
    }
    @RequestMapping("cities_district")
    public ArrayList<City> getDistrictTopCities(@RequestParam(value = "district") String district)
    {
        try
        {
            Statement stmt = con.createStatement();
            //Create string for SQL statement
            String strSelect =
                    "SELECT city.ID, city.name, city.district, city.countryCode, city.population "
                            +"FROM city "
                            +"WHERE city.district ='" + district + "'"
                            +"ORDER BY city.population DESC";
            //Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            ArrayList<City> cities = new ArrayList<City>();
            //Extract city information
            while (rset.next())
            {
                City city = new City();
                city.ID = rset.getInt("city.ID");
                city.name = rset.getString("city.Name");
                city.population = rset.getInt("city.Population");
                city.district = rset.getString("city.district");
                city.countryCode = rset.getString("city.countryCode");
                cities.add(city);
            }
            return cities;
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get City");
            return null;
        }
    }

    public City getCapitalCity(int countryCapital)
    {
        try
        {
            Statement stmt = con.createStatement();
            //Create string for SQL statement
            String strSelect =
                    "SELECT city.Name, city.ID "
                            +"FROM city "
                            +"WHERE city.ID = '" + countryCapital + "'";
            //Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            //Extract city information
            if (rset.next())
            {
                City city = new City();
                city.ID = rset.getInt("city.ID");
                city.name = rset.getString("city.Name");
                return city;
            }
            else
            {
                return null;
            }
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get Capital City");
            return null;
        }
    }


    /**
     * Gets cities in a country
     * @param countryCode How the city calls the country
     * @return All the cities in a country
     */
    @RequestMapping("get_cities")
    public ArrayList<City> getCities(@RequestParam(value = "country") String countryCode)
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
     * Gets languages in countries
     * @param language The language to look for
     * @return All the languages in countries
     */
    @RequestMapping("country_language")
    public ArrayList<CountryLanguage> getCountryLanguages(@RequestParam(value = "language") String language)
    {
        try
        {
            Statement stmt = con.createStatement();
            //Create string for SQL statement
            String strSelect =
                    "SELECT countrylanguage.CountryCode, countrylanguage.Language, countrylanguage.IsOfficial, countrylanguage.Percentage "
                            +"FROM countrylanguage "
                            +"WHERE countrylanguage.Language = '" + language + "'";
            //Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            //Extract city information
            ArrayList<CountryLanguage> languages = new ArrayList<CountryLanguage>();
            while (rset.next())
            {
                CountryLanguage countryLanguage = new CountryLanguage();
                countryLanguage.countryCode = rset.getString("countrylanguage.CountryCode");
                countryLanguage.language = rset.getString("countrylanguage.Language");
                if (rset.getString("countrylanguage.IsOfficial") == "T") countryLanguage.isOfficial = true;
                else countryLanguage.isOfficial = false;
                countryLanguage.percentage = rset.getDouble("countrylanguage.Percentage");
                languages.add(countryLanguage);
            }
            return languages;
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get languages");
            return null;
        }
    }



    //Class to hold report info for population report
    class LanguageReport
    {
        public String language;
        public String population;
        public String percentage;
    }

    //Method to print language report
    @RequestMapping("language_report")
    public ArrayList<LanguageReport> printMajorLanguages()
    {
        long totalPopulation = 0;
        long languagePopulation = 0;
        String languageName = null;
        ArrayList<Country> countries = new ArrayList<>();
        ArrayList<CountryLanguage> languages = new ArrayList<>();
        ArrayList<LanguageReport> reports = new ArrayList<>();

        TreeMap<Long, String> languagesInfo = new TreeMap<Long, String>
                (
                        new Comparator<Long>() {
                            @Override
                            public int compare(Long o1, Long o2) {
                                return o2.compareTo(o1);
                            }
                        });

        countries = getWorldPopulations();
        for (Country country : countries)
        {
            totalPopulation += country.population;
        }
        languages = getCountryLanguages("Chinese");
        for (CountryLanguage countryLanguage : languages)
        {
            Country country = getCountry(countryLanguage.countryCode);
            languageName = countryLanguage.language;
            languagePopulation += country.population / 100 * countryLanguage.percentage;
        }
        languagesInfo.put(languagePopulation, languageName);
        languagePopulation = 0;
        languageName = null;
        languages = getCountryLanguages("English");
        for (CountryLanguage countryLanguage : languages)
        {
            Country country = getCountry(countryLanguage.countryCode);
            languageName = countryLanguage.language;
            languagePopulation += country.population / 100 * countryLanguage.percentage;
        }
        languagesInfo.put(languagePopulation, languageName);
        languagePopulation = 0;
        languageName = null;
        languages = getCountryLanguages("Hindi");
        for (CountryLanguage countryLanguage : languages)
        {
            Country country = getCountry(countryLanguage.countryCode);
            languageName = countryLanguage.language;
            languagePopulation += country.population / 100 * countryLanguage.percentage;
        }
        languagesInfo.put(languagePopulation, languageName);
        languagePopulation = 0;
        languageName = null;
        languages = getCountryLanguages("Spanish");
        for (CountryLanguage countryLanguage : languages)
        {
            Country country = getCountry(countryLanguage.countryCode);
            languageName = countryLanguage.language;
            languagePopulation += country.population / 100 * countryLanguage.percentage;
        }
        languagesInfo.put(languagePopulation, languageName);
        languagePopulation = 0;
        languageName = null;
        languages = getCountryLanguages("Arabic");
        for (CountryLanguage countryLanguage : languages)
        {
            Country country = getCountry(countryLanguage.countryCode);
            languageName = countryLanguage.language;
            languagePopulation += country.population / 100 * countryLanguage.percentage;
        }
        languagesInfo.put(languagePopulation, languageName);
        languagePopulation = 0;
        languageName = null;
        for (Map.Entry<Long, String> language : languagesInfo.entrySet())
        {
            LanguageReport report = new LanguageReport();
            report.language = language.getValue();
            report.population = "" + language.getKey();
            report.percentage = "" + (100f / totalPopulation * language.getKey()) + "%";
            reports.add(report);
        }
        return reports;
    }

    /**
     * Prints the countries
     * @param area  Where to search
     * @param name The name of the continent and region
     * @return The country report
     */
    @RequestMapping("print_countries")
    public ArrayList<CountryReport> printCountriesIn(@RequestParam(value = "area") String area,@RequestParam(value = "name", required = false) String name)
    {
        City capitalCity;
        ArrayList<Country> countries = new ArrayList<>();
        CountryReport report;
        ArrayList<CountryReport> reports = new ArrayList<>();
        if(area.equals("world"))
        {
            countries = getWorldTopCountries();
        }
        if(area.equals("continent"))
        {
            countries = getContinentTopCountries(name);
        }
        if(area.equals("region"))
        {
            countries = getRegionTopCountries(name);
        }
        for (Country country : countries)
        {
            capitalCity = getCapitalCity(country.capital);
            report = printCountries(capitalCity, country);
            reports.add(report);
        }
        return reports;
    }

    /**
     * Prints the top countries
     * @param topNumber The number of countries the user wants to see
     * @param area  Where to search
     * @param name The name of the continent and region
     * @return The country report
     */
    @RequestMapping("print_top_countries")
    public ArrayList<CountryReport> printTopCountries(@RequestParam(value = "top") String topNumber,@RequestParam(value = "area") String area,@RequestParam(value = "name", required = false) String name)
    {
        City capitalCity;
        int topN = 0;
        ArrayList<Country> countries = new ArrayList<>();
        CountryReport report;
        ArrayList<CountryReport> reports = new ArrayList<>();
        try{
            topN = Integer.parseInt(topNumber);
        }catch(Exception e){
            System.out.println("Error, number field incorrect");
        }

        if(area.equals("world"))
        {
            countries = getWorldTopCountries();
        }
        if(area.equals("continent"))
        {
            countries = getContinentTopCountries(name);
        }
        if(area.equals("region"))
        {
            countries = getRegionTopCountries(name);
        }
        for (Country country : countries)
        {
            if (topN > 0)
            {
                capitalCity = getCapitalCity(country.capital);
                report = printCountries(capitalCity, country);
                reports.add(report);
                topN--;
            }
        }
        return reports;
    }

    class CountryReport
    {
        public String Code;
        public String Name;
        public String Continent;
        public String Region;
        public int Population;
        public String Capital;
    }

    /**
     * Prints the country report
     * @param capitalCity The capital city of the country
     * @param country The country
     */
    public CountryReport printCountries(City capitalCity, Country country)
    {
        CountryReport report = new CountryReport();
        report.Code = country.code;
        report.Name = country.name;
        report.Continent = country.continent;
        report.Region = country.region;
        report.Population = country.population;
        if (capitalCity != null) {
            report.Capital = capitalCity.name;
        }
        else
        {
            report.Capital = "None";
        }
        return report;
    }

    //Class to hold report info for population report
    class PopulationReport
    {
        public String name;
        public String totalPopulation;
        public String populationInCities;
        public String populationOutOfCities;
    }
    /**
     * Prints a population report
     * @param type The type of area
     * @param name The name of area
     */
    @RequestMapping("population_report")
    public ArrayList<PopulationReport> printPopulationReport(@RequestParam(value="type") String type, @RequestParam(value="name") String name)
    {
        long totalPopulation = 0; //Total population
        long cityPopulation = 0;  //Population living in cities
        long ruralPopulation = 0; //Population living out of cities
        ArrayList<Country> countries = new ArrayList<>();
        ArrayList<City> cities = new ArrayList<>();
        boolean valid = true;
        PopulationReport report = new PopulationReport();
        ArrayList<PopulationReport> reports = new ArrayList<>();

        if (type.equals("world"))
        {
            countries = getWorldPopulations();
        }
        else if (type.equals("continent"))
        {
            countries = getContinentPopulations(name);
        }
        else if (type.equals("region"))
        {
            countries = getRegionPopulations(name);
        }
        else if (type.equals("country"))
        {
            countries = getCountryPopulations(name);
        }
        else
        {
            System.out.println("Error invalid usage, Aborting...");
            valid = false;
        }
        if(valid && countries != null)
        {
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
            report.name = name;
            report.totalPopulation = "" + totalPopulation;
            report.populationInCities = "" + cityPopulation + "(" + (100f / totalPopulation * cityPopulation) + "%)";
            report.populationOutOfCities = "" + ruralPopulation + "(" + (100f / totalPopulation * ruralPopulation) + "%)";
            reports.add(report);
        }
        return reports;
    }




    class CapitalCityReport
    {
        public String Name;
        public String Country;
        public int Population;
    }

    /**
     * Prints the capital city report
     * @param city the city
     * @param country the name of the country
     * @return the capital city report
     */
    public CapitalCityReport printCapitalCity(City city, Country country)
    {
        CapitalCityReport report = new CapitalCityReport();
        report.Name = city.name;
        report.Country = country.name;
        report.Population = city.population;
        return report;
    }

    /**
     * Prints the top capital cities
     * @param topNumber the number of how many countries you want to be showed
     * @param area where to search
     * @param name the name of the continent or region
     * @return the capital city reports
     */
    @RequestMapping("print_top_capitals")
    public ArrayList<CapitalCityReport> printTopCapitalCities(@RequestParam(value = "top") String topNumber,@RequestParam(value = "area") String area,@RequestParam(value = "name", required = false) String name)
    {
        int topN = Integer.parseInt(topNumber);
        Country country;
        ArrayList<City> cities = new ArrayList<>();
        CapitalCityReport report;
        ArrayList<CapitalCityReport> reports = new ArrayList<>();
        if (area.equals("world"))
        {
            cities = getWorldTopCapitalCities();
        }
        else
        if (area.equals("continent"))
        {
            cities = getContinentTopCapitalCities(name);
        }
        else
        if (area.equals("region"))
        {
            cities = getRegionTopCapitalCities(name);
        }
        else
        {
            System.out.println("Invalid Place");
        }

        for (City city : cities)
        {
            if (topN > 0) {
                country = getCountry(city.countryCode);
                report = printCapitalCity(city, country);
                reports.add(report);
                topN--;
            }
        }
        return reports;
    }



    /**
     * Prints the capitals
     * @param area Where to search
     * @param name The name of the region or continent
     * @return the capital city reports
     */
    @RequestMapping("print_capitals")
    public ArrayList<CapitalCityReport> printCapitalCitiesIn(@RequestParam(value = "area") String area,@RequestParam(value = "name", required = false) String name)
    {
        Country country;
        ArrayList<City> cities = new ArrayList<>();
        CapitalCityReport report;
        ArrayList<CapitalCityReport> reports = new ArrayList<>();
        if (area.equals("world"))
        {
            cities = getWorldTopCapitalCities();
        }
        if (area.equals("continent"))
        {
            cities = getContinentTopCapitalCities(name);
        }
        if (area.equals("region"))
        {
            cities = getRegionTopCapitalCities(name);
        }
        for (City city : cities)
        {
            country = getCountry(city.countryCode);
            report = printCapitalCity(city, country);
            reports.add(report);
        }
        return reports;
    }

    class CityReport
    {
        public String Name;
        public String Country;
        public String District;
        public int Population;
    }

    /**
     * Prints the city report
     * @param city the city
     * @param country the name of the coutry
     * @return the report
     */
    public CityReport printCity(City city, Country country)
    {
        CityReport report = new CityReport();
        report.Name = city.name;
        report.Country = country.name;
        report.Population = city.population;
        report.District = city.district;
        return report;
    }

    /**
     * Prints the top cities
     * @param topNumber The amount of number of cities the user wants to see
     * @param area Where to search
     * @param name The name of the continent, region, country and district
     * @return The city reports
     */
    @RequestMapping("print_top_cities")
    public ArrayList<CityReport> printTopCitiesIn(@RequestParam(value = "top") String topNumber,@RequestParam(value = "area") String area,@RequestParam(value = "name", required = false) String name)
    {
        Country countryName;
        int topN = Integer.parseInt(topNumber);
        ArrayList<City> cities = new ArrayList<>();
        CityReport report;
        ArrayList<CityReport> reports = new ArrayList<>();
        if(area.equals("world"))
        {
            cities = getWorldTopCities();
        }
        if(area.equals("continent"))
        {
            cities = getContinentTopCities(name);
        }
        if(area.equals("region"))
        {
            cities = getRegionTopCities(name);
        }
        if(area.equals("country"))
        {
            cities = getCountryTopCities(name);
        }
        if(area.equals("district"))
        {
            cities = getDistrictTopCities(name);
        }
        for (City city : cities)
        {
            if (topN > 0) {
                countryName = getCountry(city.countryCode);
                report = printCity(city, countryName);
                reports.add(report);
                topN--;
            }
        }
        return reports;
    }

    /**
     * Prints the cities
     * @param area where to search
     * @param name the name of the continent, region, country, and district
     * @return
     */
    @RequestMapping("print_cities")
    public ArrayList<CityReport> printCitiesIn(@RequestParam(value = "area") String area,@RequestParam(value = "name", required = false) String name)
    {
        Country countryName;
        ArrayList<City> cities = new ArrayList<>();
        CityReport report;
        ArrayList<CityReport> reports = new ArrayList<>();
        if(area.equals("world"))
        {
            cities = getWorldTopCities();
        }
        if(area.equals("continent"))
        {
            cities = getContinentTopCities(name);
        }
        if(area.equals("region"))
        {
            cities = getRegionTopCities(name);
        }
        if(area.equals("country"))
        {
            cities = getCountryTopCities(name);
        }
        if(area.equals("district"))
        {
            cities = getDistrictTopCities(name);
        }
        for (City city : cities)
        {
            countryName = getCountry(city.countryCode);
            report = printCity(city, countryName);
            reports.add(report);
        }
        return reports;
    }

    /**
     * Main method
     */
    public static void main(String[] args)
    {
        // Connect to database
        if (args.length < 1)
        {
            connect("localhost:33060");
        }
        else
        {
            connect(args[0]);
        }

        SpringApplication.run(App.class, args);
    }
}
