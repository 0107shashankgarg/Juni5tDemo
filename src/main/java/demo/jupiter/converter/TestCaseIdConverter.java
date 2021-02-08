package demo.jupiter.converter;

import demo.jupiter.annotation.TestCaseId;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.params.converter.ArgumentConversionException;
import org.junit.jupiter.params.converter.ArgumentConverter;

import java.lang.annotation.Annotation;
import java.lang.reflect.Executable;
import java.lang.reflect.Field;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class TestCaseIdConverter implements ArgumentConverter {

    private static final String DECLARED_ANNOTATIONS = "declaredAnnotations";

    @Override
    public Integer convert(Object o, ParameterContext parameterContext) throws ArgumentConversionException {
        if (o instanceof Integer) {
            int id = (Integer) o;

            TestCaseId newAnnotation = new TestCaseId() {
                @Override
                public Class<? extends Annotation> annotationType() {
                    return TestCaseId.class;
                }
                @Override
                public int value() {
                    return id;
                }
            };

            alterAnnotation(parameterContext, TestCaseId.class, newAnnotation);
            return (Integer) o;
        } else
            throw new ArgumentConversionException("Parameter with type <" + o.getClass() + "> not supported here!");

    }

    private void alterAnnotation(ParameterContext parameterContext, Class<? extends Annotation> annotationToAlter, Annotation annotationValue) {
        Executable ex = parameterContext.getDeclaringExecutable();
        // prevent declaredAnnotations haven't initialized
        ex.getAnnotation(annotationToAlter);

        try {
            Class<?> superclass = ex.getClass().getSuperclass();
            Field annotations = superclass.getDeclaredField(DECLARED_ANNOTATIONS);
            annotations.setAccessible(true);

            @SuppressWarnings("unchecked")
            Map<Class<? extends Annotation>, Annotation> map = (Map<Class<? extends Annotation>, Annotation>) annotations.get(ex);
            if (map.getClass() == Collections.EMPTY_MAP.getClass()) {
                map = new HashMap<>();
                try {
                    annotations.set(ex, map);
                } catch (IllegalAccessException e) {
                    throw new IllegalStateException(e);
                }
            }
            map.put(annotationToAlter, annotationValue);
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

}
