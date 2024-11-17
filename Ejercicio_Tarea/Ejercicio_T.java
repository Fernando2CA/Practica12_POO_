package mx.unam.fi.poo.g1.p12_Ej_T;
import mx.unam.fi.poo.g1.p12_Ej_T.*;

import java.util.Scanner;

/**
 * Clase principal Ejercicio_T
 * @author Carrillo Alemán Luis Fernando
 * @version Noviembre - 2024
*/

public class Ejercicio_T {
    /**
     * Método main
     * Se ejecuta todo el funcionamiento de la aplicación.
     * @param args -> Arreglo por defecto del método main.
     */
    public static void main(String[] args) {
        
        CuentaBanco cuenta = new CuentaBanco(0);
        Scanner en = new Scanner(System.in);

        System.out.print("Ingrese la cantidad que desea depositar:\n> ");
        double deposito = en.nextDouble();
        cuenta.deposito(deposito);

        System.out.print("Ingrese la cantidad que desea retirar (hilo1):\n> ");
        double retiro1 = en.nextDouble();

        System.out.print("Ingrese la cantidad que desea retirar (hilo2):\n> ");
        double retiro2 = en.nextDouble();

        Thread h1 = new Thread(() -> cuenta.retirar(retiro1));
        Thread h2 = new Thread(() -> cuenta.retirar(retiro2));  

        h1.start();
        h2.start();

        try {
            h1.join();
            h2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        en.close();
        System.out.println("Saldo final en la cuenta: " + cuenta.getSaldo());
    }
}
