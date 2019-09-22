package Controlador;

import Entity.Usuario;
import java.io.IOException;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author Hp-14
 */
@ManagedBean(name="admin")
@ViewScoped
public class adminController implements Serializable{

    /**
     * Creates a new instance of adminController
     */
    public adminController() {
    }
    @PostConstruct
    public void validar(){
        try{
            if(FacesContext.getCurrentInstance().getPartialViewContext().isAjaxRequest()){
                Usuario u = (Usuario)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("administrador");
                if(u==null){
                    FacesContext.getCurrentInstance().getExternalContext().redirect("Error.xhtml");
                }
            }
        }catch(IOException e){
            
        }
    }
    public void cerrarSesion(){
        try{
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("administrador");
            FacesContext.getCurrentInstance().getExternalContext().redirect("Inicio.xhtml");
        }catch(IOException e){
            
        }
        
    }
}
