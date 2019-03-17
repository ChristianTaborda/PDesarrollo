package Controles;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class ControlMenuInicial {

    @FXML
    private Label info;
    
    public void iniciar(String cargo) {
    	
    	String mensaje = "Como " + cargo + " usted puede:\n";
    	
    	switch(cargo) {
    		case "Gerente":
    			mensaje += "\n\t- Gestionar la informaci�n de los usuarios vendedores.\n";
    	    	mensaje += "\n\t- Gestionar la informaci�n de los usuarios jefes de taller.\n";
    	    	mensaje += "\n\t- Gestionar la informaci�n de los �tems del inventario.\n";
    	    	mensaje += "\n\t- Visualizar los reportes generales de la empresa.\n";
    	    	break;
    		case "Vendedor":
    			mensaje += "\n\t- Gestionar la informaci�n de las ventas de la empresa.\n";
    	    	mensaje += "\n\t- Gestionar la informaci�n de las cotizaciones de la empresa.\n";
    	    	break;
    		case "Jefe de taller":
    			mensaje += "\n\t- Gestionar la informaci�n de las �rdenes de trabajo de la empresa.\n";
    	    	break;
    		case "Administrador":
    			mensaje += "\n\t- Gestionar la informaci�n de los usuarios gerentes.\n";
    	    	break;
    	}
    	    	
    	info.setText(mensaje);
    	
    }

}
