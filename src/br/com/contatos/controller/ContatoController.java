package br.com.contatos.controller;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import br.com.contatos.helper.MySqlConnect;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;

public class ContatoController implements Initializable {

	@FXML TextField txtNome;
	@FXML TextField txtTelefone;
	@FXML Button btnInserir;
	@FXML ListView lstContatos;
	

	private void preencherLista(){

		lstContatos.getItems().clear();

		Connection con = MySqlConnect.ConectarDb();

		String sql = "select * from contact;";

		try {
			ResultSet rs = con.createStatement().executeQuery(sql);

			while(rs.next()){

				String nome = rs.getString("name");
				String telefone = rs.getString("phone");

				lstContatos.getItems().add(nome + " - " + telefone);
			}

			con.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}

	@FXML public void inserirContato() {


		Connection con = MySqlConnect.ConectarDb();

		String sql ="insert into contact (name, phone) values( ?, ?);";

		PreparedStatement parametros;

		try {
			parametros = con.prepareStatement(sql);
			parametros.setString(1, txtNome.getText());
			parametros.setString(2, txtTelefone.getText());

			parametros.executeUpdate();
			con.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		preencherLista();

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		preencherLista();
	}



}
