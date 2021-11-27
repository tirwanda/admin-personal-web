package com.admin.dashboard.be.interview;

import org.junit.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Interview {

    Calculator underTestCalc = new Calculator();

    @Test
    public void testCalculator() {
        int result = underTestCalc.add(1, 2);
        int expected = 3;
        assertThat(result).isEqualTo(expected);
    }

    class Calculator {
        int add(int a, int b) {
            return a + b;
        }
    }
}
