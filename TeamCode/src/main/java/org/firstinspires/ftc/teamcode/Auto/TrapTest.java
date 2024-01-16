package org.firstinspires.ftc.teamcode.Auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Autonomous(group = "Auto", name = "TrapTest")

public class TrapTest extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        waitForStart();

        TrapMotionProfileRight trapRight = new TrapMotionProfileRight(this);

        trapRight.motion_profile_PID(10, 2781.1, 100);
    }
}
