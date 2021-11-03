package objectFaker;

import objectFaker.propertyGenerator.*;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by Sandrine on 16/09/2015.
 */
public class DefaultValueGeneratorFactory implements IPropertyGeneratorFactory {

    @Override
    public PropertyGenerator generator(Field field) {
        Class clazz = field.getType();
        if (clazz.equals(Long.class) || clazz.equals(long.class)) {
            return generatorForLong(field);
        }
        if (clazz.equals(Integer.class) || clazz.equals(int.class)) {
            return generatorForInt(field);
        }
        if (clazz.equals(String.class)) {
            return generatorForString(field);
        }

        if (clazz.equals(byte[].class)) {
            return this.generatorForByteArray(field);
        }

        if (List.class.isAssignableFrom(clazz)) {
            return generatorForList(field);
        }

        if (Set.class.isAssignableFrom(clazz)) {
            return generatorForSet(field);
        }

        if (Map.class.isAssignableFrom(clazz)) {
            return generatorForMap(field);
        }


        if (clazz.isEnum()) {
            return generatorForEnum(field);
        }

        return generatorForObject(field);
    }


    @Override
    public PropertyGenerator generator(Class clazz, Type... parameterTypes) {
        if (clazz.equals(Long.class) || clazz.equals(long.class)) {
            return generatorForLong();
        }
        if (clazz.equals(Integer.class) || clazz.equals(int.class)) {
            return generatorForInt();
        }
        if (clazz.equals(String.class)) {
            return generatorForString();
        }

        if (List.class.isAssignableFrom(clazz)) {
            return generatorForList(clazz, parameterTypes);
        }

        if (Set.class.isAssignableFrom(clazz)) {
            return generatorForSet(clazz, parameterTypes);
        }

        if (Map.class.isAssignableFrom(clazz)) {
            return generatorForMap(clazz, parameterTypes);
        }

        if (clazz.equals(byte[].class)) {
            return this.generatorForByteArray(clazz);
        }

        if (clazz.isEnum()) {
            return generatorForEnum(clazz);
        }

        return generatorForObject(clazz);
    }


    protected PropertyGenerator generatorForInt(Field field) {
        if (field != null && field.getName().equals("id")) {
            return new IncrementIntPropertyGenerator();
        }

        return new RandomIntPropertyGenerator(1, 10);
    }


    protected PropertyGenerator generatorForInt() {
        return new RandomIntPropertyGenerator(1, 10);
    }

    protected PropertyGenerator generatorForLong(Field field) {
        if (field != null && field.getName().equals("id")) {
            return new IncrementLongPropertyGenerator();
        }

        return new RandomLongPropertyGenerator(1, 10);
    }


    protected PropertyGenerator generatorForLong() {
        return null;
    }

    protected PropertyGenerator generatorForString(Field field) {
        if (field != null && field.getName().equals("name")) {
            return new PrefixPropertyGenerator(field.getDeclaringClass().getSimpleName(), 1);
        }
        return new PrefixPropertyGenerator(field.getName(), 1);
    }

    protected PropertyGenerator generatorForString() {
        return new PrefixPropertyGenerator("string", 1);
    }


    protected PropertyGenerator generatorForObject(Field field) {
        return new ObjectPropertyGenerator(field.getType());
    }


    protected PropertyGenerator generatorForEnum(Field field) {
        return new RandomEnumPropertyGenerator(field.getType());
    }


    protected Class getParameterClassFromList(Type type) {
        ParameterizedType t = (ParameterizedType) type;
        return (Class) t.getActualTypeArguments()[0];
    }

    protected boolean isParameterKeyAParameterizedType(Type type) {
        if (type instanceof ParameterizedType) {
            ParameterizedType t = (ParameterizedType) type;
            return t.getActualTypeArguments()[0] instanceof ParameterizedType;
        } else {
            return false;
        }
    }


    protected Class getParameterKeyClassFromMap(Type type) {
        if (type instanceof ParameterizedType) {
            ParameterizedType t = (ParameterizedType) type;
            return (Class) t.getActualTypeArguments()[0];
        } else {
            return (Class) type;
        }
    }

    protected boolean isParameterValueAParameterizedType(Type type) {
        if (type instanceof ParameterizedType) {
            ParameterizedType t = (ParameterizedType) type;
            return t.getActualTypeArguments()[1] instanceof ParameterizedType;
        } else {
            return false;
        }
    }

    protected Class getParameterValueClassFromMap(Type type) {
        if (type instanceof ParameterizedType) {
            ParameterizedType t = (ParameterizedType) type;
            return (Class) t.getActualTypeArguments()[1];
        } else {
            return (Class) type;
        }
    }

    protected ParameterizedType getParameterKeyParameterizedType(Type type) {
        ParameterizedType t = (ParameterizedType) type;
        return (ParameterizedType) t.getActualTypeArguments()[0];
    }

    protected ParameterizedType getParameterValueParameterizedType(Type type) {
        ParameterizedType t = (ParameterizedType) type;
        return (ParameterizedType) t.getActualTypeArguments()[1];
    }

    protected PropertyGenerator generatorForList(Field field) {
        return generatorForList(field.getGenericType());
    }

    protected PropertyGenerator generatorForSet(Field field) {
        return generatorForSet(field.getGenericType());
    }

    protected PropertyGenerator generatorForObject(Class clazz) {
        return new ObjectPropertyGenerator(clazz);
    }


    protected PropertyGenerator generatorForEnum(Class clazz) {
        return new RandomEnumPropertyGenerator(clazz);
    }


    protected PropertyGenerator generatorForList(Type type, Type... parameterTypes) {
        if (parameterTypes != null && parameterTypes.length == 1) {
            return new ListObjectPropertyGenerator<>((Class) parameterTypes[0], this, 3);
        } else {
            return new ListObjectPropertyGenerator<>(getParameterClassFromList(type), this, 3);
        }
    }

    protected PropertyGenerator generatorForSet(Type type, Type... parameterTypes) {
        if (parameterTypes != null && parameterTypes.length == 1) {
            return new SetObjectPropertyGenerator<>((Class) parameterTypes[0], this, 3);
        } else {
            return new SetObjectPropertyGenerator<>(getParameterClassFromList(type), this, 3);
        }
    }


    protected PropertyGenerator generatorForMap(Field field) {
        return generatorForMap(field.getGenericType());
    }

    protected PropertyGenerator generatorForMap(Type type, Type... parameterTypes) {
        if (parameterTypes != null && parameterTypes.length == 2) {
            return new MapPropertyGenerator<>((Class) parameterTypes[0], (Class) parameterTypes[1], this, 3);
        } else if (isParameterKeyAParameterizedType(type) && isParameterValueAParameterizedType(type)) {
            ParameterizedType parameterizedKeyType = getParameterKeyParameterizedType(type);
            ParameterizedType parameterizedValueType = getParameterValueParameterizedType(type);
            return new MapPropertyGenerator<>(generator((Class) parameterizedKeyType.getRawType(), parameterizedKeyType.getActualTypeArguments()), generator((Class) parameterizedValueType.getRawType(), parameterizedValueType.getActualTypeArguments()), 3);
        } else if (isParameterValueAParameterizedType(type)) {
            Class keyClass = getParameterKeyClassFromMap(type);
            ParameterizedType parameterizedValueType = getParameterValueParameterizedType(type);
            return new MapPropertyGenerator<>(keyClass, generator((Class) parameterizedValueType.getRawType(), parameterizedValueType.getActualTypeArguments()), this, 3);
        } else {
            Class keyClass = getParameterKeyClassFromMap(type);
            Class valueClass = getParameterValueClassFromMap(type);
            return new MapPropertyGenerator<>(keyClass, valueClass, this, 3);
        }
    }


    protected PropertyGenerator generatorForByteArray(Field field) {
        String prefix = field.getName();
        return new ByteArrayPropertyGenerator(new PrefixPropertyGenerator(prefix, 1));
    }


    protected PropertyGenerator generatorForByteArray(Class clazz) {
        return new ByteArrayPropertyGenerator(new PrefixPropertyGenerator("value", 1));
    }
}
