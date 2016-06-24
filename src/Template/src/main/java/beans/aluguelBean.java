package beans;

import com.locar.CRUD.Aluguel;
import com.locar.controller.AluguelJpaController;
import com.locar.controller.exceptions.NonexistentEntityException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@ManagedBean
@RequestScoped
public class aluguelBean {

    private Integer id;
    private String placa;
    private String cpf;
    private Date dataLoc;
    private Date dataDev;

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


    public aluguelBean() {
        
    }
    
    public void gravar(){
        try {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("CarrosPU");
            AluguelJpaController ujc = new AluguelJpaController(emf);
            Aluguel a = new Aluguel();
            
            a.setCpf(cpf);
            a.setPlaca(placa);
            a.setDataLoc(dataLoc);
            a.setDataDev(dataDev);
            
            ujc.create(a);
        } catch (Exception ex) {
            Logger.getLogger(aluguelBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void excluir(Integer id) throws NonexistentEntityException {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("CarrosPU");
        AluguelJpaController ujc = new AluguelJpaController(emf);
        
        ujc.destroy(id);
    }
    
    public void editar() throws Exception {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("CarrosPU");
        AluguelJpaController ujc = new AluguelJpaController(emf);
        
        Aluguel a = new Aluguel();
            
        a.setId(id);
        a.setCpf(cpf);
        a.setPlaca(placa);
        a.setDataLoc(dataLoc);
        a.setDataDev(dataDev);
        
        ujc.edit(a);
    }
    
    public void load_data(Aluguel aluguel) {
        this.id = aluguel.getId();
        this.cpf = aluguel.getCpf();
        this.placa = aluguel.getPlaca();
        this.dataLoc = aluguel.getDataLoc();
        this.dataDev = aluguel.getDataDev();
    }
    
    public List<Aluguel> obterAluguel(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("CarrosPU");
        AluguelJpaController ujc = new AluguelJpaController(emf);
        
        return ujc.findAluguelEntities();
    }    
}
