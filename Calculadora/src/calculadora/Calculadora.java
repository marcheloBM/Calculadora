/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculadora;

import Cl.Burgos.Calculadora.FUN.*;
import Cl.Burgos.Calculadora.GUI.FrHome;
import javax.swing.JOptionPane;

/**
 *
 * @author march
 */
public class Calculadora {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        boolean resp =buscarUpdate();
        if(resp==false){
            new FrHome().setVisible(true);
        }
    }
    public static boolean buscarUpdate(){
        boolean resp;
        if(Actualizacion.verificarConexion()){
            if(Actualizacion.obtenerVersion().equals(Confi.Version)){
                resp=false;
            }else{
                resp=true;
                int respu = JOptionPane.showConfirmDialog(null, "Version "+Actualizacion.obtenerVersion()+ " Diponible \n¿Desea Descargar?");
                if(respu==0){
                    JOptionPane.showMessageDialog(null, "Descargando Update \nEspere Mensaje");
                    Actualizacion.descargarUpdate(Confi.UrlDescarga);
                    resp=true;
                }else{
                    resp=false;
                }
            }
        }else{
            resp=false;
        }
        return resp;
    }   
    
}
