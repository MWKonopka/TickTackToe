import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JPanel;


public class Communicator extends JFrame implements Runnable{

	private String ip;
	private int port;
	ServerSocket serverSocket;
	Socket socket;
	boolean requestAccepted;
	boolean connected;
	DataOutputStream dos;
	DataInputStream dis;
	Board board;
	JPanel panel;
	XOField fields[][];
	
	public Communicator()
	{
		ip = "localhost";
		port = 22222;
		requestAccepted = false;
		connected = false;
		
		panel = new JPanel();
		fields = new XOField[3][3];
		setSize(400,400);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		panel.setLayout(new GridLayout(3,3));
			for(int i=0;i<3;i++)
				for(int j=0; j<3; j++)
				{
					fields[i][j] = new XOField(i,j);
					panel.add(fields[i][j]);
				}
		this.add(panel);
		setVisible(true);
		
		initialConnection();
		
		fields[0][0].addActionListener(new FieldActionListener00());
		fields[0][1].addActionListener(new FieldActionListener01());
		fields[0][2].addActionListener(new FieldActionListener02());
		fields[1][0].addActionListener(new FieldActionListener10());
		fields[1][1].addActionListener(new FieldActionListener11());
		fields[1][2].addActionListener(new FieldActionListener12());
		fields[2][0].addActionListener(new FieldActionListener20());
		fields[2][1].addActionListener(new FieldActionListener21());
		fields[2][2].addActionListener(new FieldActionListener22());
	}
	
	private void initialConnection() {
		/*
		 * Chwilowo serwer zawsze zaczyna i ma O
		 * 
		 */
		try {
		    /*Scanner sc=new Scanner(System.in);  
			System.out.println("Podaj numer ip");
			ip = sc.nextLine(); 
			System.out.println("POdaj numer portu:");
			port = sc.nextInt();*/
			socket = new Socket(ip, port);
			dos = new DataOutputStream(socket.getOutputStream());
			dis = new DataInputStream(socket.getInputStream());
			connected = true;
			board = new Board();
			board.setPlayerMark('X');
			board.setOpponentMark('O');
			board.setTurnState(false);
		} catch (IOException e) {
			System.out.println("Nie ma servera o: " + ip + ":" + port + " | Zakładam server");
			initializeServer();
			while(!requestAccepted)
				listenForServerRequest();
			
		}
		System.out.println("Połączono z serwerem.");
	}
	
	private void initializeServer() {
		try {
			serverSocket = new ServerSocket(port, 8, InetAddress.getByName(ip));
		} catch (Exception e) {
			e.printStackTrace();
		}
		board = new Board();
		board.setPlayerMark('O');
		board.setOpponentMark('X');
		board.setTurnState(true);
	}
	
	private void listenForServerRequest() {
		Socket socket = null;
		try {
			socket = serverSocket.accept();
			dos = new DataOutputStream(socket.getOutputStream());
			dis = new DataInputStream(socket.getInputStream());
			requestAccepted = true;
			System.out.println("Przyjęto zapytanie klienta o dołączenie do gry");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
class FieldActionListener00 implements ActionListener{
@Override
public void actionPerformed(ActionEvent e) {
	if(board.getTurnState())
	{
	fields[0][0].changeIcon(board.getPlayerMark());
	try {
		dos.writeInt(fields[0][0].pos1);
		dos.writeInt(fields[0][0].pos2);
	} catch (IOException e1) {
		System.out.println("Nie udało się przesłać ruchu.");
		e1.printStackTrace();
	}
	board.changeBoard(fields[0][0].pos1, fields[0][0].pos2, board.getPlayerMark());
	fields[0][0].setEnabled(false);
	board.setTurnState(false);
	}
}		
};

class FieldActionListener01 implements ActionListener{
@Override
public void actionPerformed(ActionEvent e) {
	if(board.getTurnState())
	{
	fields[0][1].changeIcon(board.getPlayerMark());
	try {
		dos.writeInt(fields[0][1].pos1);
		dos.writeInt(fields[0][1].pos2);
	} catch (IOException e1) {
		System.out.println("Nie udało się przesłać ruchu.");
		e1.printStackTrace();
	}
	board.changeBoard(fields[0][1].pos1, fields[0][1].pos2, board.getPlayerMark());
	fields[0][1].setEnabled(false);
	board.setTurnState(false);
	}
}		
};

class FieldActionListener02 implements ActionListener{
@Override
public void actionPerformed(ActionEvent e) {
	if(board.getTurnState())
	{
	fields[0][2].changeIcon(board.getPlayerMark());
	try {
		dos.writeInt(fields[0][2].pos1);
		dos.writeInt(fields[0][2].pos2);
	} catch (IOException e1) {
		System.out.println("Nie udało się przesłać ruchu.");
		e1.printStackTrace();
	}
	board.changeBoard(fields[0][2].pos1, fields[0][2].pos2, board.getPlayerMark());
	fields[0][2].setEnabled(false);
	board.setTurnState(false);
	}
}		
};

class FieldActionListener10 implements ActionListener{
@Override
public void actionPerformed(ActionEvent e) {
	if(board.getTurnState())
	{
	fields[1][0].changeIcon(board.getPlayerMark());
	try {
		dos.writeInt(fields[1][0].pos1);
		dos.writeInt(fields[1][0].pos2);
	} catch (IOException e1) {
		System.out.println("Nie udało się przesłać ruchu.");
		e1.printStackTrace();
	}
	board.changeBoard(fields[1][0].pos1, fields[1][0].pos2, board.getPlayerMark());
	fields[1][0].setEnabled(false);
	board.setTurnState(false);
	}
}		
};

class FieldActionListener11 implements ActionListener{
@Override
public void actionPerformed(ActionEvent e) {
	if(board.getTurnState())
	{
	fields[1][1].changeIcon(board.getPlayerMark());
	try {
		dos.writeInt(fields[1][1].pos1);
		dos.writeInt(fields[1][1].pos2);
	} catch (IOException e1) {
		System.out.println("Nie udało się przesłać ruchu.");
		e1.printStackTrace();
	}
	board.changeBoard(fields[1][1].pos1, fields[1][1].pos2, board.getPlayerMark());
	fields[1][1].setEnabled(false);
	board.setTurnState(false);
	}
}		
};

class FieldActionListener12 implements ActionListener{
@Override
public void actionPerformed(ActionEvent e) {
	if(board.getTurnState())
	{
	fields[1][2].changeIcon(board.getPlayerMark());
	try {
		dos.writeInt(fields[1][2].pos1);
		dos.writeInt(fields[1][2].pos2);
	} catch (IOException e1) {
		System.out.println("Nie udało się przesłać ruchu.");
		e1.printStackTrace();
	}
	board.changeBoard(fields[1][2].pos1, fields[1][2].pos2, board.getPlayerMark());
	fields[1][2].setEnabled(false);
	board.setTurnState(false);
	}
}		
};

class FieldActionListener20 implements ActionListener{
@Override
public void actionPerformed(ActionEvent e) {
	if(board.getTurnState())
	{
	fields[2][0].changeIcon(board.getPlayerMark());
	try {
		dos.writeInt(fields[2][0].pos1);
		dos.writeInt(fields[2][0].pos2);
	} catch (IOException e1) {
		System.out.println("Nie udało się przesłać ruchu.");
		e1.printStackTrace();
	}
	board.changeBoard(fields[2][0].pos1, fields[2][0].pos2, board.getPlayerMark());
	fields[2][0].setEnabled(false);
	board.setTurnState(false);
	}
}		
};

class FieldActionListener21 implements ActionListener{
@Override
public void actionPerformed(ActionEvent e) {
	if(board.getTurnState())
	{
	fields[2][1].changeIcon(board.getPlayerMark());
	try {
		dos.writeInt(fields[2][1].pos1);
		dos.writeInt(fields[2][1].pos2);
	} catch (IOException e1) {
		System.out.println("Nie udało się przesłać ruchu.");
		e1.printStackTrace();
	}
	board.changeBoard(fields[2][1].pos1, fields[2][1].pos2, board.getPlayerMark());
	fields[2][1].setEnabled(false);
	board.setTurnState(false);
	}
}		
};

class FieldActionListener22 implements ActionListener{
@Override
public void actionPerformed(ActionEvent e) {
	if(board.getTurnState())
	{
	fields[2][2].changeIcon(board.getPlayerMark());
	try {
		dos.write(fields[2][2].pos1);
		dos.write(fields[2][2].pos2);
	} catch (IOException e1) {
		System.out.println("Nie udało się przesłać ruchu.");
		e1.printStackTrace();
	}
	board.changeBoard(fields[2][2].pos1, fields[2][2].pos2, board.getPlayerMark());
	fields[2][2].setEnabled(false);
	board.setTurnState(false);
	}
}		
};

	
	@Override
	public void run() {
		while(board.checkForWin() == 0)
		{ //System.out.println("While");  //Z JAKIEGOS POWODU JAK TEN PRINT JEST ODKOMENTOWANY TO NIE ZAWSZIESZA SIĘ PO TRZECH RUCHACH
		if(!board.getTurnState())
		{ 
			int r,c;
			try {
				System.out.println("Poczekaj. Drugi gracz wykonuje ruch.");
				r = dis.readInt();
				System.out.println(r);
				c = dis.readInt();
				board.changeBoard(r, c, board.getOpponentMark());
				fields[r][c].changeIcon(board.getOpponentMark());
				fields[r][c].setEnabled(false);
				board.setTurnState(true);
				
				
			} catch (IOException e) {
				System.out.println("Nie otrzymano informacji o wykonanym ruchu");
				e.printStackTrace();
			}
		}
		
		}
		board.printResult();
		fields[0][0].setEnabled(false);
		fields[0][1].setEnabled(false);
		fields[0][2].setEnabled(false);
		fields[1][0].setEnabled(false);
		fields[1][1].setEnabled(false);
		fields[1][2].setEnabled(false);
		fields[2][0].setEnabled(false);
		fields[2][1].setEnabled(false);
		fields[2][2].setEnabled(false);
	}

}
