package objectFaker.propertyGenerator;

import objectFaker.propertyGenerator.PropertyGenerator;

import java.util.Random;

/**
 * Created by Sandrine on 16/09/2015.
 */
public class RandomEnumPropertyGenerator<T extends Enum> implements PropertyGenerator<T> {

    protected Class<T> enumeration;

    protected T[] values;


    public RandomEnumPropertyGenerator(Class<T> enumClass){
        this.enumeration = enumClass;
        this.values = this.enumeration.getEnumConstants();
    }


    @Override
    public T generate() {
        int index = new Random().nextInt(values.length - 1);
        return values[index];
    }
}
