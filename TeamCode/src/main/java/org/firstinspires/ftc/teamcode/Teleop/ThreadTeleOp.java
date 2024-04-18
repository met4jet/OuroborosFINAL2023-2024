package org.firstinspires.ftc.teamcode.Teleop;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp

public class ThreadTeleOp extends ThreadTelelib{
    @Override
    public void loop() {
        //deposit();
        axon_hangs();
        shoomShoom();
        rflip();
        lflip();
        vertical_lift_left();
        vertical_lift_right();
        drone();
        intake();
        arcadeDrive();
        axon_linac();
        killHang();
    }
    @Override
    public void kill(){
        kill();
    }
}