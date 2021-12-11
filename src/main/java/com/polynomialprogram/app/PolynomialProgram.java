/******************************************************************************
 * Compilation: javac Polynomial.java
 * Execution: java Polynomial
 *
 * Polynomials with vector form 1 and 2, also with Linked List.
 *
 * % java Polynomial
 *
 ******************************************************************************/
package com.polynomialprogram.app;

import javax.swing.JOptionPane;

import org.beryx.textio.TerminalProperties;
import org.beryx.textio.TextIO;
import org.beryx.textio.TextIoFactory;
import org.beryx.textio.TextTerminal;

/**
 * The {@code Polynomial} class represents a polynomial with vector form 1 and 2, also with Linked
 * List.
 *
 * @author ev
 * @author drestrepom
 */
public class PolynomialProgram {

    /**
     * Unit tests the polynomial data type.
     *
     * @param args the command-line arguments (none)
     */
    public static void main(String[] args) {
        mainMenu();
    }

    /**
     * This method displays the main menu of the application.
     */
    public static void mainMenu() {
        TextIO textIO = TextIoFactory.getTextIO();
        TextTerminal<?> terminal = textIO.getTextTerminal();
        TerminalProperties<?> props = terminal.getProperties();

        props.setPromptBold(true);
        props.setPromptUnderline(true);
        props.setPromptColor("cyan");

        terminal.println("*** MENÚ PRINCIPAL ***");

        props.setPromptUnderline(false);
        props.setPromptBold(false);

        String[] options = {"Polinomio Vector Forma 1", "Polinomio Vector Forma 2",
                "Polinomio Lista Ligada", "Polinomios Combinados", "Salir"};
        props.setInputColor("red");

        String option =
                textIO.newStringInputReader().withNumberedPossibleValues(options).read("Opciones");
        props.setPromptColor("white");

        try {
            if (option.equals(options[0])) {
                terminal.println();
                menuPolynomialF1(textIO, terminal, props);
            } else if (option.equals(options[1])) {
                menuPolynomialF2(textIO, terminal, props);
            } else if (option.equals(options[2])) {
                menuPolynomialLinkedList(textIO, terminal, props);
            } else if (option.equals(options[3])) {
                menuPolinomiosCombinados(textIO, terminal, props);
            } else if (options[4].equals(options[4])) {
                System.exit(0);
            }
        } catch (NumberFormatException exc) {

        }
    }

    /**
     * This method displays the menu of polynomials vector form 1 of the application.
     *
     * @param textIO
     * @param terminal
     * @param props
     */
    public static void menuPolynomialF1(TextIO textIO, TextTerminal<?> terminal,
            TerminalProperties<?> props) {

        String[] options = {"Mostrar", "Evaluar", "Sumar", "Multiplicar", "Dividir", "Comparar",
                "Ir al menú principal", "Salir"};

        props.setPromptBold(true);
        props.setPromptUnderline(true);
        props.setPromptColor("green");

        terminal.println("Polinomio Vector Forma 1");
        props.setPromptUnderline(false);
        props.setPromptBold(false);

        PolynomialF1 polynomialA, polynomialB, polynomialC;

        props.setPromptBold(true);
        terminal.println("Ingrese el polinomio 1");
        props.setPromptBold(false);

        props.setInputColor("yellow");
        int degree_a = textIO.newIntInputReader().read("Grado");
        polynomialA = new PolynomialF1(degree_a);
        polynomialA.enterTerms(textIO, terminal, props);

        terminal.println();
        props.setPromptBold(true);
        terminal.println("Ingrese el polinomio 2");
        props.setPromptBold(false);

        int degree_b = textIO.newIntInputReader().read("Grado");
        polynomialB = new PolynomialF1(degree_b);
        polynomialB.enterTerms(textIO, terminal, props);

        String option = "Salir";
        do {
            terminal.println();
            option = textIO.newStringInputReader().withNumberedPossibleValues(options)
                    .read("Options");
            if (option.equals(options[0])) {
                // Mostrar
                terminal.println();

                props.setPromptUnderline(true);
                terminal.print("Polinomio 1: ");
                props.setPromptUnderline(false);

                props.setPromptColor("yellow");
                terminal.println(polynomialA.show());

                props.setPromptColor("green");
                props.setPromptUnderline(true);
                terminal.print("Polinomio 2: ");
                props.setPromptUnderline(false);

                props.setPromptColor("yellow");
                terminal.println(polynomialB.show());

                props.setPromptColor("green");
                props.setPromptBold(false);

                terminal.println();

            } else if (option.equals(options[1])) {
                // evaluar
                float xValue = textIO.newFloatInputReader().read("Valor de x");
                float result = polynomialA.evaluate(xValue);

                terminal.print("resultado evaluar: ");
                props.setPromptColor("yellow");
                terminal.println(String.format("%f", result));
                props.setPromptColor("green");
            } else if (option.equals(options[2])) {
                // sumar
                terminal.println();
                polynomialC = polynomialA.add(polynomialB);

                terminal.print("polinomio 1: ");
                props.setPromptColor("yellow");
                terminal.println(polynomialA.show());
                props.setPromptColor("green");

                terminal.print("polinomio 2: ");
                props.setPromptColor("yellow");
                terminal.println(polynomialB.show());
                props.setPromptColor("green");

                terminal.print("polinomio resultado sumar: ");
                props.setPromptColor("yellow");
                terminal.println(polynomialC.show());
                props.setPromptColor("green");

            } else if (option.equals(options[3])) {
                // multiplicar
                terminal.println();
                polynomialC = polynomialA.multiply(polynomialB);

                terminal.print("polinomio 1: ");
                props.setPromptColor("yellow");
                terminal.println(polynomialA.show());
                props.setPromptColor("green");

                terminal.print("polinomio 2: ");
                props.setPromptColor("yellow");
                terminal.println(polynomialB.show());
                props.setPromptColor("green");

                terminal.print("polinomio resultado producto: ");
                props.setPromptColor("yellow");
                terminal.println(polynomialC.show());
                props.setPromptColor("green");
            } else if (option.equals(options[4])) {
                // Dividir
                terminal.println();
                if (polynomialA.getCoef(0) >= polynomialB.getCoef(0)) {
                    polynomialC = polynomialA.divide(polynomialB);

                    terminal.print("polinomio 1: ");
                    props.setPromptColor("yellow");
                    terminal.println(polynomialA.show());
                    props.setPromptColor("green");

                    terminal.print("polinomio 2: ");
                    props.setPromptColor("yellow");
                    terminal.println(polynomialB.show());
                    props.setPromptColor("green");

                    terminal.print("polinomio resultado dividir: ");
                    props.setPromptColor("yellow");
                    terminal.println(polynomialC.show());
                    props.setPromptColor("green");
                } else {
                    props.setPromptColor("red");
                    terminal.println("No se puede dividir");
                    props.setPromptColor("green");
                }
            } else if (option.equals(options[5])) {
                // Comparar
            } else if (option.equals(options[6])) {
                mainMenu();
            } else {
                System.exit(0);
            }

        } while (!"Salir".equalsIgnoreCase(option));

    }

    /**
     * This method displays the menu of polynomials vector form 2 of the application.
     *
     * @param textIO
     * @param terminal
     * @param props
     */
    public static void menuPolynomialF2(TextIO textIO, TextTerminal<?> terminal,
            TerminalProperties<?> props) {
        PolynomialF2 A, B, C, CopiaA;

        int canterm, opcion = -1;
        float valorx;
        String menu = "***MENU***\n" + "1- Mostrar\n" + "2- Evaluar\n" + "3- Sumar\n"
                + "4- Multiplicar\n" + "5- Dividir\n" + "6- Ir al menu ppal\n" + "0- Salir";

        canterm = Integer
                .parseInt(JOptionPane.showInputDialog("Cantidad de términos del polinomio 1"));
        A = new PolynomialF2(canterm);
        A.enterTerms(canterm);

        canterm = Integer
                .parseInt(JOptionPane.showInputDialog("Cantidad de términos del polinomio 2"));
        B = new PolynomialF2(canterm);
        B.enterTerms(canterm);
        do {
            try {
                opcion = Integer.parseInt(JOptionPane.showInputDialog(menu));
                switch (opcion) {
                    case 1 -> {
                        JOptionPane.showMessageDialog(null, "Datos del polinomio 1\n" + A.show()
                                + "\nDatos del polinomio 2\n" + B.show());
                        A.show();
                        B.show();
                    }
                    case 2 -> {
                        valorx = Float
                                .parseFloat(JOptionPane.showInputDialog("Ingrese el valor para x"));
                        JOptionPane.showMessageDialog(null,
                                "El reusltado es " + A.evaluate(valorx));
                    }
                    case 3 -> {
                        C = A.add(B);
                        JOptionPane.showMessageDialog(null,
                                "Datos del polinomio 1\n" + A.show() + "\nDatos del polinomio 2\n"
                                        + B.show() + "\nDatos del polinomio Suma\n" + C.show());
                    }
                    case 4 -> {
                        C = A.multiply(B);
                        JOptionPane.showMessageDialog(null,
                                "Datos del polinomio 1\n" + A.show() + "\nDatos del polinomio 2\n"
                                        + B.show() + "\nDatos del polinomio multiplicacion\n"
                                        + C.show());
                    }
                    case 5 -> {
                        if (A.getData(1) >= B.getData(1)) {
                            CopiaA = A.copy();

                            C = CopiaA.divide(B);
                            JOptionPane.showMessageDialog(null,
                                    "Datos del polinomio 1\n" + A.show()
                                            + "\nDatos del polinomio 2\n" + B.show()
                                            + "\nDatos del polinomio division\n" + C.show()
                                            + "\nDatos del residuo\n" + CopiaA.show());
                        } else {
                            JOptionPane.showMessageDialog(null, "No se puede divide");
                        }
                    }
                    case 6 -> mainMenu();
                    case 0 -> System.exit(0);
                    default -> JOptionPane.showMessageDialog(null, "opcion no válida");
                }// fin switch
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "opcion no válida");
            }
        } while (opcion != 0);
    }

    // Método para el menú de polinomio en lista
    public static void menuPolynomialLinkedList(TextIO textIO, TextTerminal<?> terminal,
            TerminalProperties<?> props) {
        PolynomialLinkedList A, B, C, CopiaA;

        A = new PolynomialLinkedList();
        JOptionPane.showMessageDialog(null, "Polinomio 1");
        A.enterTerms();

        B = new PolynomialLinkedList();
        JOptionPane.showMessageDialog(null, "Polinomio 2");
        B.enterTerms();

        int opcion = -1;
        float valorx;
        String menu = "***MENU***\n" + "1- Mostrar\n" + "2- Evaluar\n" + "3- Sumar\n"
                + "4- Multiplicar\n" + "5- Dividir\n" + "6- Ir al menu ppal\n" + "0- Salir";
        do {
            try {
                opcion = Integer.parseInt(JOptionPane.showInputDialog(menu));
                switch (opcion) {
                    case 1 -> {
                        JOptionPane.showMessageDialog(null, "Datos del polinomio 1\n " + A.show()
                                + "\nDatos del polinomio 2\n" + B.show());
                        A.show();
                        B.show();
                    }
                    case 2 -> {
                        valorx = Float.parseFloat(
                                JOptionPane.showInputDialog("Ingrese el valor para x "));
                        JOptionPane.showMessageDialog(null,
                                "El resultado es " + A.evaluate(valorx));
                    }
                    case 3 -> {
                        C = A.add(B);
                        JOptionPane.showMessageDialog(null,
                                "Datos del polinomio 1\n" + A.show() + "\nDatos del polinomio 2\n"
                                        + B.show() + "\nDatos del polinomio Suma\n" + C.show());
                    }
                    case 4 -> {
                        C = A.multiply(B);
                        JOptionPane.showMessageDialog(null,
                                "Datos del polinomio 1\n" + A.show() + "\nDatos del polinomio 2\n"
                                        + B.show() + "\nDatos del polinomio multiplicacion\n"
                                        + C.show());
                        break;
                    }
                    case 5 -> {
                        if (A.getHead().getExponent() >= B.getHead().getExponent()) {
                            CopiaA = A.copy();

                            C = CopiaA.divide(B);
                            JOptionPane.showMessageDialog(null,
                                    "Datos del polinomio 1\n" + A.show()
                                            + "\nDatos del polinomio 2\n" + B.show()
                                            + "\nDatos del polinomio division\n" + C.show()
                                            + "\nDatos del residuo\n" + CopiaA.show());
                        }
                    }
                    case 6 -> mainMenu();
                    case 0 -> System.exit(0);
                    // default: JOptionPane.showMessageDialog(null,"opcion no válida"); // default:
                    // JOptionPane.showMessageDialog(null,"opcion no válida");
                }
                // else
                // {
                // JOptionPane.showMessageDialog(null,"No se puede divide");
                // }
                // break;
                // fin switch
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "opcion no válida");
            }
        } while (opcion != 0);
    }
    // Método para el menú de polinomio combinados

    public static void menuPolinomiosCombinados(TextIO textIO, TextTerminal<?> terminal,
            TerminalProperties<?> props) {
        int grado, canterm, opcion = -1;

        String menu = "***MENU POLINOMIOS COMBINADOS***\n" + "1- polista=polvf1+polvf2\n"
                + "2- polvf2=polista/polvf1\n" + "3- polvf1=polvf2/polista\n"
                + "4- polvf2=polista*polvf1\n" + "5- comparar polvf1 con polvf2\n"
                + "6- Ir al menú principal\n" + "0- Salir\n" + "Digite la opcion";
        //
        do {
            try {
                opcion = Integer.parseInt(JOptionPane.showInputDialog(menu));
                switch (opcion) {
                    case 1 -> {
                        PolynomialF1 A;
                        PolynomialF2 M;
                        PolynomialLinkedList L;

                        grado = Integer.parseInt(
                                JOptionPane.showInputDialog("Ingrese el grado del polinomio 1"));
                        A = new PolynomialF1(grado);
                        A.enterTerms(textIO, terminal, props);

                        canterm = Integer.parseInt(JOptionPane
                                .showInputDialog("Cantidad de términos del polinomio 2"));
                        M = new PolynomialF2(canterm);
                        M.enterTerms(canterm);

                        L = A.addPolynomialF1WithPolynomialF2(M);

                        JOptionPane.showMessageDialog(null,
                        "Datos del polinomio 1\n" + A.show() + "\nDatos del polinomio 2\n"
                                + M.show() + "\nDatos del polinomio Suma\n" + L.show());
                    }
                    case 2 -> {
                        // polvf2=polista/polvf1
                        PolynomialF2 L;
                        PolynomialLinkedList A, CopiaA;
                        A = new PolynomialLinkedList();
                        JOptionPane.showMessageDialog(null, "Ingrese el grado del polinomio 1");
                        A.enterTerms();

                        grado = Integer.parseInt(
                                JOptionPane.showInputDialog("Ingrese el grado del polinomio 2"));
                        PolynomialF1 M = new PolynomialF1(grado);
                        M.enterTerms(textIO, terminal, props);

                        if (A.getHead().getExponent() >= M.getCoef(0)) {
                            CopiaA = A.copy();

                            L = A.dividePolynomialLinkedListWithPolynomialF1(M);
                            JOptionPane.showMessageDialog(null,
                                    "Datos del polinomio 1\n" + A.show()
                                            + "\nDatos del polinomio 2\n" + M.show()
                                            + "\nDatos del polinomio division\n" + L.show()
                                            + "\nDatos del residuo\n" + CopiaA.show());
                        }
                    }
                    case 3 -> {
                        // polvf1=polvf2/polista
                        PolynomialF2 A, CopiaA;
                        PolynomialLinkedList M;
                        PolynomialF1 L;

                        canterm = Integer.parseInt(JOptionPane
                                .showInputDialog("Cantidad de términos del polinomio 1"));
                        A = new PolynomialF2(canterm);
                        A.enterTerms(canterm);

                        M = new PolynomialLinkedList();
                        JOptionPane.showMessageDialog(null, "Ingrese el grado del polinomio 2");
                        M.enterTerms();

                        if (A.getData(0) >= M.getHead().getExponent()) {
                            CopiaA = A.copy();

                            L = CopiaA.dividePolynomialF2WithPolynomialLinkedList(M);
                            JOptionPane.showMessageDialog(null,
                                    "Datos del polinomio 1\n" + A.show()
                                            + "\nDatos del polinomio 2\n" + M.show()
                                            + "\nDatos del polinomio division\n" + L.show()
                                            + "\nDatos del residuo\n" + CopiaA.show());
                        }
                    }
                    case 4 -> {
                        // polvf2=polista*polvf1
                        PolynomialLinkedList A = new PolynomialLinkedList();
                        JOptionPane.showMessageDialog(null, "Ingrese el grado del polinomio 1");
                        A.enterTerms();

                        grado = Integer.parseInt(
                                JOptionPane.showInputDialog("Ingrese el grado del polinomio 2"));
                        PolynomialF1 M = new PolynomialF1(grado);
                        M.enterTerms(textIO, terminal, props);

                        PolynomialF2 L = A.multiplyPolynomialLinkedListWithPolynomialF1(M);

                        JOptionPane.showMessageDialog(null,
                                "Datos del polinomio 1\n" + A.show() + "\nDatos del polinomio 2\n"
                                        + M.show() + "\nDatos del resultado:\n" + L.show());
                    }
                    case 5 -> {
                        grado = Integer.parseInt(
                                JOptionPane.showInputDialog("Ingrese el grado del polinomio 1"));
                        PolynomialF1 A = new PolynomialF1(grado);
                        A.enterTerms(textIO, terminal, props);

                        canterm = Integer.parseInt(JOptionPane
                                .showInputDialog("Cantidad de términos del polinomio 2"));
                        PolynomialF2 M = new PolynomialF2(canterm);
                        M.enterTerms(canterm);

                        boolean flag = A.comparisonPolynomialF1WithPolynomialF2(M);

                        JOptionPane.showMessageDialog(null, "Datos del polinomio 1\n" + A.show()
                                + "\nDatos del polinomio 2\n" + M.show() + "\nson iguales:\n" + flag);
                    }
                    case 6 -> mainMenu();
                    case 0 -> System.exit(0);
                    default -> JOptionPane.showMessageDialog(null, "opcion no válida");
                }// fin switch
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "opcion no válida");
            }
        } while (opcion != 0);
    }
}
