package com.livk.commons.function;

import org.junit.jupiter.api.Test;

import java.util.Objects;

/**
 * <p>
 * BranchHandleTest
 * </p>
 *
 * @author livk
 */
class BranchHandleTest {

    @Test
    void trueOrFalseHandle() {
        BranchHandle.isTrueOrFalse(new Object(), Objects::nonNull).trueOrFalseHandle(() -> System.out.println(true), () -> System.out.println(false));
        BranchHandle.isTrueOrFalse(new Object(), Objects::isNull).trueOrFalseHandle(() -> System.out.println(true), () -> System.out.println(false));
    }
}
