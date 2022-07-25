import org.hibernate.annotations.Check;

import javax.validation.constraints.NotNull;

public class MeasurementDTO {

    @NotNull(message = "Field should not be empty")
    @Check(constraints = "value > -101 && value<101")
    private double value;
    @NotNull
    private SensorDTO sensor;

    public MeasurementDTO() {
    }

    public MeasurementDTO(double value, SensorDTO sensor) {
        this.value = value;
        this.sensor = sensor;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public SensorDTO getSensor() {
        return sensor;
    }

    public void setSensor(SensorDTO sensor) {
        this.sensor = sensor;
    }
}
