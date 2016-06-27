package beans;

import crud.Carros;
import controllers.CarrosJpaController;
import exceptions.NonexistentEntityException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@ManagedBean
@RequestScoped
public class carroBean {

    private String placa;
    private String marca;
    private Integer ano;
    private String modelo;
    private Boolean alugado = false;

    public carroBean() {
        
    }
    
    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public Integer getAno() {
        return ano;
    }

    public void setAno(Integer ano) {
        this.ano = ano;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public Boolean getAlugado() {
        return alugado;
    }

    public void setAlugado(Boolean alugado) {
        this.alugado = alugado;
    }
    
    public void gravar(){
        try {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("CarrosPU");
            CarrosJpaController ujc = new CarrosJpaController(emf);
            Carros c = new Carros();
            
            c.setAno(ano);
            c.setMarca(marca);
            c.setModelo(modelo);
            c.setPlaca(placa);
            c.setAlugado(alugado);
            
            ujc.create(c);
        } catch (Exception ex) {
            Logger.getLogger(carroBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void excluir(String placa) throws NonexistentEntityException {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("CarrosPU");
        CarrosJpaController ujc = new CarrosJpaController(emf);
        
        ujc.destroy(placa);
    }
    
    public void editar() throws Exception {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("CarrosPU");
        CarrosJpaController ujc = new CarrosJpaController(emf);
        Carros c = new Carros();
            
        c.setAno(ano);
        c.setMarca(marca);
        c.setModelo(modelo);
        c.setPlaca(placa);
        c.setAlugado(alugado);
        
        ujc.edit(c);
    }
    
    public void load_data(Carros carro) {
        this.marca = carro.getMarca();
        this.modelo = carro.getModelo();
        this.ano = carro.getAno();
        this.placa = carro.getPlaca();
        this.alugado = carro.getAlugado();
    }
    
    public List<Carros> obterCarros(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("CarrosPU");
        CarrosJpaController ujc = new CarrosJpaController(emf);
        
        return ujc.findCarrosEntities();
    }
}
