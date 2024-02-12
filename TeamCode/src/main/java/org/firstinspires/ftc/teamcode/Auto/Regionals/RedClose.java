package org.firstinspires.ftc.teamcode.Auto.Regionals;

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

@Autonomous(group = "Auto", name = "RedCloseRegionals")
public class RedClose extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        HuskyLensDetection husky = new HuskyLensDetection(this, 0, 0, 0);
        SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);
        Pose2d startPose = new Pose2d(14,-62,Math.toRadians(270));

        drive.setPoseEstimate(startPose);

        // LEFT
        TrajectorySequence trajSeqLeft = drive.trajectorySequenceBuilder(startPose)
                .back(27)
                .turn(Math.toRadians(-90))
                .forward(3)
                .back(41)
                .forward(7)
                .strafeTo(new Vector2d(43, -16))
                .back(10)
                .build();

        // MIDDLE
        TrajectorySequence trajSeqMiddle = drive.trajectorySequenceBuilder(startPose)
                .back(5)
                .turn(Math.toRadians(180))
                .forward(21)
                .back(8)
                .turn(Math.toRadians(90))
                .strafeTo(new Vector2d(53,-42))
                .strafeTo(new Vector2d(40, -12))
                .back(12)
                .build();

        // RIGHT
        TrajectorySequence trajSeqRight = drive.trajectorySequenceBuilder(startPose)
                .back(5)
                .turn(Math.toRadians(180))
                .strafeTo(new Vector2d(20,-42))
                .back(8)
                .turn(Math.toRadians(90))
                .strafeTo(new Vector2d(45, -48))
                .back(8)
                .forward(3)
                .strafeTo(new Vector2d(40, -12))
                .back(15)
                .build();
//        TrajectorySequence trajSeq3 = drive.trajectorySequenceBuilder(drive.getPoseEstimate())
//
//                .build();

//        Intake intake = new Intake(this);
//        VerticalLift vl = new VerticalLift(this);
//        Flip flip = new Flip(this);

        waitForStart();

        if (!isStopRequested()) {
            //drive.followTrajectorySequence(trajSeqLeft);
            //intake.deliverPurple(5);
            //drive.followTrajectorySequence(trajSeqMiddle);
            //husky.huskyPID1();
            //vl.movePIDRight(-1000, 0.003,0.005,0.0005, 1);
            //flip.lflip();
           drive.followTrajectorySequence(trajSeqRight);
        }
    }
}