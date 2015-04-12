package Negocio;

public class Usuario {
	private String Nome;
	private String Alternativa;

	public Usuario(String nome, String alternativa) {

		Nome = nome;
		Alternativa = alternativa;
	}

	public String getNome() {
		return Nome;
	}

	public void setNome(String nome) {
		Nome = nome;
	}

	public String getAlternativa() {
		return Alternativa;
	}

	public void setAlternativa(String alternativa) {
		Alternativa = alternativa;
	}

}
