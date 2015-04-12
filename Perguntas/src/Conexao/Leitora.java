package Conexao;

import java.net.DatagramPacket;
import java.net.MulticastSocket;
import javax.swing.JTextArea;

/**
*
* @author Romulo
*/
public class Leitora extends Thread {

  MulticastSocket socketMulticast;
  JTextArea area;

  public Leitora(MulticastSocket s, JTextArea a) {
      socketMulticast = s;
      area = a;
  }

  public void run() {
      try {
          while (true) {
              byte dados[] = new byte[256];
              DatagramPacket datagrama = new DatagramPacket(dados, dados.length);
              socketMulticast.receive(datagrama);
              String texto = new String(datagrama.getData());
              area.append("\n" + texto.trim());
              sleep(100);
          }
      } catch (Exception ex) {
          System.err.println(ex.toString());
      }
  }
}
