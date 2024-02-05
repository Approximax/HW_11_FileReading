import com.codeborne.xlstest.XLS;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class XLSReadFromZipTest {

    private final ClassLoader classLoader = XLSReadFromZipTest.class.getClassLoader();

    @Test
    void xlsParsingTest() throws Exception {
        try (InputStream inputStream = classLoader.getResourceAsStream("test-resources.zip");
             ZipInputStream zipInputStream = new ZipInputStream(inputStream)) {
            ZipEntry entry;
            while ((entry = zipInputStream.getNextEntry()) != null) {
                if (entry.getName().endsWith(".xlsx")) {
                    XLS xls = new XLS(zipInputStream);
                    Assertions.assertEquals("Уборщик",
                            xls.excel.getSheet("Sheet1")
                                    .getRow(4)
                                    .getCell(2)
                                    .getStringCellValue()
                            );
                }
            }
            }
        }
    }