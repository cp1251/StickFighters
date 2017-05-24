/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stickfighters;

import java.io.File;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.newdawn.slick.Animation;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

/**
 *
 * @author skazkopodatelev
 */
public class StickFighters extends StateBasedGame{
    
    public StickFighters(){
        super("Stick Fighters");
    }
    
    public static void main(String[] args) {
        try {
            AppGameContainer appgc;
            appgc = new AppGameContainer(new StickFighters());
            appgc.setDisplayMode(854, 480, false);
            appgc.start();
        } catch (SlickException ex) {
            Logger.getLogger(StickFighters.class.getName()).log(Level.ALL, null, ex);
	}
    }

    @Override
    public void initStatesList(GameContainer container) throws SlickException {
        this.addState(new Playing());
    }
    
}
