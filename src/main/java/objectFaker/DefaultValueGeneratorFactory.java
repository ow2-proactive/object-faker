package objectFaker;

import objectFaker.propertyGenerator.*;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

/**
 * Created by Sandrine on 16/09/2015.
 */
public class DefaultValueGeneratorFactory implements IPropertyGeneratorFactory {

    @Override
    public PropertyGenerator generator(Field field){
        Class clazz = field.getType();
        if(clazz.equals(Long.class) || clazz.equals(long.class)){
            return generatorForLong(field);
        }
        if(clazz.equals(Integer.class) || clazz.equals(int.class)){
            return generatorForInt(field);
        }
        if(clazz.equals(String.class)){
            return generatorForString(field);
        }

        if(clazz.equals(byte[].class)){
            return this.generatorForByteArray(field);
        }

        if(List.class.isAssignableFrom(clazz)){
            return generatorForList(field);
        }

        if(Map.class.isAssignableFrom(clazz)){
            return generatorForMap(field);
        }


        if(clazz.isEnum()){
            return generatorForEnum(field);
        }

        return generatorForObject(field);
    }


    @Override
    public PropertyGenerator generator(Class clazz){
        if(clazz.equals(Long.class) || clazz.equals(long.class)){
            return generatorForLong();
        }
        if(clazz.equals(Integer.class) || clazz.equals(int.class)){
            return generatorForInt();
        }
        if(clazz.equals(String.class)){
            return generatorForString();
        }

        if(List.class.isAssignableFrom(clazz)){
            return generatorForList(clazz);
        }

        if(Map.class.isAssignableFrom(clazz)){
            return generatorForMap(clazz);
        }

        if(clazz.equals(byte[].class)){
            return this.generatorForByteArray(clazz);
        }

        if(clazz.isEnum()){
            return generatorForEnum(clazz);
        }

        return generatorForObject(clazz);
    }


    protected PropertyGenerator generatorForInt(Field field){
        if(field != null && field.getName().equals("id")){
            return new IncrementIntPropertyGenerator();
        }

        return new RandomIntPropertyGenerator(1, 10);
    }


    protected PropertyGenerator generatorForInt(){
        return new RandomIntPropertyGenerator(1, 10);
    }

    protected PropertyGenerator generatorForLong(Field field){
        if(field != null && field.getName().equals("id")){
            return new IncrementLongPropertyGenerator();
        }

        return new RandomLongPropertyGenerator(1, 10);
    }


    protected PropertyGenerator generatorForLong(){
        return null;
    }

    protected PropertyGenerator generatorForString(Field field){
        if(field != null && field.getName().equals("name")){
            return new PrefixPropertyGenerator(field.getDeclaringClass().getSimpleName(), 1);
        }
        return new PrefixPropertyGenerator(field.getName(), 1);
    }

    protected PropertyGenerator generatorForString(){
        return new PrefixPropertyGenerator("string", 1);
    }


    protected PropertyGenerator generatorForObject(Field field){
        return new ObjectPropertyGenerator(field.getType());
    }


    protected PropertyGenerator generatorForEnum(Field field){
        return new RandomEnumPropertyGenerator(field.getType());
    }


    protected Class getParameterClassFromList(Type type){
        ParameterizedType t = (ParameterizedType) type;
        return (Class) t.getActualTypeArguments()[0];
    }


    protected Class getParameterKeyClassFromMap(Type type){
        ParameterizedType t = (ParameterizedType) type;
        return (Class) t.getActualTypeArguments()[0];
    }

    protected Class getParameterValueClassFromMap(Type type){
        ParameterizedType t = (ParameterizedType) type;
        return (Class) t.getActualTypeArguments()[1];
    }

    protected PropertyGenerator generatorForList(Field field){
        return generatorForList(field.getGenericType());
    }

    protected PropertyGenerator generatorForObject(Class clazz){
        return new ObjectPropertyGenerator(clazz);
    }


    protected PropertyGenerator generatorForEnum(Class clazz){
        return new RandomEnumPropertyGenerator(clazz);
    }


    protected PropertyGenerator generatorForList(Type type){
        return new ListObjectPropertyGenerator<>(getParameterClassFromList(type), 3);
    }


    protected PropertyGenerator generatorForMap(Field field){
        return generatorForMap(field.getGenericType());
    }

    protected PropertyGenerator generatorForMap(Type type){
        Class keyClass = getParameterKeyClassFromMap(type);
        Class valueClass = getParameterValueClassFromMap((type));
        return new MapPropertyGenerator<>(keyClass, valueClass, this, 3);
    }


    protected PropertyGenerator generatorForByteArray(Field field){
        String prefix = field.getName();
        return new ByteArrayPropertyGenerator(new PrefixPropertyGenerator(prefix, 1));
    }


    protected PropertyGenerator generatorForByteArray(Class clazz){
        return new ByteArrayPropertyGenerator(new PrefixPropertyGenerator("value", 1));
    }
}
