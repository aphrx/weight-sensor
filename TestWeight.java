import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalInput;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.RaspiPin;

class WeightSensor {

    private final GpioPinDigitalOutput pin_dout;
    private final GpioPinDigitalInput pin_sck;
    private int gain;

    private GpioPinDigitalInput wsPin;

    public WeightSensor() {
        this.pin_dout = 5;
        this.pin_sck = 6;
        this.gain = 120;

    }

    public void init() {
        final GpioController controller = GpioFactory.getInstance();
        wsPin = controller.provisionDigitalInputPin(RaspiPin.getPinByAddress(pin_dout));
    }

    public int read() {
    	pin_dout.setState(PinState.LOW);
    	while(!isReady()){
    		sleep(1);
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
    	return value;

    }

    public int getReading() {
        if (wsPin == null)
            throw new IllegalStateException("Digital input pin not initialized");

        return read();
    }
}

public class TestWeight {
	
	public static void main(String[] args){
		WeightSensor ws = new WeightSensor();
		ws.init();
		System.out.println(ws.getReading());	
	}	
	
}
