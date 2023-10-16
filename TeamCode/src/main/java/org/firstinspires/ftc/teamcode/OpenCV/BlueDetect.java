/*
 * Copyright (c) 2021 OpenFTC Team
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package org.firstinspires.ftc.teamcode.OpenCV;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.openftc.apriltag.AprilTagDetection;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;

import java.util.ArrayList;

public class BlueDetect
{
    public LinearOpMode opMode;
    OpenCvCamera camera;
    AprilTagDetectionPipeline aprilTagDetectionPipeline;

    static final double FEET_PER_METER = 3.28084;

    // Lens intrinsics
    // UNITS ARE PIXELS
    // NOTE: this calibration is for the C920 webcam at 800x448.
    // You will need to do your own calibration for other configurations!
    double fx = 578.272;
    double fy = 578.272;
    double cx = 402.145;
    double cy = 221.506;

    // UNITS ARE METERS
    double tagsize = 0.166;

    int ID_TAG_OF_INTEREST = 18; // Tag ID 18 from the 36h11 family

    //Tag IDs of Backdrop
    // BLUE
    int bLeft = 1;
    int bMiddle = 2;
    int bRight = 3;

    //RED
    /*int rLeft = 4;
    int rMiddle = 5;
    int rRight = 6;*/
    AprilTagDetection tagOfInterest = null;

    public BlueDetect(LinearOpMode opMode) {
        this.opMode = opMode;

        int cameraMonitorViewId = opMode.hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", opMode.hardwareMap.appContext.getPackageName());
        camera = OpenCvCameraFactory.getInstance().createWebcam(opMode.hardwareMap.get(WebcamName.class, "Webcam 1"), cameraMonitorViewId);
        aprilTagDetectionPipeline = new AprilTagDetectionPipeline(tagsize, fx, fy, cx, cy);

        camera.setPipeline(aprilTagDetectionPipeline);
        camera.openCameraDeviceAsync(new OpenCvCamera.AsyncCameraOpenListener()
        {
            @Override
            public void onOpened()
            {
                camera.startStreaming(800,448, OpenCvCameraRotation.UPRIGHT);
            }

            @Override
            public void onError(int errorCode)
            {

            }
        });

        opMode.telemetry.setMsTransmissionInterval(50);

    }

    public void returnPosition() {

        ArrayList<AprilTagDetection> currentDetections = aprilTagDetectionPipeline.getLatestDetections();

        if (currentDetections.size() != 0) {
            boolean tagFound = false;

            for (AprilTagDetection tag : currentDetections) {
                if (tag.id == bLeft || tag.id == bMiddle || tag.id == bRight) {
                    tagOfInterest = tag;
                    tagFound = true;
                    break;
                }
            }

            if (tagFound) {
                opMode.telemetry.addLine("Tag of interest is in sight!\n\nLocation data:");
                tagToTelemetry(tagOfInterest);
            } else {
                opMode.telemetry.addLine("Don't see tag of interest :(");

                if (tagOfInterest == null) {
                    opMode.telemetry.addLine("(The tag has never been seen)");
                } else {
                    opMode.telemetry.addLine("\nBut we HAVE seen the tag before; last seen at:");
                    tagToTelemetry(tagOfInterest);
                }
            }

        } else {
            opMode.telemetry.addLine("Don't see tag of interest :(");

            if (tagOfInterest == null) {
                opMode.telemetry.addLine("(The tag has never been seen)");
            } else {
                opMode.telemetry.addLine("\nBut we HAVE seen the tag before; last seen at:");
                tagToTelemetry(tagOfInterest);
            }

        }

        opMode.telemetry.update();
        opMode.sleep(20);

        /*
         * The START command just came in: now work off the latest snapshot acquired
         * during the init loop.
         */

        /* Update the telemetry */
        if (tagOfInterest != null) {
            opMode.telemetry.addLine("Tag snapshot:\n");
            tagToTelemetry(tagOfInterest);
            opMode.telemetry.update();
        } else {
            opMode.telemetry.addLine("No tag snapshot available, it was never sighted during the init loop :(");
            opMode.telemetry.update();
        }

        if (tagOfInterest == null || tagOfInterest.id == bLeft) {
            // blue left
        } else if (tagOfInterest.id == bMiddle) {
            // blue middle
        } else if (tagOfInterest.id == bRight) {
            // blue right
        }

    }

        /* You wouldn't have this in your autonomous, this is just to prevent the sample from ending */
        //while (opModeIsActive()) {sleep(20);}

    void tagToTelemetry(AprilTagDetection detection)
    {
        Orientation rot = Orientation.getOrientation(detection.pose.R, AxesReference.INTRINSIC, AxesOrder.YXZ, AngleUnit.DEGREES);

        opMode.telemetry.addLine(String.format("\nDetected tag ID=%d", detection.id));
        opMode.telemetry.addLine(String.format("Translation X: %.2f feet", detection.pose.x*FEET_PER_METER));
        opMode.telemetry.addLine(String.format("Translation Y: %.2f feet", detection.pose.y*FEET_PER_METER));
        opMode.telemetry.addLine(String.format("Translation Z: %.2f feet", detection.pose.z*FEET_PER_METER));
        opMode.telemetry.addLine(String.format("Rotation Yaw: %.2f degrees", rot.firstAngle));
        opMode.telemetry.addLine(String.format("Rotation Pitch: %.2f degrees", rot.secondAngle));
        opMode.telemetry.addLine(String.format("Rotation Roll: %.2f degrees", rot.thirdAngle));
    }
}