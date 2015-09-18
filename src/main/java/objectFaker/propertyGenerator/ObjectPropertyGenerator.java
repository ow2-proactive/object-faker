package objectFaker.propertyGenerator;

import objectFaker.ConflictTypeGeneratorException;
import objectFaker.DataFaker;
import objectFaker.NoSuchPropertyException;

/**
 * Created by Sandrine on 16/09/2015.
 */
public class ObjectPropertyGenerator<T> implements PropertyGenerator<T>, ICompositeGenerator {

    protected DataFaker<T> generator;

    public ObjectPropertyGenerator(Class<T> clazz){
        this.generator = new DataFaker<>(clazz);
    }


    @Override
    public T generate() {
        return this.generator.fake();
    }


    public void setGenerator(String propertyName, PropertyGenerator generator)
            throws NoSuchPropertyException, ConflictTypeGeneratorException {
        this.generator.setGenerator(propertyName, generator);
    }
}
