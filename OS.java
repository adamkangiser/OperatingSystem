import java.util.LinkedList;
import java.util.Queue;

public class OS {
    private final int timeQuantum;
    private final CPU cpu;
    private final Queue<Process> arrivalQueue;
    private Process currentProcess;
    private int timeQuantumLeft;

    public OS(int timeQuantum, CPU cpu) {
        this.timeQuantum = timeQuantum;
        this.cpu = cpu;
        this.arrivalQueue = new LinkedList<>();
        this.currentProcess = null;
        this.timeQuantumLeft = 0;
    }

    public void addProcess(Process process) {
        arrivalQueue.add(process);
    }

    public void executeCycle() {
        // Check if the current process has exceeded its time quantum
        if (currentProcess != null && timeQuantumLeft == 0) {
            cpu.scheduleProcess(currentProcess);
            currentProcess = null;
        }

        // Check if there is a new process to schedule
        if (currentProcess == null && !arrivalQueue.isEmpty()) {
            currentProcess = arrivalQueue.remove();
            timeQuantumLeft = timeQuantum;
        }

        // Execute one cycle of the current process
        if (currentProcess != null) {
            currentProcess.executeCycle();
            timeQuantumLeft--;
            if (!currentProcess.isProcessing()) {
                currentProcess = null;
            }
        }

        // Execute one cycle of the CPU
        cpu.executeCycles();
    }
}
