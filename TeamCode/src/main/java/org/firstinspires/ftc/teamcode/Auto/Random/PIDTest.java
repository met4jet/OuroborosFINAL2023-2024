package org.firstinspires.ftc.teamcode.Auto.Random;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Auto.HardwareClass.VerticalLift;

@Autonomous(group = "Auto", name = "PIDTest")

public class PIDTest extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        waitForStart();

        VerticalLift lift = new VerticalLift(this);
        //Down is positive
        //Up is negative
        //lift.movePID(100, 0.003,0.001,0.0005, 2);
        //lift.movePIDLeft(1000, 0.01,0.000,0.000, 2);
        sleep(1000);
        lift.movePIDRight(1000, 0.021,0.0005,0.0003, 2);
        lift.movePIDRight(-1000, 0.021,0.0005,0.0003, 2);

        //sleep(1000);
        //lift.movePID()

    }
}