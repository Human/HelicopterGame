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
import com.badlogic.gdx.graphics.g2d.Animation;

public class AnimationSprite extends Component {
	public enum Layer {
		DEFAULT,
		BACKGROUND,
		ACTORS_1,
		ACTORS_2,
		ACTORS_3,
		PARTICLES;
		
		public int getLayerId() {
			return ordinal();
		}
	}
	
	public String name;
	public Animation animation;
	public int width;
	public int height;
	public int base;
	public int rows;
	public int cols;
	public float frameTime;
	public boolean active;
	public boolean loop;
	public float time = 0;
	public float scaleX = 1;
	public float scaleY = 1;
	public float rotation;
	public float r = 1;
	public float g = 1;
	public float b = 1;
	public float a = 1;
	public Layer layer = Layer.DEFAULT;
}
