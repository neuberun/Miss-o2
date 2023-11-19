package sistemadevendas;

public class PessoaFisica extends Usuario {
	
	private String cpf;
	
	public PessoaFisica(int id, String nome, String localizacao, String contato, String cpf) {
		super(id, nome, localizacao, contato);
		this.cpf = cpf;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
	@Override
	public String toString() {
		return "ID: " + getId() + "\n" + "NOME: " + getNome() + "\n"+ "LOCALIZACAO: " + getLocalizacao() + "\n" + "CONTATO: " + getContato() + "\n" + "CPF: " + cpf;
	}
}
