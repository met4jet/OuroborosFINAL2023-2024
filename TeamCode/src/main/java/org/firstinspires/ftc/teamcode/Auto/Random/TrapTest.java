package org.firstinspires.ftc.teamcode.Auto.Random;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Auto.HardwareClass.TrapMotionProfileRight;

@Autonomous(group = "Auto", name = "TrapTest")

public class TrapTest extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        waitForStart();

        TrapMotionProfileRight trapRight = new TrapMotionProfileRight(this);

        // max velocity (tics/sec) :: 2781.1

        trapRight.motion_profile_PID(2500, 2650, 2500);
    }
}
