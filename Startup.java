import java.util.Scanner;

public class Startup {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Number of CPUs: ");
        int numCpus = scanner.nextInt();

        System.out.print("Time quantum: ");
        int timeQuantum = scanner.nextInt();

        System.out.print("Max arrival time: ");
        int maxArrivalTime = scanner.nextInt();

        CPU cpu = new CPU(numCpus);
        OS os = new OS(timeQuantum, cpu);
        Generator generator = new Generator(maxArrivalTime);
        MasterClock masterClock = new MasterClock();

        int totalTurnaroundTime = 0;
        int totalResponseTime = 0;
        int totalNumProcesses = 0;
        int totalCpuUtilization = 0;

        while (true) {
            // Generate new process
            Process process = generator.generateProcess();
            os.addProcess(process);

            // Execute one cycle of OS and CPU
            os.executeCycle();
            masterClock.tick();

            // Update performance metrics
            if (!process.isProcessing()) {
                int turnaroundTime = masterClock.getCurrentTime() - process.getArrivalTime();
                int responseTime = turnaroundTime - (process.getCyclesLeft() + 1);
                totalTurnaroundTime += turnaroundTime;
                totalResponseTime += responseTime;
                totalNumProcesses++;
            }
            totalCpuUtilization += cpu.getUtilization();

            // Check if simulation is done
            if (masterClock.getCurrentTime() >= 5000) {
                break;
            }
        }

        scanner.close();

        // Output performance metrics
        double avgTurnaroundTime = (double) totalTurnaroundTime / totalNumProcesses;
        double avgResponseTime = (double) totalResponseTime / totalNumProcesses;
        double cpuUtilization = (double) totalCpuUtilization / (masterClock.getCurrentTime() * numCpus);
        double throughput = (double) totalNumProcesses / masterClock.getCurrentTime();
        System.out.println("Average turnaround time: " + avgTurnaroundTime);
        System.out.println("Average response time: " + avgResponseTime);
        System.out.println("CPU utilization: " + cpuUtilization);
        System.out.println("Throughput: " + throughput);
    }
}
