package com.locar.CRUD;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "aluguel")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Aluguel.findAll", query = "SELECT a FROM Aluguel a"),
    @NamedQuery(name = "Aluguel.findById", query = "SELECT a FROM Aluguel a WHERE a.id = :id"),
    @NamedQuery(name = "Aluguel.findByPlaca", query = "SELECT a FROM Aluguel a WHERE a.placa = :placa"),
    @NamedQuery(name = "Aluguel.findByCpf", query = "SELECT a FROM Aluguel a WHERE a.cpf = :cpf"),
    @NamedQuery(name = "Aluguel.findByDataLoc", query = "SELECT a FROM Aluguel a WHERE a.dataLoc = :dataLoc"),
    @NamedQuery(name = "Aluguel.findByDataDev", query = "SELECT a FROM Aluguel a WHERE a.dataDev = :dataDev")})
public class Aluguel implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 25)
    @Column(name = "placa")
    private String placa;
    @Size(max = 15)
    @Column(name = "cpf")
    private String cpf;
    @Temporal(TemporalType.DATE)
    @Column(name = "data_loc")
    private Date dataLoc;
    @Temporal(TemporalType.DATE)
    @Column(name = "data_dev")
    private Date dataDev;

    public Aluguel() {
    }

    public Aluguel(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Date getDataLoc() {
        return dataLoc;
    }

    public void setDataLoc(Date dataLoc) {
        this.dataLoc = dataLoc;
    }

    public Date getDataDev() {
        return dataDev;
    }

    public void setDataDev(Date dataDev) {
        this.dataDev = dataDev;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Aluguel)) {
            return false;
        }
        Aluguel other = (Aluguel) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Aluguel{" + "id=" + id + ", placa=" + placa + ", cpf=" + cpf + ", dataLoc=" + dataLoc + ", dataDev=" + dataDev + '}';
    }
    
}
