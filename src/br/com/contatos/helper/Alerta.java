package br.com.contatos.helper;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class Alerta {

	
	public static void showWarning(String titulo,
			String conteudo){
		Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle(titulo);
		alert.setContentText(conteudo);
		alert.show();
	}
	
	public static void showError(String titulo,
			String conteudo){
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle(titulo);
		alert.setContentText(conteudo);
		alert.show();
	}
	
	public static void showInformation(String titulo,
			String conteudo){
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle(titulo);
		alert.setContentText(conteudo);
		alert.show();
	}

}
