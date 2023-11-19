package sistemadevendas;

public class PessoaJuridica extends Usuario{

	private String cnpj;
	
	public PessoaJuridica(int id, String nome, String localizacao, String contato, String cnpj) {
		super(id, nome, localizacao, contato);
		this.cnpj = cnpj;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}
	
	@Override
	public String toString() {
		return "ID: " + getId() + "\n" + "NOME: " + getNome() + "\n"+ "LOCALIZACAO: " + getLocalizacao() + "\n" + "CONTATO: " + getContato() + "\n" + "CNPJ: " + cnpj;
	}
}
