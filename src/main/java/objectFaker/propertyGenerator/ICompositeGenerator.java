package objectFaker.propertyGenerator;

import objectFaker.ConflictTypeGeneratorException;
import objectFaker.NoSuchPropertyException;

/**
 * Created by Sandrine on 18/09/2015.
 */
public interface ICompositeGenerator {

    void setGenerator(String propertyName, PropertyGenerator generator)
            throws NoSuchPropertyException, ConflictTypeGeneratorException;
}
