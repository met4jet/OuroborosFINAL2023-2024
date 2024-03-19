package org.firstinspires.ftc.teamcode.Auto.State;

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
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvInternalCamera;

import java.util.Arrays;

@Autonomous(group = "Auto", name = "RedCloseState")
public class RedCloseState extends LinearOpMode {
    OpenCvInternalCamera phoneCam;
    StateOpenCVRedClose.SkystoneDeterminationPipeline pipeline;
    StateOpenCVRedClose.SkystoneDeterminationPipeline.SkystonePosition pos;

    @Override
    public void runOpMode() throws InterruptedException {
        HuskyLensDetection husky = new HuskyLensDetection(this, 0, 0, 0);
        SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);
        Pose2d startPose = new Pose2d(14, -62, Math.toRadians(90));
        TrapMotionProfileRight trapRight = new TrapMotionProfileRight(this);
        VerticalLift vl = new VerticalLift(this);
        ShoomShoom shoom = new ShoomShoom(this);
        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        phoneCam = OpenCvCameraFactory.getInstance().createInternalCamera(OpenCvInternalCamera.CameraDirection.BACK, cameraMonitorViewId);
        pipeline = new StateOpenCVRedClose.SkystoneDeterminationPipeline();
        phoneCam.setPipeline(pipeline);

        HuskyLensMarker hl = new HuskyLensMarker(this);


        pos = StateOpenCVRedClose.SkystoneDeterminationPipeline.SkystonePosition.RIGHT;


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
                .build();
        ;
        TrajectorySequence trajSeq3 = drive.trajectorySequenceBuilder(startPose)
                .strafeTo(new Vector2d(-47, 42))
                .build();
        ;
        TrajectorySequence trajSeq4 = drive.trajectorySequenceBuilder(startPose)
                .strafeTo(new Vector2d(-47, 42))
                .build();
        ;

        drive.setPoseEstimate(startPose);

        TrajectoryVelocityConstraint slowConstraint = new MinVelocityConstraint(Arrays.asList(

                new TranslationalVelocityConstraint(20),

                new AngularVelocityConstraint(Math.toRadians(80))

        ));
        while(!opModeIsActive()){
            pos = pipeline.getAnalysis();
            telemetry.addData("Pos", pipeline.getAnalysis());
            telemetry.update();}
        pos = StateOpenCVRedClose.SkystoneDeterminationPipeline.SkystonePosition.CENTER;
        waitForStart();
        if (pos == StateOpenCVRedClose.SkystoneDeterminationPipeline.SkystonePosition.RIGHT) {
            trajSeq1 = drive.trajectorySequenceBuilder(startPose)
                    .splineToLinearHeading(new Pose2d(22, -41, Math.toRadians(270)), Math.toRadians(74.30))
                    .addSpatialMarker((new Vector2d(23, -40)), () -> {
                        intake.deposit();
                    })
                    .waitSeconds(.3)
                    .forward(5)
                    .addDisplacementMarker(() -> {
                        intake.killDeposit();
                    })
                    .lineToLinearHeading(new Pose2d(49, -46, Math.toRadians(180)))
                    .back(5, SampleMecanumDrive.getVelocityConstraint(30, Math.toRadians(120), 14.95),
                            SampleMecanumDrive.getAccelerationConstraint(7))
                    .forward(1)
                    .splineTo(new Vector2d(15, -11), Math.toRadians(180))
                    .addDisplacementMarker(() -> {
                        intake.axonUp(1);
                    })
                    .forward(55)
                    .splineToLinearHeading(new Pose2d(-59, -10, Math.toRadians(180)), Math.toRadians(180), SampleMecanumDrive.getVelocityConstraint(45, Math.toRadians(120), 14.95),
                            SampleMecanumDrive.getAccelerationConstraint(20))
                    .forward(3, SampleMecanumDrive.getVelocityConstraint(30, Math.toRadians(120), 14.95),
                            SampleMecanumDrive.getAccelerationConstraint(7))
                    .build();
            trajSeq2 = drive.trajectorySequenceBuilder(trajSeq1.end())
                    .back(5)
                    .back(75)
                    .splineTo(new Vector2d(47, -35), Math.toRadians(0))
                    .back(9, SampleMecanumDrive.getVelocityConstraint(30, Math.toRadians(120), 14.95),
                            SampleMecanumDrive.getAccelerationConstraint(7))
                    .forward(1)
                    .splineToLinearHeading(new Pose2d(43, -46, Math.toRadians(180)), Math.toRadians(180))
                    .back(10)

                    .build();
            trajSeq3 = drive.trajectorySequenceBuilder(trajSeq2.end())
                    .back(3)
                    .forward(1)
                    .splineToLinearHeading(new Pose2d(43, 13, Math.toRadians(180)), Math.toRadians(180))
                    .back(10)
                    .build();
        }
        else if (pos == StateOpenCVRedClose.SkystoneDeterminationPipeline.SkystonePosition.CENTER){
            trajSeq1 = drive.trajectorySequenceBuilder(startPose)
                    .splineToLinearHeading(new Pose2d(29, -20, Math.toRadians(0)), Math.toRadians(270))
                    .splineToLinearHeading(new Pose2d(22, -22.2, Math.toRadians(0)), Math.toRadians(0))
                    .back(2)
                    .addSpatialMarker((new Vector2d(22,21.2)), () -> {
                        intake.deposit();
                    })
                    .splineToLinearHeading(new Pose2d(47,-40, Math.toRadians(180)), Math.toRadians(0))
                    .addDisplacementMarker(() -> {
                        intake.killDeposit();
                    })
                    .back(5, SampleMecanumDrive.getVelocityConstraint(30, Math.toRadians(120), 14.95),
                            SampleMecanumDrive.getAccelerationConstraint(7))
                    .forward(1)
                    .splineTo(new Vector2d(15, -11), Math.toRadians(180))
                    .addDisplacementMarker(() -> {
                        intake.axonUp(1);
                    })
                    .forward(55)
                    .lineToLinearHeading(new Pose2d(-59, -10, Math.toRadians(180))/*, Math.toRadians(180)*/, SampleMecanumDrive.getVelocityConstraint(30, Math.toRadians(120), 14.95),
                            SampleMecanumDrive.getAccelerationConstraint(7))
                    .forward(3, SampleMecanumDrive.getVelocityConstraint(30, Math.toRadians(120), 14.95),
                            SampleMecanumDrive.getAccelerationConstraint(7))
                    .build();
            trajSeq2 = drive.trajectorySequenceBuilder(trajSeq1.end())
                    .back(5)
                    .back(75)
                    .splineTo(new Vector2d(47, -35), Math.toRadians(0))
                    .back(9, SampleMecanumDrive.getVelocityConstraint(30, Math.toRadians(120), 14.95),
                            SampleMecanumDrive.getAccelerationConstraint(7))
                    .forward(1)
                    .splineToLinearHeading(new Pose2d(43, -46, Math.toRadians(180)), Math.toRadians(180))
                    .back(10)

                    .build();
            trajSeq3 = drive.trajectorySequenceBuilder(trajSeq2.end())
                    .back(3)
                    .forward(1)
                    .splineToLinearHeading(new Pose2d(43, 13, Math.toRadians(180)), Math.toRadians(180))
                    .back(10)
                    .build();
        }
        else{
            trajSeq1 = drive.trajectorySequenceBuilder(startPose)
                    .splineToLinearHeading(new Pose2d(8, -37, Math.toRadians(0)), Math.toRadians(175.35))
                    .waitSeconds(1)
                    .lineToLinearHeading(new Pose2d(49,-32, Math.toRadians(180)))
                    .back(3)
                    .splineToLinearHeading(new Pose2d(47,-40, Math.toRadians(180)), Math.toRadians(0))
                    .back(5, SampleMecanumDrive.getVelocityConstraint(30, Math.toRadians(120), 14.95),
                            SampleMecanumDrive.getAccelerationConstraint(7))
                    .forward(1)
                    .splineTo(new Vector2d(15, -11), Math.toRadians(180))
                    .addDisplacementMarker(() -> {
                        intake.axonUp(1);
                    })
                    .forward(55)
                    .lineToLinearHeading(new Pose2d(-59, -10, Math.toRadians(180))/*, Math.toRadians(180)*/, SampleMecanumDrive.getVelocityConstraint(30, Math.toRadians(120), 14.95),
                            SampleMecanumDrive.getAccelerationConstraint(7))
                    //.forward(3, SampleMecanumDrive.getVelocityConstraint(30, Math.toRadians(120), 14.95),
                            //SampleMecanumDrive.getAccelerationConstraint(7))
                    .build();
            trajSeq2 = drive.trajectorySequenceBuilder(trajSeq1.end())
                    .back(5)
                    .back(75)
                    .splineTo(new Vector2d(47, -35), Math.toRadians(0))
                    .back(9, SampleMecanumDrive.getVelocityConstraint(30, Math.toRadians(120), 14.95),
                            SampleMecanumDrive.getAccelerationConstraint(7))
                    .forward(1)
                    .splineToLinearHeading(new Pose2d(43, -46, Math.toRadians(180)), Math.toRadians(180))
                    .back(10)

                    .build();
            trajSeq3 = drive.trajectorySequenceBuilder(trajSeq2.end())
                    .back(3)
                    .forward(1)
                    .splineToLinearHeading(new Pose2d(43, 13, Math.toRadians(180)), Math.toRadians(180))
                    .back(10)
                    .build();
        }


        if (!isStopRequested()) {
            drive.followTrajectorySequence(trajSeq1);
            intake.getWhite2new(2);
            sleep(500);
            drive.followTrajectorySequence(trajSeq2);

        }
    }
}