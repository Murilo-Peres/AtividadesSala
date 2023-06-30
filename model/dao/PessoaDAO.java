package model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class PessoaDAO {
    // CRUD
    // C - create | R - retrieve | U - update | D - delete
    public void inserir(Pessoa pessoa){
        ConectaBD con = new ConectaBD();
        String sql = "INSERT INTO pessoa (nome, email) VALUES (?,?)";
        try{
            PreparedStatement pst = con.getConexao().prepareStatement(sql);
            pst.setString(1, pessoa.getNome());
            pst.setString(2, pessoa.getEmail());
            pst.execute();
            System.out.println(pessoa.getNome() + " inserido");
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    /**
     * Metodo que consulta registro do tipo pessoa por id
     * @param id - refere-se a chave primaria do registro
     * @return um objeto do tipo Pessoa, caso nao encontre o registro retorna null
     */
    public Pessoa consultar(int id){
        ConectaBD con = new ConectaBD();
        String sql = "SELECT * FROM pessoa WHERE id = ?";
        Pessoa p = null;
        try {
        	
            PreparedStatement pst = con.getConexao().prepareStatement(sql);
            pst.setInt(1, id);
            ResultSet rs =  pst.executeQuery();
            if (rs.next()){
                String nome = rs.getString("nome");
                String email = rs.getString("email");
                p = new Pessoa(nome, email);
                p.setId(rs.getInt("id"));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return p;
    }

    public List<Pessoa> consultarTodos(){
        ConectaBD con = new ConectaBD();
        String sql = "SELECT * FROM pessoa";
        List<Pessoa> lista = new LinkedList<Pessoa>();
        try {
            PreparedStatement pst = con.getConexao().prepareStatement(sql);
            ResultSet rs = pst.executeQuery();

            Pessoa pessoa;
			while(rs.next()) {
				pessoa = new Pessoa();
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                String email = rs.getString("email");
                pessoa.setId(id);
                pessoa.setNome(nome);
                pessoa.setEmail(email);
                lista.add(pessoa);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return lista;
    }

    private Pessoa Pessoa() {
		// TODO Auto-generated method stub
		return null;
	}
    
    public Pessoa excluir(int id) {
    	PessoaDAO pessoaDAO = new PessoaDAO();
    	ConectaBD conexao = new ConectaBD();
    	String sql = "DELETE FROM pessoa WHERE id= ?;";
    	Pessoa p = pessoaDAO.consultar(id);
    	if (p.getId()!= 0) {
    		
    		try{
    			PreparedStatement frase = conexao.getConexao().prepareStatement(sql);
    			frase.setInt(1, id);
    			frase.execute();
    			System.out.println("Registro deletado com sucesso!!!");
    		}catch (SQLException e) {
    			System.out.println(e.getMessage());
    		}
    	} return p;		
    }
    
    
	
	public Pessoa atualizar(int id,String nome, String email) {
		PessoaDAO pessoaDAO = new PessoaDAO();
		ConectaBD conexao = new ConectaBD();
		String sql = "UPDATE pessoa SET nome = ?, email = ? WHERE id= ?;";
		Pessoa p = pessoaDAO.consultar(id);
		if (p.getId()!= 0) {
    		try{
    			PreparedStatement frase = conexao.getConexao().prepareStatement(sql);
    			frase.setString(1, nome);
    			frase.setString(2, email);
    			frase.setInt(3, id);
    			frase.execute();
    			System.out.println("Registro atualizado com sucesso!!!");
    		}catch (SQLException e) {
    			System.out.println(e.getMessage());
    		}
			
		}
		return p;
			
	}

}













