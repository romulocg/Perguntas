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
	
	

}
