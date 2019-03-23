package com.company;

import java.util.Properties;

/**
 * ParallelProps - extends Properties class to isolate each thread instance
 *
 */
public class ParallelProps extends Properties {
    public static final long serialVersionUID = 12345678L;
    private final ThreadLocal<Properties> localProperties = new ThreadLocal<Properties>() {
        @Override
        protected Properties initialValue() {
            return new Properties();
        }
    };

    public ParallelProps(Properties properties) {
        super(properties);
    }

    @Override
    public String getProperty(String key){
        String localValue = localProperties.get().getProperty(key);
        return localValue == null ? super.getProperty(key) : localValue;
    }

    @Override
    public Object setProperty(String key,String value){
        return localProperties.get().setProperty(key,value);
    }
}
