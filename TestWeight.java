import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalInput;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.RaspiPin;
import com.pi4j.io.gpio.PinState;

class WeightSensor {
	private GpioController controller;

    private final GpioPinDigitalOutput pin_dout;
    private final GpioPinDigitalInput pin_sck;
    private int gain;

    public WeightSensor() {
    	controller = GpioFactory.getInstance();
        this.pin_dout = controller.provisionDigitalOutputPin(RaspiPin.getPinByAddress(5));
        this.pin_sck = controller.provisionDigitalInputPin(RaspiPin.getPinByAddress(6));
        //this.pin_sck = 6;
        this.gain = 24;

    }

    public long read() {
    	pin_dout.setState(PinState.LOW);

    	while(!pin__sck.isLow()){
    		try {
            	Thread.sleep(delay);
        	} catch (Exception ex) {
        	}
    	}

    	long count = 0;
    	for (int i=0; i< this.gain; i++){
    		pin_dout.setState(PinState.HIGH);
    		count = count << 1;
    		pin_dout.setState(PinState.LOW);
    		if(pin_sck.isHigh()){
    			count++;
    		}
    	}

    	pin_dout.setState(PinState.HIGH);
    	count = count ^ 0x800000;
    	pin_dout.setState(PinState.LOW);
    	return count;

    }
}

public class TestWeight {
	
	public static void main(String[] args){
		WeightSensor ws = new WeightSensor();
		ws.init();
		System.out.println(ws.read());	
	}	
	
}
