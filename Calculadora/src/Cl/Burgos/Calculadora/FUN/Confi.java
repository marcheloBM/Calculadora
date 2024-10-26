/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cl.Burgos.Calculadora.FUN;

/**
 *
 * @author march
 */
public interface Confi {
    
    //Configuracion de Directorio
    static String SO = System.getProperty("os.name");
    
    //Configuraciones para Update
    static String nameArchivo = "Calculadora.jar";
    static String CarpetaUpdate = "Update/";
    static String Version = "1.0";
    static String UrlVersion = "";
    static String UrlDescarga = "";
}
