package org.firstinspires.ftc.teamcode.Auto.Random;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Auto.HardwareClass.HuskyLensDetection;

//@Autonomous(group = "Auto", name = "HuskyPID")
public class HuskyPIDTest extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        HuskyLensDetection hl = new HuskyLensDetection(this, .0095, 0, 0);
        while(!opModeIsActive()){
            hl.findPt();
        }
        waitForStart();
        hl.huskyPID1();
    }
}
