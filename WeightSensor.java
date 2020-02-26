import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalInput;
import com.pi4j.io.gpio.RaspiPin;

public class WeightSensor {

    private final int pin_dout;
    private final int pin_sck;

    private GpioPinDigitalInput wsPin;

    public WeightSensor(int d_out, int sck) {
        this.pin_dout = d_out;
        this.pin_sck = sck;
    }

    public void init() {
        final GpioController controller = GpioFactory.getInstance();
        wsPin = controller.provisionDigitalInputPin(RaspiPin.getPinByAddress(pin));
    }

    public Boolean getReading() {
        if (wsPin == null)
            throw new IllegalStateException("Digital input pin not initialized");

        return wsPin.getState().isHigh();
    }

    public static void main(String[] args) {
        System.out.println("Hello world");
    }
}