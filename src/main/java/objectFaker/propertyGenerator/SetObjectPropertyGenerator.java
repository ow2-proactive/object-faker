package objectFaker.propertyGenerator;

import java.util.HashSet;
import java.util.Set;

import objectFaker.ConflictTypeGeneratorException;
import objectFaker.IPropertyGeneratorFactory;
import objectFaker.NoSuchPropertyException;

/**
 * Created by Sandrine on 17/09/2015.
 */
public class SetObjectPropertyGenerator<T> implements PropertyGenerator<Set<T>>, ICompositeGenerator {

    protected PropertyGenerator<T> internalGenerator;

    protected int size;

    public SetObjectPropertyGenerator(Class<T> clazz, IPropertyGeneratorFactory factory, int size) {
        this.internalGenerator = factory.generator(clazz);
        this.size = size;
    }


    @Override
    public Set<T> generate() {
        Set<T> result = new HashSet<>();
        for (int i = 0; i < this.size; i++) {
            result.add(this.internalGenerator.generate());
        }
        return result;
    }


    @Override
    public void setGenerator(String propertyName, PropertyGenerator generator)
            throws NoSuchPropertyException, ConflictTypeGeneratorException {
        this.internalGenerator = generator;
    }
}
