package objectFaker.propertyGenerator;


/**
 * Created by Sandrine on 16/09/2015.
 */
public abstract class RandomNumberPropertyGenerator<T extends Number> extends RandomRangePropertyGenerator<T> {

    public RandomNumberPropertyGenerator(T first, T last){
        super(first, last);
    }

}
