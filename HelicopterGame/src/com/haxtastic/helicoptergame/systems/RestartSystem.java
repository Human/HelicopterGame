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
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.haxtastic.helicoptergame.Constants;
import com.haxtastic.helicoptergame.components.Actor;
import com.haxtastic.helicoptergame.components.Player;
import com.haxtastic.helicoptergame.components.Position;
import com.haxtastic.helicoptergame.components.Velocity;

public class RestartSystem extends EntityProcessingSystem {
	@Mapper
	ComponentMapper<Actor> am;
	
	private SimulationSystem sim;
	
	@SuppressWarnings("unchecked")
	public RestartSystem(SimulationSystem s) {
		super(Aspect.getAspectForAll(Position.class).exclude(Player.class));
		sim = s;
	}

	@Override
	protected void initialize() {
	}
	
	@Override
	protected void begin() {
	}

	@Override
	protected boolean checkProcessing() {
		if(world.getManager(GroupManager.class).getEntities(Constants.Groups.PLAYER_CAR).get(0).getComponent(Player.class).restart)
			return true;
		else
			return false;
	}

	protected void process(Entity e) {
		if(am.has(e)){
			Body actor = am.get(e).actor;
			sim.simulation.destroyBody(actor);
			actor.setUserData(null);
			actor = null;
		}
		e.deleteFromWorld();
	}

	protected void end() {
		Entity player = world.getManager(GroupManager.class).getEntities(Constants.Groups.PLAYER_CAR).get(0);
		player.getComponent(Player.class).alive = true;
		Position pos = player.getComponent(Position.class);
		Body actor = player.getComponent(Actor.class).actor;
		actor.setUserData((Object)0);
		pos.x = 2;
		pos.y = 4.5f;
		Velocity vel = player.getComponent(Velocity.class);
		vel.velocity.set(0, 0);
		actor.setTransform(new Vector2(2, 4.5f), actor.getAngle());
		actor.setLinearVelocity(0f, 0f);
		LevelSystem ls = world.getSystem(LevelSystem.class);
		WallSystem ws = world.getSystem(WallSystem.class);
		ls.initialize();
		ws.initialize();
		player.getComponent(Player.class).alive = true;
		player.getComponent(Player.class).restart = false;
	}
}
