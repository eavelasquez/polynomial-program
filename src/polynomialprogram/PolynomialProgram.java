/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package polynomialprogram;

import javax.swing.JOptionPane;

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
        int opcion = 0;
        String menu = "***MENU PRINCIPAL***\n"
                + "1- Polinomio en vector forma 1\n"
                + "2- Polinomio en vector forma 2\n"
                + "3- Polinomio en lista\n"
                + "4- Polinomios combinados\n"
                + "0- Salir";
        do {
            try {
                opcion = Integer.parseInt(JOptionPane.showInputDialog(menu));
                switch (opcion) {
                    case 1 ->
                        menuPolynomialF1();
                    case 2 ->
                        menuPolynomialF2();
                    case 3 ->
                        menuPolista();
                    case 4 ->
                        menuPolinomiosCombinados();
                    case 0 ->
                        System.exit(0);
                    default ->
                        JOptionPane.showMessageDialog(null, "opcion no válida");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "opcion no válida");
            }
        } while (opcion != 0);
    }

    // Método para el menú de polinomio en vector forma 1
    public static void menuPolynomialF1() {
        int grado, opcion = 0;
        float x, res;
        PolynomialF1 A, B, C, CopiaA;
        String menu = "***MENU***\n"
                + "1- Mostrar\n"
                + "2- Evaluar\n"
                + "3- Sumar\n"
                + "4- Multiplicar\n"
                + "5- Dividir\n"
                + "6- Comparar\n"
                + "7- Ir al menu ppal\n"
                + "0- Salir\n"
                + "Digite la opción";
        grado = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el grado del polinomio 1"));
        A = new PolynomialF1(grado);
        A.enterTerms();

        grado = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el grado del polinomio 2"));
        B = new PolynomialF1(grado);
        B.enterTerms();

        do {
            try {
                opcion = Integer.parseInt(JOptionPane.showInputDialog(menu));
                switch (opcion) {
                    case 1:
                        JOptionPane.showMessageDialog(null, "Datos del polinomio 1\n" + A.show()
                                + "\nDatos del polinomio 2\n" + B.show());
                        break;
                    case 2:
                        x = Float.parseFloat(JOptionPane.showInputDialog("Ingrese el valor de la variable"));
                        res = A.evaluate(x);
                        JOptionPane.showMessageDialog(null, "El resultado de evaluate el polinomio es " + res);
                        break;
                    case 3:
                        C = A.add(B);
                        JOptionPane.showMessageDialog(null, "Datos del polinomio 1\n" + A.show()
                                + "\nDatos del polinomio 2\n" + B.show()
                                + "\nDatos del polinomio suma\n" + C.show());
                        break;
                    case 4:
                        C = A.multiply(B);
                        JOptionPane.showMessageDialog(null, "Datos del polinomio 1\n" + A.show()
                                + "\nDatos del polinomio 2\n" + B.show()
                                + "\nDatos del polinomio producto\n" + C.show());
                        break;
                    case 5:
                        if (A.getCoef(0) >= B.getCoef(0)) {
                            CopiaA = A.copy();

                            C = CopiaA.divide(B);
                            JOptionPane.showMessageDialog(null, "Datos del polinomio 1\n" + A.show()
                                    + "\nDatos del polinomio 2\n" + B.show()
                                    + "\nDatos del polinomio division\n" + C.show()
                                    + "\nDatos del residuo\n" + CopiaA.show());
                        } else {
                            JOptionPane.showMessageDialog(null, "No se puede divide");
                        }
                        break;
                    case 6:// A.comparar(B);
                        break;
                    case 7:
                        menuPpal();
                        break;
                    case 0:
                        System.exit(0);
                        break;
                    default:
                        JOptionPane.showMessageDialog(null, "Opción no válida");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Opción no válida");
            }
        } while (opcion != 0);
    }

    // Método para el menú de polinomio en vector forma 2
    public static void menuPolynomialF2() {
        PolynomialF2 A, B, C, CopiaA;

        int canterm, opcion = -1;
        float valorx;
        String menu = "***MENU***\n"
                + "1- Mostrar\n"
                + "2- Evaluar\n"
                + "3- Sumar\n"
                + "4- Multiplicar\n"
                + "5- Dividir\n"
                + "6- Ir al menu ppal\n"
                + "0- Salir";

        canterm = Integer.parseInt(JOptionPane.showInputDialog("Cantidad de términos del polinomio 1"));
        A = new PolynomialF2(canterm);
        A.enterTerms(canterm);

        canterm = Integer.parseInt(JOptionPane.showInputDialog("Cantidad de términos del polinomio 2"));
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
                        valorx = Float.parseFloat(JOptionPane.showInputDialog("Ingrese el valor para x"));
                        JOptionPane.showMessageDialog(null, "El reusltado es " + A.evaluate(valorx));
                    }
                    case 3 -> {
                        C = A.add(B);
                        JOptionPane.showMessageDialog(null, "Datos del polinomio 1\n" + A.show()
                                + "\nDatos del polinomio 2\n" + B.show()
                                + "\nDatos del polinomio Suma\n" + C.show());
                    }
                    case 4 -> {
                        C = A.multiply(B);
                        JOptionPane.showMessageDialog(null, "Datos del polinomio 1\n" + A.show()
                                + "\nDatos del polinomio 2\n" + B.show()
                                + "\nDatos del polinomio multiplicacion\n" + C.show());
                    }
                    case 5 -> {
                        if (A.getData(1) >= B.getData(1)) {
                            CopiaA = A.copy();

                            C = CopiaA.divide(B);
                            JOptionPane.showMessageDialog(null, "Datos del polinomio 1\n" + A.show()
                                    + "\nDatos del polinomio 2\n" + B.show()
                                    + "\nDatos del polinomio division\n" + C.show()
                                    + "\nDatos del residuo\n" + CopiaA.show());
                        } else {
                            JOptionPane.showMessageDialog(null, "No se puede divide");
                        }
                    }
                    case 6 ->
                        menuPpal();
                    case 0 ->
                        System.exit(0);
                    default ->
                        JOptionPane.showMessageDialog(null, "opcion no válida");
                }// fin switch
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "opcion no válida");
            }
        } while (opcion != 0);
    }

    //Método para el menú de polinomio en lista
    public static void menuPolista() {
        PolynomialLinkedList A, B, C, CopiaA;

        A = new PolynomialLinkedList();
        A.enterTerms();

        B = new PolynomialLinkedList();
        B.enterTerms();

        int opcion = -1;
        float valorx;
        String menu = "***MENU***\n"
                + "1- Mostrar\n"
                + "2- Evaluar\n"
                + "3- Sumar\n"
                + "4- Multiplicar\n"
                + "5- Dividir\n"
                + "6- Ir al menu ppal\n"
                + "0- Salir";
        do {
            try {
                opcion = Integer.parseInt(JOptionPane.showInputDialog(menu));
                switch (opcion) {
                    case 1 -> {
                        JOptionPane.showMessageDialog(null,
                                "Datos del polinomio 1\n " + A.show() + "\nDatos del polinomio 2\n" + B.show()
                        );
                        A.show();
                        B.show();
                    }
                    case 2 -> {
                        valorx = Float.parseFloat(JOptionPane.showInputDialog("Ingrese el valor para x "));
                        JOptionPane.showMessageDialog(null, "El reusltado es " + A.evaluate(valorx));
                    }
                    case 3 -> {
                        C = A.add(B);
                        JOptionPane.showMessageDialog(null, "Datos del polinomio 1\n" + A.show()
                                + "\nDatos del polinomio 2\n" + B.show()
                                + "\nDatos del polinomio Suma\n" + C.show());
                    }
                    case 4 -> {
                        C = A.multiply(B);
                        JOptionPane.showMessageDialog(null, "Datos del polinomio 1\n" + A.show()
                                + "\nDatos del polinomio 2\n" + B.show()
                                + "\nDatos del polinomio multiplicacion\n" + C.show());
                        break;
                    }
                    case 5 -> {
                        if (A.getHead().getExponent() >= B.getHead().getExponent()) {
                            CopiaA = A.copy();

                            C = CopiaA.divide(B);
                            JOptionPane.showMessageDialog(null, "Datos del polinomio 1\n" + A.show()
                                    + "\nDatos del polinomio 2\n" + B.show()
                                    + "\nDatos del polinomio division\n" + C.show()
                                    + "\nDatos del residuo\n" + CopiaA.show());
                        }
                    }
                    case 6 ->
                        menuPpal();
                    case 0 ->
                        System.exit(0);
                    // default: JOptionPane.showMessageDialog(null,"opcion no válida");                    // default: JOptionPane.showMessageDialog(null,"opcion no válida");
                }
                // else
                // {
                // JOptionPane.showMessageDialog(null,"No se puede divide");
                // }
                // break;
                //fin switch
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "opcion no válida");
            }
        } while (opcion != 0);
    }
    //Método para el menú de polinomio combinados

    public static void menuPolinomiosCombinados() {
        int grado, canterm, opcion = -1;

        String menu = "***MENU POLINOMIOS COMBINADOS***\n"
                + "1- polista=polvf1+polvf2\n"
                + "2- polvf2=polista/polvf1\n"
                + "3- polvf1=polvf2/polista\n"
                + "4- polvf2=polista*polvf1\n"
                + "5- comparar polvf1 con polvf2\n"
                + "6- Ir al menú principal\n"
                + "0- Salir\n"
                + "Digite la opcion";
        //
        do {
            try {
                opcion = Integer.parseInt(JOptionPane.showInputDialog(menu));
                switch (opcion) {
                    case 1 -> {
                        PolynomialF1 A;
                        PolynomialF2 M;
                        PolynomialLinkedList L;

                        grado = Integer.parseInt(JOptionPane.showInputDialog(
                                "Ingrese el grado del polinomio 1"));
                        A = new PolynomialF1(grado);
                        A.enterTerms();
                        canterm = Integer.parseInt(JOptionPane.showInputDialog(
                                "Cantidad de términos del polinomio 2"));
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
                        grado = Integer.parseInt(JOptionPane.showInputDialog(
                                "Ingrese el grado del polinomio 1"));
                        A = new PolynomialF1(grado);
                        A.enterTerms();
                        canterm = Integer.parseInt(JOptionPane.showInputDialog(
                                "Cantidad de términos del polinomio 2"));
                        M = new PolynomialF2(canterm);
                        M.enterTerms(canterm);

                        L = A.addPolynomialF1WithPolynomialF2(M);
                        JOptionPane.showMessageDialog(null, "Datos del polinomio 1\n" + A.show()
                                + "\nDatos del polinomio 2\n" + M.show()
                                + "\nDatos del polinomio Suma\n" + L.show());
                    }
                    case 4 -> {
                    }
                    case 5 -> {
                    }
                    case 6 ->
                        menuPpal();
                    case 0 ->
                        System.exit(0);
                    default ->
                        JOptionPane.showMessageDialog(null, "opcion no válida");
                }//fin switch
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "opcion no válida");
            }
        } while (opcion != 0);
    }
}
