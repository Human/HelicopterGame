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
import com.artemis.managers.GroupManager;
import com.artemis.systems.EntityProcessingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.haxtastic.helicoptergame.Constants;
import com.haxtastic.helicoptergame.components.Distance;
import com.haxtastic.helicoptergame.components.Player;

public class SaveSystem extends EntityProcessingSystem {
	@Mapper ComponentMapper<Distance> dm;
	
	private Preferences prefs;
	private float prevBest = 0f;

	@SuppressWarnings("unchecked")
	public SaveSystem() {
		super(Aspect.getAspectForAll(Distance.class));
	}
	
	@Override
	protected void initialize() {
		prefs = Gdx.app.getPreferences("flygaviltspelet");
		Entity e = world.createEntity();
		Distance dist = new Distance();
		dist.bestDistance = prefs.getFloat("best");
		e.addComponent(dist);
		world.getManager(GroupManager.class).add(e, Constants.Groups.DISTANCE);
		e.addToWorld();
	}
	
	@Override
	protected void begin() {
	}
	
	@Override
	protected boolean checkProcessing() {
		if(!world.getManager(GroupManager.class).getEntities(Constants.Groups.PLAYER_CAR).get(0).getComponent(Player.class).alive)
			return true;
		else
			return false;
	}
	
	protected void process(Entity e) {
		Distance dist = dm.get(e);
		if(prevBest < dist.bestDistance){
			prefs.putFloat("best", dist.bestDistance);
			prevBest = dist.bestDistance;
			prefs.flush();
		}
	}

	protected void end() {
	}
}
