package Controlador;

import DTO.UsuarioFacadeLocal;
import Entity.Usuario;
import java.io.IOException;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
/**
 * @author Cristian Romero
 * @Vesion 1.0
 * Controlador de la vista de inicio de sesión
 */

@ManagedBean(name="inicioController")
@ViewScoped
public class InicioController implements Serializable{
    /**
     * Llamado al EJB e instancia
     */
    @EJB
    UsuarioFacadeLocal conexion;
    /**
     * Instancia del cascaron de usuarios
     */
    Usuario usuarios;
    private String name;
    private String pass;
    
    /**
     *se crea un constructor para en controlador de inicio de sesion
     */
    public InicioController() {
        
    }
    /**
     * Método inicial que crea usuarios por defecto
     */
    @PostConstruct
    public void init(){
        usuarios = new Usuario();
        
    }
    /**
     * Get de la instancia
     * @return Objeto UsuarioT
     */
    public Usuario getUsuarios() {
        return usuarios;
    }

    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {    
        this.pass = pass;
    }
    /**
     * Set de instancia
     * @param usuarios 
     */
    public void setUsuarios(Usuario usuarios) {
        this.usuarios = usuarios;
    }
    
    public String iniciarSesion(){
        String redirect = null;
        try{
            String prueba = usuarios.getUsuario();
            String p = usuarios.getClave();
            Usuario user = conexion.login(usuarios);
            if(user!=null){
                if(user.getRol().equals("administrador")){
                redirect = "admin.xhtml";
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("administrador",user);
                FacesContext.getCurrentInstance().getExternalContext().redirect(redirect);
                }else
                {
                    redirect = "usuario.xhtml";
                    FacesContext.getCurrentInstance().getExternalContext().redirect(redirect);
                }
            }else{
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ATENCION","este usuario no existe"));
            }
        }catch(IOException e){
            
        }
        return redirect;
    }
}
