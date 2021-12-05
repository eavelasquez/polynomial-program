package com.polynomialprogram.app;

import static org.junit.Assert.assertEquals;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;
import org.junit.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;


public class PolynomialF1Test {
  private static Stream<Arguments> providerForPolynomialsForDivide() {
    List<Arguments> list = new ArrayList<Arguments>();
    list.add(Arguments.of(new PolynomialF1(3, 1, 0, 1, 5), new PolynomialF1(1, 1, 1), "x^2-x+2.0"));

    list.add(Arguments.of(new PolynomialF1(4, 10, 9, -8, 6), new PolynomialF1(3, 1, 0, 1, 5),
        "10.0x+9.0"));

    list.add(
        Arguments.of(new PolynomialF1(4, 1, 0, 6, 0, 6), new PolynomialF1(2, 1, 0, 5), "x^2+1.0"));

    list.add(Arguments.of(new PolynomialF1(3, 4, -7, -11, 5), new PolynomialF1(1, 4, 5),
        "x^2-3.0x+1.0"));

    list.add(Arguments.of(new PolynomialF1(2, 2, 5, -18), new PolynomialF1(1, 1, 4), "2.0x-3.0"));

    list.add(Arguments.of(new PolynomialF1(3, 1, 1), new PolynomialF1(2, 1, 1, -2), "x"));

    Arguments[] pols = new Arguments[list.size()];
    return Stream.of(list.toArray(pols));
  }


  @ParameterizedTest
  @MethodSource("providerForPolynomialsForDivide")
  public void testDivide(PolynomialF1 pol1, PolynomialF1 pol2, String expected) {
    PolynomialF1 result = pol1.divide(pol2);
    assertEquals(result.show(), expected);
  }

}
