package com.lky.toucheffectsmodule.annotation;

import com.lky.toucheffectsmodule.types.TouchEffectsWholeType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.SOURCE)
@Target({ElementType.ANNOTATION_TYPE})
public @interface ConstraintType {
    TouchEffectsWholeType[] value() default {};
}
