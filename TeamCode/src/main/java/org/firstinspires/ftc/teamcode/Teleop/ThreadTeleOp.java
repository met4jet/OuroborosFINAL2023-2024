package org.firstinspires.ftc.teamcode.Teleop;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp

public class ThreadTeleOp extends ThreadTelelib{
    @Override
    public void loop() {
        horizontal_lift();
        shoomShoom();
        rflip();
        lflip();
        vertical_lift_left();
        vertical_lift_right();
        drone();
        box();
        linac();
        intake();
        arcadeDrive();
        tMacro();
    }
    @Override
    public void kill(){
        kill();
    }
}