/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.PorteriaV31.Utils;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import org.apache.commons.io.IOUtils;

/**
 *
 * @author amorales
 */
public class PhotoUtils {
    
    public static void almacenarFoto(String tipoObjeto, byte[] imagen, int nombre) {
        
        Properties propiedades = new Properties();
        try {
            propiedades.load(new FileInputStream("C:/Program Files/Porteria/Properties/archivo.properties"));
        } catch (IOException ex) {
            System.out.println("No se ha podido cargar el archivo Properties Porteria: " + ex);
        }
        String direccion = null;
        if (imagen == null) {
            return;
        }
        switch (tipoObjeto) {
            case Constants.OBJECT_PERSON:
                direccion = propiedades.getProperty("DireccionPersona");
                direccion = direccion + String.valueOf(nombre) + ".jpg";
                break;
        }
        try (FileOutputStream fileOut = new FileOutputStream(direccion)) {
            fileOut.write(imagen);
        } catch (Exception ex) {
            System.out.println("EXCEPTION GUARDANDO INFORMACION" + ex);
        }
    }

    public static byte[] obtenerFotoCarpeta(String nombre,String tipoObjeto) throws FileNotFoundException, IOException {

        Properties propiedades = new Properties();
        try {
            propiedades.load(new FileInputStream("C:/Program Files/Porteria/Properties/archivo.properties"));
        } catch (IOException ex) {
            System.out.println("No se ha podido cargar el archivo Properties Porteria: " + ex);
        }
        String direccion="";
        switch (tipoObjeto) {
            case Constants.OBJECT_PERSON:
            direccion = propiedades.getProperty("DireccionPersona");
            direccion = direccion + String.valueOf(nombre) + ".jpg";    
        }
       
        try {
            InputStream is = new BufferedInputStream(new FileInputStream(direccion));
            //Viene de la libreria Commons-io-2.4
            byte[] image = IOUtils.toByteArray(is);
            return image;
        } catch (Exception e) {
            System.out.println("Photo Utils: obtenerFotoCarpeta - La foto buscada no existe " + e);
            return null;
        }

    }
    /**
     * 
     * @param propiedad - El nombre que tiene en el archivo properties. TIENE QUE IR ENTRE COMILLAS
     * @param nombre Nombre del archivo que se busca (Por lo general se almacena en idObjeto)
     * @return Devuelve la direccion donde esta almacenada la foto para poderla adjuntar
     */
    public static String cargarFoto(String propiedad, String nombre) {
        Properties propiedades = new Properties();
        try {
            propiedades.load(new FileInputStream("C:/Program Files/Porteria/Properties/archivo.properties"));
        } catch (IOException ex) {
            System.out.println("No se ha podido cargar el archivo Properties Porteria: " + ex);
        }
        String direccion;
        direccion = propiedades.getProperty(propiedad);
        direccion = direccion + String.valueOf(nombre) + ".jpg";
        return direccion;
    }

}
