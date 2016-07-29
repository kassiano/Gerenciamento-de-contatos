package br.com.contatos.controller;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import br.com.contatos.helper.Alerta;
import br.com.contatos.helper.MySqlConnect;
import br.com.contatos.model.Contato;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;

public class ContatoController implements Initializable {

	@FXML TextField txtNome;
	@FXML TextField txtTelefone;
	@FXML Button btnInserir;
	@FXML ListView<Contato> lstContatos;

	boolean modoEdicao =false;
	Contato itemSelecionado=null;

	private void preencherLista(){

		lstContatos.getItems().clear();

		Connection con = MySqlConnect.ConectarDb();

		String sql = "select * from contact;";

		try {
			ResultSet rs = con.createStatement().executeQuery(sql);

			while(rs.next()){

				Contato c = new Contato();
				c.setNome(rs.getString("name"));
				c.setTelefone(rs.getString("phone"));
				c.setId(rs.getInt("id"));


				lstContatos.getItems().add(c);
			}

			con.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}

	@FXML public void inserirContato() {


		if(!modoEdicao){
			//inserir novo contato

			Contato novo = new Contato();
			novo.setNome( txtNome.getText());
			novo.setTelefone(txtTelefone.getText());

			boolean sucesso = Contato.inserir(novo);

			if(sucesso){
				Alerta.showInformation("sucesso", "Inserido com sucesso");
				limpar();
			}else{
				Alerta.showError("Erro", "Ocorreu um erro, tente novamente.");
			}

		}else{
			//editar contato

			Contato atualizado = new Contato();
			atualizado.setNome( txtNome.getText());
			atualizado.setTelefone(txtTelefone.getText());
			atualizado.setId(itemSelecionado.getId());

			boolean sucesso = Contato.atualizar(atualizado);

			if(sucesso){
				Alerta.showInformation("Sucesso", "Atualizado com sucesso");
				limpar();
				modoEdicao=false;
			}else{
				Alerta.showError("Erro", "Ocorreu um erro, tente novamente.");
			}

		}

		preencherLista();

	}

	private void limpar(){
		txtNome.setText("");
		txtTelefone.setText("");
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {


		//metodo para prencher a lista
		preencherLista();

	}

	@FXML public void atualizar() {

	    itemSelecionado = lstContatos.getSelectionModel().getSelectedItem();

		txtNome.setText(itemSelecionado.getNome());
		txtTelefone.setText(itemSelecionado.getTelefone());

		modoEdicao = true;
		btnInserir.setText("Salvar");

	}

	@FXML public void deletar() {


		Contato c = lstContatos.getSelectionModel().getSelectedItem();

		Connection con = MySqlConnect.ConectarDb();

		String sql ="delete from contact where id = ?;";

		PreparedStatement parametros;

		try {
			parametros = con.prepareStatement(sql);
			parametros.setInt(1, c.getId());
			parametros.executeUpdate();
			con.close();

			Alerta.showInformation("sucesso", "Deletado com sucesso");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		preencherLista();
	}




}
