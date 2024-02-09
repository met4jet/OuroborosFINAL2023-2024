package org.firstinspires.ftc.teamcode.Auto.Random;

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
import org.firstinspires.ftc.teamcode.Auto.HardwareClass.VerticalLift;
import org.firstinspires.ftc.teamcode.OpenCV.HuskyLensMarker;
import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.trajectorysequence.TrajectorySequence;

import java.util.Arrays;

@Autonomous(group = "Auto", name = "HuskyBlueClose")

public class HuskyBlueClose extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {
        HuskyLensMarker hl = new HuskyLensMarker(this);

        String pos = "";

        ElapsedTime t = new ElapsedTime();
        /*while(!opModeIsActive()) {
            int position = hl.getPos();
            if (position > 45 && position <= 200 ) {
                telemetry.addData("MIDDLE", hl.getPos());
                telemetry.update();
                pos = "MIDDLE";
            } else if (position > 200) {
                telemetry.addData("RIGHT", hl.getPos());
                telemetry.update();
                pos = "RIGHT";
            } else {
                telemetry.addData("LEFT", hl.getPos());
                telemetry.update();
                pos = "LEFT";
            }
        }*/

        waitForStart();
        ElapsedTime time = new ElapsedTime();
        while(time.seconds() < 2){
            int position = hl.getPos();
            if (position > 100 && position <= 235 ) {
                telemetry.addData("MIDDLE", hl.getPos());
                telemetry.update();
                pos = "MIDDLE";
            } else if (position <= 100) {
                telemetry.addData("LEFT", hl.getPos());
                telemetry.update();
                pos = "LEFT";
            } else {
                telemetry.addData("RIGHT", hl.getPos());
                telemetry.update();
                pos = "RIGHT";
            }
        }


        SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);
        Pose2d startPose =new Pose2d(14,62,Math.toRadians(90));

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


        if (pos.equals("RIGHT")) {
            trajSeq1 = drive.trajectorySequenceBuilder(startPose)
                    .back(25)
                    .turn(Math.toRadians(90))
                    .forward(6)
                    .build();
            trajSeq2 = drive.trajectorySequenceBuilder(trajSeq1.end())
                    .setVelConstraint(fastConstraint)
                    .back(50)
                    .build();
            trajSeq3 = drive.trajectorySequenceBuilder(trajSeq2.end())
                    .strafeTo(new Vector2d(43, 60))
                    .back(5)
                    .build();

        } else if (pos.equals("MIDDLE")) {
            trajSeq1 = drive.trajectorySequenceBuilder(startPose)
                    .back(48)
                    .build();
            trajSeq2 = drive.trajectorySequenceBuilder(trajSeq1.end())
                    .setVelConstraint(fastConstraint)
                    .back(2)
                    .turn(Math.toRadians(90))
                    .back(6)
                    .strafeTo(new Vector2d(43,25))
                    .back(13)
                    .build();
            trajSeq3 = drive.trajectorySequenceBuilder(trajSeq2.end())
                    .strafeTo(new Vector2d(45, 60))
                    .back(6)
                    .build();
        } else {
            trajSeq1 = drive.trajectorySequenceBuilder(startPose)
                    .turn(Math.toRadians(180))
                    .forward(33)
                    .build();
            trajSeq2 = drive.trajectorySequenceBuilder(trajSeq1.end())
                    .back(8)
                    .turn(Math.toRadians(-90))
                    .strafeTo(new Vector2d(52,35))
                    .build();
            trajSeq3 = drive.trajectorySequenceBuilder(trajSeq2.end())
                    .strafeTo(new Vector2d(43, 16))
                    .back(10)
                    .build();
        }

        waitForStart();
        if (!isStopRequested()) {
            if(pos.equals("LEFT")){
                drive.followTrajectorySequence(trajSeq1);
                intake.deliverPurple(5);
                drive.followTrajectorySequence(trajSeq2);
                vl.movePIDRight(4000, 0.01,0.000,0.000, 3);
                flip.rflip();
                vl.movePIDRight(-2000, 0.01,0.000,0.000, 2);
                drive.followTrajectorySequence(trajSeq3);
            }
            else if (pos.equals("MIDDLE")) {
                drive.followTrajectorySequence(trajSeq1);
                intake.deliverPurple(5);
                drive.followTrajectorySequence(trajSeq2);
                vl.movePIDRight(4000, 0.01,0.000,0.000, 3);
                flip.rflip();
                vl.movePIDRight(-2000, 0.01,0.000,0.000, 2);
                drive.followTrajectorySequence(trajSeq3);
            }
            else {
                drive.followTrajectorySequence(trajSeq1);
                intake.deliverPurple(2);
                drive.followTrajectorySequence(trajSeq2);
                vl.movePIDRight(4000, 0.01,0.000,0.000, 3);
                flip.rflip();
                vl.movePIDRight(-2000, 0.01,0.000,0.000, 2);
                drive.followTrajectorySequence(trajSeq3);
            }
        }

    }
}
