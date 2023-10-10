package org.firstinspires.ftc.teamcode.Auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.OpenCV.OpenCVTest;
import org.openftc.easyopencv.OpenCvWebcam;

@Autonomous(group = "Auto", name = "RedLeft")

public class RedLeft extends LinearOpMode {

    OpenCvWebcam webcam = null;
    @Override
    public void runOpMode() throws InterruptedException {

        waitForStart();

        OpenCVTest openCVTest = new OpenCVTest();
        String output = openCVTest.getPos();
        if(output.equals("LEFT")){

        }
        wait(10000);
    }
}
