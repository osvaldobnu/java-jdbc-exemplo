package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import DB.BancoDados;
import model.Pessoa;

public class PessoaDao {
	
	public List<Pessoa> getPessoas() {
		Connection conexao = null;
		Statement st = null;
		ResultSet rs = null;
		
		List<Pessoa> pessoas = new ArrayList();
		
		conexao = BancoDados.getConexao();
		try {
			//  Criando meu espaço pra executar meus comandos em SQL
			st = conexao.createStatement();
			
			rs = st.executeQuery("SELECT * FROM pessoa");
			
			while(rs.next()) {
				Pessoa pessoa = new Pessoa();
				
				pessoa.setNome(rs.getString("nome"));
				pessoa.setEmail(rs.getString("email"));
				pessoa.setIdade(rs.getInt("idade"));
				
				pessoas.add(pessoa);
			}
			
			return pessoas;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public static void inserePessoa(Pessoa pessoa) {
		Connection conexao = null;
		
		// PreparedStatement -> Para executar querys de alteração (como insert)
		PreparedStatement st = null;
		
		try {
			conexao = BancoDados.getConexao();
			
			st = conexao.prepareStatement("INSERT INTO pessoa "
					+ "(nome, email, idade)"
					+ "VALUES"
					+ "(?, ?, ?)");
			
			st.setString(1, pessoa.getNome());
			st.setString(2, pessoa.getEmail());
			st.setInt(3, pessoa.getIdade());
			
			int linhasAlteradas = st.executeUpdate();
			
			if(linhasAlteradas > 0) {
				System.out.println("Registro Inserido com sucesso");
			} else {
				System.out.println("Nenhum registro inserido");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void atualizaPessoa(int id, String email, String nome) {
		Connection conexao = null;
		PreparedStatement st = null;
		
		try {
			conexao = BancoDados.getConexao();
			
			st = conexao.prepareStatement("UPDATE pessoa"
					+ "SET email = ?, nome = ?"
					+ " WHERE id = ?");
			
			st.setString(1, email);
			st.setInt(2, id);
			
			int linhasAlteradas = st.executeUpdate();
			
			if(linhasAlteradas > 0) {
				System.out.println("Update executado com sucesso");
			} else {
				System.out.println("Sem updates");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
