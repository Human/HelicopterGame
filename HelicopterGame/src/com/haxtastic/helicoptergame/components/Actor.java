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
package com.haxtastic.helicoptergame.components;

import com.artemis.Component;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;



public class Actor extends Component {
	public Body actor;
	public String name;
	
	public Actor(World world, BodyType bt){
        BodyDef bd = new BodyDef();
        bd.type = bt;

        actor = world.createBody(bd);
	}
	
	public Actor(World world, float x, float y, BodyType bt){
        BodyDef bd = new BodyDef();
        bd.type = bt;
        bd.position.set(x, y);

        actor = world.createBody(bd);
	}
	
	public Actor(World world, Vector2 pos, BodyType bt){
        BodyDef bd = new BodyDef();
        bd.type = bt;
        bd.position.set(pos.x, pos.y);

        actor = world.createBody(bd);
	}
}
