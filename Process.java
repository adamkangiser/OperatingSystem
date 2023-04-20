import java.util.Random;

public class Process {
    private final int arrivalTime;
    private int cyclesLeft;
    private boolean isProcessing;

    private final Random rand = new Random();

    public Process(int arrivalTime) {
        this.arrivalTime = arrivalTime;
        this.cyclesLeft = rand.nextInt(10) + 1; // Random number of cycles
        this.isProcessing = true;
    }

    public int getArrivalTime() {
        return arrivalTime;
    }

    public int getCyclesLeft() {
        return cyclesLeft;
    }

    public boolean isProcessing() {
        return isProcessing;
    }

    public void executeCycle() {
        if (rand.nextBoolean()) { // 50% chance of I/O
            this.isProcessing = false;
            this.cyclesLeft = rand.nextInt(10) + 1; // Random number of I/O cycles
        } else {
            this.isProcessing = true;
            this.cyclesLeft--;
        }
    }
}
