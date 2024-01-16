package org.firstinspires.ftc.teamcode.Auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Autonomous(group = "Auto", name = "HuskyTest")

public class HuskyTest extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {
        HuskyLensDetection hl = new HuskyLensDetection(this, 0, 0, 0);
        hl.findPt();
    }
}