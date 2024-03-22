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

//@Autonomous(group = "Auto", name = "RedCloseRegionals")
public class RedClose extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);
        Pose2d startPose = new Pose2d(14,-62,Math.toRadians(270));
        TrapMotionProfileRight trapRight = new TrapMotionProfileRight(this);

        HuskyLensMarker hl = new HuskyLensMarker(this);
        VerticalLift vl = new VerticalLift(this);

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

        drive.setPoseEstimate(startPose);


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

        if (pos.equals("LEFT")) {
            trajSeq1 = drive.trajectorySequenceBuilder(startPose)
                    .back(23)
                    .turn(Math.toRadians(180))
                    .strafeLeft(17)
                    .build();
            trajSeq2 = drive.trajectorySequenceBuilder(trajSeq1.end())
                    .back(3)
                    .strafeRight(20)
                    .turn(Math.toRadians(90))
                    .strafeTo(new Vector2d(43,-33))
                    .back(12)
                    .build();
            trajSeq3 = drive.trajectorySequenceBuilder(trajSeq2.end())
                    .forward(7)
                    .strafeTo(new Vector2d(43, -60))
                    .back(10)
                    .build();

        } else if (pos.equals("MIDDLE")) {
            trajSeq1 = drive.trajectorySequenceBuilder(startPose)
                    .back(10)
                    .strafeLeft(15)
                    .splineToLinearHeading(new Pose2d(22, -31, Math.toRadians(180)), Math.toRadians(0))
                    .build();
            trajSeq2 = drive.trajectorySequenceBuilder(trajSeq1.end())
                    .back(8)
                    .strafeTo(new Vector2d(53,-42))
                    .build();
            trajSeq3 = drive.trajectorySequenceBuilder(trajSeq2.end())
                    .strafeTo(new Vector2d(40, -60))
                    .back(10)
                    .build();
        } else {
            trajSeq1 = drive.trajectorySequenceBuilder(startPose)
                    .back(5)
                    .turn(Math.toRadians(180))
                    .strafeTo(new Vector2d(22,-42))
                    .build();
            trajSeq2 = drive.trajectorySequenceBuilder(trajSeq1.end())
                    .back(8)
                    .turn(Math.toRadians(90))
                    .strafeTo(new Vector2d(47, -48))
                    .back(10)
                    .setVelConstraint(slowConstraint)
                    .build();
            trajSeq3 = drive.trajectorySequenceBuilder(trajSeq2.end())
                    .forward(3)
                    .strafeTo(new Vector2d(40, -60))
                    .back(10)
                    .build();
        }

        if (!isStopRequested()) {
            drive.followTrajectorySequence(trajSeq1);
            intake.axonUp(.25);
            sleep(500);
            intake.deliverPurple(2.5);
            drive.followTrajectorySequence(trajSeq2);
            vl.moveRightTime(.75);
            //trapRight.motion_profile_PID(2500, 2650, 2500);
            sleep(1000);
            flip.rflip();
            sleep(1000);
            drive.followTrajectorySequence(trajSeq3);
        }
    }
}