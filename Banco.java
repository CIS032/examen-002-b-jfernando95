/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.swing.JOptionPane;


public class Banco {

    static ArrayList<Cuenta> listaCuenta = new ArrayList<Cuenta>();
    static String archivo = "C://Users//Ferchitoo//Documents//Examen2//Cuentas.csv";

    public static void agregar(Cuenta cuenta) {
        listaCuenta.add(cuenta);
    }
    
    public static void grabar() {
        PrintWriter pw = null;
        try {
	    // Examen002: La ruta y el nombre del 'archivo' debe ser 
	    // establecido dinamicamente por el usuario en el lugar adecuado
            FileWriter fw = new FileWriter(archivo, true);
            pw = new PrintWriter(fw);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        for (Cuenta cuenta : listaCuenta) {
            String linea = "";
            if (cuenta instanceof CuentaAhorro) {
                linea="Cuenta Ahorro"+";"+cuenta.toString();
            }if (cuenta instanceof CuentaHipoteca) {
                linea="Cuenta Hipoteca"+";"+cuenta.toString();
            }if (cuenta instanceof CuentaPrestamo) {
                linea="Cuenta Prestamo"+";"+cuenta.toString();
            }
            pw.println(linea);
        }
        pw.close();
    }
    
    public static Cuenta buscarCuentaAhorro() {
        String nombre = (JOptionPane.showInputDialog(null, "Ingrese nombre", "Verificar Cuenta", JOptionPane.INFORMATION_MESSAGE));
        CuentaAhorro cuentaAH = new CuentaAhorro(nombre);
        Cuenta c=(Cuenta)cuentaAH;
        if (listaCuenta.contains(c)) {
            return listaCuenta.get(listaCuenta.indexOf(c));
        }
        return null;
    }
    public static Cuenta buscarCuentaHipoteca() {
        String nombre = (JOptionPane.showInputDialog(null, "Ingrese nombre", "Verificar Cuenta", JOptionPane.INFORMATION_MESSAGE));
        CuentaHipoteca cuentaAH = new CuentaHipoteca(nombre);
        Cuenta c=(Cuenta)cuentaAH;
        if (listaCuenta.contains(c)) {
            return listaCuenta.get(listaCuenta.indexOf(c));
        }
        return null;
    }

    public String leerCuentas(){
	BufferedReader leer = null;
        String lector = "";
        try {
            FileReader f = new FileReader("C://Users//Ferchitoo//Documents//Examen2//Cuentas.csv");
            leer = new BufferedReader(f);
            String linea;
            while ((linea = leer.readLine()) != null) {
                String[] registro = linea.split(",");
                if (registro[0].equals("Cuenta Ahorro")) {
                    String cuenta =registro[0];
                    String cliente=registro[1];
                    Double balance=Double.parseDouble(registro[2]);
                    Double tasaInteres=Double.parseDouble(registro[3]);
                    //int horasClase=Integer.parseInt(registro[4]);
//                  int horasComplementarias=Integer.parseInt(registro[5]);
                    CuentaAhorro ca = new CuentaAhorro(cuenta, cliente, balance, tasaInteres);
                    lector += ca.toString() + "\n";
                    listaCuenta.add(ca);
                } else if(registro[0].equals("Cuenta Hipoteca")){
                    String cuenta =registro[0];
                    String cliente=registro[1];
                    Double balance=Double.parseDouble(registro[2]);
                    Double tasaInteres=Double.parseDouble(registro[3]);
                    CuentaHipoteca ch = new CuentaHipoteca(cuenta, cliente, balance, tasaInteres);
                    lector += ch.toString() + "\n";
                    listaCuenta.add(ch);
                }else if(registro[0].equals("Cuenta Prestamo")){
                    String cuenta =registro[0];
                    String cliente=registro[1];
                    Double balance=Double.parseDouble(registro[2]);
                    Double tasaInteres=Double.parseDouble(registro[3]);
                    
                    CuentaPrestamo cp = new CuentaPrestamo(cuenta, cliente, balance, tasaInteres);
                    lector += cp.toString() + "\n";
                    listaCuenta.add(cp);
                }
            }
        } catch (IOException | NumberFormatException e) {
            System.out.println("Error en la lectura del archivo");
        }
        return lector;
    }

    
}