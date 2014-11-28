package kiloboltgame;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Frame;

public class StartingClass extends Applet implements Runnable{
	
	private static final int MILI_INTERVAL = 17;
	
	@Override
	public void init() {
		super.init();
		
		setSize(800, 480);
		setBackground(Color.BLACK);
		
		// takes focus
		setFocusable(true);
		Frame frame = (Frame) this.getParent().getParent();
        frame.setTitle("Q-Bot Alpha");
	}

	@Override
	public void start() {
		super.start();
		
		   Thread thread = new Thread(this);
		    thread.start();
	}

	@Override
	public void stop() {
		super.stop();
	}

	@Override
	public void destroy() {
		super.destroy();
	}
	
	@Override
	public void run() {
		
		while(true) {
			repaint();
			try{
				Thread.sleep(MILI_INTERVAL);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}
}
