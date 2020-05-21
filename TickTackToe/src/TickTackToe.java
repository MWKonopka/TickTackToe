/*
 * Autorzy: Mateusz Konopka, Szymon Pabjan
 * Fizyka Komputerowa, 2020
 * grupa: sk-cz-12-np.
 */
public class TickTackToe {
	static Thread thread;
	
	public static void main(String [] args)
	{
		Communicator communicator = new Communicator();
		thread = new Thread(communicator);
		thread.run();
	}
}
