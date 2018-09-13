package Controlador;

import Negocio.Login;
import Repositorio.RepositorioLogin;
import java.io.IOException;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import javax.swing.JOptionPane;
import org.primefaces.context.RequestContext;

@ManagedBean(name = "ControladorLogin")
@SessionScoped
public class ControladorLogin {
    
    private RepositorioLogin rl = null;
    private Login SelectLogin = null;
    private Login login = null;

  

    public Login getSelectLogin() {
        return SelectLogin;
    }

    public void setSelectLogin(Login SelectLogin) {
        this.SelectLogin = SelectLogin;
    }

    public ControladorLogin() {
        this.rl = new RepositorioLogin();
    }
    
    public String inserirLogin(Login l){
        l.setSenha(criptografia.Criptografia.criptografar(l.getSenha()));
        this.rl.inserir(l);
       
        return "Login.xhtml";
    }
    
    public String alterarLogin(Login l){
        this.rl.alterar(l);
        return "lista.xhtml";
    }   
    
    public Login recuperarLogin(int id){
        return this.rl.recuperar(id);
    }
    
    public void deletarLogin(Login l){
        this.rl.deletar(l);
    }
    
    public List<Login> recuperarTudoLogin(){
        return this.rl.recuperarTodos();
    }

    public RepositorioLogin getRl() {
        return rl;
    }

    public void setRl(RepositorioLogin rl) {
        this.rl = rl;
    }

    public Login getLogin() {
        return login;
    }

    public void setLogin(Login login) {
        this.login = login;
    }
    
    
    
    public String validarLogin(Login l){
        
        l.setSenha(criptografia.Criptografia.criptografar(l.getSenha()));
        this.login = this.rl.recuperarProLogin(l.getLogin(), l.getSenha());
        if(this.login != null){
           ((HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true)).setAttribute("UsusarioLogado", l);
            JOptionPane.showMessageDialog(null, "Eaiii porrra");
            return "index.xhtml";
        }else{
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Login inválido ou Usuario não cadastrado"));
        }
        return null;
    }
    
    public void logoff(){
        this.login = null;
        try{
            FacesContext.getCurrentInstance().getExternalContext().redirect("Login.xhtml");
        }catch(IOException ex){
            RequestContext.getCurrentInstance().showMessageInDialog(new FacesMessage(FacesMessage.SEVERITY_INFO,"Login", ex.getMessage()));
        }
    }
}
