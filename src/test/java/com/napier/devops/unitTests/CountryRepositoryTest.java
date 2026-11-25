package com.napier.devops.unitTests;

import com.napier.devops.model.Country;
import com.napier.devops.repository.CountryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class CountryRepositoryTest {

    private Connection con;
    private Statement stmt;
    private PreparedStatement pstmt;
    private ResultSet rset;
    private CountryRepository repo;

    @BeforeEach
    public void setUp() throws Exception {
        con = mock(Connection.class);
        stmt = mock(Statement.class);
        pstmt = mock(PreparedStatement.class);
        rset = mock(ResultSet.class);

        when(con.createStatement()).thenReturn(stmt);
        when(stmt.executeQuery(anyString())).thenReturn(rset);
        when(con.prepareStatement(anyString())).thenReturn(pstmt);
        when(pstmt.executeQuery()).thenReturn(rset);

        repo = new CountryRepository(con);
    }

    private void mockCountries() throws Exception {
        when(rset.next()).thenReturn(true, true, false);

        when(rset.getString("Code")).thenReturn("MMR", "THA");
        when(rset.getString("Name")).thenReturn("Myanmar", "Thailand");
        when(rset.getString("Continent")).thenReturn("Asia", "Asia");
        when(rset.getString("Region")).thenReturn("Southeast Asia", "Southeast Asia");
        when(rset.getLong("Population")).thenReturn(45611000L, 61399000L);
        when(rset.getString("Capital")).thenReturn("Yangon", "Bangkok");
    }

    @Test
    public void testGetAllCountriesWorld() throws Exception {
        mockCountries();

        List<Country> countries = repo.getAllCountriesWorld();

        assertNotNull(countries);
        assertEquals(2, countries.size());
        assertEquals("Myanmar", countries.get(0).getName());
        assertEquals("Thailand", countries.get(1).getName());
        verify(stmt).executeQuery(contains("ORDER BY Population DESC"));
    }

    @Test
    public void testGetAllCountriesContinent() throws Exception {
        mockCountries();

        List<Country> countries = repo.getAllCountriesContinent("Asia");

        assertNotNull(countries);
        assertEquals(2, countries.size());
        verify(pstmt).setString(1, "Asia");
        verify(pstmt).executeQuery();
    }

    @Test
    public void testGetAllCountriesRegion() throws Exception {
        mockCountries();

        List<Country> countries = repo.getAllCountriesRegion("Southeast Asia");

        assertNotNull(countries);
        assertEquals(2, countries.size());
        verify(pstmt).setString(1, "Southeast Asia");
        verify(pstmt).executeQuery();
    }

    @Test
    public void testGetTopNCountriesWorld() throws Exception {
        mockCountries();

        List<Country> countries = repo.getTopNCountriesWorld(5);

        assertNotNull(countries);
        assertEquals(2, countries.size());
        verify(pstmt).setInt(1, 5);
        verify(pstmt).executeQuery();
    }

    @Test
    public void testGetTopNCountriesContinent() throws Exception {
        mockCountries();

        List<Country> countries = repo.getTopNCountriesContinent("Asia", 5);

        assertNotNull(countries);
        assertEquals(2, countries.size());
        verify(pstmt).setString(1, "Asia");
        verify(pstmt).setInt(2, 5);
        verify(pstmt).executeQuery();
    }

    @Test
    public void testGetTopNCountriesRegion() throws Exception {
        mockCountries();

        List<Country> countries = repo.getTopNCountriesRegion("Southeast Asia", 5);

        assertNotNull(countries);
        assertEquals(2, countries.size());
        verify(pstmt).setString(1, "Southeast Asia");
        verify(pstmt).setInt(2, 5);
        verify(pstmt).executeQuery();
    }

    @Test
    public void testEmptyResults() throws Exception {
        when(rset.next()).thenReturn(false);

        List<Country> countries = repo.getAllCountriesWorld();

        assertNotNull(countries);
        assertTrue(countries.isEmpty());
    }
}