package Controles;

import java.io.IOException;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;

import BaseDatos.DaoLogin;
import Clases.Principal;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.text.Text;

public class ControlLogin {
	private DaoLogin consultador;
   
	@FXML
    private Text mensajeAdvertencia;
    @FXML
    private JFXTextField campoUsuarioLogin;
    @FXML
    private JFXPasswordField campoPasswordLogin;
    @FXML
    private JFXButton botonLogin;

    public void procesarUsuario() {
    	String usuario = campoUsuarioLogin.getText();
    	String perfil = "";
    	if(usuario.equals("")) {
    		mensajeAdvertencia.setText("Introduzca un usuario");
    	}else {
    		consultador = new DaoLogin();
    		if(consultador.consultarUsuario(usuario)) {
    			perfil = consultador.consultarContrasena(campoUsuarioLogin.getText(), campoPasswordLogin.getText());	
		         if(!perfil.equals("")){
		        	 if (perfil.equals("Gerente")) {
		    			try {
							Principal.iniciarGerente(campoUsuarioLogin.getText(), perfil);
						} catch (IOException e) {
							e.printStackTrace();
						}
		        	 }else if(perfil.equals("Vendedor")){
		        		 try {
							Principal.iniciarVendedor(campoUsuarioLogin.getText(), perfil);
						} catch (IOException e) {
							e.printStackTrace();
						}
		        	 }else if(perfil.equals("Jefe de taller")) {
		        		 try {
								Principal.iniciarJefeTaller(campoUsuarioLogin.getText(), perfil);
							} catch (IOException e) {
								e.printStackTrace();
							}
		        	 }
		        	 else if(perfil.equals("Administrador")) {
		        		 try {
								Principal.iniciarAdministrador(campoUsuarioLogin.getText(), perfil);
							} catch (IOException e) {
								e.printStackTrace();
							}
		        	 }
		        	 else {
		        		 Alert alert = new Alert(AlertType.WARNING);
		        		 alert.setTitle("Advertencia");
	        			 alert.setHeaderText(null);
	        			 alert.setContentText("Usted no esta habilitado para ingresar");
	        			 alert.showAndWait();
	        			 mensajeAdvertencia.setText("");
		        	 }
    			}else{
    				mensajeAdvertencia.setText("La contraseņa es incorrecta");
    			}
    		}else {
    			mensajeAdvertencia.setText("Usuario no valido");
    		}    		
    	}
    }
    
    @FXML
    public void onEnter(ActionEvent e){
    	procesarUsuario();
    }
    
    @FXML
    void consultarUsuario(ActionEvent event) {
    	procesarUsuario();
    }
}
