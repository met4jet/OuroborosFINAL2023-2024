package org.firstinspires.ftc.teamcode.Teleop;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp
public class Teleop extends Telelib2{
    @Override
    public void loop() {
        intake();
        arcadeDrive();
        horizontal_lift();
        //vertical_lift();
        rflip();
        lflip();
        shoomShoom();
        box();
    }
    @Override
    public void stop(){
        kill();
    }
}
