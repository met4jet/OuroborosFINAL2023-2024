package org.firstinspires.ftc.teamcode.OpenCV;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.acmerobotics.roadrunner.trajectory.constraints.AngularVelocityConstraint;
import com.acmerobotics.roadrunner.trajectory.constraints.MinVelocityConstraint;
import com.acmerobotics.roadrunner.trajectory.constraints.TrajectoryVelocityConstraint;
import com.acmerobotics.roadrunner.trajectory.constraints.TranslationalVelocityConstraint;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;


import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.teamcode.Auto.HardwareClass.Flip;
import org.firstinspires.ftc.teamcode.Auto.HardwareClass.Intake;
import org.firstinspires.ftc.teamcode.Auto.HardwareClass.VerticalLift;
import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.trajectorysequence.TrajectorySequence;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;
import org.opencv.imgproc.Moments;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;
import org.openftc.easyopencv.OpenCvPipeline;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Autonomous(group = "Auto", name = "OpenCVBlueFar")

public class OpenCVBlueFar extends LinearOpMode {

    double cX = 0;
    double cY = 0;
    double width = 0;

    private OpenCvCamera controlHubCam;  // Use OpenCvCamera class from FTC SDK
    private static final int CAMERA_WIDTH = 640; // width  of wanted camera resolution
    private static final int CAMERA_HEIGHT = 360; // height of wanted camera resolution

    // Calculate the distance using the formula

    @Override
    public void runOpMode() {

        SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);
        Pose2d startPose = new Pose2d(-40, 62, Math.toRadians(270));

        Intake intake = new Intake(this);
        VerticalLift vl = new VerticalLift(this);
        Flip flip = new Flip(this);

        // IGNORE, FOR ERROR PURPOSES
        TrajectorySequence trajSeq1 = drive.trajectorySequenceBuilder(startPose)
                .strafeTo(new Vector2d(-47, 42))
                .build();
        TrajectorySequence trajSeq2 = drive.trajectorySequenceBuilder(startPose)
                .strafeTo(new Vector2d(-47, 42))
                .build();;
        TrajectorySequence trajSeq3 = drive.trajectorySequenceBuilder(startPose)
                .strafeTo(new Vector2d(-47, 42))
                .build();;

        drive.setPoseEstimate(startPose);

        TrajectoryVelocityConstraint fastConstraint = new MinVelocityConstraint(Arrays.asList(

                new TranslationalVelocityConstraint(60),

                new AngularVelocityConstraint(1)

        ));

        initOpenCV();
        FtcDashboard dashboard = FtcDashboard.getInstance();
        telemetry = new MultipleTelemetry(telemetry, dashboard.getTelemetry());
        FtcDashboard.getInstance().startCameraStream(controlHubCam, 30);
        HuskyLensMarker hl = new HuskyLensMarker(this);


        sleep(300);

        telemetry.addData("Coordinate", "(" + (int) cX + ", " + (int) cY + ")");
        telemetry.update();

        sleep(3000);

        // The OpenCV pipeline automatically processes frames and handles detection

        if (getPos().equals("LEFT")) {
            trajSeq1 = drive.trajectorySequenceBuilder(startPose)
                    .forward(25)
                    .turn(Math.toRadians(90))
                    .forward(2)
                    .build();
            trajSeq2 = drive.trajectorySequenceBuilder(trajSeq1.end())
                    .setVelConstraint(fastConstraint)
                    .back(8)
                    .strafeRight(28)
                    .turn(Math.toRadians(180))
                    .back(70)
                    .strafeTo(new Vector2d(45, 40))
                    .back(3)
                    .build();
            trajSeq3 = drive.trajectorySequenceBuilder(trajSeq2.end())
                    .strafeTo(new Vector2d(43, 18))
                    .back(5)
                    .build();

        } else if (getPos().equals("MIDDLE")) {
            trajSeq1 = drive.trajectorySequenceBuilder(startPose)
                    .strafeTo(new Vector2d(-30, 25))
            //.forward(29)
                    .build();
            trajSeq2 = drive.trajectorySequenceBuilder(trajSeq1.end())
                    .setVelConstraint(fastConstraint)
                    .strafeRight(14)
                    .forward(23)
                    .turn(Math.toRadians(-90))
                    .back(100)
                    // **** CHANGE ****
                    .strafeTo(new Vector2d(49, 34))
                    .back(3)
                    .build();
            trajSeq3 = drive.trajectorySequenceBuilder(trajSeq2.end())
                    .strafeTo(new Vector2d(43, 18))
                    .back(6)
                    .build();
        } else {
            trajSeq1 = drive.trajectorySequenceBuilder(startPose)
                    .strafeTo(new Vector2d(-47, 42))
                    .build();
            trajSeq2 = drive.trajectorySequenceBuilder(trajSeq1.end())
                    .setVelConstraint(fastConstraint)
                    .strafeLeft(11)
                    .turn(Math.toRadians(-95))
                    .strafeLeft(32)
                    .back(100)
                    // *****CHANGE******
                    .strafeTo(new Vector2d(49, 31))
                    .back(7)
                    .build();
            trajSeq3 = drive.trajectorySequenceBuilder(trajSeq2.end())
                    .strafeTo(new Vector2d(45, 18))
                    .back(7)
                    .build();
        }

        waitForStart();

        if (!isStopRequested()) {
            if(getPos().equals("LEFT")){
                drive.followTrajectorySequence(trajSeq1);
                intake.deliverPurple(5);
                drive.followTrajectorySequence(trajSeq2);
                vl.movePIDLeft(4000, 0.01,0.000,0.000, 3);
                flip.lflip();
                vl.movePIDLeft(-2000, 0.01,0.000,0.000, 2);
                drive.followTrajectorySequence(trajSeq3);
            }
            else if (getPos().equals("MIDDLE")) {
                drive.followTrajectorySequence(trajSeq1);
                intake.deliverPurple(5);
                drive.followTrajectorySequence(trajSeq2);
                vl.movePIDLeft(4000, 0.01, 0, 0, 3);
                flip.lflip();
                vl.movePIDLeft(-2000, 0.01, 0, 0, 2);
                drive.followTrajectorySequence(trajSeq3);
            }
            else {
                drive.followTrajectorySequence(trajSeq1);
                intake.deliverPurple(5);
                drive.followTrajectorySequence(trajSeq2);
                vl.movePIDLeft(4000, 0.01, 0, 0, 3);
                flip.lflip();
                vl.movePIDLeft(-2000, 0.01, 0, 0, 2);
                drive.followTrajectorySequence(trajSeq3);
            }
        }

        // Release resources
        controlHubCam.stopStreaming();
    }

    public void initOpenCV() {

        // Create an instance of the camera
        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier(
                "cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());

        // Use OpenCvCameraFactory class from FTC SDK to create camera instance
        controlHubCam = OpenCvCameraFactory.getInstance().createWebcam(
                hardwareMap.get(WebcamName.class, "Webcam 1"), cameraMonitorViewId);

        controlHubCam.setPipeline(new YellowBlobDetectionPipeline());

        controlHubCam.openCameraDevice();
        controlHubCam.startStreaming(CAMERA_WIDTH, CAMERA_HEIGHT, OpenCvCameraRotation.UPRIGHT);
    }

    public String getPos(){
        HuskyLensMarker hl = new HuskyLensMarker(this);

        String output = "";

        if(hl.getPos() > 45 && hl.getPos() <= 200) {
            output = "MIDDLE";
        }
        else if(hl.getPos() > 200){
            output = "RIGHT";
        }
        else{
            output = "LEFT";
        }
        /*telemetry.addData("Coordinate", "(" + (int) cX + ")");
        telemetry.addData("Output", "(" + (int) cX + ", " + (int) cY + ")");
        telemetry.addData("Output :: ", output);*/
        telemetry.addData("cX", hl.getPos());
        telemetry.update();
        return output;
    }
    public class YellowBlobDetectionPipeline extends OpenCvPipeline {
        @Override
        public Mat processFrame(Mat input) {
            // Preprocess the frame to detect yellow regions
            Mat yellowMask = preprocessFrame(input);

            // Find contours of the detected yellow regions
            List<MatOfPoint> contours = new ArrayList<>();
            Mat hierarchy = new Mat();
            Imgproc.findContours(yellowMask, contours, hierarchy, Imgproc.RETR_EXTERNAL, Imgproc.CHAIN_APPROX_SIMPLE);

            // Find the largest yellow contour (blob)
            MatOfPoint largestContour = findLargestContour(contours);

            if (largestContour != null) {

                // Draw a red outline around the largest detected object
                Imgproc.drawContours(input, contours, contours.indexOf(largestContour), new Scalar(255, 0, 0), 2);
                // Calculate the width of the bounding box
                width = calculateWidth(largestContour);

                // Display the width next to the label
                String widthLabel = "Width: " + (int) width + " pixels";
                Imgproc.putText(input, widthLabel, new Point(cX + 10, cY + 20), Imgproc.FONT_HERSHEY_SIMPLEX, 0.5, new Scalar(0, 255, 0), 2);
                //Display the Distance
                // Calculate the centroid of the largest contour
                Moments moments = Imgproc.moments(largestContour);
                cX = moments.get_m10() / moments.get_m00();
                cY = moments.get_m01() / moments.get_m00();

                // Draw a dot at the centroid
                String label = "(" + (int) cX + ", " + (int) cY + ")";
                Imgproc.putText(input, label, new Point(cX + 10, cY), Imgproc.FONT_HERSHEY_COMPLEX, 0.5, new Scalar(0, 255, 0), 2);
                Imgproc.circle(input, new Point(cX, cY), 5, new Scalar(0, 255, 0), -1);

            }
            return input;
        }

        private Mat preprocessFrame(Mat frame) {
            Mat hsvFrame = new Mat();
            Imgproc.cvtColor(frame, hsvFrame, Imgproc.COLOR_BGR2HSV);

            Scalar lowerYellow = new Scalar(0, 50, 100);
            Scalar upperYellow = new Scalar(30, 255, 255);

            //0-30 PERFECT
            //30-60 detect light blue
            //120-125, yellow to red
            //sat 120-255

            // 105-125


            Mat yellowMask = new Mat();
            Core.inRange(hsvFrame, lowerYellow, upperYellow, yellowMask);

            Mat kernel = Imgproc.getStructuringElement(Imgproc.MORPH_RECT, new Size(5, 5));
            Imgproc.morphologyEx(yellowMask, yellowMask, Imgproc.MORPH_OPEN, kernel);
            Imgproc.morphologyEx(yellowMask, yellowMask, Imgproc.MORPH_CLOSE, kernel);

            return yellowMask;
        }

        private MatOfPoint findLargestContour(List<MatOfPoint> contours) {
            double maxArea = 0;
            MatOfPoint largestContour = null;

            for (MatOfPoint contour : contours) {
                double area = Imgproc.contourArea(contour);
                if (area > maxArea) {
                    maxArea = area;
                    largestContour = contour;
                }
            }

            return largestContour;
        }
        private double calculateWidth(MatOfPoint contour) {
            Rect boundingRect = Imgproc.boundingRect(contour);
            return boundingRect.width;
        }
    }
}