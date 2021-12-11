package com.polynomialprogram.app;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;
import org.junit.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;


public class PolynomialLinkedListTest {

  private static Stream<Arguments> providerForPolynomialsForDivide() {
    List<Arguments> list = new ArrayList<Arguments>();
    list.add(Arguments.of(new PolynomialLinkedList(1, 0, 1, 5), new PolynomialF1(1, 1, 1),
        "x^2-x+2.0"));

    list.add(Arguments.of(new PolynomialLinkedList(10, 9, -8, 6, 0),
        new PolynomialF1(3, 1, 1, 1, 5), "10.0x-1.0"));

    list.add(Arguments.of(new PolynomialLinkedList(4, -7, -11, 5), new PolynomialF1(1, 4, 5),
        "x^3-3.0x^2+x"));

    list.add(Arguments.of(new PolynomialLinkedList(2, 5, -18, 0), new PolynomialF1(1, 1, 4),
        "2.0x^2-3.0x-6.0"));

    list.add(
        Arguments.of(new PolynomialLinkedList(1, 0, 1, 0), new PolynomialF1(2, 1, 1, -2), "x"));

    Arguments[] pols = new Arguments[list.size()];
    return Stream.of(list.toArray(pols));
  }
  private static Stream<Arguments> providerForPolynomialsForMultiply() {
    List<Arguments> list = new ArrayList<Arguments>();
    list.add(Arguments.of(new PolynomialLinkedList(1, 0, 1, 5), new PolynomialF1(1, 1, 1),
        "x^4+x^3+x^2+x+5.00.0x"));

    list.add(Arguments.of(new PolynomialLinkedList(10, 9, -8, 6, 0),
        new PolynomialF1(3, 1, 1, 1, 5), "10.0x^7+19.0x^6+11.0x^5+57.0x^4+43.0x^3-34.0x^2+30.0x"));

    list.add(Arguments.of(new PolynomialLinkedList(4, -7, -11, 5), new PolynomialF1(1, 4, 5),
        "16.0x^4-8.0x^3-79.0x^2-35.0x+25.0"));

    list.add(Arguments.of(new PolynomialLinkedList(2, 5, -18, 0), new PolynomialF1(1, 1, 4),
        "2.0x^4+13.0x^3+2.0x^2-72.0x"));

    list.add(
        Arguments.of(new PolynomialLinkedList(1, 0, 1, 0), new PolynomialF1(2, 1, 1, -2), "x^5+x^4-2.0x^3+x^2-2.0x0.0x^3"));

    Arguments[] pols = new Arguments[list.size()];
    return Stream.of(list.toArray(pols));
  }


  @Test
  public void testInsert() {
    PolynomialLinkedList pol = new PolynomialLinkedList();
    pol.insertTerm(12, 4);
    assertTrue(pol.getHead().getCoefficient() == 12);

    PolynomialLinkedList pol2 = new PolynomialLinkedList();
    pol2.insertTerm((float) 0.75, 1);
    assertEquals(pol2.getHead().getExponent(), 1);
  }


  @ParameterizedTest
  @MethodSource("providerForPolynomialsForDivide")
  public void testDividePolynomialLinkedListWithPolynomialF1(PolynomialLinkedList pol1,
  PolynomialF1 pol2, String expected) {
    PolynomialF2 result = pol1.dividePolynomialLinkedListWithPolynomialF1(pol2);
    assertEquals(result.show(), expected);
  }

  @ParameterizedTest
  @MethodSource("providerForPolynomialsForMultiply")
  public void testMultiplyPolynomialLinkedListWithPolynomialF1(PolynomialLinkedList pol1,
    PolynomialF1 pol2, String expected) {
    PolynomialF2 result = pol1.multiplyPolynomialLinkedListWithPolynomialF1(pol2);
    assertEquals(expected, result.show());
  }
}
