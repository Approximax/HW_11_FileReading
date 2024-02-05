import com.codeborne.pdftest.PDF;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class PDFReadFromZipTest {

    private final ClassLoader classLoader = PDFReadFromZipTest.class.getClassLoader();

    @Test
    void pdfParsingTest() throws Exception {
        try (InputStream inputStream = classLoader.getResourceAsStream("test-resources.zip");
             ZipInputStream zipInputStream = new ZipInputStream(inputStream)) {
            ZipEntry entry;
            while ((entry = zipInputStream.getNextEntry()) != null) {
                if (entry.getName().endsWith(".pdf")) {
                    PDF pdf = new PDF(zipInputStream);
                    Assertions.assertTrue(pdf.text.contains("PDF INFO & TEST FILE"));
                }
            }
        }
    }
}
