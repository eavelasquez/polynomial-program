package com.polynomialprogram.app;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class PolynomialF1Test {


  @Test
  public void testDivide() {
    PolynomialF1 pol1 = new PolynomialF1(3);
    pol1.insertTerm(1, 3);
    pol1.insertTerm(1, 1);
    pol1.insertTerm(5, 0);

    PolynomialF1 pol2 = new PolynomialF1(1);
    pol2.insertTerm(1, 1);
    pol2.insertTerm(1, 0);

    PolynomialF1 result = pol1.divide(pol2);
    assertEquals(result.show(), "1.0x^2-1.0x+2.0");
  }
}
