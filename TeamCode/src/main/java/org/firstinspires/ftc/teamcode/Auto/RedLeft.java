package org.firstinspires.ftc.teamcode.Auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.OpenCV.OpenCVRed;

@Autonomous(group = "Auto", name = "RedLeft")

public class RedLeft extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {

        waitForStart();

        OpenCVRed openCVTest = new OpenCVRed();

        String output = openCVTest.getPos();
        if(output.equals("LEFT")){

        }
    }
}
