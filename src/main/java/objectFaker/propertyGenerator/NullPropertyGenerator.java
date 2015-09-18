package objectFaker.propertyGenerator;

/**
 * Created by Sandrine on 16/09/2015.
 */
public class NullPropertyGenerator implements PropertyGenerator<Object> {
    @Override
    public Object generate() {
        return null;
    }
}
