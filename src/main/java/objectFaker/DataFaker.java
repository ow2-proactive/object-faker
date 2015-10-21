package objectFaker;

import objectFaker.propertyGenerator.ICompositeGenerator;
import objectFaker.propertyGenerator.PropertyGenerator;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.reflect.FieldUtils;

/**
 * Created by Sandrine on 16/09/2015.
 */
public class DataFaker<T> {

    protected Class<T> clazz;

    protected HashMap<String, PropertyGenerator> generators = new HashMap<>();
    
    protected PropertyFinder propertyFinder = new PropertyFinder();

    public DataFaker(Class<T> clazz){
        this(clazz, new DefaultValueGeneratorFactory());
    }


    public DataFaker(Class<T> clazz, DefaultValueGeneratorFactory factory){
        this.clazz = clazz;

        //prepare default generators for each javabean property
        List<Field> properties = propertyFinder.findProperties(clazz);
        for(Field currentField: properties){
            PropertyGenerator generator = factory.generator(currentField);
            this.generators.put(currentField.getName(), generator);
        }
    }


    public HashMap<String, PropertyGenerator> getGenerators(){
        return this.generators;
    }

    
    public void setGenerator(String propertyName, PropertyGenerator generator)
            throws NoSuchPropertyException, ConflictTypeGeneratorException{
        String directPropertyName = null;
        int index = propertyName.indexOf('.');
        if(index > 1){
            directPropertyName = propertyName.substring(0, index);
        }
        else{
            directPropertyName = propertyName;
        }

        PropertyGenerator gen = this.generators.get(directPropertyName);
        if(gen == null){
            throw new NoSuchPropertyException();
        }

        if(index > 1){
            if(gen instanceof ICompositeGenerator){
                String secondPartPropertyName = propertyName.substring(index + 1, propertyName.length());
                ((ICompositeGenerator) gen).setGenerator(secondPartPropertyName, generator);
            }
            else{
                throw new ConflictTypeGeneratorException();
            }
        }
        else{
            if(index == 0){
                propertyName = propertyName.substring(1, propertyName.length());
            }
            this.generators.put(propertyName, generator);
        }
    }


    public List<T> fakeList(int numberOfFake){
        List<T> result = new ArrayList<>(numberOfFake);
        for(int i = 0; i < numberOfFake; i++){
            result.add(this.fake());
        }
        return result;
    }


    public T fake(){
        try {
            T instance = this.clazz.getConstructor().newInstance();
            for(Map.Entry<String, PropertyGenerator> currentField: this.generators.entrySet()){
                PropertyGenerator gen = currentField.getValue();
                if(gen != null) {
                    FieldUtils.writeField(instance, currentField.getKey(), gen.generate(), true);
                }
            }
            return instance;
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } 
        return null;
    }
    

    protected void fakeProperty(String propertyName){

    }
}
