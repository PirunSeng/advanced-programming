
public class Main {
  public static void main(String[] args) {
    System.out.println("[MainThread] welcome to MITE.");
    
     //Method 1 for creating thread using subclass of thread
    CounterThread counterThread = new CounterThread();
    counterThread.start();
    
//    SpeakerThread speakerThread = new SpeakerThread();
//    speakerThread.start();
    
    // Method 2 for creating thread Runnable
//    Thread thread = new Thread(new Runnable() {
//		
//		@Override
//		public void run() {
//			System.out.println("[MainThread] Counter");
//		}
//	});
//    thread.start();
    
    // method 3
    
//    AnimalThread animalThread = new AnimalThread();
//    Thread thread = new Thread(animalThread);
//    thread.start();
    
    for(int i = 0; i < 3; i++) {
    	System.out.println("[MainThead] count = " + i);
    }
  }
}

/*
Multithread Server
MainThread:
	- Bind to port
	- Waiting for client
	- Creating connection with a client
	- Create new thread to handle that connection
	- Waiting another client

TranslationThread:
  - Receive English word or action from client
  - Send translated word or action to client
*/