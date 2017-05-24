package stickfighters.utils;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import java.util.ArrayList;

/**
 * Created by skazkopodatelev on 12.05.2017.
 */
public class EasyAnimation {
	Animation animation;
	Animation hitboxes;
	float scaleFactor;
	String name;

	public EasyAnimation(String name, String folderName, float scaleFactor, boolean isFlipped, int frameDelay, boolean hasHitboxes) throws SlickException{
		this.name = isFlipped ? "Reverse" + name : name;
		this.animation = loadAnimation(folderName, name, isFlipped, frameDelay);
		this.hitboxes = hasHitboxes ? loadAnimation(folderName + "_hitbox", name, isFlipped, frameDelay) : null;
		this.scaleFactor = scaleFactor;
	}

	public Animation loadAnimation(String folderName, String name, boolean isFlipped, int frameDelay) throws SlickException {
		ArrayList<Image> frameList = new ArrayList();
		Image temp;

		for(int i = 0; getClass().getResourceAsStream("/rsc/" + folderName + "/" + name + "__" + String.format("%03d", i) + ".png")!=null; i++){
			temp = new Image("rsc/" + folderName + "/" + name + "__" + String.format("%03d", i) + ".png", false, Image.FILTER_NEAREST).getFlippedCopy(isFlipped, false);
			frameList.add(temp);
		}

		Image[] frames;
		frames = frameList.toArray(new Image[0]);
		return new Animation(frames, frameDelay);
	}


	public void draw(float x, float y){
		this.animation.draw(x, y, animation.getWidth() * scaleFactor, animation.getHeight() *scaleFactor);
		if(hasHitboxes())
			this.hitboxes.draw(x, y, animation.getWidth() * scaleFactor, animation.getHeight() * scaleFactor);
	}

	public int getHeight() {
		return Math.round(animation.getHeight() * scaleFactor);
	}

	public int getWidth() {
		return Math.round(animation.getWidth() * scaleFactor);
	}

	public int getHitWidth() { return Math.round(hitboxes.getWidth() * scaleFactor); }

	public String getName() { return name; }

	public boolean isPlaying(){ return animation.getFrame() < animation.getFrameCount()-1; }

	public void restart() { animation.restart(); hitboxes.restart(); }

	public boolean hasHitboxes(){
		return hitboxes == null ? true : false;
	}
}
