import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalInput;
import com.pi4j.io.gpio.RaspiPin;
import com.qac.sensors.Sensor;

public class WeightSensor extends Sensor<Boolean> {

    private final int pin_dout;
    private final int pin_sck;

    private GpioPinDigitalInput swPin;

    public WeightSensor(d_out, sck) {
        this.pin_dout = d_out;
        this.pin_sck = sck;
    }

    @Override
    public void init() {
        final GpioController controller = GpioFactory.getInstance();
        wsPin = controller.provisionDigitalInputPin(RaspiPin.getPinByAddress(pin));
    }

    @Override
    public Boolean getReading() {
        if (wsPin == null)
            throw new IllegalStateException("Digital input pin not initialized");

        return wsPin.getState().isHigh();
    }
}