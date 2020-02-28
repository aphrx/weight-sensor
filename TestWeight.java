import WeightSensor

public class TestWeight {
	
	public static void main(String[] args){
		final WeightSensor ws = new WeightSensor();
		ws.init();
		ws.getReading();	
	}	
	
}
