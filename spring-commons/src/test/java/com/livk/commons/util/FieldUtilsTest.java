package com.livk.commons.util;

import lombok.Data;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * <p>
 * FieldUtilsTest
 * </p>
 *
 * @author livk
 */
class FieldUtilsTest {

    @Test
    void getFieldNameTest() {
        String result = FieldUtils.getFieldName(Student::getNo);
        assertEquals("no", result);
    }
}

@Data
class Student {
    private String no;
}
