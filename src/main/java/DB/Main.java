package DB;

import java.util.List;
import DAO.PessoaDao;
import model.Pessoa;

public class Main {

	public static void main(String[] args) {
		PessoaDao dao = new PessoaDao();
		
		Pessoa p = new Pessoa();
		
		p.setNome("Danilo Gentili");
		p.setEmail("daninho@montinegro.com.br");
		p.setIdade(58);
		
		try {
			dao.inserePessoa(p);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		List<Pessoa> pessoas = dao.getPessoas();
		
		for(Pessoa pessoa : pessoas) {
			System.out.println("Nome da pessoa: " + pessoa.getNome());
		}
	}
}
