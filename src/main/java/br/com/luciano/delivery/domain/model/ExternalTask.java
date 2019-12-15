package br.com.luciano.delivery.domain.model;

import java.lang.reflect.Field;
import java.util.Collections;
import java.util.Map;

public class ExternalTask<T> {

    private String name;

    private Class<T> clazz;

    private Map<String, Field> fields;

    public ExternalTask(String name, Class<T> clazz, Map<String, Field> fields) {
        this.name = name;
        this.clazz = clazz;
        this.fields = fields;
    }

    public String getName() {
        return name;
    }

    public Class<T> getClazz() {
        return clazz;
    }

    public Map<String, Field> getFields() {
        return Collections.unmodifiableMap(fields);
    }
}
