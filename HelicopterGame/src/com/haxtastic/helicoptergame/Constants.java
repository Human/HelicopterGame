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

public class Constants {
	
	public class Groups {
		public static final String PLAYER_CAR = "player car";
		public static final String ENEMY_CARS = "enemy cars";
		public static final String SCREEN_CHANGE = "screen change";
		public static final String DISTANCE = "distance";
	}
	
	public static float PIXELS_PER_METER_X;
	public static float PIXELS_PER_METER_Y;
	
	public class Layers {
		public static final int PLAYER = (1 << 0);
		public static final int ENEMY = (1 << 1);
		public static final int WALL = (1 << 2);
	}

}
