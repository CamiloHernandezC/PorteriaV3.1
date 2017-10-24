/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.PorteriaV31.Entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author amorales
 */
@Embeddable
public class PersonasSucursalPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "Id_Persona")
    private int idPersona;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Sucursal")
    private int sucursal;

    public PersonasSucursalPK() {
    }

    public PersonasSucursalPK(int idPersona, int sucursal) {
        this.idPersona = idPersona;
        this.sucursal = sucursal;
    }

    public int getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(int idPersona) {
        this.idPersona = idPersona;
    }

    public int getSucursal() {
        return sucursal;
    }

    public void setSucursal(int sucursal) {
        this.sucursal = sucursal;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idPersona;
        hash += (int) sucursal;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PersonasSucursalPK)) {
            return false;
        }
        PersonasSucursalPK other = (PersonasSucursalPK) object;
        if (this.idPersona != other.idPersona) {
            return false;
        }
        if (this.sucursal != other.sucursal) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entities.PersonasSucursalPK[ idPersona=" + idPersona + ", sucursal=" + sucursal + " ]";
    }
    
}
