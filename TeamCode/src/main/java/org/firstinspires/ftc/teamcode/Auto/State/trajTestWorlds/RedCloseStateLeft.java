package org.firstinspires.ftc.teamcode.Auto.State.trajTestWorlds;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.acmerobotics.roadrunner.trajectory.constraints.AngularVelocityConstraint;
import com.acmerobotics.roadrunner.trajectory.constraints.MinVelocityConstraint;
import com.acmerobotics.roadrunner.trajectory.constraints.TrajectoryVelocityConstraint;
import com.acmerobotics.roadrunner.trajectory.constraints.TranslationalVelocityConstraint;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Auto.HardwareClass.Flip;
import org.firstinspires.ftc.teamcode.Auto.HardwareClass.HuskyLensDetection;
import org.firstinspires.ftc.teamcode.Auto.HardwareClass.HuskyLensMarker;
import org.firstinspires.ftc.teamcode.Auto.HardwareClass.Intake;
import org.firstinspires.ftc.teamcode.Auto.HardwareClass.ShoomShoom;
import org.firstinspires.ftc.teamcode.Auto.HardwareClass.TrapMotionProfileRight;
import org.firstinspires.ftc.teamcode.Auto.HardwareClass.VerticalLift;
import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.trajectorysequence.TrajectorySequence;

import java.util.Arrays;

@Autonomous(group = "Auto", name = "RedCloseStateLeft")
public class RedCloseStateLeft extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        HuskyLensDetection husky = new HuskyLensDetection(this, 0, 0, 0);
        SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);
        Pose2d startPose = new Pose2d(14,-62,Math.toRadians(90));
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

        trajSeq1 = drive.trajectorySequenceBuilder(startPose)
                .splineToLinearHeading(new Pose2d(11, -37, Math.toRadians(0)), Math.toRadians(180))
                .lineToLinearHeading(new Pose2d(49,-32, Math.toRadians(180)))
                .forward(1)
                .splineTo(new Vector2d(15, -10), Math.toRadians(180))
                //.splineToLinearHeading(new Pose2d(15.00, 11.00, Math.toRadians(180.00)), Math.toRadians(180.00))
                .forward(50)
                .splineTo(new Vector2d(-58, -11), Math.toRadians(180))
                .waitSeconds(.1)
                .splineToLinearHeading(new Pose2d(-54, -10, Math.toRadians(180)), Math.toRadians(180))
                //.splineToLinearHeading(new Pose2d(-58, 10, Math.toRadians(180)), Math.toRadians(0))
                .back(65)
                .splineTo(new Vector2d(51, -25), Math.toRadians(0))
                //.splineToLinearHeading(new Pose2d(51,35, Math.toRadians(180)), Math.toRadians(0))
                .forward(1)
                .splineToLinearHeading(new Pose2d(43,-50, Math.toRadians(180)), Math.toRadians(180))
                .back(10)
                .build();
        trajSeq2 = drive.trajectorySequenceBuilder(trajSeq1.end())
                .forward(1)
                .splineTo(new Vector2d(15, 11), Math.toRadians(180))
                .forward(50)
                .splineTo(new Vector2d(-58, 10), Math.toRadians(180),SampleMecanumDrive.getVelocityConstraint(30, Math.toRadians(120), 14.95),
                        SampleMecanumDrive.getAccelerationConstraint(30))
                .addDisplacementMarker(() -> {
                    intake.getWhite(1);
                })
                .back(70)
                .addDisplacementMarker(()->{
                    intake.backIntake(1);
                })
                .splineTo(new Vector2d(51, 35), Math.toRadians(0))
                .back(5)

                .build();
        trajSeq3 = drive.trajectorySequenceBuilder(trajSeq2.end())
                .back(3)
                .forward(1)
                .splineToLinearHeading(new Pose2d(43,13, Math.toRadians(180)), Math.toRadians(180))
                .back(10)
                .build();

        if (!isStopRequested()) {
            drive.followTrajectorySequence(trajSeq1);

        }
    }
}