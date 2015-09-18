package objectFaker.propertyGenerator;

/**
 * Created by Sandrine on 16/09/2015.
 */
public class FixedPropertyGenerator<T> implements PropertyGenerator<T> {

    protected T value;

    public FixedPropertyGenerator(T value){
        this.value = value;
    }


    @Override
    public T generate() {
        return this.value;
    }
}
