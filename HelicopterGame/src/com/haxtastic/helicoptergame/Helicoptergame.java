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
import com.artemis.managers.GroupManager;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.OrthographicCamera;

public class Helicoptergame extends Game {
	public static final int FRAME_WIDTH = 1920;
	public static final int FRAME_HEIGHT = 1080;
	
	@Override
	public void create() {
		World world = new World();
		world.setManager(new GroupManager());
		OrthographicCamera camera = new OrthographicCamera(Helicoptergame.FRAME_WIDTH, Helicoptergame.FRAME_HEIGHT);
		Constants.PIXELS_PER_METER_X = Helicoptergame.FRAME_WIDTH/16;
		Constants.PIXELS_PER_METER_Y = Helicoptergame.FRAME_HEIGHT/9;
		camera.position.set(0, camera.viewportHeight/2, 0);
		camera.update();
		setScreen(new TitleScreen(this, world, camera));
	}
}
