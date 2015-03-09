package ec.com.webapplication.managed.bean;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import ec.com.webapplication.globals.VariablesGlobales.Estado;
import ec.com.webapplication.model.Login;
import ec.com.webapplication.service.ILoginService;
import ec.com.webapplication.security.FacesUtils;

/**
 *
 * @author Alexi
 */
@ManagedBean(name="loginBean")
@RequestScoped
public class LoginBean implements Serializable{  

    /**
	 * 
	 */
	private static final long serialVersionUID = -7883301876834795924L;
	
 	//Spring Login Service es inyectado...
	@ManagedProperty(value="#{LoginService}")
    ILoginService loginService;
	Login login;
	FacesUtils facesUtils;
	
    private String user;
	private String password;
	private Estado estado = Estado.ACTIVO;

    public String getUser() {  
        return user;  
    }  

    public void setUser(String user) {  
        this.user = user;  
    }  

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public String getPassword() {  
        return password;  
    }  

    public void setPassword(String password) {  
        this.password = password;  
    }  
    
    public ILoginService getLoginService() {
		return loginService;
	}

	public void setLoginService(ILoginService loginService) {
		this.loginService = loginService;
	}

	public Login getLogin() {
		login = new Login();
		login = getLoginService().getByUser(user, password);
		return login;
	}

	public void setLogin(Login login) {
		this.login = login;
	}  
	
	public void login() {   
		FacesContext context = FacesContext.getCurrentInstance();
        try{
        	this.getLogin();
        	if (login.getUsuario().equals(this.getUser())){
        		//facesUtils.setSessionMapValue(context,"USUARIO",user);
        		context.getExternalContext().getSessionMap().put("USUARIO", this.getUser());
        		context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Inciando sesión",this.getUser()));
        		context.getExternalContext().redirect("pages/principal.jsf");
	        } else {   
	        	context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Error al iniciar sesión", "Credenciales Incorrectas"));
	        }  
        }catch(Exception e){
        	context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, e.toString(),"¡Error!" ));
        }
    }
	
	 public void logout() {  
		 FacesMessage msg = null;
		 FacesContext context = FacesContext.getCurrentInstance();
     	try{
     		//facesUtils.removeSessionMapValue(context, "USUARIO");
     		context.getExternalContext().getSessionMap().remove("USUARIO");
     		msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cerrando sesión", this.getUser());
     		context.getExternalContext().redirect("/webapplication/");
     	}catch(Exception e){
        	msg = new FacesMessage(FacesMessage.SEVERITY_WARN, e.toString(), "¡Error!");
        }
     	context.addMessage(null, msg);
     }
	 
	 public void verificarSesion(){
		 FacesContext context = FacesContext.getCurrentInstance();
		 try{
			 if (context.getExternalContext().getSessionMap().get("USUARIO")==null){
				 context.getExternalContext().redirect("/webapplication/");
			 }
		 }catch(Exception e){
        	 context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, e.toString(),"¡Error!" ));
         }
	 }
	 
	 public void verificarSesionLogin(){
		 FacesContext context = FacesContext.getCurrentInstance();
		 try{
			 if (context.getExternalContext().getSessionMap().get("USUARIO")!=null){
				 context.getExternalContext().redirect("pages/principal.jsf");
			 }
		 }catch(Exception e){
        	 context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, e.toString(),"¡Error!" ));
         }
	 }
}