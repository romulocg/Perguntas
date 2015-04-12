package Dados;

import java.util.ArrayList;

import Negocio.Usuario;

public class ListaUsuarios {
	private ArrayList<Usuario> usuarios = new ArrayList<Usuario>();

	public ListaUsuarios() {

	}

	public ArrayList<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(ArrayList<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public boolean existeUsuario(Usuario usuario) {
		for (int i = 0; i < usuarios.size(); i++) {
			if (usuario.getNome().equals(usuarios.get(i).getNome()))
				return true;
		}
		return false;
	}
}
