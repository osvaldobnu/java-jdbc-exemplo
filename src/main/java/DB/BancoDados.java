package DB;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

import javax.management.RuntimeErrorException;

public class BancoDados {
	private static Connection conexao = null;
	
	public static Connection getConexao() {
		if(conexao == null) {
			try {
				Properties props = carregaProperties();
				
				String url = props.getProperty("dburl") + "?useSSL=false&allowPublicKeyRetrieval=true";
				
				conexao = DriverManager.getConnection(url, props);
				
				System.out.println("Conectado no banco com sucesso");
			} catch (Exception e) {
				throw new RuntimeErrorException(null, e.getMessage());
			}
		}
		
		return conexao;
	}
	
	@SuppressWarnings("unused")
	private static Properties carregaProperties() {
		try (FileInputStream file = new FileInputStream("db.properties")) {
			// FileInputStream -> Objeto usado para ler arquivos
			Properties props = new Properties();
			
			// Properties -> Objeto para ler propriedades de um arquivo
			props.load(file);
			
			// props.load -> Utilizando a biblioteca props para ler um arquivo
			
			return props;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public static void fechaConexao() {
		if(conexao != null) {
			try {
				conexao.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
