package objectFaker;

import objectFaker.propertyGenerator.PropertyGenerator;

import java.lang.reflect.Field;

/**
 * Created by Sandrine on 17/09/2015.
 */
public interface IPropertyGeneratorFactory {

    PropertyGenerator generator(Field field);

    PropertyGenerator generator(Class clazz);
}
