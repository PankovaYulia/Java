import interfaces.AutoInjectable;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Properties;

/**
 * Инжектор зависимостей, который внедряет реализации на основе конфигурации
 */
public class Injector {
    /**
     * Внедряет зависимости в переданный объект
     * @param injectableObject объект для внедрения зависимостей
     * @param <T> тип объекта
     * @return объект с внедренными зависимостями
     */
    public <T> T inject(T injectableObject) {
        Properties properties = new Properties();
        try (FileInputStream fileInputStream = new FileInputStream("src/main/properties/config.properties")) {
            properties.load(fileInputStream);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        Class<?> injectableClass = injectableObject.getClass();
        Field[] fields = injectableClass.getDeclaredFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(AutoInjectable.class)) {
                Class<?> fieldType = field.getType();
                String interfaceName = properties.getProperty(fieldType.getName());
                try {
                    Object implementation = Class.forName(interfaceName).newInstance();
                    field.setAccessible(true);
                    field.set(injectableObject, implementation);
                }
                catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
        return injectableObject;
    }
}