package objectFaker.propertyGenerator;

import objectFaker.propertyGenerator.PropertyGenerator;

import java.util.Random;

/**
 * Created by Sandrine on 16/09/2015.
 */
public abstract class RandomNumberPropertyGenerator<T extends Number> implements PropertyGenerator<T> {

    protected T first;

    protected T last;

    protected Random rand;


    public RandomNumberPropertyGenerator(T first, T last){
        this.first = first;
        this.last = last;
        this.rand = new Random();
    }

}
