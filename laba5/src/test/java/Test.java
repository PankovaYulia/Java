import implementations.*;
import interfaces.*;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import java.io.*;
import java.lang.reflect.*;
import java.nio.file.*;
import java.util.*;

class DependencyInjectionTest {

    private static final String CONFIG_PATH = "src/main/properties/config.properties";
    private String originalConfigContent;

    @BeforeEach
    void backupConfig() throws IOException {
        originalConfigContent = Files.readString(Paths.get(CONFIG_PATH));
    }

    @AfterEach
    void restoreConfig() throws IOException {
        Files.writeString(Paths.get(CONFIG_PATH), originalConfigContent);
    }

    @Test
    void testSomeImplInjection() {
        SomeBean bean = new Injector().inject(new SomeBean());
        assertInstanceOf(SomeImpl.class, getPrivateField(bean, "field1"));
    }

    @Test
    void testSODoerInjection() {
        SomeBean bean = new Injector().inject(new SomeBean());
        assertInstanceOf(SODoer.class, getPrivateField(bean, "field2"));
    }

    @Test
    void testMethodOutput() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        SomeBean bean = new Injector().inject(new SomeBean());
        bean.foo();

        String output = out.toString().replace("\r", "");
        assertEquals("A\nC\n", output);
    }

    @Test
    void testAlternativeImplementation() throws IOException {
        // Меняем конфигурацию
        Files.writeString(Paths.get(CONFIG_PATH),
                "interfaces.SomeInterface=implementations.OtherImpl\n" +
                        "interfaces.SomeOtherInterface=implementations.SODoer");

        SomeBean bean = new Injector().inject(new SomeBean());
        assertInstanceOf(OtherImpl.class, getPrivateField(bean, "field1"));

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        bean.foo();

        String output = out.toString().replace("\r", "");
        assertEquals("B\nC\n", output);
    }

    @Test
    void testMissingConfigProperty() throws IOException {
        Files.writeString(Paths.get(CONFIG_PATH),
                "interfaces.SomeOtherInterface=implementations.SODoer");

        SomeBean bean = new SomeBean();
        assertThrows(RuntimeException.class, () -> new Injector().inject(bean));
    }

    @Test
    void testAutoInjectableAnnotation() throws NoSuchFieldException {
        Field field1 = SomeBean.class.getDeclaredField("field1");
        assertTrue(field1.isAnnotationPresent(AutoInjectable.class));

        Field field2 = SomeBean.class.getDeclaredField("field2");
        assertTrue(field2.isAnnotationPresent(AutoInjectable.class));
    }

    private Object getPrivateField(Object obj, String fieldName) {
        try {
            Field field = obj.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);
            return field.get(obj);
        } catch (Exception e) {
            throw new RuntimeException("Failed to access private field", e);
        }
    }

    // Вспомогательный класс для тестирования
    public static class TestBean {
        @AutoInjectable
        private SomeInterface field;
    }

    @Test
    void testWithDifferentBean() {
        TestBean bean = new Injector().inject(new TestBean());
        assertInstanceOf(SomeImpl.class, getPrivateField(bean, "field"));
    }
}