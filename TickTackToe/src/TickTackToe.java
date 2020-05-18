
public class TickTackToe {
	static Thread thread;
	
	public static void main(String [] args)
	{
		Communicator communicator = new Communicator();
		thread = new Thread(communicator);
		thread.run();
	}
}
