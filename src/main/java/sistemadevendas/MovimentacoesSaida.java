package sistemadevendas;

public class MovimentacoesSaida {
	
	private String produto;
	private int idComprador;
	private int quantidade;
	private float precoUnitario;
	private float valorTotal;
	
	public MovimentacoesSaida(String produto, int idComprador, int quantidade, float precoUnitario, float valorTotal) {
		this.produto = produto;
		this.idComprador = idComprador;
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
	public int getIdComprador() {
		return idComprador;
	}
	public void setIdComprador(int idComprador) {
		this.idComprador = idComprador;
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
		return "PROUTO: " + produto + "\n" + "ID COMPRADOR: " + idComprador + "\n" + "QUANTIDADE EM ESTOQUE: " + quantidade + "\n" + "PRECO UNITARIO: " + precoUnitario +
				"VALOR TOTAL: " + valorTotal;
	}
	
}
