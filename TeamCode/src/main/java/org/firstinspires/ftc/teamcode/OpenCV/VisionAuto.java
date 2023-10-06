package org.firstinspires.ftc.teamcode.OpenCV;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;
import org.openftc.easyopencv.OpenCvInternalCamera;

@Autonomous(name="Pixel Detector", group="Auto")
public class VisionAuto extends LinearOpMode {

    OpenCvCamera vision;
    private PixelDetector detector = new PixelDetector();
    private String position;
    @Override
    public void runOpMode() throws InterruptedException {
        int cameraMonitorViewId = hardwareMap.appContext
                .getResources().getIdentifier("cameraMonitorViewId",
                        "id", hardwareMap.appContext.getPackageName());
        vision = OpenCvCameraFactory.getInstance()
                .createInternalCamera(OpenCvInternalCamera.CameraDirection.BACK, cameraMonitorViewId);
        vision.openCameraDevice();
        vision.setPipeline(detector);

        vision.startStreaming(320,240, OpenCvCameraRotation.SIDEWAYS_LEFT);


        while(!isStarted()){
            position = detector.position;
            telemetry.addData("position", position);
        }

        // code while robot is running

        if(position.equals("LEFT")){

        }
        else if(position.equals("RIGHT")){

        }
        else{

        }
    }
}