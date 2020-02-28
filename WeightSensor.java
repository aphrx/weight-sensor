package weightsensor;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalInput;
import com.pi4j.io.gpio.RaspiPin;

public class WeightSensor {

    private int pin_dout = 4;
    private int pin_sck = 5;

    private GpioPinDigitalInput wsPin;

    public WeightSensor() {
        this.pin_dout = d_out;
        this.pin_sck = sck;
    }

    public void init() {
        final GpioController controller = GpioFactory.getInstance();
        wsPin = controller.provisionDigitalInputPin(RaspiPin.getPinByAddress(pin_dout));
    }

    public Boolean getReading() {
        if (wsPin == null)
            throw new IllegalStateException("Digital input pin not initialized");

        return wsPin.getState().isHigh();
    }
}
