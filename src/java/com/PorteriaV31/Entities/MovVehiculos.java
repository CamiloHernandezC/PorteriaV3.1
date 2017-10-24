/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.PorteriaV31.Entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author amorales
 */
@Entity
@Table(name = "mov_vehiculos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MovVehiculos.findAll", query = "SELECT m FROM MovVehiculos m"),
    @NamedQuery(name = "MovVehiculos.findByIdMovVehiculo", query = "SELECT m FROM MovVehiculos m WHERE m.idMovVehiculo = :idMovVehiculo"),
    @NamedQuery(name = "MovVehiculos.findByFechaEntrada", query = "SELECT m FROM MovVehiculos m WHERE m.fechaEntrada = :fechaEntrada"),
    @NamedQuery(name = "MovVehiculos.findByHoraEntrada", query = "SELECT m FROM MovVehiculos m WHERE m.horaEntrada = :horaEntrada"),
    @NamedQuery(name = "MovVehiculos.findByFechaSalida", query = "SELECT m FROM MovVehiculos m WHERE m.fechaSalida = :fechaSalida"),
    @NamedQuery(name = "MovVehiculos.findByHoraSalida", query = "SELECT m FROM MovVehiculos m WHERE m.horaSalida = :horaSalida"),
    @NamedQuery(name = "MovVehiculos.findByObservacionEntrada", query = "SELECT m FROM MovVehiculos m WHERE m.observacionEntrada = :observacionEntrada"),
    @NamedQuery(name = "MovVehiculos.findByObservacionSalida", query = "SELECT m FROM MovVehiculos m WHERE m.observacionSalida = :observacionSalida"),
    @NamedQuery(name = "MovVehiculos.findBySalidaForzosa", query = "SELECT m FROM MovVehiculos m WHERE m.salidaForzosa = :salidaForzosa"),
    @NamedQuery(name = "MovVehiculos.findByIngresoForzado", query = "SELECT m FROM MovVehiculos m WHERE m.ingresoForzado = :ingresoForzado"),
    @NamedQuery(name = "MovVehiculos.findByFecha", query = "SELECT m FROM MovVehiculos m WHERE m.placa.placa = :fecha"),
    @NamedQuery(name = "MovVehiculos.findByPorteria", query = "SELECT m FROM MovVehiculos m WHERE m.porteria = :porteria"),
    @NamedQuery(name = "MovVehiculos.findByMomentoEntrada", query = "SELECT m FROM MovVehiculos m WHERE m.momentoEntrada = :momentoEntrada"),
    @NamedQuery(name = "MovVehiculos.findByMomentoSalida", query = "SELECT m FROM MovVehiculos m WHERE m.momentoSalida = :momentoSalida")})
public class MovVehiculos extends AbstractEntity {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "Id_Mov_Vehiculo")
    private Integer idMovVehiculo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Fecha_Entrada")
    @Temporal(TemporalType.DATE)
    private Date fechaEntrada;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Hora_Entrada")
    @Temporal(TemporalType.TIME)
    private Date horaEntrada;
    @Column(name = "Fecha_Salida")
    @Temporal(TemporalType.DATE)
    private Date fechaSalida;
    @Column(name = "Hora_Salida")
    @Temporal(TemporalType.TIME)
    private Date horaSalida;
    @Size(max = 140)
    @Column(name = "Observacion_Entrada")
    private String observacionEntrada;
    @Size(max = 140)
    @Column(name = "Observacion_Salida")
    private String observacionSalida;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Salida_Forzosa")
    private boolean salidaForzosa;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Ingreso_Forzado")
    private boolean ingresoForzado;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Fecha")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Porteria")
    private int porteria;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Momento_Entrada")
    @Temporal(TemporalType.TIMESTAMP)
    private Date momentoEntrada;
    @Column(name = "Momento_Salida")
    @Temporal(TemporalType.TIMESTAMP)
    private Date momentoSalida;
    @JoinColumn(name = "Usuario", referencedColumnName = "Id_Persona")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Personas usuario;
    @JoinColumn(name = "Sucursal", referencedColumnName = "Id_Sucursal")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Sucursales sucursal;
    @JoinColumn(name = "Mov_Persona_Entrada", referencedColumnName = "Id_Mov_Persona")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private MovPersonas movPersonaEntrada;
    @JoinColumn(name = "Mov_Persona_Salida", referencedColumnName = "Id_Mov_Persona")
    @ManyToOne(fetch = FetchType.LAZY)
    private MovPersonas movPersonaSalida;
    @JoinColumn(name = "Placa", referencedColumnName = "Placa")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Vehiculos placa;

    public MovVehiculos() {
    }

    public MovVehiculos(Integer idMovVehiculo) {
        this.idMovVehiculo = idMovVehiculo;
    }

    public MovVehiculos(Integer idMovVehiculo, Date fechaEntrada, Date horaEntrada, boolean salidaForzosa, boolean ingresoForzado, Date fecha, int porteria, Date momentoEntrada) {
        this.idMovVehiculo = idMovVehiculo;
        this.fechaEntrada = fechaEntrada;
        this.horaEntrada = horaEntrada;
        this.salidaForzosa = salidaForzosa;
        this.ingresoForzado = ingresoForzado;
        this.fecha = fecha;
        this.porteria = porteria;
        this.momentoEntrada = momentoEntrada;
    }

    public Integer getIdMovVehiculo() {
        return idMovVehiculo;
    }

    public void setIdMovVehiculo(Integer idMovVehiculo) {
        this.idMovVehiculo = idMovVehiculo;
    }

    public Date getFechaEntrada() {
        return fechaEntrada;
    }

    public void setFechaEntrada(Date fechaEntrada) {
        this.fechaEntrada = fechaEntrada;
    }

    public Date getHoraEntrada() {
        return horaEntrada;
    }

    public void setHoraEntrada(Date horaEntrada) {
        this.horaEntrada = horaEntrada;
    }

    public Date getFechaSalida() {
        return fechaSalida;
    }

    public void setFechaSalida(Date fechaSalida) {
        this.fechaSalida = fechaSalida;
    }

    public Date getHoraSalida() {
        return horaSalida;
    }

    public void setHoraSalida(Date horaSalida) {
        this.horaSalida = horaSalida;
    }

    public String getObservacionEntrada() {
        return observacionEntrada;
    }

    public void setObservacionEntrada(String observacionEntrada) {
        this.observacionEntrada = observacionEntrada;
    }

    public String getObservacionSalida() {
        return observacionSalida;
    }

    public void setObservacionSalida(String observacionSalida) {
        this.observacionSalida = observacionSalida;
    }

    public boolean getSalidaForzosa() {
        return salidaForzosa;
    }

    public void setSalidaForzosa(boolean salidaForzosa) {
        this.salidaForzosa = salidaForzosa;
    }

    public boolean getIngresoForzado() {
        return ingresoForzado;
    }

    public void setIngresoForzado(boolean ingresoForzado) {
        this.ingresoForzado = ingresoForzado;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getPorteria() {
        return porteria;
    }

    public void setPorteria(int porteria) {
        this.porteria = porteria;
    }

    public Date getMomentoEntrada() {
        return momentoEntrada;
    }

    public void setMomentoEntrada(Date momentoEntrada) {
        this.momentoEntrada = momentoEntrada;
    }

    public Date getMomentoSalida() {
        return momentoSalida;
    }

    public void setMomentoSalida(Date momentoSalida) {
        this.momentoSalida = momentoSalida;
    }

    public Personas getUsuario() {
        return usuario;
    }

    public void setUsuario(Personas usuario) {
        this.usuario = usuario;
    }

    public Sucursales getSucursal() {
        return sucursal;
    }

    public void setSucursal(Sucursales sucursal) {
        this.sucursal = sucursal;
    }

    public MovPersonas getMovPersonaEntrada() {
        return movPersonaEntrada;
    }

    public void setMovPersonaEntrada(MovPersonas movPersonaEntrada) {
        this.movPersonaEntrada = movPersonaEntrada;
    }

    public MovPersonas getMovPersonaSalida() {
        return movPersonaSalida;
    }

    public void setMovPersonaSalida(MovPersonas movPersonaSalida) {
        this.movPersonaSalida = movPersonaSalida;
    }

    public Vehiculos getPlaca() {
        return placa;
    }

    public void setPlaca(Vehiculos placa) {
        this.placa = placa;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idMovVehiculo != null ? idMovVehiculo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MovVehiculos)) {
            return false;
        }
        MovVehiculos other = (MovVehiculos) object;
        if ((this.idMovVehiculo == null && other.idMovVehiculo != null) || (this.idMovVehiculo != null && !this.idMovVehiculo.equals(other.idMovVehiculo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entities.MovVehiculos[ idMovVehiculo=" + idMovVehiculo + " ]";
    }

    @Override
    public int getPrimaryKey() {
        return idMovVehiculo;
    }

    @Override
    public void setPrimaryKey(int primaryKey) {
        idMovVehiculo=primaryKey;
    }

    @Override
    public void setUser(Personas user) {
        usuario = user;
    }

    @Override
    public void setDate(Date date) {
        fecha = date;
    }

    @Override
    public void setStatus(Integer STATUS_INACTIVE) {
        //Nothing to do here
    }
    
}
