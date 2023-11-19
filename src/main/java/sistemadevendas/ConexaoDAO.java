package sistemadevendas;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

public class ConexaoDAO {
	
	public static int retornaIdSequenUsuarios() {
	    Connection conexao = null;
	    String id = "";
	    
	    
	    try{
	        Class.forName("com.mysql.cj.jdbc.Driver");
	        conexao = DriverManager.getConnection("jdbc:mysql://localhost/bancomissao2", "root", "marcosph");
	        ResultSet rsUsuarios = conexao.createStatement().executeQuery("select * from id_sequenusuarios");
	        while(rsUsuarios.next()) {
	        	id = rsUsuarios.getString("id");
	        }
	        int id2 = Integer.parseInt(id);
	        id2++;
	        String id3 = "" + id2;
	        String str = "update id_sequenusuarios set id =" + id3;
	        Statement s = conexao.createStatement();
            s.addBatch(str);
            s.executeBatch();
	    }catch(ClassNotFoundException e){
	        System.out.println("Driver do banco de dados nao localizado");
	    }catch(SQLException s){
	       s.printStackTrace();
	    }

	    return Integer.parseInt(id);
	}
	
	public static int retornaIdSequenProdutos() {
	    Connection conexao = null;
	    String id = "";
	    
	    try{
	        Class.forName("com.mysql.cj.jdbc.Driver");
	        conexao = DriverManager.getConnection("jdbc:mysql://localhost/bancomissao2", "root", "marcosph");
	        ResultSet rsProduto = conexao.createStatement().executeQuery("select * from id_sequenprodutos");
	        while(rsProduto.next()) {
	        	id = rsProduto.getString("id");
	        }
	        int id2 = Integer.parseInt(id);
	        id2++;
	        String id3 = "" + id2;
	        String str = "update id_sequenprodutos set id =" + id3;
	        Statement s = conexao.createStatement();
            s.addBatch(str);
            s.executeBatch();
	    }catch(ClassNotFoundException e){
	        System.out.println("Driver do banco de dados não localizado");
	    }catch(SQLException s){
	       s.printStackTrace();
	    }

	    return Integer.parseInt(id);
	}
	
	public static void cadastrarPessoaFisica(String nome, String localizacao, String contato, String cpf) {
	    Connection conexao = null;
	    
	    try{
	        Class.forName("com.mysql.cj.jdbc.Driver");
	        conexao = DriverManager.getConnection("jdbc:mysql://localhost/bancomissao2", "root", "marcosph");
	        Statement s = conexao.createStatement();
	        int id = retornaIdSequenUsuarios();
            String str = "insert into pessoa_fisica(id, nome, localizacao, contato, cpf) values (" + id + ",'" + nome + "','" + localizacao + "','" + contato + "','" + cpf + "')"; 
            s.addBatch(str);
            s.executeBatch();
	    }catch(ClassNotFoundException e){
	        System.out.println("Driver do banco de dados não localizado");
	    }catch(SQLException s){
	       s.printStackTrace();
	    }
	}
	
	public static void cadastrarPessoaJuridica(String nome, String localizacao, String contato, String cnpj) {
	    Connection conexao = null;
	    
	    try{
	        Class.forName("com.mysql.cj.jdbc.Driver");
	        conexao = DriverManager.getConnection("jdbc:mysql://localhost/bancomissao2", "root", "marcosph");
	        Statement s = conexao.createStatement();
	        int id = retornaIdSequenUsuarios();
            String str = "insert into pessoa_juridica(id, nome, localizacao, contato, cnpj) values (" + id + ",'" + nome + "','" + localizacao + "','" + contato + "','" + cnpj + "')"; 
            s.addBatch(str);
            s.executeBatch();
	    }catch(ClassNotFoundException e){
	        System.out.println("Driver do banco de dados não localizado");
	    }catch(SQLException s){
	       s.printStackTrace();
	    }
	}
	
	public static ArrayList<Produto> retornaProdutos() {
	    Connection conexao = null;
	    ArrayList<Produto> produtos = new ArrayList<Produto>();
	    String id = "";
        String nome = "";
        String qtd = "";
        String preco = "";
	    
	    try{
	        Class.forName("com.mysql.cj.jdbc.Driver");
	        conexao = DriverManager.getConnection("jdbc:mysql://localhost/bancomissao2", "root", "marcosph");
	        ResultSet rsProduto = conexao.createStatement().executeQuery("select * from produtos");
	        
	        while(rsProduto.next()){
	            id = rsProduto.getString("id");
	            nome = rsProduto.getString("nome");
	            qtd = rsProduto.getString("quantidade");
	            preco = rsProduto.getString("preco");
	            produtos.add(new Produto(Integer.parseInt(id), nome, Integer.parseInt(qtd), Float.parseFloat(preco)));
	        }
	    }catch(ClassNotFoundException e){
	        System.out.println("Driver do banco de dados não localizado");
	    }catch(SQLException s){
	       s.printStackTrace();
	    }
	
	    return produtos;
	}
	
	public static void compraProduto(String produto, int idFornecedor, int qtd) {
	    Connection conexao = null;
	    String id = "";
        String nome = "";
        String qtd1 = "";
        String preco = "";
	    
	    try{
	        Class.forName("com.mysql.cj.jdbc.Driver");
	        conexao = DriverManager.getConnection("jdbc:mysql://localhost/bancomissao2", "root", "marcosph");
	        ResultSet rsProduto = conexao.createStatement().executeQuery("select * from produtos");
	        boolean possui = false;
	        
	        while(rsProduto.next()){
	            id = rsProduto.getString("id");
	            nome = rsProduto.getString("nome");
	            qtd1 = rsProduto.getString("quantidade");
	            preco = rsProduto.getString("preco");
	            if(nome.equals(produto)) {
	            	possui = true;
	            	break;
	            }
	        }
	        
	        if(possui) {
	        	int qtd2 = 0;
	        	qtd2 = Integer.parseInt(qtd1);
	        	qtd2 += qtd;
	        	float valorTotal = qtd * Float.parseFloat(preco);
	        	Statement s = conexao.createStatement();
	        	int id2 = Integer.parseInt(id);
	        	String str = "update produtos set quantidade = " + qtd2 + " where id = " + id2;
	        	String str2 = "insert into movimentacoes_entrada(produto, id_fornecedor, quantidade, preco_unitario, valor_total) values ('" + produto + "'," + idFornecedor + "," +
	                    + qtd + "," + preco + "," + valorTotal + ")";
	        	s.addBatch(str);       	
	        	s.addBatch(str2);
	        	s.executeBatch();
	        } else {
	        	Statement s = conexao.createStatement();
	        	Scanner scan = new Scanner(System.in);
	        	int id2 = retornaIdSequenProdutos();
	        	System.out.print("DIGITE O PRECO DO PRODUTO: ");
	        	float preco2 = scan.nextFloat();
	        	//float preco3 = Float.parseFloat(preco2);
	        	float valorTotal = qtd * preco2;
	        	String str = "insert into produtos(id, nome, quantidade, preco) values ("+ id2 + ",'" +  produto + "'," + qtd + "," + preco2 + ")";
	        	String str2 = "insert into movimentacoes_entrada(produto, id_fornecedor, quantidade, preco_unitario, valor_total) values ('" + produto + "'," + idFornecedor + "," +
	                    + qtd + "," + preco2 + "," + valorTotal + ")";
	        	s.addBatch(str);
	        	s.addBatch(str2);
	        	s.executeBatch();
	        }
	    }catch(ClassNotFoundException e){
	        System.out.println("Driver do banco de dados não localizado");
	    }catch(SQLException s){
	       s.printStackTrace();
	    }
	}
	
	public static void vendeProduto(String produto, int idComprador, int qtd) throws ExceptionVendaProduto{
	    Connection conexao = null;
	    String id = "";
        String nome = "";
        String qtd1 = "";
        String preco = "";
	    
	    try{
	        Class.forName("com.mysql.cj.jdbc.Driver");
	        conexao = DriverManager.getConnection("jdbc:mysql://localhost/bancomissao2", "root", "marcosph");
	        ResultSet rsProduto = conexao.createStatement().executeQuery("select * from produtos");
	        
	        while(rsProduto.next()){
	            id = rsProduto.getString("id");
	            nome = rsProduto.getString("nome");
	            qtd1 = rsProduto.getString("quantidade");
	            preco = rsProduto.getString("preco");
	            if(nome.equals(produto)) {
	            	break;
	            }
	        }
	        int qtd2 = Integer.parseInt(qtd1);
	        if(qtd > qtd2) {
	        	throw new ExceptionVendaProduto();
	        }
	        float preco2 = Float.parseFloat(preco);
	        float valorTotal = qtd * preco2;
	        qtd2 -= qtd; 
	        Statement s = conexao.createStatement();
            String str = "update produtos set quantidade = " + qtd2 + " where id = " + Integer.parseInt(id);
            String str2 = "insert into movimentacoes_saida(produto, id_vendedor, quantidade, preco_unitario, valor_total) values ('" + produto + "'," + idComprador + "," +
                    + qtd + "," + preco + "," + valorTotal + ")";
            s.addBatch(str);
            s.executeBatch();
            s.addBatch(str2);
            s.executeBatch();
	    }catch(ClassNotFoundException e){
	        System.out.println("Driver do banco de dados não localizado");
	    }catch(SQLException s){
	       s.printStackTrace();
	    }
	}
	
	public static ArrayList<PessoaFisica> retornaPessoaFisica() {
			ArrayList<PessoaFisica> pessoasFisica = new ArrayList<PessoaFisica>();
		    Connection conexao = null;
		    String id = "";
		    String nome = "";
		    String localizacao = "";
	        String contato = "";
	        String cpf = "";
		    
		    try{
		        Class.forName("com.mysql.cj.jdbc.Driver");
		        conexao = DriverManager.getConnection("jdbc:mysql://localhost/bancomissao2", "root", "marcosph");
		        ResultSet rsPessoaFisica = conexao.createStatement().executeQuery("select * from pessoa_fisica");
		        
		        while(rsPessoaFisica.next()){
		        	id = rsPessoaFisica.getString("id");
		        	nome = rsPessoaFisica.getString("nome");
		            localizacao = rsPessoaFisica.getString("localizacao");
		            contato = rsPessoaFisica.getString("contato");
		            cpf = rsPessoaFisica.getString("cpf");
		            pessoasFisica.add(new PessoaFisica(Integer.parseInt(id), nome, localizacao, contato, cpf));
		        }
		    }catch(ClassNotFoundException e){
		        System.out.println("Driver do banco de dados não localizado");
		    }catch(SQLException s){
		       s.printStackTrace();
		    }
		
		    return pessoasFisica;
		}

		public static ArrayList<PessoaJuridica> retornaPessoaJuridica() {
			ArrayList<PessoaJuridica> pessoasJuridica = new ArrayList<PessoaJuridica>();
		    Connection conexao = null;
		    String id = "";
		    String nome = "";
		    String localizacao = "";
		    String contato = "";
		    String cnpj = "";
		    
		    try{
		        Class.forName("com.mysql.cj.jdbc.Driver");
		        conexao = DriverManager.getConnection("jdbc:mysql://localhost/bancomissao2", "root", "marcosph");
		        ResultSet rsPessoaJuridica = conexao.createStatement().executeQuery("select * from pessoa_juridica");
		        
		        while(rsPessoaJuridica.next()){
		        	id = rsPessoaJuridica.getString("id");
		        	nome = rsPessoaJuridica.getString("nome");
		            localizacao = rsPessoaJuridica.getString("localizacao");
		            contato = rsPessoaJuridica.getString("contato");
		            cnpj = rsPessoaJuridica.getString("cnpj");
		            pessoasJuridica.add(new PessoaJuridica(Integer.parseInt(id), nome, localizacao, contato, cnpj));
		        }
		    }catch(ClassNotFoundException e){
		        System.out.println("Driver do banco de dados não localizado");
		    }catch(SQLException s){
		       s.printStackTrace();
		    }
		
		    return pessoasJuridica;
		}
		
		public static ArrayList<MovimentacoesEntrada> retornaMovimentacoesEntrada() {
			ArrayList<MovimentacoesEntrada> movimentacoesEntrada = new ArrayList<MovimentacoesEntrada>();
		    Connection conexao = null;
		    String produto = "";
		    String idFornecedor = "";
		    String qtd = "";
		    String precoUnitario = "";
		    String valorTotal = "";
		    
		    try{
		        Class.forName("com.mysql.cj.jdbc.Driver");
		        conexao = DriverManager.getConnection("jdbc:mysql://localhost/bancomissao2", "root", "marcosph");
		        ResultSet rsMovimentacoesEntrada = conexao.createStatement().executeQuery("select * from movimentacoes_entrada");
		        
		        while(rsMovimentacoesEntrada.next()){
		            produto = rsMovimentacoesEntrada.getString("produto");
		            idFornecedor = rsMovimentacoesEntrada.getString("id_fornecedor");
		            qtd = rsMovimentacoesEntrada.getString("quantidade");
		            precoUnitario = rsMovimentacoesEntrada.getString("preco_unitario");
		            valorTotal = rsMovimentacoesEntrada.getString("valor_total");
		            movimentacoesEntrada.add(new MovimentacoesEntrada(produto, Integer.parseInt(idFornecedor), Integer.parseInt(qtd), Float.parseFloat(precoUnitario), Float.parseFloat(valorTotal)));
		        }
		    }catch(ClassNotFoundException e){
		        System.out.println("Driver do banco de dados não localizado");
		    }catch(SQLException s){
		       s.printStackTrace();
		    }
		
		    return movimentacoesEntrada;
		}
		
		public static ArrayList<MovimentacoesSaida> retornaMovimentacoesSaida() {
			ArrayList<MovimentacoesSaida> movimentacoesSaida = new ArrayList<MovimentacoesSaida>();
		    Connection conexao = null;
		    String produto = "";
		    String idComprador = "";
		    String qtd = "";
		    String precoUnitario = "";
		    String valorTotal = "";
		    
		    try{
		        Class.forName("com.mysql.cj.jdbc.Driver");
		        conexao = DriverManager.getConnection("jdbc:mysql://localhost/bancomissao2", "root", "marcosph");
		        ResultSet rsMovimentacoesSaida = conexao.createStatement().executeQuery("select * from movimentacoes_saida");
		        
		        while(rsMovimentacoesSaida.next()){
		            produto = rsMovimentacoesSaida.getString("produto");
		            idComprador = rsMovimentacoesSaida.getString("id_vendedor");
		            qtd = rsMovimentacoesSaida.getString("quantidade");
		            precoUnitario = rsMovimentacoesSaida.getString("preco_unitario");
		            valorTotal = rsMovimentacoesSaida.getString("valor_total");
		            movimentacoesSaida.add(new MovimentacoesSaida(produto, Integer.parseInt(idComprador), Integer.parseInt(qtd), Float.parseFloat(precoUnitario), Float.parseFloat(valorTotal)));
		        }
		    }catch(ClassNotFoundException e){
		        System.out.println("Driver do banco de dados não localizado");
		    }catch(SQLException s){
		       s.printStackTrace();
		    }
		
		    return movimentacoesSaida;
		}
		
		
}
