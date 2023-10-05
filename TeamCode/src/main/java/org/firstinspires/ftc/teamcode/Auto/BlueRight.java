package org.firstinspires.ftc.teamcode.Auto;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.trajectory.constraints.AngularVelocityConstraint;
import com.acmerobotics.roadrunner.trajectory.constraints.MinVelocityConstraint;
import com.acmerobotics.roadrunner.trajectory.constraints.TrajectoryVelocityConstraint;
import com.acmerobotics.roadrunner.trajectory.constraints.TranslationalVelocityConstraint;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.trajectorysequence.TrajectorySequence;

import java.util.Arrays;

@Autonomous(group = "Auto", name = "BlueRight")

public class BlueRight extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);

        Pose2d startPose = new Pose2d(-40, 62, Math.toRadians(270));

        TrajectoryVelocityConstraint slowConstraint = new MinVelocityConstraint(Arrays.asList(

                new TranslationalVelocityConstraint(80),

                new AngularVelocityConstraint(10)

        ));

        TrajectoryVelocityConstraint slowConstraint1 = new MinVelocityConstraint(Arrays.asList(

                new TranslationalVelocityConstraint(60),

                new AngularVelocityConstraint(7)

        ));

        drive.setPoseEstimate(startPose);
        //

        TrajectorySequence trajSeq = drive.trajectorySequenceBuilder(startPose)
                .forward(28)
                .setVelConstraint(slowConstraint)
                //.splineTo(new Vector2d( 0,35), Math.toRadians(0))
                .turn(Math.toRadians(90))
                .setVelConstraint(slowConstraint)
                .forward(50)
                .setVelConstraint(slowConstraint)
                .lineToLinearHeading(new Pose2d(35, 34, Math.toRadians(180)))
                .setVelConstraint(slowConstraint)
                .forward(60)
                .setVelConstraint(slowConstraint1)
                .build();

        waitForStart();

        if (!isStopRequested())
            drive.followTrajectorySequence(trajSeq);
    }
}