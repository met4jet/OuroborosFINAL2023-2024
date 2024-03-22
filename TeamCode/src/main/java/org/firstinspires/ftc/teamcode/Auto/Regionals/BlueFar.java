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
import org.firstinspires.ftc.teamcode.Auto.HardwareClass.HuskyLensDetection;
import org.firstinspires.ftc.teamcode.Auto.HardwareClass.Intake;
import org.firstinspires.ftc.teamcode.Auto.HardwareClass.ShoomShoom;
import org.firstinspires.ftc.teamcode.Auto.HardwareClass.TrapMotionProfileRight;
import org.firstinspires.ftc.teamcode.Auto.HardwareClass.VerticalLift;
import org.firstinspires.ftc.teamcode.Auto.HardwareClass.HuskyLensMarker;
import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.trajectorysequence.TrajectorySequence;

import java.util.Arrays;

//@Autonomous(group = "Auto", name = "BlueFarRegionals")
public class BlueFar extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        HuskyLensDetection husky = new HuskyLensDetection(this, 0, 0, 0);
        SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);
        Pose2d startPose = new Pose2d(-38,62,Math.toRadians(90));
        TrapMotionProfileRight trapRight = new TrapMotionProfileRight(this);
        VerticalLift vl = new VerticalLift(this);
        ShoomShoom shoom = new ShoomShoom(this);

        HuskyLensMarker hl = new HuskyLensMarker(this);

        String pos = "";


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
                .build();;
        TrajectorySequence trajSeq3 = drive.trajectorySequenceBuilder(startPose)
                .strafeTo(new Vector2d(-47, 42))
                .build();;
        TrajectorySequence trajSeq4 = drive.trajectorySequenceBuilder(startPose)
                .strafeTo(new Vector2d(-47, 42))
                .build();;

        drive.setPoseEstimate(startPose);

        TrajectoryVelocityConstraint slowConstraint = new MinVelocityConstraint(Arrays.asList(

                new TranslationalVelocityConstraint(20),

                new AngularVelocityConstraint(Math.toRadians(80))

        ));


        waitForStart();
        ElapsedTime time = new ElapsedTime();
        while(time.seconds() < 3){
            int position = hl.getPos();
            if (position >= 180 ) {
                telemetry.addData("RIGHT", hl.getPos());
                telemetry.update();
                pos = "RIGHT";
            } else if (position < 180 && position > 1) {
                telemetry.addData("MIDDLE", hl.getPos());
                telemetry.update();
                pos = "MIDDLE";
            } else {
                telemetry.addData("LEFT", hl.getPos());
                telemetry.update();
                pos = "LEFT";
            }
        }

        pos= "MIDDLE";

        if (pos.equals("LEFT")) {
            trajSeq1 = drive.trajectorySequenceBuilder(startPose)
                    .back(5)
                    .lineToLinearHeading(new Pose2d(-42,38.5, Math.toRadians(270)))
                    .strafeLeft(18.5,SampleMecanumDrive.getVelocityConstraint(55, Math.toRadians(180), 14.95),
                            SampleMecanumDrive.getAccelerationConstraint(55))
//                    ,SampleMecanumDrive.getVelocityConstraint(55, Math.toRadians(180), 14.95),
//                                                SampleMecanumDrive.getAccelerationConstraint(55)
//                    .back(25)
//                    .turn(Math.toRadians(-90))
//                    .forward(4)
                    .build();
            trajSeq2 = drive.trajectorySequenceBuilder(trajSeq1.end())
                    .strafeRight(20)
                    .lineToLinearHeading(new Pose2d(-56, 38, Math.toRadians(180)))
                    //SampleMecanumDrive.getVelocityConstraint(40, Math.toRadians(150), 14.95),
                    //                            SampleMecanumDrive.getAccelerationConstraint(40)
                    .build();
            trajSeq3 = drive.trajectorySequenceBuilder(trajSeq2.end())

                    .back(3)
                    .strafeLeft(25)
                    .back(70)
                    .addDisplacementMarker(() -> {
                        intake.backIntake(2);
                    })
                    .strafeTo(new Vector2d(48, 38))
                    .back(8,SampleMecanumDrive.getVelocityConstraint(40, Math.toRadians(150), 14.95),
                            SampleMecanumDrive.getAccelerationConstraint(40))
                    .build();

            trajSeq4 = drive.trajectorySequenceBuilder(trajSeq3.end())
                    .strafeTo(new Vector2d(43, 13))
                    .back(4)
                    .build();

        } else if (pos.equals("MIDDLE")) {
            trajSeq1 = drive.trajectorySequenceBuilder(startPose)
                    .back(8)
                    .addDisplacementMarker(() -> {
                        intake.axonUp(.25);
                    })
                    .splineToLinearHeading(new Pose2d(-47, 26, Math.toRadians(0)), Math.toRadians(0))
                    .build();
            trajSeq2 = drive.trajectorySequenceBuilder(trajSeq1.end())
                    .addDisplacementMarker(() -> {
                        intake.axonUp(.25);
                    })
                    .back(4)
                    .lineToLinearHeading(new Pose2d(-55,35,Math.toRadians(180)), SampleMecanumDrive.getVelocityConstraint(52, Math.toRadians(120), 14.95),
                            SampleMecanumDrive.getAccelerationConstraint(52))
                    .forward(3, SampleMecanumDrive.getVelocityConstraint(30, Math.toRadians(120), 14.95),
                            SampleMecanumDrive.getAccelerationConstraint(30))
                    .addDisplacementMarker(() -> {
                        intake.getWhite1(1);
                        intake.axonUp(.25);
                    })
                    .waitSeconds(1)
                    .back(1)
                    .strafeLeft(23)
                    .addDisplacementMarker(() -> {
                        intake.backIntake(2);
                    })
                    .back(70)
                    .addDisplacementMarker(() -> {
                        intake.reverseIntake(1);
                    })
                    .strafeTo(new Vector2d(45,28))
                    .back(12, SampleMecanumDrive.getVelocityConstraint(30, Math.toRadians(120), 14.95),
                            SampleMecanumDrive.getAccelerationConstraint(30))
//                    .turn(Math.toRadians(90))
//                    .forward(13)
                    .addDisplacementMarker(() -> {
                        vl.moveBothTime(.75);
                        flip.rflip();
                        flip.lflip();
                    })
                    .waitSeconds(1.2)
//                    .forward(3)
//                    .strafeTo(new Vector2d(45, 29))
                    .back(1, SampleMecanumDrive.getVelocityConstraint(30, Math.toRadians(120), 14.95),
                            SampleMecanumDrive.getAccelerationConstraint(30))
                    .addDisplacementMarker(() ->{
                        intake.kill();
                    })
                    .build();
            trajSeq3 = drive.trajectorySequenceBuilder(trajSeq2.end())
                    .forward(5)
                    .strafeTo(new Vector2d(43, 13))
                    .back(10)
                    .build();
        } else {
            trajSeq1 = drive.trajectorySequenceBuilder(startPose)
                    .back(6)
                    .lineToLinearHeading(new Pose2d(-45,48,Math.toRadians(90)))
                    .forward(5)
                    .setVelConstraint(slowConstraint)
                    .build();
            /*trajSeqHalf = drive.trajectorySequenceBuilder(trajSeq1.end())
                    .back(4)
                    .lineToLinearHeading(new Pose2d(-65, 38, Math.toRadians(180)))
                    // ADD INTAKE
                    .forward(5)
                    .setVelConstraint(slowConstraint)
                    .build();*/
            trajSeq2 = drive.trajectorySequenceBuilder(trajSeqHalf.end())

                    .back(3)
                    .lineToLinearHeading(new Pose2d(-56,52, Math.toRadians(180)))
                    //.lineToLinearHeading(new Pose2d(-56,-39,Math.toRadians(180)))
                    //.turn(Math.toRadians(90))
                    .addDisplacementMarker(() -> {
                        intake.axonUp(.25);
                    })
                    .strafeLeft(16, SampleMecanumDrive.getVelocityConstraint(30, Math.toRadians(120), 14.95),
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
                    .strafeTo(new Vector2d(43,33))
                    .back(13, SampleMecanumDrive.getVelocityConstraint(30, Math.toRadians(120), 14.95),
                            SampleMecanumDrive.getAccelerationConstraint(30))
                    .setVelConstraint(slowConstraint)
                    .build();
            trajSeq3 = drive.trajectorySequenceBuilder(trajSeq2.end())
                    .forward(5)
                    .addDisplacementMarker(() -> {
                        intake.kill();
                    })
                    .strafeTo(new Vector2d(43,13))
                    .build();
        }

        if (!isStopRequested()) {

            if(pos.equals("LEFT")) {
                intake.axonUp(.25);
                drive.followTrajectorySequence(trajSeq1);
                intake.deliverPurple(.5);

//                drive.followTrajectorySequence(trajSeq2);
//                intake.getWhite(1.5
//                );
//
//                drive.followTrajectorySequence(trajSeq3);
//
//                shoom.transferSub();
//                sleep(500);
//
//                vl.moveBothTime(1);
//                sleep(1010);
//                flip.rflip();
//                flip.lflip();
//                sleep(1000);
//                drive.followTrajectorySequence(trajSeq4);
            }
            else if (pos.equals("MIDDLE")){
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
        }
    }
}