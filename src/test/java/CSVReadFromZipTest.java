import com.opencsv.CSVReader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class CSVReadFromZipTest {

    private final ClassLoader classLoader = CSVReadFromZipTest.class.getClassLoader();

    @Test
    void csvParsingTest() throws Exception {
        try (InputStream inputStream = classLoader.getResourceAsStream("test-resources.zip");
             ZipInputStream zipInputStream = new ZipInputStream(inputStream)) {
            ZipEntry entry;
            while ((entry = zipInputStream.getNextEntry()) != null) {
                if (entry.getName().endsWith(".csv")) {
                    CSVReader csvReader = new CSVReader(new InputStreamReader(zipInputStream, StandardCharsets.UTF_8));
                    List<String[]> content = csvReader.readAll();
                    Assertions.assertArrayEquals(
                            new String[]{"Семен", " Иванов"}, content.get(2)
                    );
                }
            }
        }
    }
}