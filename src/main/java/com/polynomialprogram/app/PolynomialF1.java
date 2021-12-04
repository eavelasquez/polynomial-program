/**
 * **************************************************************************** Compilation: javac
 * PolynomialF1.java Execution: java PolynomialF1
 *
 * Polynomials Vector Form 1.
 *
 * % java PolynomialF1 p(x) = 4x^3 + 3x^2 + 2x + 1 q(x) = 3x^2 + 5 p(x) + q(x) = 4x^3 + 6x^2 + 2x +
 * 6 p(x) * q(x) = 12x^5 + 9x^4 + 26x^3 + 18x^2 + 10x + 5 p(x) / q(x)
 *
 */
package com.polynomialprogram.app;

import java.util.Random;
import javax.swing.JOptionPane;

import org.beryx.textio.TerminalProperties;
import org.beryx.textio.TextIO;
import org.beryx.textio.TextTerminal;

/**
 * The {@code PolynomialF1} class represents a polynomial vector form 1. Polynomials are immutable:
 * their values cannot be changed after they are created. It includes methods for addition,
 * subtraction, multiplication, comparison and evaluation.
 *
 * @author ev
 */
public class PolynomialF1 {

    private int degree; // degree of polynomial
    private float[] coef; // coefficients p(x) = sum { coef[i] * x^i }

    /**
     * Initializes a new polynomial x^b
     *
     * @param n the degree of the polynomial.
     * @throws IllegalArgumentException if {@code n} is negative
     */
    public PolynomialF1(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("degree cannot be negative: " + n);
        }
        this.degree = n;
        this.coef = new float[this.degree + 2];
        this.coef[0] = this.degree;
    }

    /**
     * Returns the degree of this polynomial.
     *
     * @return the degree of this polynomial.
     */
    public int getDegree() {
        return degree;
    }

    /**
     * Sets the degree of this polynomial.
     *
     * @param n the degree of this polynomial
     */
    public void setDegree(int n) {
        this.degree = n;
    }

    /**
     * Returns the coefficient of the position {@code i}.
     *
     * @param i the position
     * @return the coefficient of the position {@code i}
     */
    public float getCoef(int i) {
        return coef[i];
    }

    /**
     * Set a new coefficient at a specific position
     *
     * @param a new coefficient
     * @param i the position
     */
    public void setCoef(float a, int i) {
        coef[i] = a;
    }

    // @Override
    // public String toString() {
    // if (degree == -1) {
    // return "0";
    // } else if (degree == 0) {
    // return "" + coef[0];
    // } else if (degree == 1) {
    // return coef[1] + "x " + coef[0];
    // }
    // String s = coef[degree] + "x^" + degree;
    // for (int i = degree - 1; i >= 0; i--) {
    // if (coef[i] == 0) {
    // continue;
    // } else if (coef[i] > 0) {
    // s = s + " + " + (coef[i]);
    // } else if (coef[i] < 0) {
    // s = s + " - " + (-coef[i]);
    // }
    // if (i == 1) {
    // s = s + "x";
    // } else if (i > 1) {
    // s = s + "x^" + i;
    // }
    // }
    // return "PolynomialF1{" + s + '}';
    // }
    /**
     * This method is used to generate polynomial vector form 1 random.
     */
    public void generatePolynomialF1Random() {
        Random random = new Random();

        for (int i = 1; i < coef[0] + 2; i++) {
            coef[i] = random.nextInt((int) coef[0]);
            // coef[i] = (int) (random.nextInt((int) coef[0]) * Math.pow(-1, random.nextInt((int)
            // coef[0])));
        }
    }

    /**
     * This method is used to get a string that represents the polynomial.
     *
     * @return the string that represents the polynomial.
     */
    public String show() {
        String string = "";

        for (int i = 1; i < coef[0] + 2; i++) {
            if (coef[i] != 0) {
                string += coef[i] > 0 && i > 1 ? " + " : "";
                string += coef[i] + "x^" + (int) (coef[0] + 1 - i);
            }
        }

        return string;
    }

    /**
     * This method is used to enter the coefficients of the polynomial.
     */
    public void enterTerms(TextIO textIO, TextTerminal<?> terminal, TerminalProperties<?> props) {
        boolean continueAddTerms = false;

        terminal.println("Agregar terminos al polinomio");

        props.setPromptBold(false);
        props.setPromptUnderline(false);
        props.setInputColor("yellow");
        do {
            props.setPromptUnderline(true);
            terminal.println("Nuevo termino");
            props.setPromptUnderline(false);

            float coe = textIO.newFloatInputReader().read("coeficiente");
            int _degree = textIO.newIntInputReader().withMinVal(0).withMaxVal(degree).read("grado");
            this.storeTerm(coe, _degree);
            continueAddTerms = textIO.newBooleanInputReader().read("continuar agregando terminos?");
        } while (continueAddTerms);
    }

    /**
     * This method is used to store terms in the polynomial.
     *
     * @param a the coefficient to be stored.
     * @param exponent the exponent to be stored.
     */
    public void storeTerm(float a, int exponent) {
        if (exponent >= 0 && exponent <= coef[0]) {
            int i = (int) coef[0] + 1 - exponent;

            if (coef[i] == 0) {
                coef[i] = a;
            } else {
                System.out.println("A data already exists in that position.");
            }
        } else {
            System.out.println("The exponent \'" + exponent
                    + "\' does not correspond to the degree of the polynomial \'" + degree + "\'");
        }
    }

    /**
     * This method is used to adjust the polynomial.
     */
    public void adjust() {
        int i = 1, count = 0;

        while (i < coef[0] + 2 && coef[i] == 0) {
            count += 1;
            i += 1;
        }

        for (int j = i; j < coef[0] + 2; j++) {
            coef[j - count] = coef[j];
        }

        coef[0] -= count;
    }

    /**
     * This method is used to changes the size of the {@code coef} array.
     *
     * @param exponent the exponent.
     */
    public void resize(int exponent) {
        int i = exponent + 1;
        this.degree = exponent + 2;
        float aux[] = new float[this.degree];

        for (int j = (int) coef[0] + 1; j > 0; j--) {
            aux[i] = coef[j];
            i -= 1;
        }

        coef = aux;
    }

    /**
     * This method is used to insert terms in the polynomial. A different from the method for store
     * terms, add the coefficients of the terms with equal degree.
     *
     * @param a the coefficient to be inserted.
     * @param exponent the exponent to be inserted.
     */
    public void insertTerm(float a, int exponent) {
        if (exponent < 0) {
            System.out.println("The exponent isn't valid.");
        } else if (exponent <= coef[0]) {
            int i = (int) coef[0] + 1 - exponent;
            coef[i] = coef[i] + a;
            this.adjust();
        } else {
            this.resize(exponent);
            coef[0] = exponent;
            coef[1] = a;
        }
    }

    /**
     * Returns the result of evaluating this polynomial at the point x.
     *
     * @param x the point at which to evaluate the polynomial
     * @return the integer whose value is {@code (this(x))}
     */
    public float evaluate(float x) {
        float result = 0;

        for (int i = 1; i < coef[0] + 2; i++) {
            result += coef[i] * (float) (Math.pow(x, (coef[0] + 1 - i)));
        }

        return result;
    }

    /**
     * This method is used to return maximum of two floats.
     *
     * @param m the first number.
     * @param n the second number.
     * @return maximum between {@code m} and {@code n}.
     */
    static int max(float m, float n) {
        return (int) ((m > n) ? m : n);
    }

    /**
     * This method is used to add two polynomials. It means that it adds the coefficients of the
     * terms with the same degree.
     *
     * @param B the other polynomial.
     * @return the polynomial whose value is {@code (this(x) + that(x))}.
     */
    public PolynomialF1 add(PolynomialF1 B) {
        int i = 1, j = 1, exponentA, exponentB;
        int size = max(coef[0], B.getCoef(0));
        PolynomialF1 R = new PolynomialF1(size);

        while (i < coef[0] + 2 && j < B.getCoef(0) + 2) {
            exponentA = (int) coef[0] + 1 - i;
            exponentB = (int) B.getCoef(0) + 1 - j;

            if (exponentA > exponentB) {
                R.insertTerm(coef[i], exponentA);
                i += 1;
            } else if (exponentB > exponentA) {
                R.setCoef(B.getCoef(j), (int) (B.getCoef(0) + 1 - exponentB));
                j += 1;
            } else {
                R.setCoef((coef[i] + B.getCoef(j)), (int) (B.getCoef(0) + 1 - exponentA));
                i += 1;
                j += 1;
            }
        }
        R.adjust();

        return R;
    }

    /**
     * This method is used to multiply two polynomial. Takes time proportional to the product of the
     * degrees.
     *
     * @param B the other polynomial.
     * @return the polynomial whose value is {@code (this(x) * that(x))}.
     */
    public PolynomialF1 multiply(PolynomialF1 B) {
        PolynomialF1 R = new PolynomialF1((int) (coef[0] + B.getCoef(0)));

        for (int i = 1; i < B.getCoef(0) + 2; i++) {
            for (int j = 1; j < coef[0] + 2; j++) {
                int exponentR = (int) ((B.getCoef(0) + 1 - i) + (coef[0] + 1 - j));
                float coefficientR = coef[j] * B.getCoef(i);
                R.insertTerm(coefficientR, exponentR);
            }
        }

        return R;
    }

    /**
     * This method is used to copy one polynomial.
     *
     * @return the duplicate of the polynomial.
     */
    public PolynomialF1 copy() {
        PolynomialF1 duplicate = new PolynomialF1((int) coef[0]);

        for (int i = 1; i < coef[0] + 2; i++) {
            duplicate.setCoef(coef[i], i);
        }

        return duplicate;
    }

    /**
     * This method is used to divide two polynomial.
     *
     * @param B the other polynomial.
     * @return the polynomial whose value is {@code (this(x) / that(x))}.
     */
    public PolynomialF1 divide(PolynomialF1 B) {
        int exponentR, exponentA, exponentB;
        float coefficientR, coefficientA;
        PolynomialF1 R = new PolynomialF1((int) (coef[0] - B.getCoef(0)));

        while (coef[0] >= B.getCoef(0)) {
            exponentR = (int) (coef[0] - B.getCoef(0));
            coefficientR = coef[1] / B.getCoef(1);

            R.insertTerm(coefficientR, exponentR);

            for (int i = 1; i < B.getCoef(0) + 2; i++) {
                exponentB = (int) B.getCoef(0) + 1 - i;
                exponentA = exponentR + exponentB;
                coefficientA = coefficientR * B.getCoef(i);

                this.insertTerm(-coefficientA, exponentA);
            }
            this.adjust();
        }

        return R;
    }

    /**
     * This method is used to add a polynomial vector form 1 with polynomial vector form 2 and
     * return a linked list polynomial.
     *
     * @param B the other polynomial.
     * @return the polynomial whose value is {@code (this(x) + that(x))}.
     */
    public PolynomialLinkedList addPolynomialF1WithPolynomialF2(PolynomialF2 B) {
        int i = 1, j = 1, exponentA, exponentB;
        PolynomialLinkedList R = new PolynomialLinkedList();

        while (i < coef[0] + 2 && j < B.getData(0) * 2 + 1) {
            exponentA = (int) coef[0] + 1 - i;
            exponentB = (int) B.getData(1);

            if (exponentA > exponentB) {
                R.insertTerm(coef[i], exponentA);
                i += 1;
            } else if (exponentB > exponentA) {
                R.insertTerm(B.getData(j + 1), (int) B.getData(j));
                j += 1;
            } else {
                R.insertTerm(coef[i] + B.getData(j + 1), (int) B.getData(j));
                i += 1;
                j += 1;
            }
        }

        while (i < coef[0] + 2) {
            R.insertTerm(coef[i], (int) coef[0] + 1 - i);
            i += 1;
        }

        return R;
    }

    /**
     * This method is used to compares this polynomial to the specified polynomial of the class
     * PolynomialF2.
     *
     * @param B the other polynomial
     * @return {@code true} if this polynomial equals {@code B}; {@code false} otherwise
     */
    public boolean comparisonPolynomialF1WithPolynomialF2(PolynomialF2 B) {
        int i = 1, j = 1, exponentA, exponentB;

        while (i < coef[0] + 2 && j < B.getData(0) * 2 + 1) {
            exponentA = (int) coef[0] + 1 - i;
            exponentB = (int) B.getData(j);

            if (exponentA != exponentB) {
                return false;
            }
            if (coef[i] != B.getData(j + 1)) {
                return false;
            }
            i += 1;
            j += 2;
        }

        return true;
    }
}
