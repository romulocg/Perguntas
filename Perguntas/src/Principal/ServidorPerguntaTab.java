package Principal;

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
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import Dados.ListaUsuarios;
import Negocio.Usuario;

public class ServidorPerguntaTab extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextArea area;
	private JLabel label1;
	private JButton botao1;
	private JLabel label2;
	private JButton botao2;
	private JLabel label3;
	private JButton botao3;
	private JLabel label4;
	private JButton botao4;
	private JLabel label5;
	private JButton botao5;
	private InetAddress enderecoMulticast;
	private int porta = 9999;
	private MulticastSocket socket;
	private Usuario usuario = new Usuario(null, 'z');
	ListaUsuarios usuarios = new ListaUsuarios();

	public ServidorPerguntaTab() throws IOException {

		setTitle("Perguntas - Usuário " + "Servidor");
		area = new JTextArea("Perguntas iniciado.\n");

		// area.setLineWrap(true);
		area.setEnabled(false);
		botao1 = new JButton("A)");
		botao1.addActionListener(this);
		botao2 = new JButton("B)");
		botao2.addActionListener(this);
		botao3 = new JButton("C)");
		botao3.addActionListener(this);
		botao4 = new JButton("D)");
		botao4.addActionListener(this);
		botao5 = new JButton("E)");
		botao5.addActionListener(this);

		enderecoMulticast = InetAddress.getByName("236.52.65.8");

		socket = new MulticastSocket(porta);
		socket.joinGroup(enderecoMulticast);
		// subclasse da thread
		new Leitora(socket, area).start(); // aqui manda o socket(porta = 9999 +
											// IP) e a mensagem da textArea da
											// form!

		getContentPane().add(area, BorderLayout.CENTER);
		JPanel painel = new JPanel(new GridLayout(1, 2));
		painel.add(label1);
		painel.add(botao1);
		painel.add(label2);
		painel.add(botao2);
		painel.add(label3);
		painel.add(botao3);
		painel.add(label4);
		painel.add(botao4);
		painel.add(label5);
		painel.add(botao5);
		getContentPane().add(painel, BorderLayout.SOUTH);
		setBounds(300, 200, 350, 400);
		setResizable(false);
		setVisible(true);
	}

	public static void main(String[] programa) throws IOException {
		ServidorPerguntaTab pergunta = new ServidorPerguntaTab();
		pergunta.addWindowStateListener(new WindowAdapter() {
			public void windowClosing(WindowEvent evento) {
				System.exit(0);
			}
		});
	}

	public void actionPerformed(ActionEvent e) {
		try {
			byte dados[] = new String("Servidor" + ": "
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
