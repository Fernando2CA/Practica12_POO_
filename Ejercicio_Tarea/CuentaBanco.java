package mx.unam.fi.poo.g1.p12_Ej_T;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Clase CuentaBanco
 * @author Carrillo Alemán Luis Fernando
 * @version Noviembre - 2024
 */
    
public class CuentaBanco implements Runnable {
    private double saldo;
    private Lock cerrojo = new ReentrantLock();

    /**
     * Método Cosntructor.
     * Para construir objetos CuentaBanco.
     * @param saldo -> Atributo para el saldo de la cuenta.
     */
    public CuentaBanco(double saldo) {
        setSaldo(saldo);
    }

    /**
     * Método get
     * @return saldo -> Regresa el atributo saldo de la cuenta.
     */
    public double getSaldo() {
        return this.saldo;
    }

    /**
     * Método set
     * @param saldo -> Para asignar el saldo de la cuenta.
     */
    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    /**
     * Método que realiza un depósito en la cuenta.
     * Utiliza un lock(cerrojo) para evitar condiciones de carrera.
     * @param dinero Cantidad de dinero a depositar en la cuenta.
     */
    public void deposito(double dinero) {
        cerrojo.lock();
        try {
            System.out.println("\nSaldo inicial: " + this.getSaldo());
            saldo += dinero;
            System.out.println("\nSaldo después del depósito: " + this.getSaldo());
        } finally {
            cerrojo.unlock();
        }
    }

    /**
     * Método que realiza un retiro en la cuenta.
     * Utiliza un lock(cerrojo) para evitar condiciones de carrera.
     * @param dinero Cantidad de dinero a retirar de la cuenta.
     */
    public void retirar(double dinero) {
        cerrojo.lock();
        try {
            if (saldo >= dinero) {
                saldo -= dinero;
                System.out.println("Saldo después del retiro: " + getSaldo());
            } else {
                System.out.println("Error, saldo insuficiente...");
            }
        } finally {
            cerrojo.unlock();
        }
    }

    /**
     * Método de ejecución del hilo. 
     * Este método realiza un depósito y un retiro con cantidades de 0.
     */
    @Override
    public void run() {
        deposito(0);
        retirar(0);
    }
}
