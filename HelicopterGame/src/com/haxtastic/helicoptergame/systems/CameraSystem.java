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
import com.artemis.annotations.Mapper;
import com.artemis.systems.EntityProcessingSystem;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.haxtastic.helicoptergame.Constants;
import com.haxtastic.helicoptergame.components.Player;
import com.haxtastic.helicoptergame.components.Position;

public class CameraSystem extends EntityProcessingSystem {
	@Mapper ComponentMapper<Position> pm;
	
	OrthographicCamera camera;
	float cameraOffset;
	
	@SuppressWarnings("unchecked")
	public CameraSystem(OrthographicCamera camera) {
		super(Aspect.getAspectForAll(Player.class));
		this.camera = camera;
		cameraOffset = 6f;
	}
	
	@Override
	protected void initialize() {
	}

	@Override
	protected void process(Entity e) {
		Position pos = pm.get(e);
		camera.translate(((pos.x + cameraOffset) * Constants.PIXELS_PER_METER_X) - camera.position.x, 0);
		camera.update();
	}

}
