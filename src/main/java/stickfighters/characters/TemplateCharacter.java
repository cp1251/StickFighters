package stickfighters.characters;

import com.sun.istack.internal.NotNull;
import org.newdawn.slick.Animation;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;
import stickfighters.utils.EasyAnimation;

import java.awt.*;
import java.util.HashMap;

/**
 * Created by skazkopodatelev on 11.05.2017.
 */
public class TemplateCharacter {
	HashMap<String, EasyAnimation> animations;
	EasyAnimation currentAnimation;
	Vector2f cords;
	Vector2f size;
	int speed;
	int direction;

	public TemplateCharacter(@NotNull String type, @NotNull Vector2f cords, @NotNull float scaleFactor) throws SlickException {
		animationsLoader(type, scaleFactor);
		size = new Vector2f(currentAnimation.getWidth(), currentAnimation.getHeight());
		this.cords = new Vector2f(cords.x, cords.y - currentAnimation.getHeight());
		this.direction = 1;

	}

	private void animationsLoader(String type, float scaleFactor) throws SlickException {
		animations = new HashMap<>();
		animations.put("Idle", new EasyAnimation("Idle", type, scaleFactor, false, 80, false));
		animations.put("ReverseIdle", new EasyAnimation("Idle", type, scaleFactor, true, 80, false));
		animations.put("Run", new EasyAnimation("Run", type, scaleFactor, false, 60, false));
		animations.put("ReverseRun", new EasyAnimation("Run", type, scaleFactor, true, 60, false));
		animations.put("Attack", new EasyAnimation("Attack", type, scaleFactor, false, 40, true));
		animations.put("ReverseAttack", new EasyAnimation("Attack", type, scaleFactor, true, 40, true));
		currentAnimation = animations.get("Idle");
	}

	public Vector2f getSize(){
		return size;
	}

	public void draw(){
		currentAnimation.draw(cords.x, cords.y);
	}

	public Vector2f getCords(){
		return cords;
	}

	public void setCordX(float x){
		cords.x = x;
	}

	public void setCordY(float y){
		cords.y = y;
	}

	public void setCurrentAnimation(String name){
		currentAnimation = animations.get(name);
	}

	public EasyAnimation getCurrentAnimation() {
		return currentAnimation;
	}

	public void setDirection(int direction) {
		this.direction = direction;
	}

	public int getDirection() {
		return direction;
	}
}
