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
import org.firstinspires.ftc.teamcode.Auto.HardwareClass.TrapMotionProfileRight;
import org.firstinspires.ftc.teamcode.Auto.HardwareClass.HuskyLensMarker;
import org.firstinspires.ftc.teamcode.Auto.HardwareClass.VerticalLift;
import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.trajectorysequence.TrajectorySequence;

import java.util.Arrays;

@Autonomous(group = "Auto", name = "BlueCloseRegionals")
public class BlueClose extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        Pose2d startPose = new Pose2d(14,62,Math.toRadians(90));

        SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);
        TrapMotionProfileRight trapRight = new TrapMotionProfileRight(this);

        HuskyLensMarker hl = new HuskyLensMarker(this);

        String pos = "";

        Intake intake = new Intake(this);
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

        TrajectoryVelocityConstraint slowConstraint = new MinVelocityConstraint(Arrays.asList(

                new TranslationalVelocityConstraint(20),

                new AngularVelocityConstraint(Math.toRadians(80))

        ));


        VerticalLift vl = new VerticalLift(this);

        waitForStart();

        ElapsedTime time = new ElapsedTime();
        while(time.seconds() < 3){
            int position = hl.getPos();
            if (position >= 150 ) {
                telemetry.addData("MIDDLE", hl.getPos());
                telemetry.update();
                pos = "MIDDLE";
            } else if (position < 150 && position > 1) {
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
                    .back(5)
                    .turn(Math.toRadians(180))
                    .strafeTo(new Vector2d(22,43))
                    .back(4)
                    .build();
            trajSeq2 = drive.trajectorySequenceBuilder(trajSeq1.end())
                    .back(7)
                    .turn(Math.toRadians(-90))
                    .strafeTo(new Vector2d(45, 35))
                    .back(8)
                    .setVelConstraint(slowConstraint)

                    .build();
            trajSeq3 = drive.trajectorySequenceBuilder(trajSeq2.end())
                    .strafeTo(new Vector2d(43,60))
                    .back(15)
                    .build();

        } else if (pos.equals("MIDDLE")) {
            trajSeq1 = drive.trajectorySequenceBuilder(startPose)
                    .back(5)
                    .turn(Math.toRadians(180))
                    .forward(22)
                    .build();
            trajSeq2 = drive.trajectorySequenceBuilder(trajSeq1.end())
                    .back(8)
                    .turn(Math.toRadians(-90))
                    .strafeTo(new Vector2d(48,35))
                    .back(8, SampleMecanumDrive.getVelocityConstraint(30, Math.toRadians(120), 14.95),
                            SampleMecanumDrive.getAccelerationConstraint(3))
                    .setVelConstraint(slowConstraint)
                    .build();
            trajSeq3 = drive.trajectorySequenceBuilder(trajSeq2.end())
                    .strafeTo(new Vector2d(38, 55))
                    .strafeTo(new Vector2d(43,60))
                    .back(10)
                    .build();
        } else {
            trajSeq1 = drive.trajectorySequenceBuilder(startPose)
                    .back(27)
                    .turn(Math.toRadians(90))
                    .forward(3)
                    .build();
            trajSeq2 = drive.trajectorySequenceBuilder(trajSeq1.end())
                    .strafeTo(new Vector2d(45, 28))
                    .back(11, SampleMecanumDrive.getVelocityConstraint(30, Math.toRadians(120), 14.95),
                            SampleMecanumDrive.getAccelerationConstraint(3))
                    .build();
            trajSeq3 = drive.trajectorySequenceBuilder(trajSeq2.end())
                    .forward(10)
                    .strafeTo(new Vector2d(43, 63))
                    .back(10)
                    .build();
        }

        if (!isStopRequested()) {
            drive.followTrajectorySequence(trajSeq1);
            intake.axonUp(1);
            intake.deliverPurple(2.5);
            drive.followTrajectorySequence(trajSeq2);
            //trapRight.motion_profile_PID(2500, 2650, 2500);
            vl.moveRightTime(1.2);
            sleep(1500);
            flip.rflip();
            sleep(1000);
            drive.followTrajectorySequence(trajSeq3);
        }
    }
}