/**
 * **************************************************************************** Compilation: javac
 * PolynomialF2.java Execution: java PolynomialF2
 *
 * Polynomials Vector Form 2.
 *
 * % java PolynomialF2 p(x) = 4x^3 + 3x^2 + 2x + 1 q(x) = 3x^2 + 5 p(x) + q(x) = 4x^3 + 6x^2 + 2x +
 * 6 p(x) * q(x) = 12x^5 + 9x^4 + 26x^3 + 18x^2 + 10x + 5 p(x) / q(x)
 *
 */
package com.polynomialprogram.app;

import javax.swing.JOptionPane;

/**
 * The {@code PolynomialF2} class represents a polynomial vector form 2. Polynomials are immutable:
 * their values cannot be changed after they are created. It includes methods for addition,
 * subtraction, multiplication, comparison and evaluation.
 *
 * @author ev
 */
public class PolynomialF2 {

    private int size; // size of polynomial
    private float[] polynomial; // polynomial p(x) = sum { coef[i + 1] * x^coef[i] }

    /**
     * Initializes a new polynomial x^b
     *
     * @param n number of terms of the polynomial.
     * @throws IllegalArgumentException if {@code n} is negative.
     */
    public PolynomialF2(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("number of terms cannot be negative: " + n);
        }
        this.size = n * 2 + 1;
        this.polynomial = new float[this.size];
        this.polynomial[0] = n;
    }
    public PolynomialF2(int n, float ... terms) {
        this(n);
        for (int i = 0; i < terms.length; i++) {
            if (terms[i] == 0){
                continue;
            }
            int exponent = (terms.length - i)-1;
            float coef = terms[i];
            this.storeTerm(coef, exponent);
        }

    }

    public PolynomialF2(PolynomialLinkedList from) {
        int degree = from.getHead().getExponent();

        this.setSize(degree);
        this.polynomial = new float[this.size];
        this.polynomial[0] = degree;


        Node start = from.getHead();
        if (start != null) {
            while (start != null) {
                this.insertTerm(start.getCoefficient(), start.getExponent());
            }
        }
    }

    /**
     * Returns the size of this polynomial.
     *
     * @return the size of this polynomial.
     */
    public int getSize() {
        return size;
    }

    /**
     * Sets the size of this polynomial.
     *
     * @param n the size of this polynomial
     */
    public void setSize(int n) {
        this.size = n;
    }

    /**
     * Returns the data of the polynomial position {@code i}.
     *
     * @param i the position
     * @return the data of the polynomial position {@code i}
     */
    public float getData(int i) {
        return polynomial[i];
    }

    /**
     * Set a new data at a specific position
     *
     * @param data new data
     * @param i the position
     */
    public void setData(float data, int i) {
        this.polynomial[i] = data;
    }

    /**
     * This method is used to generate polynomial vector form 2 random.
     */
    public void generatePolynomialF1Random() {}

    /**
     * This method is used to get a string that represents the polynomial.
     *
     * @return the string that represents the polynomial.
     */
    public String show() {
        String string = "";

        for (int i = 1; i < polynomial[0] * 2 + 1; i += 2) {
            int exponent = (int) polynomial[i];
            String literal = (exponent > 0 ? "x" : "");
            String coeff = ((polynomial[i + 1] == 1 || polynomial[i + 1] == -1) && !literal.isEmpty()) ? "" : ("" + polynomial[i + 1]);
            coeff += ((coeff.isEmpty() && polynomial[i + 1] == -1) ? "-": "");

            string += polynomial[i + 1] > 0 && i > 1 ? "+" : "";
            string += coeff + literal + (exponent > 1 ? ("^" + exponent) : "");
        }

        return string;
    }

    /**
     * This method is used to store terms in the polynomial.
     *
     * @param coefficient the coefficient to be stored.
     * @param exponent the exponent to be stored.
     * @return a boolean that confirms of the term does not exists.
     */
    public boolean storeTerm(float coefficient, int exponent) {
        int i = 1;
        boolean isExist = true;

        while (i < polynomial[0] * 2 + 1 && polynomial[i] > exponent) {
            i += 2;
        }

        if (i < polynomial[0] * 2 + 1 && polynomial[i] == exponent && polynomial[i + 1] != 0) {
            System.out.println("A term with exponent already exists.");
            isExist = false;
        } else {
            for (int j = (int) polynomial[0] * 2 - 1; j > i; j--) {
                polynomial[j + 1] = polynomial[j - 1];
            }
            polynomial[i] = exponent;
            polynomial[i + 1] = coefficient;
        }

        return isExist;
    }

    /**
     * This method is used to enter the coefficients and exponents of the polynomial.
     *
     * @param n number of terms of the polynomial.
     */
    public void enterTerms(int n) {
        float a;
        int exponent;
        boolean flag;
        for (int i = 1; i <= n; i++) {
            a = Float.parseFloat(JOptionPane.showInputDialog("Enter the coefficient:"));
            exponent = Integer.parseInt(JOptionPane.showInputDialog("Enter the exponent:"));

            flag = this.storeTerm(a, exponent);
            i = flag == false ? i - 1 : i;
        }
    }

    /**
     * This method is used to increases or decreases the size of the {@code polynomial} array by
     * {@code n} positions.
     *
     * @param n number of positions.
     */
    public void resize(int n) {
        this.size += n;
        float aux[] = new float[this.size];
        int lenght = n < 0 && this.size >= (polynomial[0] * 2 + 1) ? this.size
                : (int) polynomial[0] * 2 + 1;

        // Manual array copy.
        for (int i = 0; i < lenght; i++) {
            aux[i] = polynomial[i];
        }
        // Alternative array copy.
        // System.arraycopy(polynomial, 0, aux, 0, (int) polynomial[0] * 2 + 1);
        polynomial = aux;
    }

    /**
     * This method is used to insert terms in the polynomial. A different from the method for store
     * terms, add the coefficients of the terms with equal degree.
     *
     * @param coefficient the coefficient to be inserted.
     * @param exponent the exponent to be inserted.
     */
    public void insertTerm(float coefficient, int exponent) {
        if (coefficient != 0) {
            int i = 1;

            while (i < polynomial[0] * 2 + 1 && polynomial[i] > exponent
                    && polynomial[i + 1] != 0) {
                i += 2;
            }

            if (i < polynomial[0] * 2 + 1 && polynomial[i] == exponent && polynomial[i + 1] != 0) {
                float sum = polynomial[i + 1] + coefficient;

                if (sum != 0) {
                    polynomial[i + 1] = sum;
                } else {
                    for (int j = i; j < (polynomial[0] * 2 - 1); j += 2) {
                        polynomial[j] = polynomial[j + 2];
                        polynomial[j + 1] = polynomial[j + 3];
                    }
                    polynomial[0] -= 1;
                }
            } else {
                if (polynomial[0] * 2 + 1 == this.size) {
                    this.resize(2);
                }
                for (int k = (int) (polynomial[0] * 2 - 1); k >= i; k--) {
                    polynomial[k + 2] = polynomial[k];
                }
                polynomial[0] += 1;
                polynomial[i] = exponent;
                polynomial[i + 1] = coefficient;
            }
        }
    }

    /**
     * This method is used to remove terms in the polynomial.
     *
     * @param exponent the exponent to be removed.
     * @return confirmation if term was found and removed.
     */
    public boolean removeTerm(int exponent) {
        int i = 1, j;
        boolean isFound = false;

        while (i < polynomial[0] * 2 + 1 && polynomial[i] > exponent && polynomial[i + 1] != 0) {
            i += 2;
        }

        if (i < polynomial[0] * 2 + 1 && polynomial[i] == exponent && polynomial[i + 1] != 0) {
            isFound = true;

            // TODO: test remove element from array and reposition terms.
            for (j = i; j < (polynomial[0] * 2 - 1); j += 2) {
                polynomial[j] = polynomial[j + 2];
                polynomial[j + 1] = polynomial[j + 3];
            }
            polynomial[0] -= 1;
        }

        return isFound;
    }

    /**
     * This method is used to evaluate this polynomial at the point x.
     *
     * @param x the point at which to evaluate the polynomial.
     * @return the result of evaluating the integer whose value is {@code (this(x))}.
     */
    public float evaluate(float x) {
        float result = 0;

        for (int i = 1; i < polynomial[0] * 2 + 1; i += 2) {
            result += polynomial[i + 1] * (float) (Math.pow(x, polynomial[i]));
        }

        return result;
    }

    /**
     * This method is used to add two polynomials. It means that it adds the coefficients of the
     * terms with the same degree.
     *
     * @param B the other polynomial.
     * @return the polynomial whose value is {@code (this(x) + that(x))}.
     */
    public PolynomialF2 add(PolynomialF2 B) {
        int i = 1, j = 1, exponentA, exponentB;
        float coefficientA, coefficientB;
        PolynomialF2 R = new PolynomialF2(0);

        while (i < polynomial[0] * 2 + 1 && j < B.getData(0) * 2 + 1) {
            exponentA = (int) polynomial[i];
            exponentB = (int) B.getData(j);
            coefficientA = polynomial[i + 1];
            coefficientB = B.getData(j + 1);

            if (exponentA == exponentB) {
                R.insertTerm(coefficientA + coefficientB, exponentA);
                i += 2;
                j += 2;
            } else if (exponentA > exponentB) {
                R.insertTerm(coefficientA, exponentA);
                i += 2;
            } else {
                R.insertTerm(coefficientB, exponentB);
                j += 2;
            }
        }

        while (i < polynomial[0] * 2 + 1) {
            exponentA = (int) polynomial[i];
            coefficientA = polynomial[i + 1];

            R.insertTerm(coefficientA, exponentA);
            i += 2;
        }
        while (j < B.getData(0) * 2 + 1) {
            exponentB = (int) B.getData(j);
            coefficientB = B.getData(j + 1);

            R.insertTerm(coefficientB, exponentB);
            j += 2;
        }

        return R;
    }

    /**
     * This method is used to multiply two polynomial. Takes time proportional to the product of the
     * degrees.
     *
     * @param B the other polynomial.
     * @return the polynomial whose value is {@code (this(x) * that(x))}.
     */
    public PolynomialF2 multiply(PolynomialF2 B) {
        PolynomialF2 R = new PolynomialF2(0);

        for (int i = 1; i < B.getData(0) * 2 + 1; i += 2) {
            for (int j = 1; j < (int) (polynomial[0] * 2 + 1); j += 2) {
                R.insertTerm(polynomial[j + 1] * B.getData(i + 1),
                        (int) (polynomial[j] + B.getData(i)));
            }
        }

        return R;
    }

    /**
     * This method is used to copy one polynomial.
     *
     * @return the duplicate of the polynomial.
     */
    public PolynomialF2 copy() {
        PolynomialF2 duplicate = new PolynomialF2((int) polynomial[0]);

        for (int i = 1; i < polynomial[0] * 2 + 1; i++) {
            duplicate.setData(polynomial[i], i);
        }

        return duplicate;
    }

    /**
     * This method is used to divide two polynomial.
     *
     * @param B the other polynomial.
     * @return the polynomial whose value is {@code (this(x) / that(x))}.
     */
    public PolynomialF2 divide(PolynomialF2 B) {
        int exponentR, exponentA;
        float coefficientR, coefficientA;
        PolynomialF2 R = new PolynomialF2(0);

        while (polynomial[1] >= B.getData(1)) {
            exponentR = (int) (polynomial[1] - B.getData(1));
            coefficientR = polynomial[2] / B.getData(2);

            R.insertTerm(coefficientR, exponentR);

            for (int i = 1; i < B.getData(0) * 2 + 1; i += 2) {
                exponentA = exponentR + (int) B.getData(i);
                coefficientA = coefficientR * B.getData(i + 1);

                this.insertTerm(-coefficientA, exponentA);
            }
        }

        return R;
    }

    /**
     * This method is used to divide a polynomial vector form 2 with polynomial linked list and
     * returns a polynomial vector form 1.
     *
     * @param B the other polynomial.
     * @return the polynomial whose value is {@code (this(x) / that(x))}
     */
    public PolynomialF1 dividePolynomialF2WithPolynomialLinkedList(PolynomialLinkedList B) {
        Node startB;
        int exponentR, exponentA;
        float coefficientR, coefficientA;
        PolynomialF1 R = new PolynomialF1((int) polynomial[1] - B.getHead().getExponent());

        while (polynomial[1] >= B.getHead().getExponent()) {
            exponentR = (int) polynomial[1] - B.getHead().getExponent();
            coefficientR = polynomial[2] / B.getHead().getCoefficient();

            R.insertTerm(coefficientR, exponentR);

            startB = B.getHead();
            while (startB != null) {
                exponentA = exponentR + startB.getExponent();
                coefficientA = coefficientR * startB.getCoefficient();

                this.insertTerm(-coefficientA, exponentA);

                startB = startB.getNext();
            }
        }

        return R;
    }
}
