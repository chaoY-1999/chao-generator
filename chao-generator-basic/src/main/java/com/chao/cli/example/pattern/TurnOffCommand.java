package com.chao.cli.example.pattern;

public class TurnOffCommand implements Command{
    private Device device;

    public TurnOffCommand(Device device) {
        this.device = device;
    }
    @Override
    public void execute() {
        device.turnOff();
    }
}
