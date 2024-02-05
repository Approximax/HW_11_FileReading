import com.fasterxml.jackson.databind.ObjectMapper;
import models.Human;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

public class JsonParsingTest{

    private final ClassLoader classLoader = JsonParsingTest.class.getClassLoader();

    @Test
    void jsonParsing() throws Exception {
        try (InputStream inputStream = classLoader.getResourceAsStream("test-json.json");
             Reader reader = new InputStreamReader(inputStream)) {
            ObjectMapper objectMapper = new ObjectMapper();
            Human human = objectMapper.readValue(reader, Human.class);

            Assertions.assertEquals("Kirsten Sellers", human.name);
            Assertions.assertEquals(24, human.age);
            Assertions.assertArrayEquals(
                    new String[]{"cooking", "coding", "singing"},
                    human.interests.toArray()
            );
            Assertions.assertEquals("59761c233d8d0f92a6b0570d", human.uid.id);
            Assertions.assertEquals(true, human.uid.isActive);
        }
    }
}
