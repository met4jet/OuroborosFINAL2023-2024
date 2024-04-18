package org.firstinspires.ftc.teamcode.Auto.Worlds;


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

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.acmerobotics.roadrunner.trajectory.constraints.AngularVelocityConstraint;
import com.acmerobotics.roadrunner.trajectory.constraints.MinVelocityConstraint;
import com.acmerobotics.roadrunner.trajectory.constraints.TrajectoryVelocityConstraint;
import com.acmerobotics.roadrunner.trajectory.constraints.TranslationalVelocityConstraint;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.teamcode.Auto.HardwareClass.Flip;
import org.firstinspires.ftc.teamcode.Auto.HardwareClass.Intake;
import org.firstinspires.ftc.teamcode.Auto.HardwareClass.ShoomShoom;
import org.firstinspires.ftc.teamcode.Auto.HardwareClass.VerticalLift;
import org.firstinspires.ftc.teamcode.Auto.State.BluePipeline;
import org.firstinspires.ftc.teamcode.Auto.State.RedPipeline;
import org.firstinspires.ftc.teamcode.Auto.State.StateOpenCVBlueClose;
import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.trajectorysequence.TrajectorySequence;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;
import org.openftc.easyopencv.OpenCvWebcam;

import java.util.Arrays;

/*
 * This sample demonstrates how to run analysis during INIT
 * and then snapshot that value for later use when the START
 * command is issued. The pipeline is re-used from SkystoneDeterminationExample
 */
@Autonomous(group = "Auto", name = "BlueFarWorldsIntegrated")
public class BlueFarWorldsIntegrated extends LinearOpMode
{
    OpenCvWebcam camera;
    StateOpenCVBlueClose.SkystoneDeterminationPipeline pipeline;
    StateOpenCVBlueClose.SkystoneDeterminationPipeline.SkystonePosition snapshotAnalysis = StateOpenCVBlueClose.SkystoneDeterminationPipeline.SkystonePosition.LEFT; // default

    @Override
    public void runOpMode()
    {
        /**
         * NOTE: Many comments have been omitted from this sample for the
         * sake of conciseness. If you're just starting out with EasyOpenCv,
         * you should take a look at {@link InternalCamera1Example} or its
         * webcam counterpart, {@link WebcamExample} first.
         */

        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "identifyier","teamcode");
        camera = OpenCvCameraFactory.getInstance().createWebcam(hardwareMap.get(WebcamName.class, "Webcam 1"));
        RedPipeline detectBlue = new RedPipeline(telemetry);
        camera.setPipeline(detectBlue);

        camera.setMillisecondsPermissionTimeout(5000);

        camera.openCameraDeviceAsync(new OpenCvCamera.AsyncCameraOpenListener()
        {
            @Override
            public void onOpened()
            {

                camera.startStreaming(320,240, OpenCvCameraRotation.UPRIGHT);
            }

            @Override
            public void onError(int errorCode)
            {



            }
        });



        SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);
        Pose2d startPose = new Pose2d(14, -62, Math.toRadians(90));

        VerticalLift vl = new VerticalLift(this);
        ShoomShoom shoom = new ShoomShoom(this);

        Intake intake = new Intake(this);
        Flip flip = new Flip(this);

        // IGNORE, FOR ERROR PURPOSES
        TrajectorySequence trajSeq1 = drive.trajectorySequenceBuilder(startPose)
                .strafeTo(new Vector2d(8, -37))
                .build();
        TrajectorySequence trajSeqHalf = drive.trajectorySequenceBuilder(startPose)
                .strafeTo(new Vector2d(-47, 42))
                .build();
        TrajectorySequence trajSeq2 = drive.trajectorySequenceBuilder(startPose)
                .strafeTo(new Vector2d(-47, 42))
                .build();
        ;
        TrajectorySequence trajSeq3 = drive.trajectorySequenceBuilder(startPose)
                .strafeTo(new Vector2d(-47, 42))
                .build();
        ;
        TrajectorySequence trajSeq4 = drive.trajectorySequenceBuilder(startPose)
                .strafeTo(new Vector2d(-47, 42))
                .build();
        ;

        drive.setPoseEstimate(startPose);

        TrajectoryVelocityConstraint slowConstraint = new MinVelocityConstraint(Arrays.asList(

                new TranslationalVelocityConstraint(20),

                new AngularVelocityConstraint(Math.toRadians(80))

        ));

        /*
         * The INIT-loop:
         * This REPLACES waitForStart!
         */
        /*while (!isStarted() && !isStopRequested())
        {
            telemetry.addData("Realtime analysis", pipeline.getAnalysis());
            telemetry.update();

            // Don't burn CPU cycles busy-looping in this sample
            sleep(50);
        }

        /*
         * The START command just came in: snapshot the current analysis now
         * for later use. We must do this because the analysis will continue
         * to change as the camera view changes once the robot starts moving!
         */
        //snapshotAnalysis = pipeline.getAnalysis();

        /*
         * Show that snapshot on the telemetry
         */
        telemetry.addData("Snapshot post-START analysis", RedPipeline.positionMain);
        telemetry.update();
        waitForStart();
        camera.stopStreaming();

        switch (RedPipeline.positionMain)
        {
            case "left":
            {
                trajSeq1 = drive.trajectorySequenceBuilder(startPose)
                        .splineToLinearHeading(new Pose2d(-57,-28, Math.toRadians(180)), Math.toRadians(90))
                        .back(1)
                        .waitSeconds(.3)
                        .addSpatialMarker((new Vector2d(-57, -28)), () -> {
                            intake.deposit();
                        })
                        .lineToLinearHeading(new Pose2d(-57,-33.5, Math.toRadians(180)))
                        .splineToLinearHeading(new Pose2d(-45,-55, Math.toRadians(180)), Math.toRadians(0))
                        .back(60,
                                SampleMecanumDrive.getVelocityConstraint(55, Math.toRadians(180), 14.95),
                                SampleMecanumDrive.getAccelerationConstraint(55))
                        .splineTo(new Vector2d(51,-35), Math.toRadians(0))
                        //.splineToLinearHeading(new Pose2d(51,35, Math.toRadians(180)), Math.toRadians(0))
                        .build();
                trajSeq2 = drive.trajectorySequenceBuilder(trajSeq1.end())
                        .forward(1)
                        .splineToLinearHeading(new Pose2d(43,-13, Math.toRadians(180)), Math.toRadians(180))
                        .back(10)
                        .build();
                break;
            }

            case "right":
            {
                trajSeq1 = drive.trajectorySequenceBuilder(startPose)
                        .splineToLinearHeading(new Pose2d(-33, -27, Math.toRadians(180)), Math.toRadians(0))
                        .lineToLinearHeading(new Pose2d(-55,-34, Math.toRadians(180)))
                        .waitSeconds(1)
                        .addSpatialMarker((new Vector2d(-55, -34)), () -> {
                            intake.deposit();
                        })
                        .back(1)
                        .splineToLinearHeading(new Pose2d(-40,-57, Math.toRadians(180)), Math.toRadians(180))
                        .back(60,SampleMecanumDrive.getVelocityConstraint(55, Math.toRadians(180), 14.95),
                                SampleMecanumDrive.getAccelerationConstraint(55))
                        .splineTo(new Vector2d(51,-35), Math.toRadians(0))
                        .back(5)
                        //.splineToLinearHeading(new Pose2d(51,35, Math.toRadians(180)), Math.toRadians(0))
                        .build();
                trajSeq2 = drive.trajectorySequenceBuilder(trajSeq1.end())
                        .forward(1)
                        .splineToLinearHeading(new Pose2d(43,-13, Math.toRadians(180)), Math.toRadians(180))
                        .back(10)
                        .build();
                break;
            }

            case "middle":
            {
                trajSeq1 = drive.trajectorySequenceBuilder(startPose)
                        .splineToLinearHeading(new Pose2d(-43, -20, Math.toRadians(180)), Math.toRadians(90))
                        .lineToLinearHeading(new Pose2d(-57.5, -20, Math.toRadians(180)))
                        .back(1)
                        .waitSeconds(.5)
                        .addSpatialMarker((new Vector2d(-57.5, -20)), () -> {
                            intake.deposit();
                        })
                        .splineToLinearHeading(new Pose2d(-50,-57, Math.toRadians(180)), Math.toRadians(0))
                        .back(45,
                                SampleMecanumDrive.getVelocityConstraint(55, Math.toRadians(180), 14.95),
                                SampleMecanumDrive.getAccelerationConstraint(55))
                        .splineTo(new Vector2d(51,-35), Math.toRadians(0))
                        .back(2,
                                SampleMecanumDrive.getVelocityConstraint(55, Math.toRadians(180), 14.95),
                                SampleMecanumDrive.getAccelerationConstraint(55))
                        .build();
                trajSeq2 = drive.trajectorySequenceBuilder(trajSeq1.end())
                        .forward(1)
                        .splineToLinearHeading(new Pose2d(43,-13, Math.toRadians(180)), Math.toRadians(180))
                        .back(10)
                        .build();
                break;
            }
        }
//
//        /* You wouldn't have this in your autonomous, this is just to prevent the sample from ending */
//        while (opModeIsActive())
//        {
        if (!isStopRequested()) {
            drive.followTrajectorySequence(trajSeq1);
            flip.holdRightFlip();
            vl.moveRightTime(.6);
            flip.rflip();
            drive.followTrajectorySequence(trajSeq2);
//
//            }
//            // Don't burn CPU cycles busy-looping in this sample
//            sleep(50);
        }
    }
}

