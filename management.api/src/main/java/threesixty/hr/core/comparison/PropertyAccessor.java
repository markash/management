package threesixty.hr.core.comparison;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Objects;

public class PropertyAccessor<X> {

    private Class<X> clazz;
    private Method method;
    private String propertyName;

    public <T> PropertyAccessor(
        final Class<X> entityClass) {

        this.clazz = entityClass;
    }

    public PropertyAccessor<X> property(final String propertyName) throws NoSuchMethodException {

        this.propertyName = propertyName;

        String methodName = "get" + propertyName.substring(0, 1).toUpperCase() + propertyName.substring(1);
        
        this.method = this.clazz.getMethod(methodName);
        return this;
    }

    public Object extract(final X instance) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {

        if (Objects.isNull(instance)) {

            return null;
        }

        return this.method.invoke(instance);
    }		

    public String getPropertyName() {

        return this.propertyName;
    }
}