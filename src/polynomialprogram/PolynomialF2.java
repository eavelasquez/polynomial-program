/** ****************************************************************************
 *  Compilation:  javac Polynomial.java
 *  Execution:    java Polynomial
 *
 *  Polynomials Vector Form 2.
 *
 *  % java PolynomialF2
 *  zero(x)     = 0
 *  p(x)        = 4x^3 + 3x^2 + 2x + 1
 *  q(x)        = 3x^2 + 5
 *  p(x) + q(x) = 4x^3 + 6x^2 + 2x + 6
 *  p(x) * q(x) = 12x^5 + 9x^4 + 26x^3 + 18x^2 + 10x + 5
 *
 ***************************************************************************** */
package polynomialprogram;

/**
 * The {@code PolynomialF1} class represents a polynomial vector form 2.
 * Polynomials are immutable: their values cannot be changed after they are
 * created. It includes methods for addition, subtraction, multiplication,
 * comparison and evaluation.
 *
 * @author ev
 */
public class PolynomialF2 {

    private int size;           // size of polynomial
    private float[] polynomial; // polynomial p(x) = sum { coef[i + 1] * x^coef[i] }

    /**
     * Initializes a new polynomial x^b
     *
     * @param n number of terms of the polynomial
     * @throws IllegalArgumentException if {@code n} is negative
     */
    public PolynomialF2(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("number of terms cannot be negative: " + n);
        }
        size = n * 2 + 1;
        polynomial = new float[size];
        polynomial[0] = n;
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
        polynomial[i] = data;
    }

    public String show() {
        String string = "";
        for (int i = 0; i < polynomial[0] * 2 + 1; i += 2) {
            string += polynomial[i + 1] > 0 && i > 1 ? " + " : "";
            string += polynomial[i + 1] + "X^" + (int) polynomial[i];
        }
        return string;
    }

    public boolean storeTerm(float coefficient, int exponent) {
        int i = 1;
        boolean isExist = true;

        while (i < polynomial[0] * 2 + 1 && polynomial[i] > exponent) {
            i += 2;
        }

        if (i < polynomial[0] * 2 + 1 && polynomial[i] == exponent && polynomial[i + 1] != 0) {
            // throw
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

    public void resize() {
        size += 2;
        float aux[] = new float[size];
        for (int i = 0; i < (int) polynomial[0] * 2 + 1; i++) {
            aux[i] = polynomial[i];
        }
        polynomial = aux;
    }

    public void insertTerm(float coefficient, int exponent) {
        if (coefficient != 0) {
            int i = 1, j;

            while (i < polynomial[0] * 2 + 1 && polynomial[i] > exponent && polynomial[i + 1] != 0) {
                i += 2;
            }

            if (i < polynomial[0] * 2 + 1 && polynomial[i] == exponent && polynomial[i + 1] != 0) {
                if ((polynomial[i + 1] + coefficient) != 0) {
                    polynomial[i + 1] = polynomial[i + 1] + coefficient;
                } else {
                    for (j = i; j < (polynomial[0] * 2 - 1); j++) {
                        polynomial[j] = polynomial[j + 2];
                        polynomial[j + 1] = polynomial[j + 3];
                    }
                    polynomial[0] -= 1;
                }
            } else {
                if (polynomial[0] * 2 + 1 == size) {
                    resize();
                }

                for (j = (int) polynomial[0] * 2 + 1; j >= i; j--) {
                    polynomial[j + 2] = polynomial[j];
                }
                polynomial[0] += 1;
                polynomial[i] = exponent;
                polynomial[i + 1] = coefficient;
            }
        }
    }

    /**
     * Returns the result of evaluating this polynomial at the point x.
     *
     * @param x the point at which to evaluate the polynomial
     * @return the integer whose value is {@code (this(x))}
     */
    public float evaluate(int x) {
        float result = 0;
        for (int i = 1; i < polynomial[0] * 2 + 1; i += 2) {
            result += polynomial[i + 1] * (float) (Math.pow(x, polynomial[i]));
        }
        return result;
    }

    static int max(float m, float n) {
        return (int) ((m > n) ? m : n);
    }

    /**
     * Returns the sum of this polynomial and the specified polynomial.
     *
     * @param B the other polynomial
     * @return the polynomial whose value is {@code (this(x) + that(x))}
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
     * Returns the product of this polynomial and the specified polynomial.
     * Takes time proportional to the product of the degrees.
     *
     * @param B the other polynomial
     * @return the polynomial whose value is {@code (this(x) * that(x))}
     */
    public PolynomialF2 multiply(PolynomialF2 B) {
        PolynomialF2 R = new PolynomialF2(0);

        for (int i = 1; i < B.getData(0) * 2 + 1; i += 2) {
            for (int j = 1; j < (int) polynomial[0] * 2 + 1; j += 2) {
                R.insertTerm(polynomial[j + 1] * B.getData(i + 1), (int) (polynomial[j] + B.getData(i)));
            }
        }
        return R;
    }

    public PolynomialF2 copy() {
        PolynomialF2 duplicate = new PolynomialF2((int) polynomial[0]);

        for (int i = 1; i < polynomial[0] * 2 + 1; i++) {
            duplicate.setData(polynomial[i], i);
        }
        return duplicate;
    }

    public PolynomialF2 divide(PolynomialF2 B) {
        int exponent;
        float coefficient;
        PolynomialF2 R = new PolynomialF2(0);

        while (polynomial[1] >= B.getData(1)) {
            exponent = (int) (polynomial[1] - B.getData(1));
            coefficient = polynomial[2] / B.getData(2);

            R.insertTerm(coefficient, exponent);

            for (int i = 0; i < B.getData(0) * 2 + 1; i += 2) {
                this.insertTerm(-(coefficient * B.getData(i + 1)), (exponent + (int) B.getData(i)));
            }
        }
        return R;
    }

}
