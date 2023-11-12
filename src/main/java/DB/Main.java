package DB;

import java.util.List;
import DAO.PessoaDao;
import model.Pessoa;

public class Main {

	public static void main(String[] args) {
		PessoaDao dao = new PessoaDao();
		
		dao.atualizaPessoa(3, "rafaelamonteroxo@gmail.com");
		
		/*List<Pessoa> pessoas = dao.getPessoas();
		
		for(Pessoa pessoa : pessoas) {
			System.out.println("Nome da pessoa: " + pessoa.getNome());
		}*/
	}
}
