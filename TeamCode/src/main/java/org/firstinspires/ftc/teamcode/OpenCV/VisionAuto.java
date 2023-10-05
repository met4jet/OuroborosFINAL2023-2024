package org.firstinspires.ftc.teamcode.OpenCV;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvInternalCamera;

@Autonomous(name="Pixel Detector", group="Auto")
public class VisionAuto extends LinearOpMode {

    OpenCvCamera vision;
    @Override
    public void runOpMode() throws InterruptedException {
        int cameraMonitorViewId = hardwareMap.appContext
                .getResources().getIdentifier("cameraMonitorViewId",
                        "id", hardwareMap.appContext.getPackageName());
        vision = OpenCvCameraFactory.getInstance()
                .createInternalCamera(OpenCvInternalCamera.CameraDirection.BACK, cameraMonitorViewId);
        Detector detector = new Detector(telemetry);
        vision.setPipeline(detector);
        /*vision.openCameraDeviceAsync(
                () -> vision.startStreaming(320, 240, OpenCvCameraRotation.UPRIGHT)
        );*/

        waitForStart();
        switch (detector.getLocation()){
            case FRONT:
                //...
                break;
            case MIDDLE:
                //...
                break;
            case BACK:
                //...
                break;
            case NOT_FOUND:
                //...
        }
        vision.stopStreaming();

    }
}