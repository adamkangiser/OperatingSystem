public class MasterClock {
    private int currentTime;

    public MasterClock() {
        this.currentTime = 0;
    }

    public int getCurrentTime() {
        return currentTime;
    }

    public void tick() {
        currentTime++;
    }
}
