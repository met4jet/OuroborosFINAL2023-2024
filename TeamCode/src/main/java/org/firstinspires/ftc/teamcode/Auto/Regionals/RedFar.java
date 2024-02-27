package org.firstinspires.ftc.teamcode.Auto.Regionals;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.acmerobotics.roadrunner.trajectory.constraints.AngularVelocityConstraint;
import com.acmerobotics.roadrunner.trajectory.constraints.MinVelocityConstraint;
import com.acmerobotics.roadrunner.trajectory.constraints.TrajectoryVelocityConstraint;
import com.acmerobotics.roadrunner.trajectory.constraints.TranslationalVelocityConstraint;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Auto.HardwareClass.Flip;
import org.firstinspires.ftc.teamcode.Auto.HardwareClass.Intake;
import org.firstinspires.ftc.teamcode.Auto.HardwareClass.ShoomShoom;
import org.firstinspires.ftc.teamcode.Auto.HardwareClass.TrapMotionProfileRight;
import org.firstinspires.ftc.teamcode.Auto.HardwareClass.VerticalLift;
import org.firstinspires.ftc.teamcode.Auto.HardwareClass.HuskyLensMarker;
import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.trajectorysequence.TrajectorySequence;

import java.util.Arrays;

@Autonomous(group = "Auto", name = "RedFarRegionals")
public class RedFar extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);
        HuskyLensMarker hl = new HuskyLensMarker(this);

        String pos = "";

        Pose2d startPose = new Pose2d(-38,-62,Math.toRadians(270));

        Intake intake = new Intake(this);
        Flip flip = new Flip(this);
        TrapMotionProfileRight trapRight = new TrapMotionProfileRight(this);
        VerticalLift vl = new VerticalLift(this);
        ShoomShoom shoom = new ShoomShoom(this);

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
        TrajectorySequence trajSeq4 = drive.trajectorySequenceBuilder(startPose)
                .strafeTo(new Vector2d(-47, 42))
                .build();;
        TrajectorySequence trajSeqMiddle = drive.trajectorySequenceBuilder(startPose)
                .strafeTo(new Vector2d(-47, 42))
                .build();;


        drive.setPoseEstimate(startPose);

        TrajectoryVelocityConstraint slowConstraint = new MinVelocityConstraint(Arrays.asList(

                new TranslationalVelocityConstraint(3),

                new AngularVelocityConstraint(Math.toRadians(80))

        ));

        waitForStart();

        ElapsedTime time = new ElapsedTime();
        while(time.seconds() < 3){
            int position = hl.getPos();
            if (position >= 150 ) {
                telemetry.addData("MIDDLE", hl.getPos());
                telemetry.update();
                pos = "MIDDLE";
            } else if (position < 200 && position > 1) {
                telemetry.addData("LEFT", hl.getPos());
                telemetry.update();
                pos = "LEFT";
            } else {
                telemetry.addData("RIGHT", hl.getPos());
                telemetry.update();
                pos = "RIGHT";
            }
        }

        if (pos.equals("LEFT")) {
            trajSeq1 = drive.trajectorySequenceBuilder(startPose)
                    // ** SPLINE TO
//                    .back(5)
//                    .splineToLinearHeading(new Pose2d(-47,-43,Math.toRadians(90)), Math.toRadians(0))
                    .back(6)
                    .lineToLinearHeading(new Pose2d(-45,-48,Math.toRadians(90)))
                    .forward(5)
                    .setVelConstraint(slowConstraint)
//                    .turn(Math.toRadians(-90))
//                    .back(2.5)
                    .build();
//            .back(8)
//                    .splineToLinearHeading(new Pose2d(-58, -35, Math.toRadians(180)), Math.toRadians(0))
            trajSeq2 = drive.trajectorySequenceBuilder(trajSeq1.end())
//                    .back(3)
//                    .strafeRight(23)
//                    .back(70)
//                    .strafeTo(new Vector2d(43, -20))
//                    .back(10)
                    .back(3)
                    .lineToLinearHeading(new Pose2d(-56,-52, Math.toRadians(180)))
                    //.lineToLinearHeading(new Pose2d(-56,-39,Math.toRadians(180)))
                    //.turn(Math.toRadians(90))
                    .addDisplacementMarker(() -> {
                        intake.axonUp(.25);
                    })
                    .strafeRight(16, SampleMecanumDrive.getVelocityConstraint(30, Math.toRadians(120), 14.95),
                            SampleMecanumDrive.getAccelerationConstraint(30))
                    .forward(1, SampleMecanumDrive.getVelocityConstraint(30, Math.toRadians(120), 14.95),
                            SampleMecanumDrive.getAccelerationConstraint(30))
                    .addDisplacementMarker(() -> {
                        intake.getWhite1(1);
                        intake.axonUp(.25);
                    })
                    .waitSeconds(1)
                    .strafeRight(23)
                    .addDisplacementMarker(() -> {
                        intake.backIntake(1);
                    })
                    .back(70)
                    .addDisplacementMarker(() -> {
                        intake.reverseIntake(1);
                    })
                    .strafeTo(new Vector2d(43,-33))
                    .back(13, SampleMecanumDrive.getVelocityConstraint(30, Math.toRadians(120), 14.95),
                            SampleMecanumDrive.getAccelerationConstraint(30))
                    .setVelConstraint(slowConstraint)

                    .build();
            trajSeqMiddle = drive.trajectorySequenceBuilder(trajSeq2.end())
                    .strafeTo(new Vector2d(43,-30))
                    .back(10, SampleMecanumDrive.getVelocityConstraint(30, Math.toRadians(120), 14.95),
                            SampleMecanumDrive.getAccelerationConstraint(30))
                    .build();

            trajSeq3 = drive.trajectorySequenceBuilder(trajSeq2.end())
//                    .strafeTo(new Vector2d(43, -13))
//                    .back(10)



                    .forward(5)
                    .addDisplacementMarker(() -> {
                        intake.kill();
                    })
                    .strafeTo(new Vector2d(43,-13))

                    .build();

        } else if (pos.equals("MIDDLE")) {
            trajSeq1 = drive.trajectorySequenceBuilder(startPose)
                    // ** SPLINE
                    .back(8)
                    .addDisplacementMarker(() -> {
                        intake.axonUp(.25);
                    })
                    .splineToLinearHeading(new Pose2d(-47, -25, Math.toRadians(0)), Math.toRadians(0))

//                    .back(5)
//                    .turn(Math.toRadians(180))
//                    .forward(21)
                    .build();
//            .strafeLeft(12)
//                    .strafeTo(new Vector2d(-58, -35))
//                    .back(5)
//                    .strafeTo(new Vector2d(-45,-10))
            trajSeq2 = drive.trajectorySequenceBuilder(trajSeq1.end())
                    .addDisplacementMarker(() -> {
                        intake.axonUp(.25);
                    })
                    .lineToLinearHeading(new Pose2d(-55,-35,Math.toRadians(180)), SampleMecanumDrive.getVelocityConstraint(52, Math.toRadians(120), 14.95),
                            SampleMecanumDrive.getAccelerationConstraint(52))
                    .forward(3, SampleMecanumDrive.getVelocityConstraint(30, Math.toRadians(120), 14.95),
                            SampleMecanumDrive.getAccelerationConstraint(30))
                    .addDisplacementMarker(() -> {
                        intake.getWhite1(1);
                        intake.axonUp(.25);
                    })
                    .waitSeconds(1)
                    .back(1)
                    .strafeRight(23)
                    .addDisplacementMarker(() -> {
                        intake.backIntake(2);
                    })
                    .back(70)
                    .addDisplacementMarker(() -> {
                        intake.reverseIntake(1);
                    })
                    .strafeTo(new Vector2d(45,-37))
                    .back(12, SampleMecanumDrive.getVelocityConstraint(30, Math.toRadians(120), 14.95),
                            SampleMecanumDrive.getAccelerationConstraint(30))
//                    .turn(Math.toRadians(90))
//                    .forward(13)
                    .addDisplacementMarker(() -> {
                        vl.moveRightTime(.75);
                        flip.rflip();
                    })
                    .waitSeconds(1.2)
                    .forward(3)
                    .strafeTo(new Vector2d(45, -27))
                    .back(12, SampleMecanumDrive.getVelocityConstraint(30, Math.toRadians(120), 14.95),
                            SampleMecanumDrive.getAccelerationConstraint(30))
                    .addDisplacementMarker(() ->{
                        intake.kill();
                    })
                    .setVelConstraint(slowConstraint)
                    .build();
            trajSeq3 = drive.trajectorySequenceBuilder(trajSeq2.end())

                    .forward(5)
                    .strafeTo(new Vector2d(43, -13))
                    .back(10)
                    .build();
        } else {
            trajSeq1 = drive.trajectorySequenceBuilder(startPose)
                    // *** SPLINE ***
//                    .splineToLinearHeading(new Pose2d(-25, -35, Math.toRadians(90)), Math.toRadians(0), SampleMecanumDrive.getVelocityConstraint(15, Math.toRadians(80), 14.95),
//                            SampleMecanumDrive.getAccelerationConstraint(15))
                    .back(5)
                    .lineToLinearHeading(new Pose2d(-42,-38, Math.toRadians(90)))
                    .strafeRight(19,SampleMecanumDrive.getVelocityConstraint(55, Math.toRadians(180), 14.95),
                            SampleMecanumDrive.getAccelerationConstraint(55))



                    //.lineToLinearHeading(new Pose2d(-47, 37, Math.toRadians(270)))
                    /*
                    .back(27)
                    .turn(Math.toRadians(90))
                    .forward(4)*/
//                    .back(25)
//                    .turn(Math.toRadians(180))
//                    .strafeRight(13)
                    .build();
            trajSeq2 = drive.trajectorySequenceBuilder(trajSeq1.end())
                    //.back(2)
                    .strafeLeft(20)
                    //.forward(2)
                    .lineToLinearHeading(new Pose2d(-56, -39, Math.toRadians(180)),SampleMecanumDrive.getVelocityConstraint(40, Math.toRadians(150), 14.95),
                            SampleMecanumDrive.getAccelerationConstraint(40))

                    /*.back(11)
                    .strafeLeft(22)
                    .turn(Math.toRadians(180))
                    .back(70)
                    .strafeTo(new Vector2d(45, -36))
                    .back(13,
                            SampleMecanumDrive.getVelocityConstraint(30, Math.toRadians(120), 14.95),
                            SampleMecanumDrive.getAccelerationConstraint(30))
                    .setVelConstraint(slowConstraint)*/
                    .build();
            trajSeq3 = drive.trajectorySequenceBuilder(trajSeq2.end())
                    .lineToLinearHeading(new Pose2d(-38, -13, Math.toRadians(180)))
                    .addDisplacementMarker(() -> {
                        intake.backIntake(1);
                    })
                    .back(70)
                    .addDisplacementMarker(() -> {
                        intake.reverseIntake(1);
                    })
                    .strafeTo(new Vector2d(45,-36))
                    .back(12, SampleMecanumDrive.getVelocityConstraint(30, Math.toRadians(120), 14.95),
                            SampleMecanumDrive.getAccelerationConstraint(30))

                    /*.forward(5)
                    .strafeTo(new Vector2d(43, -13))
                    .back(12)*/
                    .build();
            trajSeq4 = drive.trajectorySequenceBuilder(trajSeq3.end())
                    .forward(5)
                    .strafeTo(new Vector2d(-43, -13))
                    .back(6)
                    /*.strafeTo(new Vector2d(43, -13))
                    .back(12)*/
                    .build();
        }



        if (!isStopRequested()) {
            if(pos.equals("LEFT")) {
                drive.followTrajectorySequence(trajSeq1);
                intake.axonUp(.25);
                // UNCOMMENT TO DELIVER:
                intake.deliverPurple(2);
//                drive.followTrajectorySequence(trajSeq2);
//
//                /*intake.axonUp(.3);
//                intake.getWhite(1);*/
//
//                shoom.transferSub();
//
//                sleep(500);
//                vl.moveBothTime(.7);
//                //            //trapRight.motion_profile_PID(2500, 2650, 2500);
//                sleep(450);
//                flip.rflip();
//                sleep(50);
//                flip.lflip();
//                drive.followTrajectorySequence(trajSeq3);

            }
            if(pos.equals("MIDDLE")) {
                drive.followTrajectorySequence(trajSeq1);
                // UNCOMMENT TO DELIVER:
                intake.deliverPurple(.5);
//                drive.followTrajectorySequence(trajSeq2);
//                shoom.transferSub();
//
//                /*intake.axonUp(.3);
//                intake.getWhite(1);*/
//
//
//
//                sleep(500);
//                vl.moveLeftTime(.9);
//                //            //trapRight.motion_profile_PID(2500, 2650, 2500);
//                sleep(450);
//                flip.lflip();
//                drive.followTrajectorySequence(trajSeq3);

            }
            if(pos.equals("RIGHT")) {
                drive.followTrajectorySequence(trajSeq1);
                // UNCOMMENT TO DELIVER:
                intake.axonUp(1);
                intake.deliverPurple(1);
                intake.axonUp(.25);
//                drive.followTrajectorySequence(trajSeq2);
//                intake.getWhite1(2);
//                shoom.transferSub();
//
//                /*intake.axonUp(.3);
//                intake.getWhite(1);*/
//
//
//                drive.followTrajectorySequence(trajSeq3);
//                vl.moveBothTime(.9);
//                //            //trapRight.motion_profile_PID(2500, 2650, 2500);
//                sleep(450);
//                flip.lflip();
//                sleep(50);
//                flip.rflip();
//                drive.followTrajectorySequence(trajSeq4);
            }
//            sleep(1000);
//            drive.followTrajectorySequence(trajSeq3);
//
//
//            shoom.transfer();
//
//            intake.axonUp(1);
//                intake.getWhite(2.5);
//
//            vl.moveRightTime(1);
//            flip.rflip();
        }
    }
}