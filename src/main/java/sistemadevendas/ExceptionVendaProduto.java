package sistemadevendas;

public class ExceptionVendaProduto extends Exception{

	@Override
	public String getMessage() {
		return "Quantidade a ser vendida é superior ao estoque!";
	}
}
