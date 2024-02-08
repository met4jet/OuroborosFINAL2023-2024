package org.firstinspires.ftc.teamcode.Auto.Random;

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
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Auto.HardwareClass.Flip;
import org.firstinspires.ftc.teamcode.Auto.HardwareClass.HuskyLensDetection;
import org.firstinspires.ftc.teamcode.Auto.HardwareClass.Intake;
import org.firstinspires.ftc.teamcode.Auto.HardwareClass.VerticalLift;
import org.firstinspires.ftc.teamcode.Loop;
import org.firstinspires.ftc.teamcode.OpenCV.HuskyLensMarker;
import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.trajectorysequence.TrajectorySequence;

import java.util.Arrays;

@Autonomous(group = "Auto", name = "HuskyTest")

public class HuskyTest extends LinearOpMode {

    VerticalLift vl_thd;

    Thread move_vl = new Thread(new Runnable(){
        @Override
        public void run(){
            vl_thd.movePIDLeft(4000, 0.001, 0, 0, 3);
        }
    });

    Loop loop_liftVL = new Loop();

    @Override
    public void runOpMode() throws InterruptedException {

        vl_thd = new VerticalLift(this);
        //dt.encoderMove(.5, 3, 3, false, false);
        loop_liftVL.add(move_vl);

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


        if (pos.equals("LEFT")) {
            trajSeq1 = drive.trajectorySequenceBuilder(startPose)
                    .back(25)
                    .turn(Math.toRadians(-90))
                    .forward(5)
                    .build();
            trajSeq2 = drive.trajectorySequenceBuilder(trajSeq1.end())
                    .setVelConstraint(fastConstraint)
                    .back(11)
                    .strafeRight(28)
                    .turn(Math.toRadians(180))
                    .back(70)
                    .strafeTo(new Vector2d(45, 40))
                    .back(7)
                    .build();
            trajSeq3 = drive.trajectorySequenceBuilder(trajSeq2.end())
                    .strafeTo(new Vector2d(43, 18))
                    .back(5)
                    .build();

        } else if (pos.equals("MIDDLE")) {
            trajSeq1 = drive.trajectorySequenceBuilder(startPose)
                    .back(48)
                    //.forward(29)
                    .build();
            trajSeq2 = drive.trajectorySequenceBuilder(trajSeq1.end())
                    .setVelConstraint(fastConstraint)
                    //.strafeRight(14)
                    //.forward(23)
                    .turn(Math.toRadians(90))
                    .back(75)
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
                    //.lineToLinearHeading(new Pose2d(-47, 37, Math.toRadians(270)))
                    .turn(Math.toRadians(180))
                    .strafeTo(new Vector2d(-45,37))
                    .back(4)
                    .build();
            trajSeq2 = drive.trajectorySequenceBuilder(trajSeq1.end())
                    .setVelConstraint(fastConstraint)
                    .back(4)
                    .strafeLeft(11)
                    //.forward(4)
                    .turn(Math.toRadians(-90))
                    .strafeLeft(38)
                    .back(70)
                    // *****CHANGE******
                    .strafeTo(new Vector2d(49, 31))
                    .back(10)
                    .build();
            trajSeq3 = drive.trajectorySequenceBuilder(trajSeq2.end())
                    .strafeTo(new Vector2d(45, 15))
                    .back(7)
                    .build();
        }

        waitForStart();

        if (!isStopRequested()) {
            if(pos.equals("LEFT")){
                drive.followTrajectorySequence(trajSeq1);
                intake.deliverPurple(5);
                loop_liftVL.run();
                drive.followTrajectorySequence(trajSeq2);
                vl.movePIDLeft(4000, 0.01,0.000,0.000, 3);
                flip.lflip();
                vl.movePIDLeft(-2000, 0.01,0.000,0.000, 2);
                drive.followTrajectorySequence(trajSeq3);
            }
            else if (pos.equals("MIDDLE")) {
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
                intake.deliverPurple(2);
                drive.followTrajectorySequence(trajSeq2);
                vl.movePIDLeft(4000, 0.01, 0, 0, 3);
                flip.lflip();
                vl.movePIDLeft(-2000, 0.01, 0, 0, 2);
                //drive.followTrajectorySequence(trajSeq3);
            }
        }

    }
}