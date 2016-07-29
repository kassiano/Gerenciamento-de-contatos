package br.com.contatos.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import br.com.contatos.helper.Alerta;
import br.com.contatos.helper.MySqlConnect;

public class Contato {

	private int id;
	private String nome;
	private String telefone;

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return nome;
	}

	public static boolean inserir(Contato contato){

		Connection con = MySqlConnect.ConectarDb();

		String sql ="insert into contact (name, phone) values( ?, ?);";

		PreparedStatement parametros;

		try {
			parametros = con.prepareStatement(sql);
			parametros.setString(1, contato.getNome());
			parametros.setString(2, contato.getTelefone());

			parametros.executeUpdate();
			con.close();

			return true;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

			return false;
		}
	}

	public static boolean atualizar(Contato contato){

		Connection con = MySqlConnect.ConectarDb();

		String sql ="update contact set name =?, phone=? where id = ?;";

		PreparedStatement parametros;

		try {
			parametros = con.prepareStatement(sql);
			parametros.setString(1, contato.getNome() );
			parametros.setString(2, contato.getTelefone() );
			parametros.setInt(3, contato.getId());
			parametros.executeUpdate();
			con.close();

			return true;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;

		}

	}



}
