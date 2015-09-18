package objectFaker.propertyGenerator;

import objectFaker.ConflictTypeGeneratorException;
import objectFaker.IPropertyGeneratorFactory;
import objectFaker.NoSuchPropertyException;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Sandrine on 17/09/2015.
 */
public class MapPropertyGenerator<K, V> implements PropertyGenerator<Map<K, V>>, ICompositeGenerator {
    protected PropertyGenerator<K> keyGenerator;

    protected PropertyGenerator<V> valueGenerator;

    protected int size;

    public MapPropertyGenerator(Class<K> keyClass, Class<V> valueClass, IPropertyGeneratorFactory factory, int size){
        this.keyGenerator = factory.generator(keyClass);
        this.valueGenerator = factory.generator(valueClass);
        this.size = size;
    }

    public MapPropertyGenerator(PropertyGenerator<K> keyGenerator, PropertyGenerator<V> valueGenerator, int size){
        this.keyGenerator = keyGenerator;
        this.valueGenerator = valueGenerator;
        this.size = size;
    }



    @Override
    public Map<K, V> generate() {
        Map<K, V> result = new HashMap<>();
        for(int i = 0; i < this.size; i++){
            K key = this.keyGenerator.generate();
            V value = this.valueGenerator.generate();
            result.put(key, value);
        }
        return result;
    }


    @Override
    public void setGenerator(String propertyName, PropertyGenerator generator)
            throws NoSuchPropertyException, ConflictTypeGeneratorException {
        if(propertyName.contains(".")) {
            if (propertyName.startsWith("key.")) {
                String name = propertyName.substring(4, propertyName.length());
                if (keyGenerator instanceof ICompositeGenerator) {
                    ((ICompositeGenerator) this.keyGenerator).setGenerator(name, generator);
                    return;
                } else {
                    throw new ConflictTypeGeneratorException();
                }
            }

            if (propertyName.startsWith("value.")) {
                String name = propertyName.substring(6, propertyName.length());
                if (this.valueGenerator instanceof ICompositeGenerator) {
                    ((ICompositeGenerator) this.valueGenerator).setGenerator(name, generator);
                    return;
                } else {
                    throw new ConflictTypeGeneratorException();
                }
            }
        }
        else{
            if(propertyName.equals("key")){
                this.keyGenerator = generator;
                return;
            }
            if(propertyName.equals("value")){
                this.valueGenerator = generator;
                return;
            }
        }

        throw new NoSuchPropertyException();
    }
}
