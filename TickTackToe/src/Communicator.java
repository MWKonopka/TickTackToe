import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;



public class Communicator implements Runnable{

	private String ip;
	private int port;
	ServerSocket serverSocket;
	Socket socket;
	boolean requestAccepted;
	boolean connected;
	DataOutputStream dos;
	DataInputStream dis;
	Board board;
	
	public Communicator()
	{
		ip = "localhost";
		port = 22222;
		requestAccepted = false;
		connected = false;
		
		initialConnection();
	}
	
	private void initialConnection() {
		/*
		 * Chwilowo serwer zawsze zaczyna i ma O
		 * 
		 */
		try {
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
	
	public void play()
	{

	}

	@Override
	public void run() {
		Screen frame= new Screen();	
		frame.setTitle("Screen");
		frame.setSize(600, 400);
		frame.setVisible(true);
		Scanner scanner = new Scanner(System.in);
		while(board.checkForWin() == 0)
		{
		board.printBoard();
		if(board.getTurnState())
		{
			int r = 0;
			int c = 0;
			System.out.println("Podaj numer rzędu od 0 do 3:");
			//TRUAJ JEST PROBLEM Z TYM, ŻE THREAD NIE CZEKA NA WCIŚNIECIE PRZYCISKU
			r = frame.r_out; //scanner.nextInt();
			//System.out.println("Podaj numer kolumny od 0 do 3:");
			c =frame.c_out;//scanner.nextInt();
			frame.check = false;
			board.changeBoard(r, c, board.getPlayerMark());
			try {
				dos.writeInt(r);
				dos.writeInt(c);
				board.setTurnState(false);
			} catch (IOException e) {
				System.out.println("Nie udało się przesłać informacji o wykonanym ruchu");
				e.printStackTrace();
			}
		}
		else
		{
			int r,c;
			try {
				System.out.println("Poczekaj. Drugi gracz wykonuje ruch.");
				r = dis.readInt();
				c = dis.readInt();
				board.changeBoard(r, c, board.getOpponentMark());
				board.setTurnState(true);
			} catch (IOException e) {
				System.out.println("Nie otrzymano informacji o wykonanym ruchu");
				e.printStackTrace();
			}
		}
		
	}
		board.printResult();
		scanner.close();
	}

}
