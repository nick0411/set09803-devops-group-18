import com.napier.devops.model.City;
import com.napier.devops.reportformat.CityReport;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CityReportTest {

    @Test
    public void testPrintCityReport() {
        List<City> cities = List.of(
                new City("Yangon", "MMR", "Rangoon [Yangon]",
                        3361700),
                new City("Mandalay", "MMR", "Mandalay",
                        885300)
        );

        PrintStream originalOut = System.out;
        ByteArrayOutputStream output = new ByteArrayOutputStream();

        try {
            System.setOut(new PrintStream(output, true,
                    StandardCharsets.UTF_8));

            CityReport.printCityReport(cities);

        } finally {
            System.setOut(originalOut);
        }

        String result = output.toString(StandardCharsets.UTF_8);

        assertTrue(result.contains("Name"));
        assertTrue(result.contains("Country"));
        assertTrue(result.contains("District"));
        assertTrue(result.contains("Population"));
        assertTrue(result.contains("Yangon"));
        assertTrue(result.contains("Mandalay"));
        assertTrue(result.contains("3,361,700"));
        assertTrue(result.contains("885,300"));
    }
}
