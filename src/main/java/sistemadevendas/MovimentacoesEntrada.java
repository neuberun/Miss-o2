package sistemadevendas;

public class MovimentacoesEntrada {
	
	private String produto;
	private int idFornecedor;
	private int quantidade;
	private float precoUnitario;
	private float valorTotal;
	
	public MovimentacoesEntrada(String produto, int idFornecedor, int quantidade, float precoUnitario, float valorTotal) {
		this.produto = produto;
		this.idFornecedor = idFornecedor;
		this.quantidade = quantidade;
		this.precoUnitario = precoUnitario;
		this.valorTotal = valorTotal;
	}
	
	
	public String getProduto() {
		return produto;
	}
	public void setProduto(String produto) {
		this.produto = produto;
	}
	public int getIdFornecedor() {
		return idFornecedor;
	}
	public void setFornecedor(int idFornecedor) {
		this.idFornecedor = idFornecedor;
	}
	public int getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	public float getPrecoUnitario() {
		return precoUnitario;
	}
	public void setPrecoUnitario(float precoUnitario) {
		this.precoUnitario = precoUnitario;
	}
	public float getValorTotal() {
		return valorTotal;
	}
	public void setValorTotal(float valorTotal) {
		this.valorTotal = valorTotal;
	}
	
	@Override
	public String toString() {
		return "PROUTO: " + produto + "\n" + "ID FORNECEDOR: " + idFornecedor + "\n" + "QUANTIDADE EM ESTOQUE: " + quantidade + "\n" + "PRECO UNITARIO: " + precoUnitario +
				"VALOR TOTAL: " + valorTotal;
	}
}
