/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.PorteriaV31.Querys;

/**
 *
 * @author MAURICIO
 */
public class Querys {
    
    //<editor-fold desc="PERSONAS SUCURSAL CLI QUERY" defaultstate="collapsed">
    public static final String PERSONAS_SUCURSAL_CLI_ALL= "SELECT a FROM PersonasSucursal a ";
    public static final String PERSONAS_SUCURSAL_CLI_PERSONA= " a.personasSucursalPK.idPersona = '";
    public static final String PERSONAS_SUCURSAL_CLI_SUCURSAL= " a.personasSucursalPK.sucursal = '";
    public static final String PERSONAS_SUCURSAL_CLI_ESTADO= " a.estado.idEstado = '";
    public static final String PERSONAS_SUCURSAL_CLI_NO_ESTADO= " a.estado.idEstado != '";
        public static String PERSONAS_SUCURSAL_ID_EXTERNO=" a.idExterno = '";
    //</editor-fold>
    
    //<editor-fold desc="PERSONAS CLI QUERY" defaultstate="collapsed">
    public static final String PERSONA_CLI_ALL= "SELECT a FROM Personas a ";
    public static final String PERSONA_CLI_DOC_TYPE= " a.tipoDocumento.tipoDocumento = '";
    public static final String PERSONA_CLI_DOC_NUMBER= " a.numeroDocumento = '";
    public static final String PERSONA_CLI_SUCURSAL= " a.idSucursal.idSucursal = '";
    public static final String PERSONA_CLI_ESTADO= " a.idEstado.idEstado = '";
    public static final String PERSONA_CLI_ESTADO_N= " a.idEstado.idEstado != '";
    public static final String PERSONA_CLI_PRIMARY_KEY= "SELECT a FROM Personas a ORDER BY a.idPersona DESC";
    public static final String PERSONA_CLI_IN_SUCURSAL= " a.idSucursal IN ";
    //</editor-fold>
    
    //<editor-fold desc="MOV PERSONAS CLI QUERY" defaultstate="collapsed">
    public static final String MOV_PERSONA_CLI_ALL= "SELECT a FROM MovPersonas a ";
    public static final String MOV_PERSONA_CLI_PERSONA= " a.personasSucursal.personas.idPersona = '";
    public static final String MOV_PERSONA_CLI_SUCURSAL=" a.idSucursal.idSucursal = '";
    public static final String MOV_PERSONA_CLI_MOMENTO_SALIDA_NULL= " a.momentoSalida IS NULL";
    public static final String MOV_PERSONA_CLI_PRIMARY_KEY= "SELECT a FROM MovPersonas a ORDER BY a.idMovPersona DESC";
    public static final String MOV_PERSONA_CLI_ORDER_BY_ID = " ORDER BY a.idMovPersona DESC";
    public static final String MOV_PERSONA_CLI_ENTRY_DATE = " a.momentoEntrada LIKE '";//SHOULD FINISH WITH %
    //</editor-fold>
    
    //<editor-fold desc="MUNICIPIOS CLI QUERY" defaultstate="collapsed">
    public static final String MUNICIPIOS_CLI_DEPARTAMENTO= "SELECT m FROM Municipios m WHERE m.departamento.idDepartamento = '";
    //</editor-fold>
    
    //<editor-fold desc="PORTERIA SUCURSAL CLI QUERY" defaultstate="collapsed">
    public static String PORTERIA_SUCURSAL_CLI_PORTERIA= "SELECT a FROM PorteriasSucursal a WHERE a.porteriasSucursalPK.porteria = '";
    //</editor-fold>
    
    //<editor-fold desc="VEHICLES SUCURSAL CLI QUERY" defaultstate="collapsed">
    public static final String VEHICLES_SUCURSAL_CLI_ALL= "SELECT a FROM VehiculosSucursal a";
    public static final String VEHICLES_SUCURSAL_CLI_PLACA= " a.vehiculosSucursalPK.placa = '";
    public static final String VEHICLES_SUCURSAL_CLI_SUCURSAL= " a.vehiculosSucursalPK.sucursal = '";
    //</editor-fold>
    
    //<editor-fold desc="VEHICLES CLI QUERY" defaultstate="collapsed">
    public static final String VEHICLES_CLI_ALL= "SELECT a FROM Vehiculos a ";
    public static final String VEHICLES_CLI_PLACA= " a.placa = '";
    public static final String VEHICLES_CLI_PRIMARY_KEY= "SELECT a FROM Vehiculos a ORDER BY a.idVehiculo DESC";
    //</editor-fold>
    
    //<editor-fold desc="MOVVEHICLES CLI QUERY" defaultstate="collapsed">
    public static final String MOV_VEHICLES_CLI_ALL= "SELECT a FROM MovVehiculos a ";
    public static final String MOV_VEHICLES_CLI_PLACA= " a.placa.placa = '";
    public static final String MOV_VEHICLES_CLI_PRIMARY_KEY= "SELECT a FROM MovVehiculos a ORDER BY a.idMovVehiculo DESC";
    public static final String MOV_VEHICLE_CLI_MOMENTO_SALIDA_NULL= " a.momentoSalida IS NULL";
    //</editor-fold>
    
    //<editor-fold desc="EMPRESA ORIGEN CLI QUERY" defaultstate="collapsed">
    public static String EMPRESA_ORIGEN_PRIMARY_KEY= "SELECT e FROM EmpresaOrigen e ORDER BY e.idEmpresaOrigen  DESC";
    public static String EMPRESA_ORIGEN_NAME= "SELECT e FROM EmpresaOrigen e WHERE e.nombre1 ='";
    //</editor-fold>
    
    //<editor-fold desc="ENTIDADES CLI QUERY" defaultstate="collapsed">
    public static String ENTIDADES_BY_CATEGORY= "SELECT e FROM Entidades e WHERE e.categoria.idCategoria ='";
    //</editor-fold>
    

     //<editor-fold desc="USUARIOS QUERY" defaultstate="collapsed">
    public static final String USUARIOS_ALL = "SELECT a FROM Usuarios a";
    public static final String USUARIOS_ID=" a.idUsuario = '";
    public static final String USUARIOS_PASSWORD=" a.password = '";
    public static final String USUARIOS_SESION= " a.sesion = '";
    public static final String USUARIOS_ID_SESION= " a.iDSesion = '";
    public static final String USUARIOS_FECHA_DESDE= " a.fechaDesde <= '";
    public static final String USUARIOS_FECHA_HASTA= " a.fechaHasta >= '";
    //</editor-fold>
    
    public static final String EPS_PRIMARY_KEY = "SELECT e FROM Eps e ORDER BY e.eps DESC";
    public static final String ARL_PRIMARY_KEY = "SELECT a FROM Arl a ORDER BY a.arl DESC";
    
    public static final String AREAS_EMPRESA_ALL="SELECT a FROM AreasEmpresa a";
    public static final String AREAS_EMPRESA_SUCURSAL=" a.sucursal.idSucursal = '";
   
    public static final String ACCESO_USUARIO_ALL ="SELECT a FROM AccesoUsuario a";
    public static final String ACCESO_USUARIO_USUARIO=" a.usuario.idUsuario = '";
    public static final String ENTIDADES_ALL = "SELECT e FROM Entidades e";
    public static final String ENTIDADES_CATEGORIA = " e.categoria.idCategoria = '";
    
}
