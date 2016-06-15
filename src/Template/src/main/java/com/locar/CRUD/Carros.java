/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.locar.CRUD;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Felipe
 */
@Entity
@Table(name = "carros")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Carros.findAll", query = "SELECT c FROM Carros c"),
    @NamedQuery(name = "Carros.findByMarca", query = "SELECT c FROM Carros c WHERE c.marca = :marca"),
    @NamedQuery(name = "Carros.findByModelo", query = "SELECT c FROM Carros c WHERE c.modelo = :modelo"),
    @NamedQuery(name = "Carros.findByAno", query = "SELECT c FROM Carros c WHERE c.ano = :ano"),
    @NamedQuery(name = "Carros.findByPlaca", query = "SELECT c FROM Carros c WHERE c.placa = :placa"),
    @NamedQuery(name = "Carros.findByAlugado", query = "SELECT c FROM Carros c WHERE c.alugado = :alugado")})
public class Carros implements Serializable {

    private static final long serialVersionUID = 1L;
    @Size(max = 25)
    @Column(name = "marca")
    private String marca;
    @Size(max = 25)
    @Column(name = "modelo")
    private String modelo;
    @Column(name = "ano")
    private Integer ano;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "placa")
    private String placa;
    @Column(name = "alugado")
    private Boolean alugado;

    public Carros() {
    }

    public Carros(String placa) {
        this.placa = placa;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public Integer getAno() {
        return ano;
    }

    public void setAno(Integer ano) {
        this.ano = ano;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public Boolean getAlugado() {
        return alugado;
    }

    public void setAlugado(Boolean alugado) {
        this.alugado = alugado;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (placa != null ? placa.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Carros)) {
            return false;
        }
        Carros other = (Carros) object;
        if ((this.placa == null && other.placa != null) || (this.placa != null && !this.placa.equals(other.placa))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.locar.CRUD.Carros[ placa=" + placa + " ]";
    }
    
}
