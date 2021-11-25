/** ****************************************************************************
 *  Compilation:  javac Polynomial.java
 *  Execution:    java Polynomial
 *
 *  Polynomials Vector Form 1.
 *
 *  % java PolynomialF1
 *  zero(x)     = 0
 *  p(x)        = 4x^3 + 3x^2 + 2x + 1
 *  q(x)        = 3x^2 + 5
 *  p(x) + q(x) = 4x^3 + 6x^2 + 2x + 6
 *  p(x) * q(x) = 12x^5 + 9x^4 + 26x^3 + 18x^2 + 10x + 5
 *
 ***************************************************************************** */
package polynomialprogram;

/**
 * The {@code PolynomialF1} class represents a polynomial vector form 1.
 * Polynomials are immutable: their values cannot be changed after they are
 * created. It includes methods for addition, subtraction, multiplication,
 * comparison and evaluation.
 *
 * @author ev
 */
public class PolynomialF1 {

    private int degree;     // degree of polynomial (-1 for the zero polynomial)
    private float[] coef;   // coefficients p(x) = sum { coef[i] * x^i }

    /**
     * Initializes a new polynomial x^b
     *
     * @param n the exponent
     * @throws IllegalArgumentException if {@code n} is negative
     */
    public PolynomialF1(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("exponent cannot be negative: " + n);
        }
        degree = n;
        coef = new float[degree + 2];
        coef[0] = degree;
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

    /**
     * Get size of coefficient array
     *
     * @return the length of the array
     */
    public int getSizeCoef() {
        return coef.length;
    }

    @Override
    public String toString() {
        if (degree == -1) {
            return "0";
        } else if (degree == 0) {
            return "" + coef[0];
        } else if (degree == 1) {
            return coef[1] + "x " + coef[0];
        }
        String s = coef[degree] + "x^" + degree;
        for (int i = degree - 1; i >= 0; i--) {
            if (coef[i] == 0) {
                continue;
            } else if (coef[i] > 0) {
                s = s + " + " + (coef[i]);
            } else if (coef[i] < 0) {
                s = s + " - " + (-coef[i]);
            }
            if (i == 1) {
                s = s + "x";
            } else if (i > 1) {
                s = s + "x^" + i;
            }
        }
        return "PolynomialF1{" + s + '}';
    }

    public void show() {
        for (int i = 0; i < coef[0] + 2; i++) {
        }
    }

    public void storeTerm(float a, int degree) {
        int i;
        if (degree >= 0 && degree <= coef[0]) {
            i = (int) coef[0] + 1 - degree;
            if (coef[i] == 0) {
                coef[i] = a;
            } else {
                // throw
            }
        } else {
            // throw
        }
    }

    public void adjust() {
        int j = 1, count = 0;
        while (j < coef[0] + 2 && coef[j] == 0) {
            count += 1;
        }
        for (int k = 0; k < coef[0] + 2; k++) {
            coef[k - count] = coef[k];
        }
        coef[0] -= count;
    }

    public void resize(int degree) {
        int i = degree + 1;
        float aux[] = new float[degree + 2];
        for (int j = (int) coef[0] + 1; j > 0; j--) {
            aux[i] = coef[j];
            i -= 1;
        }
        coef = aux;
    }

    public void insertTerm(float a, int degree) {
        int i;
        if (degree < 0) {
            // throw
        } else {
            if (degree <= coef[0]) {
                i = (int) coef[0] + 1 - degree;
                coef[i] = coef[i] + a;
                this.adjust();
            } else {
                this.resize(degree);
                coef[0] = degree;
                coef[1] = a;
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
        for (int i = 1; i < coef[0] + 2; i++) {
            result += coef[i] * (float) (Math.pow(x, (coef[0] + 1 - i)));
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
    public PolynomialF1 add(PolynomialF1 B) {
        int i = 1, j = 1, exponentA, exponentB;
        int size = max(coef[0], B.getCoef(0));
        PolynomialF1 R = new PolynomialF1(size);

        while (i < coef[0] + 2 && j < B.getCoef(0) + 2) {
            exponentA = (int) coef[0] + 1 - i;
            exponentB = (int) B.getCoef(0) + 1 - j;

            if (exponentA > exponentB) {
                R.insertTerm(B.getCoef(j), exponentA);
            } else if (exponentB > exponentA) {
                R.setCoef(B.getCoef(j), (int) (B.getCoef(0) + 1 - exponentB));
                i += 1;
            } else {
                R.setCoef((coef[j] + B.getCoef(j)), (int) (B.getCoef(0) + 1 - exponentA));
                i += 1;
                j += 1;
            }
        }
        R.adjust();
        return R;
    }

    /**
     * Returns the product of this polynomial and the specified polynomial.
     * Takes time proportional to the product of the degrees.
     *
     * @param B the other polynomial
     * @return the polynomial whose value is {@code (this(x) * that(x))}
     */
    public PolynomialF1 multiply(PolynomialF1 B) {
        PolynomialF1 R = new PolynomialF1((int) (coef[0] + B.getCoef(0)));

        for (int i = 0; i < B.getCoef(0) + 2; i++) {
            for (int j = 0; j < coef[0] + 2; j++) {
                float a = coef[j] * B.getCoef(i);
                int n = ((int) (B.getCoef(0) + 1 - i)) + ((int) (coef[0] + 1 - j));
                R.insertTerm(a, n);
            }
        }
        return R;
    }

    public PolynomialF1 copy() {
        PolynomialF1 duplicate = new PolynomialF1(degree);

        for (int i = 1; i < coef[0] + 2; i++) {
            duplicate.setCoef(coef[i], i);
        }
        return duplicate;
    }

    public PolynomialF1 divide(PolynomialF1 B) {
        int exponent;
        float coefficient;
        PolynomialF1 R = new PolynomialF1((int) (coef[0] - B.getCoef(0)));

        while (coef[0] >= B.getCoef(0)) {
            exponent = (int) (coef[0] - B.getCoef(0));
            coefficient = coef[1] / B.getCoef(1);

            R.insertTerm(coefficient, exponent);

            for (int i = 0; i < B.getCoef(0) + 2; i++) {
                this.insertTerm(-(coefficient * B.getCoef(i)), (exponent + ((int) B.getCoef(0) + 1 - i)));
            }
            this.adjust();
        }
        return R;
    }

}
