package com.mike.gamename.screen.game;


import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;

/**
 * Created by mikeldiez on 08/09/2017.
 */

public class GameController implements InputProcessor {


    /*atributes*/


    /*debug atributes*/
    private boolean freezeGame = false;

    public GameController() {

    }

    public void update(float delta) {

        //pausamos el renderizado y la logica para debugear
        if (!freezeGame) {
            //logica del juego
        }


    }


    @Override
    public boolean keyDown(int keycode) {
        if (keycode == Input.Keys.P) {
            freezeGame = !freezeGame;
        }
        return true;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
