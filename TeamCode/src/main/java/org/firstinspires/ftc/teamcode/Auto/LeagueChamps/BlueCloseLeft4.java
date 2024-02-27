package org.firstinspires.ftc.teamcode.Auto.LeagueChamps;

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
import org.firstinspires.ftc.teamcode.Auto.HardwareClass.Intake;
import org.firstinspires.ftc.teamcode.Auto.HardwareClass.VerticalLift;
import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.trajectorysequence.TrajectorySequence;

import java.util.Arrays;

//@Autonomous(group = "Auto", name = "BlueCloseLeft4")
public class BlueCloseLeft4 extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        HuskyLensDetection husky = new HuskyLensDetection(this, 0, 0, 0);
        SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);
        Pose2d startPose = new Pose2d(14,62,Math.toRadians(270));

        drive.setPoseEstimate(startPose);

        TrajectoryVelocityConstraint fastConstraint = new MinVelocityConstraint(Arrays.asList(

                new TranslationalVelocityConstraint(60),

                new AngularVelocityConstraint(1)

        ));

        TrajectorySequence trajSeq1 = drive.trajectorySequenceBuilder(startPose)
                .setVelConstraint(fastConstraint)
                .strafeTo(new Vector2d(23,40))
                .back(3)

                .build();

        TrajectorySequence trajSeq2 = drive.trajectorySequenceBuilder(trajSeq1.end())
                .lineToLinearHeading(new Pose2d(38,36, Math.toRadians(180)))
                .back(3)
                .build();

        TrajectorySequence trajSeq3 = drive.trajectorySequenceBuilder(drive.getPoseEstimate())
                .strafeTo(new Vector2d(43,60))
                .back(10)
                .build();

        Intake intake = new Intake(this);
        VerticalLift vl = new VerticalLift(this);
        Flip flip = new Flip(this);

        waitForStart();

        if (!isStopRequested()) {
        drive.followTrajectorySequence(trajSeq1);
            //intake.deliverPurple(5);
            drive.followTrajectorySequence(trajSeq2);
            husky.huskyPID1();
            //vl.movePIDRight(-1000, 0.003,0.005,0.0005, 1);
            //flip.lflip();
            drive.followTrajectorySequence(trajSeq3);
        }
    }
}