import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalInput;
import com.pi4j.io.gpio.RaspiPin;

class WeightSensor {

    private final int pin_dout;
    private final int pin_sck;

    private GpioPinDigitalInput wsPin;

    public WeightSensor() {
        this.pin_dout = 4;
        this.pin_sck = 5;
    }

    public void init() {
        final GpioController controller = GpioFactory.getInstance();
        wsPin = controller.provisionDigitalInputPin(RaspiPin.getPinByAddress(pin_dout));
    }

    public Boolean getReading() {
        if (wsPin == null)
            throw new IllegalStateException("Digital input pin not initialized");

        return "Hello";
    }
}

public class TestWeight {
	
	public static void main(String[] args){
		WeightSensor ws = new WeightSensor();
		ws.init();
		System.out.println(ws.getReading());	
	}	
	
}
