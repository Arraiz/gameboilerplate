package com.mike.gamename.screen.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mike.gamename.Common.GameCongif;
import com.mike.gamename.utils.CameraDebugUtils;
import com.mike.gamename.utils.MouseDebug;
import com.mike.gamename.utils.ScreenUtilities;

/**
 * Created by mikeldiez on 08/09/2017.
 */

/*estas clases no implemetaran ningun metodo salvo dispose ya que sera implemetado en la gamecreeen
* esta clase solo controlara el renderiado sin logica alguna la lo gica la impkemetaremos en el controlador
* */
public class GameRenderer implements Disposable {

    /*atributos*/

    /**Controllador de logica del juego**/
    private final GameController gameController;
    private ShapeRenderer renderer;
    private final SpriteBatch batch;
    /**atributoos de debugeo**/
    private BitmapFont debugFont;
    private OrthographicCamera debugCamera;
    private Viewport debugViewport;
    /***********************/
    /****Viewport y camara del mundo de juego WORLD*/
    private Viewport viewport;
    private OrthographicCamera camera;
    /***********************************************/


    public GameRenderer(GameController gameController,SpriteBatch batch) {

        this.gameController = gameController;
        this.batch=batch;
        init();
    }
    private void init() {
        /**Inicializamos el renderizado y las camaras**/
        renderer = new ShapeRenderer();
        /**camaras de juego HUD y debug**/
        camera = new OrthographicCamera();
        viewport = new FitViewport(GameCongif.WORLD_WIDTH, GameCongif.WORLD_HEIGHT, camera);
        //para para texto de debugeo
        debugCamera = new OrthographicCamera();
        debugViewport = new FitViewport(GameCongif.HUD_WIDTH, GameCongif.HUD_HEIGHT, debugCamera);
        /**fuente de debugeo**/
        debugFont = new BitmapFont();
    }
    public void render(float delta) {
        //limpar la screen cada frame
        ScreenUtilities.ClearScreen(Color.GRAY);
        /**lineas de debugeo**/
        renderDebug();

    }
    public void resize(int width, int height) {
        /**actualizacion de todos los viewports**/
        viewport.update(width, height, true);
        debugViewport.update(width, height, true);
    }
    private void renderDebug() {

        /**texto de debug**/
        debugViewport.apply();
        batch.setProjectionMatrix(debugCamera.combined);
        batch.begin();
        CameraDebugUtils.debugHUDStatistics(viewport,batch,debugFont);
        /*****/

        /**lineas de debugeo**/
        //proyectamos
        batch.end();
        //aplicamos el viewport
        viewport.apply();
        //proyectamos
        renderer.setProjectionMatrix(camera.combined);
        renderer.begin(ShapeRenderer.ShapeType.Line);
        /**herramientas de deugeo**/
        CameraDebugUtils.RenderBounds(renderer, viewport);
        CameraDebugUtils.SetCameraControls(camera, viewport);
        MouseDebug.MouseCoords(renderer, viewport);
        renderer.end();


    }
    @Override
    public void dispose() {
        renderer.dispose();
        debugFont.dispose();

    }

}
