package sistemadevendas;

public class Usuario {
	
	private int id;
	private String nome;
	private String localizacao;
	private String contato;
	
	public Usuario(int id, String nome, String localizacao, String contato) {
		this.id = id;
		this.nome = nome;
		this.localizacao = localizacao;
		this.contato = contato;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getLocalizacao() {
		return localizacao;
	}
	public void setLocalizacao(String localizacao) {
		this.localizacao = localizacao;
	}
	public String getContato() {
		return contato;
	}
	public void setContato(String contato) {
		this.contato = contato;
	}
}
