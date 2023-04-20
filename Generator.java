public class Generator {
    private final int maxArrivalTime;
    private int currentTime;

    public Generator(int maxArrivalTime) {
        this.maxArrivalTime = maxArrivalTime;
        this.currentTime = 0;
    }

    public Process generateProcess() {
        Process process = new Process(currentTime);
        this.currentTime += (int) (Math.random() * maxArrivalTime) + 1; // Random arrival time
        return process;
    }
}
