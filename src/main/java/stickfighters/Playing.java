/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stickfighters;

import java.util.ArrayList;
import java.util.HashMap;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import stickfighters.characters.TemplateCharacter;
import stickfighters.utils.EasyAnimation;
import stickfighters.utils.MathUtils;

/**
 *
 * @author skazkopodatelev
 */
public class Playing extends BasicGameState {
    TemplateCharacter player;
    TemplateCharacter dummy;
    Vector2f resolution;
    Input input;
    Image background;
    float speed = 10  * 0.025f;
    private float currentBkgX = 0;

    @Override
    public int getID() {
        return 1;
    }

    @Override
    public void init(GameContainer container, StateBasedGame game) throws SlickException {
        container.setTargetFrameRate(25);
        input = container.getInput();

        background = new Image("rsc/background.jpg");
        resolution = new Vector2f(container.getWidth(), container.getHeight());
        player = new TemplateCharacter("NinjaGirl", new Vector2f(0, resolution.y), 0.5f);
        dummy = new TemplateCharacter("NinjaGirl", new Vector2f(500, resolution.y), 0.5f);
    }

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
        g.setBackground(Color.white);
        g.drawImage(background, currentBkgX, 0);
        g.setColor(Color.black);
        g.drawLine(0, resolution.y/2, resolution.x, resolution.y/2);
        
        player.draw();
        dummy.draw();
    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
        int runstate = 0;

        if(input.isKeyDown(Input.KEY_UP) && player.getCords().y > resolution.y/2 - player.getSize().y){
            runstate = 1;
            player.setCordY( player.getCords().y - speed * delta );
            player.setCurrentAnimation("Run");

            if(player.getCords().y < resolution.y/2 - player.getSize().y)
                player.setCordY(resolution.y/2 - player.getSize().y);
        }

        if(input.isKeyDown(Input.KEY_DOWN) && player.getCords().y < resolution.y - player.getSize().y){
            runstate = 1;
            player.setCordY( player.getCords().y + speed * delta );
            player.setCurrentAnimation("Run");

            if(player.getCords().y > resolution.y - player.getSize().y)
                player.setCordY(resolution.y - player.getSize().y);
        }

        if(input.isKeyDown(Input.KEY_LEFT)){
            runstate = 1;
            player.setDirection(0);
            player.setCurrentAnimation("ReverseRun");
            player.setCordX(player.getCords().x - speed * delta);

            if(player.getCords().x < 154) {
                if(currentBkgX + speed * delta/2 > 0){
                    currentBkgX = 0;
                    if(player.getCords().x < 0)
                        player.getCords().x = 0;
                } else {
                    currentBkgX += speed * delta/2;
                    player.getCords().x = 154;
                }
            }
        }

        if(input.isKeyDown(Input.KEY_RIGHT)){
            runstate = 1;
            player.setDirection(1);
            player.setCurrentAnimation("Run");
            player.setCordX(player.getCords().x + speed * delta);

            if(player.getCords().x > 700 - player.getSize().x) {
                if(currentBkgX - speed * delta/2 < resolution.x - background.getWidth()){
                    currentBkgX = resolution.x - background.getWidth();
                    if(player.getCords().x > resolution.x - player.getSize().x)
                        player.setCordX(resolution.x - player.getSize().x);
                } else {
                    currentBkgX -= speed * delta/2;
                    player.setCordX(700 - player.getSize().x);
                }
            }
        }

        if(input.isKeyPressed(Input.KEY_X) && !player.getCurrentAnimation().getName().equals("Attack")){
            player.setCurrentAnimation("Attack");
            player.getCurrentAnimation().restart();
        }

        if(player.getCurrentAnimation().getName().equals("Attack") && player.getCurrentAnimation().isPlaying())
            runstate = 2;

        if(runstate == 0) {
            if (player.getDirection() == 1)
                player.setCurrentAnimation("Idle");
            if (player.getDirection() == 0)
                player.setCurrentAnimation("ReverseIdle");
        }

        if(MathUtils.algorithm(
                player.getCords().x
        ))

    }
}
