package Clases;

import com.jfoenix.controls.JFXTextField;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class Validador {
	
	public void mostrarMensaje(int tipo, String mensaje, String titulo) {
		
		Alert alerta = null;
	    	
	    switch(tipo) {
	    	case 1:
	    		alerta = new Alert(AlertType.INFORMATION);
	    		break;
	    	case 2:
	    			alerta = new Alert(AlertType.WARNING);
	    			break;
	    }
	    	
	    alerta.setTitle(titulo);
	    alerta.setHeaderText(null);
	    alerta.setContentText(mensaje);
	    alerta.showAndWait();
	    	
	}
    
    public int contarArroba(String prueba) {
    	int arroba = 0;
    	for(int x=0; x<prueba.length(); x++) {
    		if(prueba.charAt(x) == 64) {
    			arroba++;
    		}
    	}
    	return arroba;
    }
    
    public boolean validarCorreo(String prueba) {
    	
    	boolean salida = true;
    	
    	if(contarArroba(prueba) != 1) {
    		salida = false;
    	}
    	
    	return salida;
    	
    }
    	
    public boolean validarCaracteres(String prueba, int tipo) {
    	
    	boolean salida = true;
  	
    	for(int x=0; x<prueba.length(); x++) {
			switch(tipo) {
				case 1:
					if(prueba.charAt(x) < 48 || prueba.charAt(x) > 57) {
            			salida = false;
            		}
					break;
				case 2:
					if(prueba.charAt(x) < 65 || (prueba.charAt(x) > 90 && prueba.charAt(x) < 97) || prueba.charAt(x) > 122) {
            			salida = false;
            		}
					break;
			}
		}
    	
    	return salida;		
    	
    }
    
    public boolean validarCampo(JFXTextField T, int longitud, String campo, int tipo) {
    	
    	boolean salida = true;
    	String prueba = T.getText();
    	
    	if(prueba.compareTo("") == 0) {
    		salida = false;
    		mostrarMensaje(2, "El campo " + campo + " est� vac�o", "Informaci�n incompleta");
    	}else {
    		if(prueba.length() > longitud) {
    			salida = false;
    			mostrarMensaje(2, "El campo " + campo + " debe tener m�ximo " + longitud + " caracteres", "Informaci�n inv�lida");
    		}else {
    			if(tipo == 3) {
    				salida = validarCorreo(prueba);
    			}else {
    				salida = validarCaracteres(prueba, tipo);
    			}
    			if(!salida) {
        			if(tipo == 3) {
        				mostrarMensaje(2, "El campo " + campo + " contiene una direcci�n de correo inv�lida", "Informaci�n inv�lida");
        			}else {
        				mostrarMensaje(2, "El campo " + campo + " contiene caracteres inv�lidos", "Informaci�n inv�lida");
        			}        			
        		}
    		}    		
    	}
    	
    	return salida;    	
    	
    }

    public boolean validarID(JFXTextField entrada, String campo) {
    	
    	boolean salida = true;
    	String prueba = entrada.getText();
    	
    	if(prueba.compareTo("") == 0) {
    		salida = false;
    		mostrarMensaje(2, "El campo " + campo + " est� vac�o", "Informaci�n incompleta");
    	}else {
    		for(int x=0; x<prueba.length(); x++) {
    			if(prueba.charAt(x) < 48 || prueba.charAt(x) > 57) {
    				if(prueba.charAt(x) < 65 || (prueba.charAt(x) > 90 && prueba.charAt(x) < 97) || prueba.charAt(x) > 122) {
    					salida = false;
    					mostrarMensaje(2, "El campo de b�squeda contiene caracteres inv�lidos", "Informaci�n incompleta");
    				}
    			}
    		}
    	}
    	
    	return salida;    
    	
    }
    
}
    	
    
    		
    	


