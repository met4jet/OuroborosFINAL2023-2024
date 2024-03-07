package org.firstinspires.ftc.teamcode.Auto.Random;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Teleop.ThreadTelelib;

@TeleOp

public class TeleopOutake extends TelelibOutake {
    @Override
    public void loop() {
        //vertical_lift_right();
        //vertical_lift_left();
        //lflip();
        rflip();
        deposit();

    }
    @Override
    public void kill(){
        kill();
    }
}