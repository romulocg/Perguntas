package Conexao;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import Dados.ListaUsuarios;
import Negocio.Usuario;

public class PerguntaTab extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextArea area;
	private JTextField campo;
	private JButton botao;
	private InetAddress enderecoMulticast;
	private int porta = 9999;
	private MulticastSocket socket;
	private Usuario usuario = new Usuario(null, 'z');
	ListaUsuarios usuarios = new ListaUsuarios();

	public PerguntaTab() throws IOException {

		do {
			usuario.setNome(JOptionPane.showInputDialog(null,
					"Digite o seu nome"));
		} while (usuarios.existeUsuario(usuario));

		setTitle("Perguntas - Usuário " + usuario.getNome());
		area = new JTextArea("Chat iniciado.\n");

		// area.setLineWrap(true);
		area.setEnabled(false);
		campo = new JTextField(10);
		botao = new JButton("Enviar");
		botao.addActionListener(this);

		enderecoMulticast = InetAddress.getByName("236.52.65.8");

		socket = new MulticastSocket(porta);
		socket.joinGroup(enderecoMulticast);
		// subclasse da thread
		new Leitora(socket, area).start(); // aqui manda o socket(porta = 9999 +
											// IP) e a mensagem da textArea da
											// form!

		getContentPane().add(area, BorderLayout.CENTER);
		JPanel painel = new JPanel(new GridLayout(1, 2));
		painel.add(campo);
		painel.add(botao);
		getContentPane().add(painel, BorderLayout.SOUTH);
		setBounds(300, 200, 350, 400);
		setResizable(false);
		setVisible(true);
	}

	public static void main(String[] programa) throws IOException {
		PerguntaTab pergunta = new PerguntaTab();
		pergunta.addWindowStateListener(new WindowAdapter() {
			public void windowClosing(WindowEvent evento) {
				System.exit(0);
			}
		});
	}

	public void actionPerformed(ActionEvent e) {
		try {
			byte dados[] = new String(usuario.getNome() + ": "
					+ campo.getText()).getBytes();

			DatagramPacket datagrama = new DatagramPacket(dados, dados.length,
					enderecoMulticast, porta);
			socket.send(datagrama);
			campo.setText("");

		} catch (Exception ex) {
			System.err.println("Exceção no envio de dados: " + ex.getMessage());
		}
	}

}
