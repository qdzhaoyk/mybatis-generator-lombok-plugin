package com.xeelink.mybatis.plugins;

import java.util.Properties;

/**
 * @author: zhaoyk
 * @date: 2019-3-26 16:12
 * @description:
 */
public class LombokPluginExistsBaseEntity extends LombokPlugin {

    @Override
    public void setProperties(Properties properties) {
        super.setProperties(properties);

        //@Data is default annotation
        annotations.add(Annotations.DATA);
        annotations.add(Annotations.EqualsAndHashCode);


        for (String annotationName : properties.stringPropertyNames()) {
            if (annotationName.contains(".")) {
                // Not an annotation name
                continue;
            }
            String value = properties.getProperty(annotationName);
            if (!Boolean.parseBoolean(value)) {
                // The annotation is disabled, skip it
                continue;
            }
            Annotations annotation = Annotations.getValueOf(annotationName);
            if (annotation == null) {
                continue;
            }
            String optionsPrefix = annotationName + ".";
            for (String propertyName : properties.stringPropertyNames()) {
                if (!propertyName.startsWith(optionsPrefix)) {
                    // A property not related to this annotation
                    continue;
                }
                String propertyValue = properties.getProperty(propertyName);
                annotation.appendOptions(propertyName, propertyValue);
                annotations.add(annotation);
                annotations.addAll(Annotations.getDependencies(annotation));
            }
        }
    }
}
