package com.ebook.util.config;

/**
 * Created by donh on 2018/6/12.
 */

import org.codehaus.jackson.annotate.JacksonAnnotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@JacksonAnnotation
public @interface JsonPropertiesFilter {
    String[] filterFields();
}
