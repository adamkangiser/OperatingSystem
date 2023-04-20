import java.util.ArrayList;
import java.util.List;

public class CPU {
    private final int numCores;
    private final List<Process> runningProcesses;

    private int totalExecutedCycles;
    private int totalIdleCycles;

    public CPU(int numCores) {
        this.numCores = numCores;
        this.runningProcesses = new ArrayList<>(numCores);

        this.totalExecutedCycles = 0;
        this.totalIdleCycles = 0;
    }

    public void scheduleProcess(Process process) {
        if (runningProcesses.size() < numCores) {
            runningProcesses.add(process);
        }
    }

    public void executeCycles() {
        List<Process> processesCopy = new ArrayList<>(runningProcesses);
        boolean isIdle = true;

        for (Process process : processesCopy) {
            process.executeCycle();
            isIdle = isIdle && !process.isProcessing();

            if (!process.isProcessing()) {
                runningProcesses.remove(process);
            }
        }

        if (isIdle && !runningProcesses.isEmpty()) {
            totalIdleCycles++;
        }
        if (!runningProcesses.isEmpty()) {
            totalExecutedCycles++;
        }
    }




    public double getUtilization() {
        return 1 - ((double) totalIdleCycles / totalExecutedCycles);
    }
}
