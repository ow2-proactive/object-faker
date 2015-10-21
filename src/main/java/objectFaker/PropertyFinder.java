package objectFaker;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.reflect.FieldUtils;

public class PropertyFinder {

	public List<Field> findProperties(Class clazz){
        ArrayList<Field> properties = new ArrayList<>();
        
        Field[] fields = FieldUtils.getAllFields(clazz);
        for(Field currentField: fields){
            if(hasGetter(clazz, currentField) && hasSetter(clazz, currentField)){
                properties.add(currentField);
            }
        }

        return properties;
    }


    protected boolean hasGetter(Class clazz, Field field){
        try {
            clazz.getMethod(getterField(field.getName()));
            return true;
        } catch (NoSuchMethodException e) {
            return false;
        }
    }


    protected String getterField(String fieldName){
        return "get" + fieldName.substring(0,1).toUpperCase() + fieldName.substring(1);
    }

    protected boolean hasSetter(Class clazz, Field field){
        try {
            clazz.getMethod(setterField(field.getName()), field.getType());
            return true;
        } catch (NoSuchMethodException e) {
            return false;
        }
    }

    protected String setterField(String fieldName){
        return "set" + fieldName.substring(0,1).toUpperCase() + fieldName.substring(1);
    }
}
