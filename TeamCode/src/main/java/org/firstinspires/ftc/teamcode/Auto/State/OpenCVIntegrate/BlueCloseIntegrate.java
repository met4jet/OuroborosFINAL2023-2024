package org.firstinspires.ftc.teamcode.Auto.State.OpenCVIntegrate;


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
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.teamcode.Auto.HardwareClass.Flip;
import org.firstinspires.ftc.teamcode.Auto.HardwareClass.Intake;
import org.firstinspires.ftc.teamcode.Auto.HardwareClass.ShoomShoom;
import org.firstinspires.ftc.teamcode.Auto.HardwareClass.VerticalLift;
import org.firstinspires.ftc.teamcode.Auto.State.BluePipeline;
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
@TeleOp
public class BlueCloseIntegrate extends LinearOpMode
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
        BluePipeline detectBlue = new BluePipeline(telemetry);
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
        Pose2d startPose = new Pose2d(14, 62, Math.toRadians(270));

        VerticalLift vl = new VerticalLift(this);
        ShoomShoom shoom = new ShoomShoom(this);

        Intake intake = new Intake(this);
        Flip flip = new Flip(this);

        // IGNORE, FOR ERROR PURPOSES
        TrajectorySequence trajSeq1 = drive.trajectorySequenceBuilder(startPose)
                .strafeTo(new Vector2d(-47, 42))
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
        telemetry.addData("Snapshot post-START analysis", BluePipeline.positionMain);
        telemetry.update();
        waitForStart();
        camera.stopStreaming();

        switch (BluePipeline.positionMain)
        {
            case "left":
            {
                trajSeq1 = drive.trajectorySequenceBuilder(startPose)
                        .splineToLinearHeading(new Pose2d(23, 40.5, Math.toRadians(90)), Math.toRadians(-74.30))
                        .addSpatialMarker((new Vector2d(23, 40)), () -> {
                            intake.deposit();
                        })
                        .waitSeconds(.3)
                        .forward(5)
                        .addDisplacementMarker(() -> {
                            intake.killDeposit();
                        })
                        .lineToLinearHeading(new Pose2d(49, 37, Math.toRadians(180)))
                        .addDisplacementMarker(()->{
                            intake.axonUp(1);
                        })
                        .back(5)
                        .build();
                trajSeq2 = drive.trajectorySequenceBuilder(trajSeq1.end())
                        .forward(1)
                        .splineTo(new Vector2d(15, 11), Math.toRadians(180))
                        .addDisplacementMarker(()->{
                            intake.axonUp(1);
                        })
                        .forward(50)
                        .addDisplacementMarker(()->{
                            intake.axonUp(1);
                        })
                        .forward(5, SampleMecanumDrive.getVelocityConstraint(30, Math.toRadians(120), 14.95),
                                SampleMecanumDrive.getAccelerationConstraint(7))
                        .addDisplacementMarker(() -> {
                            intake.axonUp(1);
                        })
                        .splineToLinearHeading(new Pose2d(-59, 12.2, Math.toRadians(180)), Math.toRadians(180), SampleMecanumDrive.getVelocityConstraint(40, Math.toRadians(120), 15.58),
                                SampleMecanumDrive.getAccelerationConstraint(7))
                        .forward(2.4, SampleMecanumDrive.getVelocityConstraint(30, Math.toRadians(120), 14.95),
                                SampleMecanumDrive.getAccelerationConstraint(7))
                        .build();
                trajSeq3 = drive.trajectorySequenceBuilder(trajSeq2.end())
                        .back(5)
                        .back(75)
                        .splineTo(new Vector2d(47, 35), Math.toRadians(0))
                        .back(9, SampleMecanumDrive.getVelocityConstraint(30, Math.toRadians(120), 14.95),
                                SampleMecanumDrive.getAccelerationConstraint(7))
                        .build();
                trajSeq4 = drive.trajectorySequenceBuilder(trajSeq3.end())
                        .forward(1)
                        .splineToLinearHeading(new Pose2d(43, 46, Math.toRadians(180)), Math.toRadians(180))
                        .back(10)
                        .build();
                break;
            }

            case "right":
            {
                trajSeq1 = drive.trajectorySequenceBuilder(startPose)
                        .splineToLinearHeading(new Pose2d(6.75, 28, Math.toRadians(0)), Math.toRadians(180))
                        .addSpatialMarker((new Vector2d(6.75,28)), () -> {
                            intake.deposit();
                        })
                        .waitSeconds(.3)
                        //.lineToLinearHeading(new Pose2d(49,32, Math.toRadians(180)))

                        .splineToLinearHeading(new Pose2d(47,26, Math.toRadians(180)), Math.toRadians(0))
                        .addDisplacementMarker(() -> {
                            intake.killDeposit();
                        })
                        .back(5, SampleMecanumDrive.getVelocityConstraint(30, Math.toRadians(120), 14.95),
                                SampleMecanumDrive.getAccelerationConstraint(7))
                        .forward(1)
                        .splineTo(new Vector2d(15, 11), Math.toRadians(180))
                        .addDisplacementMarker(() -> {
                            intake.axonUp(1);
                        })
                        .forward(60)
                        .lineToLinearHeading(new Pose2d(-59, 12, Math.toRadians(180))/*, Math.toRadians(180)*/, SampleMecanumDrive.getVelocityConstraint(30, Math.toRadians(120), 14.95),
                                SampleMecanumDrive.getAccelerationConstraint(7))
                        .forward(3, SampleMecanumDrive.getVelocityConstraint(30, Math.toRadians(120), 14.95),
                                SampleMecanumDrive.getAccelerationConstraint(7))
                        .build();
                trajSeq2 = drive.trajectorySequenceBuilder(trajSeq1.end())
                        .back(5)
                        .back(75)
                        .splineTo(new Vector2d(47, 35), Math.toRadians(0))
                        .back(9, SampleMecanumDrive.getVelocityConstraint(30, Math.toRadians(120), 14.95),
                                SampleMecanumDrive.getAccelerationConstraint(7))


                        .build();
                trajSeq3 = drive.trajectorySequenceBuilder(trajSeq2.end())
                        .forward(1)
                        .splineToLinearHeading(new Pose2d(43, 46, Math.toRadians(180)), Math.toRadians(180))
                        .back(10)
                        .build();
                break;
            }

            case "middle":
            {
                trajSeq1 = drive.trajectorySequenceBuilder(startPose)
                        .splineToLinearHeading(new Pose2d(29, 20, Math.toRadians(0)), Math.toRadians(270))
                        .splineToLinearHeading(new Pose2d(22, 22.2, Math.toRadians(0)), Math.toRadians(0))
                        .addSpatialMarker((new Vector2d(22,21.2)), () -> {
                            intake.deposit();
                        })
                        .splineToLinearHeading(new Pose2d(47,30, Math.toRadians(180)), Math.toRadians(0))
                        .addDisplacementMarker(() -> {
                            intake.killDeposit();
                        })
                        .back(5, SampleMecanumDrive.getVelocityConstraint(30, Math.toRadians(120), 14.95),
                                SampleMecanumDrive.getAccelerationConstraint(7))

                        .forward(1)
                        .splineTo(new Vector2d(15, 11), Math.toRadians(180))
                        .addDisplacementMarker(() -> {
                            intake.axonUp(1);
                        })
                        .forward(55)
                        .lineToLinearHeading(new Pose2d(-59, 12, Math.toRadians(180))/*, Math.toRadians(180)*/, SampleMecanumDrive.getVelocityConstraint(30, Math.toRadians(120), 14.95),
                                SampleMecanumDrive.getAccelerationConstraint(7))
                        .forward(3, SampleMecanumDrive.getVelocityConstraint(30, Math.toRadians(120), 14.95),
                                SampleMecanumDrive.getAccelerationConstraint(7))
                        .build();
                trajSeq2 = drive.trajectorySequenceBuilder(trajSeq1.end())
                        .back(5)
                        .addDisplacementMarker(() -> {
                            intake.backIntake(1);
                        })
                        .back(75)
                        .splineTo(new Vector2d(47, 35), Math.toRadians(0))
                        .addDisplacementMarker(() -> {
                            intake.inIntake();
                        })
                        .back(9, SampleMecanumDrive.getVelocityConstraint(30, Math.toRadians(120), 14.95),
                                SampleMecanumDrive.getAccelerationConstraint(7))
                        .build();
                trajSeq3 = drive.trajectorySequenceBuilder(trajSeq2.end())
                        .forward(1)
                        .splineToLinearHeading(new Pose2d(43, 52, Math.toRadians(180)), Math.toRadians(180))
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
                vl.moveRightTime(1);
                flip.rflip();
                vl.moveDownRightTime(1);
                intake.getWhite2new(0.1);
                intake.axonUp(0.1);
                drive.followTrajectorySequence(trajSeq2);
                intake.axonUp(.1);
                intake.getWhite2(1);
                intake.getWhite2low(4);
                sleep(200);
                drive.followTrajectorySequence(trajSeq3);
                shoom.transfer();
                flip.lflipHold();
                vl.moveBothTime(1);
                flip.rflipUno();
                flip.lflip();
                sleep(100);
                drive.followTrajectorySequence(trajSeq4);
//
//            }
//            // Don't burn CPU cycles busy-looping in this sample
//            sleep(50);
        }
    }
}

