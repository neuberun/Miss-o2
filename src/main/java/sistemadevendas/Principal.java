package sistemadevendas;

import java.util.ArrayList;
import java.util.Scanner;

public class Principal {
	public static void main(String args[]) {
		boolean sair = false;
		Scanner scan = new Scanner(System.in);
		
		while(!sair) {
			
			System.out.println("1 - CADASTRAR PESSOA FISICA\n" + "2 - CADASTRAR PESSOA JURIDICA\n"
			+ "3 - DADOS COMPLETOS DE PESSOAS FISICAS\n" + "4 - DADOS COMPLETOS DE PESSOAS JURIDICAS\n" 
			+ "5 - COMPRAR PRODUTO\n" + "6 - VENDER PRODUTO\n"
			+ "7 - MOVIMENTACOES DE ENTRADA DE PRODUTO\n" + "8 - MOVIMENTACOES DE SAIDA DE PRODUTO\n"
			+ "9 - VALOR DAS ENTRADAS POR PRODUTO\n" + "10 - VALOR DAS SAIDAS POR PRODUTO\n" + "11 - VALOR TOTAL DE ENTRADA AGRUPADO POR OPERADOR\n"
			+ "12 - VALOR TOTAL DE SAIDA AGRUPADO POR OPERADOR\n"
			+ "13 - VALOR MEDIA DA VENDA POR PRODUTO\n"+ "14 - LISTAR PRODUTOS\n" + "0 - SAIR");
			
			int opc = scan.nextInt();
			scan.nextLine();
			
			switch(opc) {
			
				case 1:
					System.out.print("DIGITE O NOME DA PESSOA FISICA: ");
					String nome = scan.nextLine();
					System.out.print("DIGITE A LOCALIZACAO: ");
					String localizacao = scan.nextLine();
					System.out.print("CONTATO: ");
					String contato = scan.nextLine();
					System.out.print("CPF: ");
					String cpf = scan.nextLine();
					ConexaoDAO.cadastrarPessoaFisica(nome, localizacao, contato, cpf);
					break;
					
				case 2:
					System.out.print("DIGITE O NOME DA PESSOA JURIDICA: ");
					String nome2 = scan.nextLine();
					System.out.print("DIGITE A LOCALIZACAO: ");
					String localizacao2 = scan.nextLine();
					System.out.print("CONTATO: ");
					String contato2 = scan.nextLine();
					System.out.print("CNPJ: ");
					String cnpj = scan.nextLine();
					ConexaoDAO.cadastrarPessoaJuridica(nome2, localizacao2, contato2, cnpj);
					break;
					
				case 3:
					for(PessoaFisica f : ConexaoDAO.retornaPessoaFisica()) {
						System.out.println("--------------------");
						System.out.println(f);
					}
					System.out.println("--------------------");
					break;
					
				case 4:
					for(PessoaJuridica j : ConexaoDAO.retornaPessoaJuridica()) {
						System.out.println("--------------------");
						System.out.println(j);
					}
					System.out.println("--------------------");
					break;
					
				case 5:
					System.out.print("DIGITE O NOME DO PRODUTO: ");
					String nome3 = scan.nextLine();
					System.out.print("DIGITE A QUANTIDADE: ");
					int qtd1 = scan.nextInt();
					System.out.print("DIGITE O FORNECEDOR: ");
					int id_fornecedor = scan.nextInt();
					
					ConexaoDAO.compraProduto(nome3, id_fornecedor, qtd1);
					break;
					
				case 6:
					System.out.print("DIGITE O NOME DO PRODUTO: ");
					String nome4 = scan.nextLine();
					System.out.print("DIGITE A QUANTIDADE: ");
					int qtd2 = scan.nextInt();
					System.out.print("DIGITE O COMPRADOR: ");
					int id_comprador = scan.nextInt();
					try {
					ConexaoDAO.vendeProduto(nome4, id_comprador, qtd2);
					} catch(ExceptionVendaProduto e) {
						System.out.println(e.getMessage());
					}
					break;
					
				case 7:
					ArrayList<MovimentacoesEntrada> movimentacoesEntrada;
					movimentacoesEntrada = ConexaoDAO.retornaMovimentacoesEntrada();
					for(MovimentacoesEntrada m : movimentacoesEntrada) {
						System.out.println("--------------------");
						System.out.println(m);
					}
					System.out.println("--------------------");
					break;
					
				case 8:
					ArrayList<MovimentacoesSaida> movimentacoesSaida;
					movimentacoesSaida = ConexaoDAO.retornaMovimentacoesSaida();
					for(MovimentacoesSaida m : movimentacoesSaida) {
						System.out.println("--------------------");
						System.out.println(m);
					}
					System.out.println("--------------------");
					break;
					
				case 9:
					System.out.print("DIGITE O PRODUTO: ");
					String produto = scan.nextLine();
					ArrayList<MovimentacoesEntrada> movimentacoesEntrada2;
					movimentacoesEntrada2 = ConexaoDAO.retornaMovimentacoesEntrada();
					for(MovimentacoesEntrada m : movimentacoesEntrada2) {
						if(m.getProduto().equals(produto)) {
							System.out.println("--------------------");
							System.out.println("PRODUTO: " + produto + "\n" + "VALOR: " + m.getValorTotal());
						}
					}
					System.out.println("--------------------");
					break;
				
				case 10:
					System.out.print("DIGITE O PRODUTO: ");
					String produto2 = scan.nextLine();
					ArrayList<MovimentacoesSaida> movimentacoesSaida2 = new ArrayList<MovimentacoesSaida>();
					movimentacoesSaida2 = ConexaoDAO.retornaMovimentacoesSaida();
					for(MovimentacoesSaida m : movimentacoesSaida2) {
						if(m.getProduto().equals(produto2)) {
							System.out.println("--------------------");
							System.out.println("PRODUTO: " + produto2 + "\n" + "VALOR: " + m.getValorTotal());
						}
					}
					System.out.println("--------------------");
					break;
				case 11:
					ArrayList<PessoaJuridica> pessoasJuridicas = ConexaoDAO.retornaPessoaJuridica();
					ArrayList<MovimentacoesEntrada> movimentacoesEntrada3 = ConexaoDAO.retornaMovimentacoesEntrada();
					float valor = 0;
					
					for(PessoaJuridica p : pessoasJuridicas) {
						for(MovimentacoesEntrada m : movimentacoesEntrada3) {
							if(p.getId() == m.getIdFornecedor()) {
								valor += m.getValorTotal();
							}
						}
						System.out.println("--------------------");
						System.out.println(p.getNome() + "\n" + "VALOR TOTAL DE ENTRADA: " + valor);
						valor = 0;
					}
					System.out.println("--------------------");
					break;
					
				case 12:
					ArrayList<PessoaFisica> pessoasFisicas = ConexaoDAO.retornaPessoaFisica();
					ArrayList<MovimentacoesSaida> movimentacoesSaida3 = ConexaoDAO.retornaMovimentacoesSaida();
					float valor2 = 0;
					
					for(PessoaFisica p : pessoasFisicas) {
						for(MovimentacoesSaida m : movimentacoesSaida3) {
							if(p.getId() == m.getIdComprador()) {
								valor2 += m.getValorTotal();
							}
						}
						System.out.println("--------------------");
						System.out.println(p.getNome() + "\n" + "VALOR TOTAL DE SAIDA: " + valor2);
						valor = 0;
					}
					System.out.println("--------------------");
					break;
				case 13:
					ArrayList<MovimentacoesSaida> movimentacaoSaida = ConexaoDAO.retornaMovimentacoesSaida();
					ArrayList<Produto> produtos = ConexaoDAO.retornaProdutos();
					
					for(Produto p : produtos) {
						float valor3 = 0;
						int qtd = 0;
						boolean flag = false;
						for(MovimentacoesSaida m : movimentacaoSaida) {
							if(p.getNome().equals(m.getProduto())) {
								valor3 = m.getValorTotal();
								qtd = m.getQuantidade();
								flag = true;
							}
						}
						if(flag) {
							float valorMedio = valor3/qtd;
							System.out.println("--------------------");
							System.out.println("NOME PRODUTO: " + p.getNome() + "\n" + "VALOR MEDIO DE VENDA: " + valorMedio);
						}
					}
					System.out.println("--------------------");
					break;
					
				case 14:
					produtos = ConexaoDAO.retornaProdutos();
					for(Produto p : produtos) {
						System.out.println("--------------------");
						System.out.println(p);
					}
					System.out.println("--------------------");
					break;
	
				case 0:
					sair = true;
					break;
			}
		}
		scan.close();
	}
}
