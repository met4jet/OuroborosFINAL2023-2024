package org.firstinspires.ftc.teamcode.Auto.Random;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Auto.HardwareClass.HuskyLensDetection;

@Autonomous(group = "Auto", name = "HuskyTest")

public class HuskyTest extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {
        HuskyLensDetection hl = new HuskyLensDetection(this, 0, 0, 0);
        hl.findPt();
    }
}