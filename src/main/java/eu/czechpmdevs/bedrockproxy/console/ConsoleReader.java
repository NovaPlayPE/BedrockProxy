package eu.czechpmdevs.bedrockproxy.console;

import lombok.Setter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.ConcurrentLinkedQueue;

public class ConsoleReader extends Thread {

    @Setter
    private boolean isRunning = true;

    protected ConcurrentLinkedQueue<String> outputStream = new ConcurrentLinkedQueue<String>();

    public String readConsole() {
        return this.outputStream.poll();
    }

    @Override
    public void run() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        while (this.isRunning) {
            try {
                String command = reader.readLine();
                this.outputStream.add(command);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
