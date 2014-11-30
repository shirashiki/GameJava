package kiloboltgame.framework;

import java.awt.Image;
import java.util.ArrayList;

public class Animation {
	private ArrayList<AnimFrame> frames;

	// volatile guarantees we have atomic access
	private volatile int currentFrame;

	/*
	 * animTime will keep track of how much time has elapsed since the current
	 * image was displayed, so that when animTime reaches a certain value, we
	 * can switch over to the next frame.
	 */
	private volatile long animTime;
	private long totalDuration;

	/**
	 * Constructor
	 */
	public Animation() {
		frames = new ArrayList<AnimFrame>();
		totalDuration = 0;

		synchronized (this) {
			animTime = 0;
			currentFrame = 0;
		}

	}

	public synchronized void addFrame(Image image, long duration) {
		totalDuration += duration;
		frames.add(new AnimFrame(image, totalDuration));
	}

	/**
	 * Switch frames as necessary
	 * @param elapsedTime
	 */
	public synchronized void update(long elapsedTime) {
		if (frames.size() > 1) {
			animTime += elapsedTime;
			if (animTime >= totalDuration) {
				animTime = animTime % totalDuration;
				currentFrame = 0;

			}

			while (animTime > getFrame(currentFrame).endTime) {
				currentFrame++;

			}
		}
	}

	public synchronized Image getImage() {
		if (frames.size() == 0) {
			return null;
		} else {
			return getFrame(currentFrame).image;
		}
	}

	private AnimFrame getFrame(int i) {
		return (AnimFrame) frames.get(i);
	}

	
	private class AnimFrame {

		Image image;
		long endTime;

		public AnimFrame(Image image, long endTime) {
			this.image = image;
			this.endTime = endTime;
		}
	}

}
