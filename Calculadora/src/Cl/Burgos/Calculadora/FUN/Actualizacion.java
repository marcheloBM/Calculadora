/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cl.Burgos.Calculadora.FUN;

import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author march
 */
public class Actualizacion {
    
    //metodo para verificar si existe alguna conexion a internet
    public static boolean verificarConexion(){
        try{
            URL url = new URL(Confi.UrlVersion);
            URLConnection con = url.openConnection();
            con.connect();
            return true;
        }catch (MalformedURLException ex) {
            return false;
//            Logger.getLogger(Actualizacion.class.getName()).log(Level.SEVERE, null, ex);
        }catch(IOException ex){
            return false;
//            Logger.getLogger(Actualizacion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    //Metodo para obtener el contenido de un archivo
    public static String obtenerVersion(){
        try {
            URL url = new URL(Confi.UrlVersion);
            URLConnection con = url.openConnection();
            return obtenerContenidoURL(url);
        } catch (MalformedURLException ex) {
            Logger.getLogger(Actualizacion.class.getName()).log(Level.SEVERE, null, ex);
        } catch(IOException ex){
            Logger.getLogger(Actualizacion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    //Es necesario otro metodo para teminar el anterior
    private static String obtenerContenidoURL(URL url) {
        try {
            Scanner s = new Scanner(url.openStream()).useDelimiter("\\2");
            String contenido = s.next();
            return contenido;
        } catch (IOException ex) {
            Logger.getLogger(Actualizacion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    //Ahora el metodo para redireccionar a un enlace
    public static void abrirEnlace(String url){
        try {
            Desktop.getDesktop().browse(new java.net.URI(url));
        } catch (URISyntaxException | IOException ex) {
            Logger.getLogger(Actualizacion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    //Metodo para descargar el archivo directo desde java
    public static void descargarUpdate(String url){
        try {
            //dirección url del recurso a descargar
//            String url = "https://github.com/marchelo1989/ActualizarAPP/releases/download/v1.0/UpdateAPP.jar";
            //nombre del archivo destino
//            String name = "ApliClaves.exe";
            String name = Confi.nameArchivo;
            
            //Directorio destino para las descargas
//            String folder = "Update/";
            String folder = Confi.CarpetaUpdate;
            
            //Crea el directorio de destino en caso de que no exista
            File dir = new File(folder);
            
            if (!dir.exists())
                if (!dir.mkdir())
                    // no se pudo crear la carpeta de destino
                    return;
            
            //Creamos el archivo destino, en caso de existir lo elimina:
            File file = new File(folder + name);
            
            //Establece la conexion con la url
            URLConnection conn = new URL(url).openConnection();
            conn.connect();
            
            System.out.println("\nempezando descarga: \n");
            System.out.println(">> URL: " + url);
            System.out.println(">> Nombre: " + name);
            System.out.println(">> Tamaño: " + conn.getContentLength() + " bytes");
            
            String datos="";
            datos="\nArchivo Descargado: \n>> URL: " + url+"\n>> Nombre: "+ name+"\n>> Tamaño: " + conn.getContentLength() + " bytes";
                        
            //Abrimos los Stream
            InputStream in = conn.getInputStream();
            OutputStream out = new FileOutputStream(file);
            
            int b = 0;
            while (b != -1) {
              b = in.read();
              if (b != -1)
                out.write(b);
            }
            JOptionPane.showMessageDialog(null, datos);
            AbrirCarpeta();
            out.close();
            in.close();
        } catch (MalformedURLException e) {
          System.out.println("la url: " + url + " no es valida!");
        } catch (IOException e) {
          e.printStackTrace();
        }
        System.exit (0);
    }
    
    public static void AbrirCarpeta() throws IOException{
        if(Confi.SO.startsWith("Windows")){
            File directorio = new File(Confi.CarpetaUpdate);
            Desktop.getDesktop().open(directorio);
        }else{
            File directorio = new File(Confi.CarpetaUpdate);
            Desktop.getDesktop().open(directorio);
        }
        
    }
}
