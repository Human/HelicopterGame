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
package com.haxtastic.helicoptergame.systems;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.World;
import com.artemis.annotations.Mapper;
import com.artemis.systems.EntityProcessingSystem;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.haxtastic.helicoptergame.GameScreen;
import com.haxtastic.helicoptergame.components.ScreenChange;
import com.haxtastic.helicoptergame.components.Sprite;

public class ScreenChangeSystem extends EntityProcessingSystem {
	@Mapper ComponentMapper<ScreenChange> sm;
	
	private Game game;
	private World world;
	private OrthographicCamera camera;
	
	@SuppressWarnings("unchecked")
	public ScreenChangeSystem(Game g, World w, OrthographicCamera c) {
		super(Aspect.getAspectForAll(Sprite.class, ScreenChange.class));
		game = g;
		world = w;
		camera = c;
	}

	@Override
	protected void initialize() {
	}
	
	@Override
	protected void begin() {
	}

	@Override
	protected boolean checkProcessing() {
		return true;
	}

	protected void process(Entity e) {
		ScreenChange screen = sm.get(e);
		if(screen.change == true){
			e.deleteFromWorld();
			game.setScreen(new GameScreen(game, world, camera));
		}
	}

	protected void end() {
	}
}
