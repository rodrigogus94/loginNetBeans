package Negocio;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;



@ManagedBean(name = "Login")
@RequestScoped
@Entity
@Table(name = "Login")
public class Login implements Serializable{
    
    @Id
    @GeneratedValue
    private int id;
    
    @Column(name = "Nome", length = 100)
    private String Nome;
    
    @Column(name = "Login", length = 100)
    private String login;
    
    @Column(name = "Senha", length = 100)   
    private String senha;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return Nome;
    }

    public void setNome(String Nome) {
        this.Nome = Nome;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
    
    @Override
    public int hashCode(){
        int hash = 7;
        hash = 59 * hash + this.id;
        return hash;
    }
    
    @Override
    public boolean equals(Object obj){
        if(obj == null){
            return false;
        }
        if(getClass() != obj.getClass()){
            return false;
        }
        final Login other = (Login) obj;
        if(this.id != other.id){
            return false;
        }
        return true;
    }
}
