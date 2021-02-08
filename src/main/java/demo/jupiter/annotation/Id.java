package demo.jupiter.annotation;

import demo.jupiter.converter.TestCaseIdConverter;
import org.junit.jupiter.params.converter.ConvertWith;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.ANNOTATION_TYPE, ElementType.PARAMETER})
@ConvertWith(TestCaseIdConverter.class)
public @interface Id {
}
