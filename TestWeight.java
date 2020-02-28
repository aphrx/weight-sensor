package weightsensor;

import WeightSensor.*;

public class TestWeight {
	
	public static void main(String[] args){
		WeightSensor ws = new WeightSensor();
		ws.init();
		ws.getReading();	
	}	
	
}
