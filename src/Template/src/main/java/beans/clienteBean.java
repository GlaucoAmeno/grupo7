package beans;

import com.locar.CRUD.Cliente;
import com.locar.controller.ClienteJpaController;
import com.locar.controller.exceptions.NonexistentEntityException;
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
public class clienteBean {

    private String nome;
    private Date nasc;
    private String cpf;
    private String endereco;
    private String telefone;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Date getNasc() {
        return nasc;
    }

    public void setNasc(Date nasc) {
        this.nasc = nasc;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
    
    public clienteBean() {
    }
    
    public void gravar(){
        try {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("CarrosPU");
            ClienteJpaController ujc = new ClienteJpaController(emf);
            Cliente c = new Cliente();
            
            c.setNome(nome);
            c.setNasc(nasc);
            c.setCpf(cpf);
            c.setEndereco(endereco);
            c.setTelefone(telefone);
            
            ujc.create(c);
        } catch (Exception ex) {
            Logger.getLogger(clienteBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void excluir(String cpf) throws NonexistentEntityException {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("CarrosPU");
        ClienteJpaController ujc = new ClienteJpaController(emf);
        
        ujc.destroy(cpf);
    }
    
    public void editar() throws Exception {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("CarrosPU");
        ClienteJpaController ujc = new ClienteJpaController(emf);
        
        Cliente c = new Cliente();
            
        c.setNome(nome);
        c.setNasc(nasc);
        c.setCpf(cpf);
        c.setEndereco(endereco);
        c.setTelefone(telefone);
        
        ujc.edit(c);
    }
    
    public void load_data(Cliente cliente) {
        this.nome = cliente.getNome();
        this.cpf = cliente.getCpf();
        this.nasc = cliente.getNasc();
        this.endereco = cliente.getEndereco();
        this.telefone = cliente.getTelefone();
    }
    
    public List<Cliente> obterClientes(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("CarrosPU");
        ClienteJpaController ujc = new ClienteJpaController(emf);
        
        return ujc.findClienteEntities();
    }
}
