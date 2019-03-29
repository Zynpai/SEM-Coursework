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
    @RequestMapping("population_continent")
    public ArrayList<Country> getContinentPopulations(@RequestParam(value = "continent") String continent)
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
    @RequestMapping("population_region")
    public ArrayList<Country> getRegionPopulations(@RequestParam(value = "region") String region)
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
    @RequestMapping("population_country")
    public ArrayList<Country> getCountryPopulations(@RequestParam(value = "country") String countryName)
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
    @RequestMapping("population_district")
    public ArrayList<City> getDistrictPopulations(@RequestParam(value = "district") String district)
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
    @RequestMapping("population_city")
    public ArrayList<City> getCityPopulations(@RequestParam(value = "city") String cityName)
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
     * Gets the countries and puts it to largest to smallest based on their population
     * @return A list of countries and their details, or null if theres is an error
     */
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

    public ArrayList<Country> getContinentTopCountries(String continent)
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

    public ArrayList<Country> getRegionTopCountries(String region)
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
    public Country getCountry(String countryCode)
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

    public ArrayList<City> getContinentTopCities(String continent)
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

    public ArrayList<City> getRegionTopCities(String region)
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

    public ArrayList<City> getCountryTopCities(String country)
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

    public ArrayList<City> getDistrictTopCities(String district)
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
     * Gets languages in countries
     * @param language The language to look for
     * @return All the languages in countries
     */
    public ArrayList<CountryLanguage> getCountryLanguages(String language)
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

    /**
     * Prints a language report
     * @param languages The language to report on
     */
    public void printLanguageReport(ArrayList<CountryLanguage> languages)
    {
        long totalPopulation = 0;
        long languagePopulation = 0;
        ArrayList<Country> countries;
        String languageName = null;

        countries = getWorldPopulations();
        for (Country country : countries)
        {
            totalPopulation += country.population;
        }
        for (CountryLanguage countryLanguage : languages)
        {
            Country country = getCountry(countryLanguage.countryCode);
            languageName = countryLanguage.language;
            languagePopulation += country.population / 100 * countryLanguage.percentage;
        }

        System.out.println("Language: " + languageName);
        System.out.println("Population who speak it: " + languagePopulation);
        System.out.println("Percentage of world population: " + (100f / totalPopulation * languagePopulation));
    }

    public void printMajorLanguages()
    {
        long totalPopulation = 0;
        long languagePopulation = 0;
        String languageName = null;
        ArrayList<Country> countries;
        ArrayList<CountryLanguage> languages;
        TreeMap<Long, String> languagesInfo = new TreeMap<Long, String>
                (
                        new Comparator<Long>() {
                            @Override
                            public int compare(Long o1, Long o2) {
                                return o2.compareTo(o1);
                            }
                        });

        System.out.println("Language report");
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
            System.out.println("Language: " + language.getValue());
            System.out.println("Population who speak it: " + language.getKey());
            System.out.println("Percentage of world population: " + (100f / totalPopulation * language.getKey()));
        }
    }
    /**
     * Prints the countries in the given area, largest to smallest based on population
     * @param countries The list of countries
     * @param continent The continent
     * @param region The region
     * @param worldBool The boolean that sees if the user is searching in the world
     * @param continentBool The boolean that sees if the user is searching in the continent
     * @param regionBool The boolean that sees if the user is searching in the region
     */
    public void printCountriesIn(ArrayList<Country> countries, String continent, String region, boolean worldBool, boolean continentBool, boolean regionBool)
    {
        City capitalCity;
        if(worldBool)
        {
            countries = getWorldTopCountries();
        }
        if(continentBool)
        {
            countries = getContinentTopCountries(continent);
        }
        if(regionBool)
        {
            countries = getRegionTopCountries(region);
        }
        for (Country country : countries)
        {
            capitalCity = getCapitalCity(country.capital);
            printCountries(capitalCity, country);
        }
    }

    public void printTopCountries(ArrayList<Country> countries, String continent, String region, int topNumber, boolean worldBool, boolean continentBool, boolean regionBool)
    {
        City capitalCity;
        int top = 1;
        if(worldBool)
        {
            countries = getWorldTopCountries();
        }
        if(continentBool)
        {
            countries = getContinentTopCountries(continent);
        }
        if(regionBool)
        {
            countries = getRegionTopCountries(region);
        }
        for (Country country : countries)
        {
            if (topNumber > 0)
            {
                System.out.println("Top " + top);
                capitalCity = getCapitalCity(country.capital);
                printCountries(capitalCity, country);
                topNumber--;
                top++;
            }
        }
    }

    /**
     * Prints the country report
     * @param capitalCity The capital city of the country
     * @param country The country
     */
    public void printCountries(City capitalCity, Country country)
    {
        System.out.println("Code:" + country.code);
        System.out.println("Name:" + country.name);
        System.out.println("Continent:" + country.continent);
        System.out.println("Region:" + country.region);
        System.out.println("Population:" + country.population);
        if (capitalCity != null) {
            System.out.println("Capital:" + capitalCity.name);
        }
        else
        {
            System.out.println("Has no capital city.");
        }
        System.out.println("------------------------");
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
        boolean valid = true;

        if (worldBool)
        {
            countries = getWorldPopulations();
            name = "World";
        }else if (continentBool && continentName != null && continentName != "")
        {
            countries = getContinentPopulations(continentName);
            name = continentName;
        }else if (regionBool && regionName != null && regionName != "")
        {
            countries = getRegionPopulations(regionName);
            name = regionName;
        }else if (countryBool && countryName != null && countryName != "")
        {
            countries = getCountryPopulations(countryName);
            name = countryName;
        } else{
            System.out.println("Error invalid usage, Aborting...");
            valid = false;
        }
        if(valid && countries != null){
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
        int top = 1;
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
                System.out.println("Top " + top);
                country = getCountry(city.countryCode);
                printCapitalCities(city, country);
                topNumber--;
                top++;
            }
        }
    }

    /**
     * Prints capital cities in the given area in largest to smallest
     * @param cities The list of cities
     * @param continent The continent
     * @param region The region
     * @param worldBool Sees if the user is searching in world
     * @param continentBool Sees if the user is searching in the continent
     * @param regionBool Sees if the user is searching in the region
     */
    public void printCapitalCitiesIn(ArrayList<City> cities, String continent, String region, boolean worldBool, boolean continentBool, boolean regionBool)
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
            country = getCountry(city.countryCode);
            printCapitalCities(city, country);
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

    public void printTopCitiesIn(ArrayList<City> cities, String continent, String region, String country,  String district, int topNumber, boolean worldBool, boolean continentBool, boolean regionBool, boolean countryBool, boolean districtBool)
    {
        Country countryName;
        int top = 1;
        if(worldBool)
        {
            cities = getWorldTopCities();
        }
        if(continentBool)
        {
            cities = getContinentTopCities(continent);
        }
        if(regionBool)
        {
            cities = getRegionTopCities(region);
        }
        if(countryBool)
        {
            cities = getCountryTopCities(country);
        }
        if(districtBool)
        {
            cities = getDistrictTopCities(district);
        }
        for (City city : cities)
        {

            if (topNumber > 0) {
                System.out.println("Top " + top);
                countryName = getCountry(city.countryCode);
                printCities(city, countryName);
                topNumber--;
                top++;
            }
        }
    }

    public void printCitiesIn(ArrayList<City> cities, String continent, String region, String country,  String district, boolean worldBool, boolean continentBool, boolean regionBool, boolean countryBool, boolean districtBool)
    {
        Country countryName;
        if(worldBool)
        {
            cities = getWorldTopCities();
        }
        if(continentBool)
        {
            cities = getContinentTopCities(continent);
        }
        if(regionBool)
        {
            cities = getRegionTopCities(region);
        }
        if(countryBool)
        {
            cities = getCountryTopCities(country);
        }
        if(districtBool)
        {
            cities = getDistrictTopCities(district);
        }
        for (City city : cities)
        {
            countryName = getCountry(city.countryCode);
            printCities(city, countryName);
        }
    }

    public void printCities(City city, Country country)
    {
        System.out.println("Name:" + city.name);
        System.out.println("Country:" + country.name);
        System.out.println("District:" + city.district);
        System.out.println("Population:" + city.population);
        System.out.println("------------------------");
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
