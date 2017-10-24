/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.PorteriaV31.Utils;

/**
 *
 * @author MAURICIO
 */
public class Constants {
    
    // <editor-fold desc="PERSISTENCE ACTIONS" defaultstate="collapsed">
    public static final int CREATE = 1;
    public static final int UPDATE = 2;
    //</editor-fold>
    
    // <editor-fold desc="ERRORS" defaultstate="collapsed">
    public static final int OK = 0;
    public static final int UNKNOWN_EXCEPTION = 1;
    public static final int UNKNOWN_EXCEPTION_AT_FINALLY = 2;
    public static final int NO_RESULT_EXCEPTION = 3;
    public static final int NO_UNIQUE_RESULT_EXCEPTION = 4;
    public static final int VALIDATION_ERROR = 5;
    public static final int PERSISTANCE_EXCEPTION= 6;
    public static final int REPEATED_RECORD=7;
    public static final int NO_SEARCH=8;
    public static final int OBJECT_BLOCK=9;
    //</editor-fold>
    
    //THIS ERRORS MESSAGES ARE ONLY FOR INTERNAL USE
    // <editor-fold desc="ERROR MESSAGES" defaultstate="collapsed">
    public static final String MESSAGE_VALIDATION_ERROR = "APP ERROR: VALIDATION ERROR ";
    public static final String MESSAGE_UNKNOWN_EXCEPTION = "APP EXCEPTION: UNKNOWN EXCEPTION ";
    public static final String MESSAGE_UNKNOWN_EXCEPTION_AT_FINALLY = "APP EXCEPTION: UNKNOWN EXCEPTION AT FINALLY ";
    public static final String MESSAGE_NO_RESULT_EXCEPTION = "APP EXCEPTION: NO RESULT EXCEPTION";
    public static final String MESSAGE_NO_UNIQUE_RESULT_EXCEPTION = "APP EXCEPTION: NO UNIQUE RESULT EXCEPTION";
    public static final String MESSAGE_DATE_FORMAT_EXCEPTION = "APP EXCEPTION: DATE FORMAT EXCEPTION";
    public static final String LOGGER_ERROR_MESSAGE="com.Activ Exception: ";
    //</editor-fold>
    
    // <editor-fold desc="LOGIN CASES" defaultstate="collapsed">
    public static final String LOGIN_OK = "LOGIN OK";
    public static final String LOGIN_FAIL = "LOGIN FAIL";
    public static final String EXPIRED_USER = "EXPIRED_USER";
    //</editor-fold>
    
    //NOMBRE DEL CAMPO FORMULARIO EN LA TABLA CONFIG DE LA BASE DE DATOS
    // <editor-fold desc="FORMULARIO CONFIGURACION" defaultstate="collapsed">
    public static String CONFIGPERSONSFORM ="PERSONA";
    public static String CONFIGVEHICLESFORM ="VEHICULO";
    //</editor-fold>
    
    // <editor-fold desc="ENTITIES" defaultstate="collapsed">
    public static final int ENTITY_VISITANT = 5;
    //</editor-fold>
    
    // <editor-fold desc="STATUS" defaultstate="collapsed">
    public static final int STATUS_ENTRY = 3;
    public static final int STATUS_INACTIVE = 4;
    public static final int STATUS_BLOCKED= 2;
    //</editor-fold>
    
    // <editor-fold desc="DOCUMENT_TYPE" defaultstate="collapsed">
    public static final int DOCUMENT_TYPE_CEDULA = 1;
    //</editor-fold>
    
    // <editor-fold desc="ORIGIN ENTERPRISE" defaultstate="collapsed">
    public static final int ORIGIN_ENTERPRISE_OTHER = 1;
    //</editor-fold>
    
    // <editor-fold desc="HTTP SESSION" defaultstate="collapsed">
    public static final String SESSION_USER="USER";
    //</editor-fold>
    
    // <editor-fold desc="EVENT TYPE" defaultstate="collapsed">
    public static final String STRING_ENTRY="E";
    public static final String STRING_EXIT="S";
    //</editor-fold>
    
    // <editor-fold desc="OBJECT TYPE" defaultstate="collapsed">
    public static final String OBJECT_PERSON = "Persona";
    //</editor-fold>
    
    // <editor-fold desc="MOVEMENT TYPE" defaultstate="collapsed">
    public static final String MOVEMENT_ENTRY = "Entrada";
    public static final String MOVEMENT_EXIT = "Salida";
    public static final String MOVEMENT_NO = "No movment";
     //</editor-fold>
    
    // <editor-fold desc="CATEGORIES" defaultstate="collapsed">
    public static final String CATEGORY_PERSON="1";
    public static final String CATEGORY_VEHICLE="2";
    //</editor-fold>
}
