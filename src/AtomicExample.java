
import java.util.concurrent.atomic.AtomicInteger;
/**
 *
 * @author Jordan
 */
public class AtomicExample {
    private static AtomicInteger at = new AtomicInteger(0);

	static class MyRunnable implements Runnable {

		private int myCounter;
		private int myPrevCounter;
		private int myCounterPlusten;
		private boolean isNine;

		public void run() {
			myCounter = at.incrementAndGet();
			System.out.println("Thread " + Thread.currentThread().getId() + "  / Counter : " + myCounter);
			myPrevCounter = at.getAndIncrement();
			System.out.println("Thread " + Thread.currentThread().getId() + " / Previous : " + myPrevCounter); 
			myCounterPlusten = at.addAndGet(10);		
			System.out.println("Thread " + Thread.currentThread().getId() + " / plus ten : " + myCounterPlusten);
			isNine = at.compareAndSet(14, 3);
			if (isNine) {
				System.out.println("Thread " + Thread.currentThread().getId() 
						+ " / Value was equal to 14, so it was updated to " + at.intValue());
			}

		}
	}

	public static void main(String[] args) {
		Thread t1 = new Thread(new MyRunnable());
		Thread t2 = new Thread(new MyRunnable());
		t1.start();
		t2.start();
	}
}
