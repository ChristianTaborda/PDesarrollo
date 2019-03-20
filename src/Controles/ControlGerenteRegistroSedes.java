package Controles;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import BaseDatos.DaoSede;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.text.Text;
import javafx.scene.control.Alert.AlertType;
import javafx.util.Duration;
public class ControlGerenteRegistroSedes {
	private DaoSede sede;
	
    @FXML private JFXTextField campoNombre;
    @FXML private JFXTextField campoTelefono;
    @FXML private JFXTextField campoDireccion;
    @FXML private JFXTextField campoTamano;
    @FXML private JFXTextField campoEmpleados;
    @FXML private DatePicker fechaRegistroSedes;
    
    @FXML private JFXButton botonRegistroSedes;

    @FXML private Text errorTelefono;
    @FXML private Text errorEmpleados;
    @FXML private Text errorFecha;
    @FXML private Text errorNombre;
    @FXML private Text errorDireccion;
    @FXML private Text errorTamano;
    
    public void initialize() {    	
    	sede = new DaoSede();
    	campoNombre.setOnKeyPressed((e) -> {
			errorNombre.setText("");
		});
    	campoTelefono.setOnKeyPressed((e) -> {
			errorTelefono.setText("");
		});
    	campoDireccion.setOnKeyPressed((e) -> {
    		errorDireccion.setText("");
		});		
    	campoTamano.setOnKeyPressed((e) -> {
    		errorTamano.setText("");
		});
    	campoEmpleados.setOnKeyPressed((e) -> {
    		errorEmpleados.setText("");
		});	
    	fechaRegistroSedes.valueProperty().addListener((ov, oldValue, newValue) -> {
    		errorFecha.setText("");
         });
}
    
    public boolean verificarCampos() {
		String nombre = campoNombre.getText();
		String telefono = campoTelefono.getText();
    	String direccion = campoDireccion.getText();
    	String tamano = campoTamano.getText();
    	String empleados = campoEmpleados.getText();
    	LocalDate localDate = fechaRegistroSedes.getValue();
    	String fecha = "";
		int valido = 0;
		if(nombre.replace(" ", "").equals("")) {
			errorNombre.setText(("El campo Nombre esta vacio"));
   		 	return false;
		}
		if(nombre.length()>50) {
			errorNombre.setText(("El nombre es muy largo"));
			return false;
		}		
		
    	if(telefono.replace(" ", "").equals("")) {
    		errorTelefono.setText(("El campo Telefono esta vacio"));
       		 return false;
    	}else {
    		if(telefono.matches("[0-9]*")) {
    			if(telefono.length()<6 || telefono.length()>10) {
    				errorTelefono.setText(("El Telefono tener entre 6 y 10 n�meros"));
        			return false;
    			}
    		}else {
    			errorTelefono.setText(("El Telefono debe ser numeros"));    			
    			return false;
    			}
    	}
    	if(direccion.replace(" ", "").equals("")) {
    		errorDireccion.setText(("El campo Direccion esta vacio"));
   		 	return false;
    	} 
    	if(direccion.length()>50) {
    		errorDireccion.setText(("La direccion es muy larga"));
			return false;
		}	
    	if(tamano.equals("")) {
    		errorTamano.setText(("El campo Tama�o esta vacio"));
   		 	return false;
    	}else {
    		if(tamano.matches("[0-9]*")) {	    		
    		}else {
    			errorTamano.setText(("El campo tama�o debe ser numerico"));
       		 	return false;
    		}
    	}
    	if(empleados.replace(" ", "").equals("")) {
    		errorEmpleados.setText(("El campo Empleados esta vacio"));
   		 	return false;
    	}else {
    		if(empleados.matches("[0-9]*")) { 		    		
    		}else {
    			errorEmpleados.setText(("El campo Empleados debe ser solo numeros"));
       		 	return false;
    		}
    	}
    	if(localDate == null) {
    		errorFecha.setText(("El campo Fecha esta vacio"));
   		 	return false;
    	}else {
        	DateTimeFormatter pattern = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        	fecha = localDate.format(pattern);
    	}

    	
		valido = sede.crearSede(nombre, telefono, direccion, tamano, empleados, fecha);
    	if(valido == -1) {
    		mostrarMensaje("Error", "Error: La sede ya esta registrada");
    		return false;
    	}else {
    		Alert alert = new Alert(AlertType.INFORMATION);
    		alert.setTitle("Confirmacion");
    		alert.setHeaderText(null);
    		alert.setContentText("La sede se registro con exito");
    		alert.showAndWait();
    		return true;
    	}
    }
    
    public void mostrarMensaje(String titulo, String mensaje) {
    	Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle(titulo);
		alert.setHeaderText(null);
		alert.setContentText(mensaje);
		alert.showAndWait();
    }
    
    
    @FXML
    void registrarSede(ActionEvent event) {
    	if(verificarCampos()) {
    		campoNombre.setText("");
    		campoTelefono.setText("");
        	campoDireccion.setText("");
        	campoTamano.setText("");
        	campoEmpleados.setText("");
        	fechaRegistroSedes.setValue(null);
    	} 
    } 

}
