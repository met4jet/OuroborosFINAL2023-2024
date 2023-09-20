package org.firstinspires.ftc.teamcode.Teleop;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp
public class Teleop extends Telelib{
    @Override
    public void loop() {
        arcadeDrive();
    }
    @Override
    public void stop(){
        kill();
    }
}
