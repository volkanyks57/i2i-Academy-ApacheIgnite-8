package i2iacademy;

public class Subscriber {

    private String customerId;
    private double dataUsage;
    private int smsUsage;
    private int callUsage;

    public Subscriber(String customerId, double dataUsage, int smsUsage, int callUsage) {
        this.customerId = customerId;
        this.dataUsage = dataUsage;
        this.smsUsage = smsUsage;
        this.callUsage = callUsage;
    }

    public String getCustomerId() { return customerId; }
    public double getDataUsage() { return dataUsage; }
    public int getSmsUsage() { return smsUsage; }
    public int getCallUsage() { return callUsage; }

    @Override
    public String toString() {
        return "Subscriber{customerId='" + customerId + "'" +
                ", dataUsage=" + dataUsage + " MB" +
                ", smsUsage=" + smsUsage +
                ", callUsage=" + callUsage + " dk}";
    }
}