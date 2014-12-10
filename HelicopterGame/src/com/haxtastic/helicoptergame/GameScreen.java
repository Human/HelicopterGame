/*
Copyright 2014 Magnus Bridén

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
*/
package com.haxtastic.helicoptergame;

import com.artemis.World;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.haxtastic.helicoptergame.systems.AnimationRenderSystem;
import com.haxtastic.helicoptergame.systems.CameraSystem;
import com.haxtastic.helicoptergame.systems.CollisionSystem;
import com.haxtastic.helicoptergame.systems.DistanceSystem;
import com.haxtastic.helicoptergame.systems.HudRenderSystem;
import com.haxtastic.helicoptergame.systems.InterpolationSystem;
import com.haxtastic.helicoptergame.systems.LevelSystem;
import com.haxtastic.helicoptergame.systems.PlayerInputSystem;
import com.haxtastic.helicoptergame.systems.RestartSystem;
import com.haxtastic.helicoptergame.systems.SaveSystem;
import com.haxtastic.helicoptergame.systems.SimulationSystem;
import com.haxtastic.helicoptergame.systems.SpriteRenderSystem;
import com.haxtastic.helicoptergame.systems.WallSystem;
import com.haxtastic.helicoptergame.EntityFactory;

public class GameScreen implements Screen {
	//private Game game;
	private World world;
	private OrthographicCamera camera;
	//private Box2DDebugRenderer debugRenderer;
	
	private float accum = 0;
	private float dt = 1.0f / 45.0f;
	//private float t = 0;
	
	private SimulationSystem simulationSystem;
	private HudRenderSystem hudRenderSystem;
	private SpriteRenderSystem spriteRenderSystem;
	private AnimationRenderSystem animationRenderSystem;
	private InterpolationSystem interpolationSystem;
	private CameraSystem cameraSystem;
	
	
	public GameScreen(Game g, World w, OrthographicCamera c) {
		//game = g;
		world = w;
		camera = c;
		
		EntityFactory.cleanWorld(world);
		
		world.setSystem(new DistanceSystem());
		cameraSystem 			= 	world.setSystem(new CameraSystem(camera), true);
		interpolationSystem 	= 	world.setSystem(new InterpolationSystem(), true);
		simulationSystem 		= 	world.setSystem(new SimulationSystem(0.0f, -17.0f));
		spriteRenderSystem 		= 	world.setSystem(new SpriteRenderSystem(camera), true);
		hudRenderSystem 		= 	world.setSystem(new HudRenderSystem(new OrthographicCamera(Helicoptergame.FRAME_WIDTH, Helicoptergame.FRAME_HEIGHT)), true);
		animationRenderSystem 	=	world.setSystem(new AnimationRenderSystem(camera), true);
		
		world.setSystem(new WallSystem(simulationSystem));
		world.setSystem(new LevelSystem(simulationSystem));
		world.setSystem(new RestartSystem(simulationSystem));
		world.setSystem(new PlayerInputSystem());
		CollisionSystem col = new CollisionSystem();
		world.setSystem(col);
		world.setSystem(new SaveSystem());
		
		world.initialize();
		world.setDelta(dt);
		
		simulationSystem.simulation.setContactListener(col);
		
		EntityFactory.createPlayer(world, simulationSystem, 2, 4.5f).addToWorld();
		
		//debugRenderer = new Box2DDebugRenderer();
	}
	
	@Override
	public void render(float delta) {
		Gdx.gl10.glClear(GL10.GL_COLOR_BUFFER_BIT);
		camera.update();
		
		if(delta > 0.45f)
			delta = 0.45f;
		
		accum += delta;
		
		while(accum >= dt){
			world.process();
			//t += dt;
			accum -= dt;
		}
		
		simulationSystem.simulation.clearForces();
		interpolationSystem.alpha = accum/dt;
		//interpolationSystem.process();
		
		cameraSystem.process();
		
		spriteRenderSystem.process();
		animationRenderSystem.process();
		hudRenderSystem.process();
		//debugRenderer.render(sim.simulation, camera.combined.scale(Constants.PIXELS_PER_METER_X, Constants.PIXELS_PER_METER_Y, 1.0f));
	}
	
	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void show() {
	}

	@Override
	public void hide() {
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}

	@Override
	public void dispose() {
	}

}
