package objectFaker;

import objectFaker.propertyGenerator.PropertyGenerator;

import java.lang.reflect.Field;
import java.lang.reflect.Type;

/**
 * Created by Sandrine on 17/09/2015.
 */
public interface IPropertyGeneratorFactory {

    PropertyGenerator generator(Field field);

    PropertyGenerator generator(Class clazz, Type... parameterTypes);
}
