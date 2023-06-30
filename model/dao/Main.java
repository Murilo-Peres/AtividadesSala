package model.dao;

import javax.swing.JOptionPane;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.Scanner;
import javax.swing.JTextArea;


public class Main {
	
	public static String leString (String mensagem) {
		String valor = JOptionPane.showInputDialog(null, mensagem);
		return valor;
		
	}
	
	public static int menu() {
		Scanner input = new Scanner(System.in);
		System.out.println("MENU PESSOA");
		System.out.println("1- Inserir");
		System.out.println("2- Listar todos");
		System.out.println("3- Listar por id");
		System.out.println("4- Excluir por id");
		System.out.println("5- Atualizar");
		System.out.println("6- Sair");
		System.out.print("Digite: ");
		return input.nextInt();
	}
	
	public static void metodoInserir() {
		String nome = leString("Digite o nome: ");
		String email = leString("Digite o email: ");
		Pessoa pessoa01 = new Pessoa(nome, email);
		PessoaDAO pessoaDAO01 = new PessoaDAO();
		pessoaDAO01.inserir(pessoa01);
	}
	
	public static void metodoExcluir() {
		String strId = leString("Digite o id: ");
		int id = Integer.parseInt(strId);
		PessoaDAO pessoaDAO = new PessoaDAO();
		Pessoa pessoa = pessoaDAO.excluir(id);
		if(pessoa.getId()!= 0) {			
			System.out.println("Usuário " + pessoa.getNome() + " deletado.");
	}
		
}
		
		
		public static void metodoAtualizar() {		
			String StrId = leString("Digite o id a ser atualizado: ");
			String nome = leString("Digite o nome: ");
			String email = leString("Digite o email: ");
			int id = Integer.parseInt(StrId);
			PessoaDAO pessoaDAO = new PessoaDAO();
			Pessoa pessoa = pessoaDAO.atualizar(id, nome, email);
			if(pessoa.getNome()!=null) {
				String texto = "☑ Usuário " + pessoa.getNome() + " atualizado com sucesso!!!.";
				JOptionPane.showMessageDialog(null, new JTextArea(texto));
			}else {
				String texto = "Registro não encontrado";
				JOptionPane.showMessageDialog(null, new JTextArea(texto));
			}
		}
		
		public static void metodoConsultarTodos() {
			PessoaDAO pessoaDAO01 = new PessoaDAO();
			List<Pessoa> listaPessoas = pessoaDAO01.consultarTodos();
			if(!listaPessoas.isEmpty()) {
				String saida = "";
				saida += "Id\t"+"Nome\t"+"Email\n";
			for(Pessoa p : listaPessoas) {
				saida += p.getId()+"\t";
				saida += p.getNome()+"\t";
				saida += p.getEmail() + "\n";
				}
			JOptionPane.showMessageDialog(null, new JTextArea(saida));
				}else{
					System.out.println("Não tem registros!!!");
			}
		}

	public static void main(String[] args) {
		int op;
		do {
			op = menu();
		switch (op) {
		case 1: {
			metodoInserir();
			break;
		}
		case 2: {
			metodoConsultarTodos();
			break;
		}
		case 3: {
			String idStr = leString("Digite id: ");
			int id = Integer.parseInt(idStr);
			PessoaDAO dao = new PessoaDAO();
			Pessoa pess = dao.consultar(id);
			String saida;
			if (pess != null) {
				saida = "id\tnome\temail\n";
				saida += pess.getId() + "\t";
				saida = saida + pess.getNome() + "\t";
				saida += pess.getEmail() + "\n";
			}else {
				saida = "Registro não foi encontrado";
			}
			JOptionPane.showMessageDialog(null, new JTextArea(saida));
			
			break;
		}
		
		case 4:{
			metodoExcluir();
			break;
		}
		case 5:{
			metodoAtualizar();
			break;
		}
		case 6: {
			System.out.println("Saindo...");
			break;
		}
		
		default:
			System.out.println("Opção inválida!!!");
		}
		
		}while(op!=6);

	}

}
