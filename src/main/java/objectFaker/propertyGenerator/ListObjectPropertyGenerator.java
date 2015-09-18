package objectFaker.propertyGenerator;

import objectFaker.ConflictTypeGeneratorException;
import objectFaker.NoSuchPropertyException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sandrine on 17/09/2015.
 */
public class ListObjectPropertyGenerator<T> implements PropertyGenerator<List<T>>, ICompositeGenerator {

    protected ObjectPropertyGenerator<T> internalGenerator;

    protected int size;

    public ListObjectPropertyGenerator(Class<T> clazz, int size){
        this.internalGenerator = new ObjectPropertyGenerator<T>(clazz);
        this.size = size;
    }



    @Override
    public List<T> generate() {
        List<T> result = new ArrayList<>();
        for(int i = 0; i < this.size; i++){
            result.add(this.internalGenerator.generate());
        }
        return result;
    }


    @Override
    public void setGenerator(String propertyName, PropertyGenerator generator)
            throws NoSuchPropertyException, ConflictTypeGeneratorException {
        this.internalGenerator.setGenerator(propertyName, generator);
    }
}
