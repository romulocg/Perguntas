package Negocio;

public class Usuario {
	private String Nome;
	private char Alternativa;

	public Usuario(String nome, char alternativa) {

		Nome = nome;
		Alternativa = alternativa;
	}

	public String getNome() {
		return Nome;
	}

	public void setNome(String nome) {
		Nome = nome;
	}

	public char getAlternativa() {
		return Alternativa;
	}

	public void setAlternativa(char alternativa) {
		Alternativa = alternativa;
	}

}
