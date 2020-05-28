import java.awt.Dimension;
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
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Communicator extends JFrame implements Runnable {
	/**
	 * Klasa odpowiadająca za tworzenie serwera. Połączenie serwera i klienta.
	 * Obsługę stosownych komunikatów w przypadku rozłączenia, przeprowadzenie
	 * rozgrywki oraz aktualizację GUI. Podział zadań ekskluzywnych serwer - klient:
	 * Serwer: pola: isServer, serverSocket, requestAccepted metody:
	 * initializeServer, listenForServerRequest Klient: pola: isClient. Pozostałe
	 * pola i metody są wykorzystywane zarówno przez klienta jak i serwer.
	 * Przeprowadzanie rozgrywki i wzajmna komunikacja obsługiwane są przez metodę
	 * run (klasa opiera się na interfejsie Runnable).
	 */

	private String ip;
	private int port;
	ServerSocket serverSocket;
	Socket socket;
	boolean requestAccepted;
	boolean isClient; // true dla klienta
	boolean isServer; // true dla serwera
	DataOutputStream dos;
	DataInputStream dis;
	Board board;
	JPanel panel;
	XOField fields[][];
	boolean wasBreak;

	JPanel optionsPanel;

	public Communicator() {
		ip = "localhost"; // wartosci domyslne
		port = 22222;
		requestAccepted = false;
		isClient = false;
		isServer = false;
		wasBreak = false;

		panel = new JPanel();
		fields = new XOField[3][3];
		setSize(400, 400);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		panel.setLayout(new GridLayout(3, 3));
		for (int i = 0; i < 3; i++)
			for (int j = 0; j < 3; j++) {
				fields[i][j] = new XOField(i, j);
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

		optionsPanel = new JPanel();
		optionsPanel.setPreferredSize(new Dimension(350, 80));
	}

	private void initialConnection() {
		/*
		 * Serwer zawsze zaczyna i ma O
		 * 
		 */
		try {
			/*
			 * Scanner sc=new Scanner(System.in); System.out.println("Podaj numer ip"); ip =
			 * sc.nextLine(); System.out.println("POdaj numer portu:"); port = sc.nextInt();
			 */
			socket = new Socket(ip, port);
			dos = new DataOutputStream(socket.getOutputStream());
			dis = new DataInputStream(socket.getInputStream());
			isClient = true;
			board = new Board();
			board.setPlayerMark('X');
			board.setOpponentMark('O');
			board.setTurnState(false);
		} catch (IOException e) {
			System.out.println("Nie ma servera o: " + ip + ":" + port + " | Zakładam server");
			initializeServer();
			isServer = true;
			while (!requestAccepted) {
				System.out.println("Czekam na klienta.");
				listenForServerRequest();
			}

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

	class FieldActionListener00 implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (board.getTurnState()) {
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

	class FieldActionListener01 implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (board.getTurnState()) {
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

	class FieldActionListener02 implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (board.getTurnState()) {
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

	class FieldActionListener10 implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (board.getTurnState()) {
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

	class FieldActionListener11 implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (board.getTurnState()) {
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

	class FieldActionListener12 implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (board.getTurnState()) {
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

	class FieldActionListener20 implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (board.getTurnState()) {
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

	class FieldActionListener21 implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (board.getTurnState()) {
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

	class FieldActionListener22 implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (board.getTurnState()) {
				fields[2][2].changeIcon(board.getPlayerMark());
				try {
					dos.writeInt(fields[2][2].pos1);
					dos.writeInt(fields[2][2].pos2);
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
		while (board.checkForWin() == 0) {
			System.out.println("");
			if (!board.getTurnState()) {
				int r, c;
				try {
					System.out.println("Poczekaj. Drugi gracz wykonuje ruch.");
					r = dis.readInt();
					c = dis.readInt();
					board.changeBoard(r, c, board.getOpponentMark());
					fields[r][c].changeIcon(board.getOpponentMark());
					fields[r][c].setEnabled(false);
					board.setTurnState(true);

				} catch (IOException e) {
					System.out.println("Nie otrzymano informacji o wykonanym ruchu");
					e.printStackTrace();
					wasBreak = true; // ustawia, że nastąpiło zerwanie
					break;
				}
			}
		}

		if (wasBreak == false) {// to wwszystko wrzuyciłęm w ifa by wyrzuacło ponowną rozgrywke tylko gdy gra
									// zakonczyła się normalnie
			board.printResult();
			int result = JOptionPane.showConfirmDialog(null, optionsPanel, "Czy chcesz zagrać ponownie",
					JOptionPane.OK_CANCEL_OPTION);
			if (result == JOptionPane.OK_OPTION) {
				if (isClient == false)
					board.setTurnState(true);
				else
					board.setTurnState(false);

				System.out.println("Ponowana gra. Turn state: " + board.getTurnState());

				for (int i = 0; i < 3; i++)
					for (int j = 0; j < 3; j++) {
						fields[i][j].setEnabled(true);
						fields[i][j].setIcon(null);
					}
				board.initializeBoard();
				System.out.println("checkForWin: " + board.checkForWin());
				this.run();
			} else
				System.exit(0);
		}
		if (wasBreak == true && isServer == true) {
			int result = JOptionPane.showConfirmDialog(null, optionsPanel,
					"Zerwano połączenie. Chcesz poczekać na nowego klienta?", JOptionPane.OK_CANCEL_OPTION);
			if (result == JOptionPane.OK_OPTION) {
				if (isClient == false) {
					board.setTurnState(true);
					listenForServerRequest();
				} else {
					board.setTurnState(false);
				}

				System.out.println("Nowa gra. Turn state: " + board.getTurnState());

				for (int i = 0; i < 3; i++)
					for (int j = 0; j < 3; j++) {
						fields[i][j].setEnabled(true);
						fields[i][j].setIcon(null);
					}
				board.initializeBoard();
				System.out.println("checkForWin: " + board.checkForWin());
				this.run();
			} else
				System.exit(0);
		}
		if (wasBreak == true && isServer == false) {
			System.out.println("Zerwano połączenie z serwerem.");
		}

	}

}
