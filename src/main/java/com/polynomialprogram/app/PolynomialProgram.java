/*
 * To change this license header, choose License Headers in Project Properties. To change this
 * template file, choose Tools | Templates and open the template in the editor.
 */
package com.polynomialprogram.app;

import javax.swing.JOptionPane;

import org.beryx.textio.TerminalProperties;
import org.beryx.textio.TextIO;
import org.beryx.textio.TextIoFactory;
import org.beryx.textio.TextTerminal;

/**
 *
 * @author ev
 */
public class PolynomialProgram {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        menuPpal();
    }

    // Método para el menú principal
    public static void menuPpal() {
        TextIO textIO = TextIoFactory.getTextIO();
        TextTerminal<?> terminal = textIO.getTextTerminal();
        TerminalProperties<?> props = terminal.getProperties();

        props.setPromptBold(true);
        props.setPromptUnderline(true);
        props.setPromptColor("cyan");
        terminal.println("***MENU PRINCIPAL***");

        props.setPromptUnderline(false);
        props.setPromptBold(false);

        String[] options = {"Polinomio en vector forma 1", "Polinomio en vector forma 2",
                "Polinomio en lista", "Polinomios combinados", "salir"};
        props.setInputColor("red");
        String option =
                textIO.newStringInputReader().withNumberedPossibleValues(options).read("Options");
        props.setPromptColor("withe");

        try {
            if (option == options[0]) {
                terminal.println();
                menuPolynomialF1(textIO, terminal, props);
            } else if (option == options[1]) {
                menuPolynomialF2(textIO, terminal, props);
            } else if (option == options[2]) {
                menuPolista(textIO, terminal, props);
            } else if (option == options[3]) {
                menuPolinomiosCombinados(textIO, terminal, props);
            } else if (options[4] == options[4]) {
                System.exit(0);
            }
        } catch (NumberFormatException exc) {

        }
    }

    // Método para el menú de polinomio en vector forma 1
    public static void menuPolynomialF1(TextIO textIO, TextTerminal<?> terminal,
            TerminalProperties<?> props) {

        String[] options =
                {"Mostrar", "Evaluar", "Sumar", "Multiplicar", "Dividir", "Comparar", "salir"};

        props.setPromptBold(true);
        props.setPromptUnderline(true);
        props.setPromptColor("green");

        terminal.println("Polinomio vector forma 1");
        props.setPromptUnderline(false);
        props.setPromptBold(false);

        PolynomialF1 polynomialA, polynomialB, polynomialC;

        props.setPromptBold(true);
        terminal.println("Ingrese el polinomio 1");
        props.setPromptBold(false);

        props.setInputColor("yellow");
        int degree_a = textIO.newIntInputReader().read("grado");
        polynomialA = new PolynomialF1(degree_a);
        polynomialA.enterTerms(textIO, terminal, props);

        terminal.println();
        props.setPromptBold(true);
        terminal.println("Ingrese el polinomio 2");
        props.setPromptBold(false);

        int degree_b = textIO.newIntInputReader().read("grado");
        polynomialB = new PolynomialF1(degree_b);
        polynomialB.enterTerms(textIO, terminal, props);

        String option = "salir";
        do {
            terminal.println();
            option = textIO.newStringInputReader().withNumberedPossibleValues(options)
                    .read("Options");
            if (option == options[0]) {
                // Mostrar
                terminal.println();

                props.setPromptUnderline(true);
                terminal.print("polinomio 1: ");
                props.setPromptUnderline(false);

                props.setPromptColor("yellow");
                terminal.println(polynomialA.show());

                props.setPromptColor("green");
                props.setPromptUnderline(true);
                terminal.print("polinomio 2");
                props.setPromptUnderline(false);

                props.setPromptColor("yellow");
                terminal.println(polynomialB.show());

                props.setPromptColor("green");
                props.setPromptBold(false);

                terminal.println();

            } else if (option == options[1]) {
                // evaluar
                float xValue = textIO.newFloatInputReader().read("valor de x");
                float result = polynomialA.evaluate(xValue);

                terminal.print("resultado evaluar: ");
                props.setPromptColor("yellow");
                terminal.println(String.format("%f", result));
                props.setPromptColor("green");
            } else if (option == options[2]) {
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

            } else if (option == options[3]) {
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
            } else if (option == options[4]) {
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
            } else if (option == options[5]) {
                // Comparar

            } else {
                System.exit(0);
            }

        } while (option != "salir");

    }

    // Método para el menú de polinomio en vector forma 2
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
                    case 6 -> menuPpal();
                    case 0 -> System.exit(0);
                    default -> JOptionPane.showMessageDialog(null, "opcion no válida");
                }// fin switch
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "opcion no válida");
            }
        } while (opcion != 0);
    }

    // Método para el menú de polinomio en lista
    public static void menuPolista(TextIO textIO, TextTerminal<?> terminal,
            TerminalProperties<?> props) {
        PolynomialLinkedList A, B, C, CopiaA;

        A = new PolynomialLinkedList();
        A.enterTerms();

        B = new PolynomialLinkedList();
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
                    case 6 -> menuPpal();
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
                    }
                    case 2 -> {
                    }
                    case 3 -> {
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
                    case 4 -> {
                    }
                    case 5 -> {
                    }
                    case 6 -> menuPpal();
                    case 0 -> System.exit(0);
                    default -> JOptionPane.showMessageDialog(null, "opcion no válida");
                }// fin switch
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "opcion no válida");
            }
        } while (opcion != 0);
    }
}
