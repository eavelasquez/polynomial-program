/**
 * **************************************************************************** Compilation: javac
 * PolynomialLinkedList.java Execution: java PolynomialLinkedList
 *
 * Polynomials represented by a Linked List.
 *
 * % java PolynomialLinkedList p(x) = 4x^3 + 3x^2 + 2x + 1 q(x) = 3x^2 + 5 p(x) + q(x) = 4x^3 + 6x^2
 * + 2x + 6 p(x) * q(x) = 12x^5 + 9x^4 + 26x^3 + 18x^2 + 10x + 5
 *
 */
package com.polynomialprogram.app;

import javax.swing.JOptionPane;

/**
 * The {@code PolynomialLinkedList} class represents a polynomial represented by a linked list.
 * Polynomials are immutable: their values cannot be changed after they are created. It includes
 * methods for addition, subtraction, multiplication, comparison and evaluation.
 *
 * @author ev
 */
public class PolynomialLinkedList {

    private Node head; // head of list

    /**
     * Initializes a new linked list.
     */
    public PolynomialLinkedList() {
        this.head = null;
    }

    public PolynomialLinkedList(PolynomialF1 from) {
        this.head = null;
        for (int i = 1; i < from.getCoef(0) + 2; i++) {
            if (from.getCoef(i) != 0) {
                this.storeTerm(from.getCoef(i), (int) (from.getCoef(0) + 1 - i));
            }
        }
    }

    /**
     * Returns the node head of this linked list.
     *
     * @return the node head of this linked list.
     */
    public Node getHead() {
        return head;
    }

    /**
     * Sets the node head of this linked list.
     *
     * @param head the node head of this linked list.
     */
    public void setHead(Node head) {
        this.head = head;
    }

    /**
     * This method is used to get a string that represents the linked list polynomial.
     *
     * @return the string that represents the linked list polynomial
     */
    public String show() {
        Node start = this.head;
        String string = "";

        if (start == null) {
            System.out.println("The linked list of the polynomial is empty.");
        } else {
            while (start != null) {
                string += start.getCoefficient() > 0 && start != this.head ? " + " : "";
                string += start.getCoefficient() + "x^" + (int) start.getExponent();
                start = start.getNext();
            }
        }

        return string;
    }

    /**
     * This method is used to store terms in the linked list of the polynomial.
     *
     * @param coefficient the coefficient to be stored.
     * @param exponent the exponent to be stored.
     */
    public void storeTerm(float coefficient, int exponent) {
        Node start = this.head, newNode, previous = null;

        while (start != null && start.getExponent() > exponent) {
            previous = start;
            start = start.getNext();
        }

        if (start != null && start.getExponent() == exponent) {
            System.out.println("A term with exponent alredy exists.");
        } else {
            newNode = new Node(coefficient, exponent);
            newNode.setNext(start);

            if (start == this.head) {
                this.head = newNode;
            } else {
                previous.setNext(newNode);
            }
        }
    }

    /**
     * This method is used to enter the coefficients and exponents of the polynomial.
     */
    public void enterTerms() {
        float a;
        int exponent;
        String answer = JOptionPane.showInputDialog("Do you want to enter term? Y/N");

        while (answer.equalsIgnoreCase("y")) {
            a = Float.parseFloat(JOptionPane.showInputDialog("Enter the coefficient:"));
            exponent = Integer.parseInt(JOptionPane.showInputDialog("Enter the exponent:"));

            this.storeTerm(a, exponent);
            answer = JOptionPane.showInputDialog("Do you want to enter term? Y/N");
        }
    }

    /**
     * This method is used to insert terms in the linked list of the polynomial. A different from
     * the method for store terms, add the coefficients of the terms with equal degree.
     *
     * @param coefficient the coefficient to be inserted.
     * @param exponent the exponent to be inserted.
     */
    public void insertTerm(float coefficient, int exponent) {
        if (coefficient != 0) {
            Node start = this.head, newNode, previous = null;

            while (start != null && start.getExponent() > exponent) {
                previous = start;
                start = start.getNext();
            }

            if (start != null && start.getExponent() == exponent) {
                float sum = start.getCoefficient() + coefficient;

                if (sum != 0) {
                    start.setCoefficient(sum);
                } else if (start == this.head) {
                    this.head = this.head.getNext();
                } else {
                    previous.setNext(start.getNext());
                }
            } else {
                newNode = new Node(coefficient, exponent);
                newNode.setNext(start);

                if (start == this.head) {
                    this.head = newNode;
                } else if (previous != null) {
                    previous.setNext(newNode);
                } else if (this.head == null) {
                    this.head = newNode;
                }
            }
        }
    }

    /**
     * This method is used to remove terms in the linked list of the polynomial.
     *
     * @param exponent the exponent to be removed.
     * @return confirmation if term was found and removed.
     */
    public boolean removeTerm(int exponent) {
        Node start = this.head, previous = null;
        boolean isFound = false;

        while (start != null && start.getExponent() < exponent) {
            previous = start;
            start = start.getNext();
        }

        if (start != null && start.getExponent() == exponent) {
            isFound = true;

            if (start == this.head) {
                this.head = start.getNext();
            } else {
                previous.setNext(start.getNext());
            }
        }

        return isFound;
    }

    public boolean removeNegativeFirs() {
        Node currentTerm = head, previous = null;
        boolean isRemoved = false;

        if (currentTerm == null) {
            return false;
        }

        while (currentTerm.getCoefficient() >= 0) {
            previous = currentTerm;
            currentTerm = currentTerm.getNext();
        }

        if (currentTerm.getCoefficient() < 0) {
            isRemoved = true;

            if (currentTerm == this.head) {
                this.head = currentTerm.getNext();
            } else {
                previous.setNext(currentTerm.getNext());
            }
        }

        return isRemoved;
    }

    /**
     * This method is used to remove the first term with coefficient negative in the linked list of
     * the polynomial.
     *
     * @return confirmation if term was found and removed.
     */
    public boolean removeFirstTermWithCoefficientNegative() {
        Node start = this.head, previous = null;
        boolean isRemoved = false;

        while (start != null && start.getCoefficient() >= 0) {
            previous = start;
            start = start.getNext();
        }

        if (start != null && start.getCoefficient() < 0) {
            isRemoved = true;

            if (start == this.head) {
                this.head = start.getNext();
            } else {
                previous.setNext(start.getNext());
            }
        }

        return isRemoved;
    }

    /**
     * This method is used to evaluate this polynomial at the point x.
     *
     * @param x the point at which to evaluate the polynomial.
     * @return the result of evaluating the integer whose value is {@code (this(x))}.
     */
    public float evaluate(float x) {
        float result = 0;
        Node start = this.head;

        while (start != null) {
            result += start.getCoefficient() * (Math.pow(x, start.getExponent()));
            start.getNext();
        }

        return result;
    }

    /**
     * This method is used to add two polynomial linked list. It means that it adds the coefficients
     * of the terms with the same degree.
     *
     * @param B the other linked list polynomial
     * @return the polynomial whose value is {@code (this(x) + that(x))}
     */
    public PolynomialLinkedList add(PolynomialLinkedList B) {
        Node startA = this.head, startB = B.getHead();
        int exponentA, exponentB;
        float coefficientA, coefficientB;
        PolynomialLinkedList R = new PolynomialLinkedList();

        while (startA != null && startB != null) {
            exponentA = startA.getExponent();
            exponentB = startB.getExponent();
            coefficientA = startA.getCoefficient();
            coefficientB = startB.getCoefficient();

            if (exponentA == exponentB) {
                R.insertTerm(coefficientA + coefficientB, exponentA);
            } else if (exponentA > exponentB) {
                R.insertTerm(coefficientA, exponentA);
                startA = startA.getNext();
            } else {
                R.insertTerm(coefficientB, exponentB);
                startB = startB.getNext();
            }
        }

        while (startA != null) {
            R.insertTerm(startA.getCoefficient(), startA.getExponent());
            startA = startA.getNext();
        }

        while (startB != null) {
            R.insertTerm(startB.getCoefficient(), startB.getExponent());
            startB = startB.getNext();
        }

        return R;
    }

    /**
     * This method is used to multiply two polynomial linked list. Takes time proportional to the
     * product of the degrees.
     *
     * @param B the other linked list polynomial
     * @return the polynomial whose value is {@code (this(x) * that(x))}
     */
    public PolynomialLinkedList multiply(PolynomialLinkedList B) {
        Node startA = this.head, startB;
        int exponentA, exponentB;
        float coefficientA, coefficientB;
        PolynomialLinkedList R = new PolynomialLinkedList();

        while (startA != null) {
            startB = B.getHead();

            while (startB != null) {
                exponentA = startA.getExponent();
                exponentB = startB.getExponent();
                coefficientA = startA.getCoefficient();
                coefficientB = startB.getCoefficient();

                R.insertTerm(coefficientA * coefficientB, exponentA + exponentB);
                startB = startB.getNext();
            }
            startA = startA.getNext();
        }

        return R;
    }

    /**
     * This method is used to copy one polynomial.
     *
     * @return the duplicate of the polynomial.
     */
    public PolynomialLinkedList copy() {
        Node start = this.head;
        PolynomialLinkedList duplicate = new PolynomialLinkedList();

        while (start != null) {
            duplicate.insertTerm(start.getCoefficient(), start.getExponent());
            start = start.getNext();
        }

        return duplicate;
    }

    /**
     * This method is used to divide two polynomial linked list.
     *
     * @param B the other linked list polynomial.
     * @return the polynomial whose value is {@code (this(x) / that(x))}
     */
    public PolynomialLinkedList divide(PolynomialLinkedList B) {
        Node startA = this.head, startB = B.getHead();
        int exponentR, exponentA;
        float coefficientR, coefficientA;
        PolynomialLinkedList R = new PolynomialLinkedList();

        while ((startA != null && startB != null) && startA.getExponent() >= startB.getExponent()) {
            exponentR = startA.getExponent() - startB.getExponent();
            coefficientR = startA.getCoefficient() / startB.getCoefficient();

            R.insertTerm(coefficientR, exponentR);

            while (startB != null) {
                exponentA = exponentR + startB.getExponent();
                coefficientA = coefficientR * startB.getCoefficient();

                this.insertTerm(-coefficientA, exponentA);

                startB = startB.getNext();
            }
            startA = startA.getNext();
        }

        return R;
    }

    /**
     * This method is used to multiply a polynomial linked list with polynomial vector form 1 and
     * returns a polynomial vector form 2.
     *
     * @param B the other linked list polynomial.
     * @return the polynomial whose value is {@code (this(x) * that(x))}
     */
    public PolynomialF2 multiplyPolynomialLinkedListWithPolynomialF1(PolynomialF1 B) {
        Node startA = this.head;
        PolynomialF2 R = new PolynomialF2(0);

        for (int i = 1; i < B.getCoef(0) + 2; i++) {
            while (startA != null) {
                int exponentR = startA.getExponent() + (int) (B.getCoef(0) + 1 - i);
                float coefficientR = startA.getCoefficient() * B.getCoef(i);

                R.insertTerm(coefficientR, exponentR);

                startA.getNext();
            }
        }

        return R;
    }

    /**
     * This method is used to divide a polynomial linked list with polynomial vector form 1 and
     * returns a polynomial vector form 2.
     *
     * @param B the other linked list polynomial.
     * @return the polynomial whose value is {@code (this(x) / that(x))}
     */
    public PolynomialF2 dividePolynomialLinkedListWithPolynomialF1(PolynomialF1 B) {
        PolynomialLinkedList newFormB = new PolynomialLinkedList(B);
        PolynomialLinkedList resultDivide = this.divide(newFormB);
        PolynomialF2 result = new PolynomialF2(resultDivide);
        Node startA = this.head;
        int exponentR, exponentA;
        float coefficientR, coefficientA;
        PolynomialF2 R = new PolynomialF2(0);
        coefficientR = startA.getCoefficient() / B.getCoef(1);

        while (startA != null && startA.getExponent() >= B.getCoef(0)) {
            exponentR = (int) (startA.getExponent() - B.getCoef(0));
            coefficientR = startA.getCoefficient() / B.getCoef(1);

            R.insertTerm(coefficientR, exponentR);

            for (int i = 1; i < B.getCoef(0) + 2; i++) {
                exponentA = exponentR - (int) (B.getCoef(0) + 1 - i);
                coefficientA = coefficientR * B.getCoef(i);

                this.insertTerm(-coefficientA, exponentA);

            }
            startA.getNext();
        }

        return R;
    }

    public boolean compareWithPolynomialF1(PolynomialF1 polynomialB) {
        Node startA = this.getHead();
        int i = 1, exponentB, exponentA;

        while (i < polynomialB.getCoef(0) + 2 && startA != null) {
            exponentB = (int) polynomialB.getCoef(0) + 1 - i;
            exponentA = startA.getExponent();

            if (exponentB != exponentA) {
                return false;
            }
            if (polynomialB.getCoef(i) != startA.getCoefficient()) {
                return false;
            }
            i += 1;
            startA = startA.getNext();
        }

        return true;
    }
}


/**
 * The {@code Node} class represents a node for linked lists.
 *
 * @author ev
 */
class Node {

    private float coefficient; // coefficient
    private int exponent; // exponent (0 for independent term)
    private Node next; // next node head

    /**
     * Initializes a new node.
     *
     * @param coefficient the coefficient.
     * @param exponent the exponent.
     * @throws IllegalArgumentException if {@code coefficient} is zero.
     * @throws IllegalArgumentException if {@code exponent} is negative.
     */
    public Node(float coefficient, int exponent) {
        if (coefficient == 0) {
            throw new IllegalArgumentException("coefficient cannot be zero");
        }
        if (exponent < 0) {
            throw new IllegalArgumentException("exponent cannot be negative: " + exponent);
        }
        this.coefficient = coefficient;
        this.exponent = exponent;
        this.next = null;
    }

    /**
     * Returns the coefficient of this node
     *
     * @return the coefficient of this node.
     */
    public float getCoefficient() {
        return coefficient;
    }

    /**
     * Sets the coefficient of this node.
     *
     * @param coefficient the coefficient.
     */
    public void setCoefficient(float coefficient) {
        this.coefficient = coefficient;
    }

    /**
     * Returns the exponent of this node.
     *
     * @return the exponent of this node.
     */
    public int getExponent() {
        return exponent;
    }

    /**
     * Sets the exponent of this node.
     *
     * @param exponent the exponent.
     */
    public void setExponent(int exponent) {
        this.exponent = exponent;
    }

    /**
     * Returns the next node head of this node.
     *
     * @return the next node head of this node.
     */
    public Node getNext() {
        return next;
    }

    /**
     * Sets the next node head of this node.
     *
     * @param next the next node head.
     */
    public void setNext(Node next) {
        this.next = next;
    }
}
