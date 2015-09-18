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

/**
 * Created by Sandrine on 16/09/2015.
 */
public class DataFaker<T> {

    protected Class<T> clazz;

    protected HashMap<String, PropertyGenerator> generators = new HashMap<>();

    public DataFaker(Class<T> clazz){
        this(clazz, new DefaultValueGeneratorFactory());
    }


    public DataFaker(Class<T> clazz, DefaultValueGeneratorFactory factory){
        this.clazz = clazz;

        //prepare default generators for each javabean property
        List<Field> properties = computePropertyNames();
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
                    Field field = this.clazz.getDeclaredField(currentField.getKey());
                    String setterName = setterField(currentField.getKey());
                    Method method = this.clazz.getDeclaredMethod(setterName, field.getType());
                    method.invoke(instance, gen.generate());
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
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        return null;
    }


    protected List<Field> computePropertyNames(){
        ArrayList<Field> properties = new ArrayList<>();

        Field[] fields = this.clazz.getDeclaredFields();
        for(Field currentField: fields){
            if(hasGetter(currentField) && hasSetter(currentField)){
                properties.add(currentField);
            }
        }

        return properties;
    }


    protected boolean hasGetter(Field field){
        try {
            this.clazz.getDeclaredMethod(getterField(field.getName()));
            return true;
        } catch (NoSuchMethodException e) {
            return false;
        }
    }


    protected String getterField(String fieldName){
        return "get" + fieldName.substring(0,1).toUpperCase() + fieldName.substring(1);
    }

    protected boolean hasSetter(Field field){
        try {
            this.clazz.getDeclaredMethod(setterField(field.getName()), field.getType());
            return true;
        } catch (NoSuchMethodException e) {
            return false;
        }
    }

    protected String setterField(String fieldName){
        return "set" + fieldName.substring(0,1).toUpperCase() + fieldName.substring(1);
    }


    protected void fakeProperty(String propertyName){

    }
}
