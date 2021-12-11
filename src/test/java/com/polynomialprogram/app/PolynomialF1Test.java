package com.polynomialprogram.app;

import static org.junit.Assert.assertEquals;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;
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

  private static Stream<Arguments> providerForPolynomialsForCompare() {
    List<Arguments> list = new ArrayList<Arguments>();
    list.add(Arguments.of(new PolynomialF1(3, 1, 0, 1, 5), new PolynomialF2(2, 1, 1), false));

    list.add(
        Arguments.of(new PolynomialF1(4, 10, 9, -8, 6), new PolynomialF2(4, 1, 0, 1, 5), false));

    list.add(
        Arguments.of(new PolynomialF1(4, 1, 0, 6, 0, 6), new PolynomialF2(5, 1, 0, 6, 0, 6), true));

    list.add(
        Arguments.of(new PolynomialF1(3, 4, -7, -11, 5), new PolynomialF2(4, 4, -7, -11, 5), true));

    list.add(Arguments.of(new PolynomialF1(2, 2, 5, -18), new PolynomialF2(1, 1, 4), false));

    list.add(
    Arguments.of(new PolynomialF1(3, 1, 1), new PolynomialF2(4, 1, 1, 0, 0), "x^3+2.0x^2+x-2.0"));

    Arguments[] pols = new Arguments[list.size()];
    return Stream.of(list.toArray(pols));
  }

  private static Stream<Arguments> providerForPolynomialsForAdd() {
    List<Arguments> list = new ArrayList<Arguments>();
    list.add(
        Arguments.of(new PolynomialF1(3, 1, 0, 1, 5), new PolynomialF1(1, 1, 1), "x^3+2.0x+6.0"));

    list.add(Arguments.of(new PolynomialF1(4, 10, 9, -8, 6), new PolynomialF1(3, 1, 0, 1, 5),
        "10.0x^4+10.0x^3-8.0x^2+7.0x+5.0"));

    list.add(Arguments.of(new PolynomialF1(4, 1, 0, 6, 0, 6), new PolynomialF1(2, 1, 0, 5),
        "x^4+7.0x^2+11.0"));

    list.add(Arguments.of(new PolynomialF1(3, 4, -7, -11, 5), new PolynomialF1(1, 4, 5),
        "4.0x^3-7.0x^2-7.0x+10.0"));

    list.add(Arguments.of(new PolynomialF1(2, 2, 5, -18), new PolynomialF1(1, 1, 4),
        "2.0x^2+6.0x-14.0"));

    list.add(
        Arguments.of(new PolynomialF1(3, 1, 1), new PolynomialF1(2, 1, 1, -2), "x^3+2.0x^2+x-2.0"));

    Arguments[] pols = new Arguments[list.size()];
    return Stream.of(list.toArray(pols));
  }

  @ParameterizedTest
  @MethodSource("providerForPolynomialsForDivide")
  public void testDivide(PolynomialF1 pol1, PolynomialF1 pol2, String expected) {
    PolynomialF1 result = pol1.divide(pol2);
    assertEquals(result.show(), expected);
  }

  @ParameterizedTest
  @MethodSource("providerForPolynomialsForAdd")
  public void testAdd(PolynomialF1 pol1, PolynomialF1 pol2, String expected) {
    PolynomialF1 result = pol1.add(pol2);
    assertEquals(result.show(), expected);
  }

  @ParameterizedTest
  @MethodSource("providerForPolynomialsForCompare")
  public void testcomparisonPolynomialF1WithPolynomialF2(PolynomialF1 pol1, PolynomialF2 pol2,
      boolean expected) {
    System.out.println(pol1.show());
    System.out.println(pol2.show());
    boolean result = pol1.comparisonPolynomialF1WithPolynomialF2(pol2);
    assertEquals(expected, result);
  }

}
