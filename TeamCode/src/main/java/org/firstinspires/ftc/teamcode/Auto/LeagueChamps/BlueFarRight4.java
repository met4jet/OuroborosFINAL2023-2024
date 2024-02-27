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
import org.firstinspires.ftc.teamcode.Auto.HardwareClass.Intake;
import org.firstinspires.ftc.teamcode.Auto.HardwareClass.VerticalLift;
import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.trajectorysequence.TrajectorySequence;

import java.util.Arrays;

//@Autonomous(group = "Auto", name = "BlueFarRight4")
public class BlueFarRight4 extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);
        Pose2d startPose = new Pose2d(-40, 62, Math.toRadians(270));
        // NEED TO TEST AGAIN BECAUSE 70 CHANGED TO 62

        drive.setPoseEstimate(startPose);

        TrajectoryVelocityConstraint fastConstraint = new MinVelocityConstraint(Arrays.asList(

                new TranslationalVelocityConstraint(60),

                new AngularVelocityConstraint(1)

        ));

        // BLUE RIGHT RIGHT

        TrajectorySequence trajSeq1 = drive.trajectorySequenceBuilder(startPose)
                // *** TEST *** *** CHANGE***
                .strafeRight(7)
                //.forward(20)


                /*.turn(Math.toRadians(9;0))
                .forward(55)
                .lineToLinearHeading(new Pose2d(45, 27, Math.toRadians(180)))
                .lineToLinearHeading(new Pose2d(43,12, Math.toRadians(180)))
                .back(10)*/
                .build();

        TrajectorySequence trajSeq2 = drive.trajectorySequenceBuilder(trajSeq1.end())
                .setVelConstraint(fastConstraint)

                .strafeLeft(11)
                .turn(Math.toRadians(-95))
                .strafeLeft(32)
                .back(80)
                // *****CHANGE******
                .strafeTo(new Vector2d(49, 31))
                .back(7)

                /*.turn(Math.toRadians(90))
                .forward(55)
                .lineToLinearHeading(new Pose2d(45, 27, Math.toRadians(180)))
                .lineToLinearHeading(new Pose2d(43,12, Math.toRadians(180)))
                .back(10)*/
                .build();
        TrajectorySequence trajSeq3 = drive.trajectorySequenceBuilder(trajSeq2.end())
                // *****CHANGE******
                .strafeTo(new Vector2d(45, 18))

                //.lineToLinearHeading(new Pose2d(43,12, Math.toRadians(180)))
                .back(7)
                .build();

    Intake intake = new Intake(this);
    VerticalLift vl = new VerticalLift(this);
    Flip flip = new Flip(this);

    waitForStart();
    if (!isStopRequested()) {
        drive.followTrajectorySequence(trajSeq1);
        intake.deliverPurple(5);
        drive.followTrajectorySequence(trajSeq2);
        vl.movePIDLeft(1000, 0.02, 0.005, 0.005, 2);

        flip.lflip();


        //vl.movePIDLeft(0, 0.02,0.0005,0.0005, 2);
        drive.followTrajectorySequence(trajSeq3);
    }


        // BLUE RIGHT MIDDLE
        /*RoadRunnerBotEntity myBot = new DefaultBotBuilder(meepmeep)
                .setConstraints(60, 60, Math.toRadians(100), Math.toRadians(180), 15)
                .followTrajectorySequence(drive ->
                        drive.trajectorySequenceBuilder(new Pose2d(-40,62,Math.toRadians(270)))
                                .lineToLinearHeading(new Pose2d(-34,34, Math.toRadians(270)))
                                .back(4)
                                .strafeRight(18)
                                .turn(Math.toRadians(-90))
                                .strafeLeft(25)
                                .back(80)
                                .lineToLinearHeading(new Pose2d(45, 35, Math.toRadians(180)))
                                //.lineToLinearHeading(new Pose2d(38, 12, Math.toRadians(180)))
                                //.forward(90)
                                .forward(2)
                                .lineToLinearHeading(new Pose2d(43,12, Math.toRadians(180)))
                                .back(10)
                                .build()
                );*/

        // BLUE RIGHT LEFT
        /*RoadRunnerBotEntity myBot = new DefaultBotBuilder(meepmeep)
                .setConstraints(60, 60, Math.toRadians(100), Math.toRadians(180), 15)
                .followTrajectorySequence(drive ->
                        drive.trajectorySequenceBuilder(new Pose2d(-40,62, Math.toRadians(270)))
                                .forward(5)
                                .lineToLinearHeading(new Pose2d(-34,32))
                                .back(6)
                                .strafeRight(20)
                                .forward(80)
                                .lineToLinearHeading(new Pose2d(45, 40, Math.toRadians(180)))
                                //.lineToLinearHeading(new Pose2d(38, 12, Math.toRadians(180)))
                                //.forward(90)
                                .forward(2)
                                .lineToLinearHeading(new Pose2d(43,12, Math.toRadians(180)))
                                .back(10)
                                //new Pose2d(36,60, Math.toRadians(180))
                                .back(7)
                                .build()
                );*/

        // RED RIGHT RIGHT
        /*RoadRunnerBotEntity myBot = new DefaultBotBuilder(meepmeep)
                .setConstraints(60, 60, Math.toRadians(100), Math.toRadians(180), 15)
                .followTrajectorySequence(drive ->
                        drive.trajectorySequenceBuilder(new Pose2d(-40,-62, Math.toRadians(90)))
                                .forward(5)
                                .lineToLinearHeading(new Pose2d(-34,-32))
                                .back(6)
                                .strafeLeft(20)
                                .forward(80)
                                .lineToLinearHeading(new Pose2d(45, -40, Math.toRadians(180)))
                                //.lineToLinearHeading(new Pose2d(38, 12, Math.toRadians(180)))
                                //.forward(90)
                                .forward(2)
                                .lineToLinearHeading(new Pose2d(43,-10, Math.toRadians(180)))
                                .back(10)
                                .build()
                );*/

        // RED RIGHT MIDDLE
        /*RoadRunnerBotEntity myBot = new DefaultBotBuilder(meepmeep)
                .setConstraints(60, 60, Math.toRadians(100), Math.toRadians(180), 15)
                .followTrajectorySequence(drive ->
                        drive.trajectorySequenceBuilder(new Pose2d(-40,-62,Math.toRadians(90)))
                                .lineToLinearHeading(new Pose2d(-35,-34,Math.toRadians(90)))
                                .back(3)
                                .strafeLeft(15)
                                .forward(25)
                                .turn(Math.toRadians(90))
                                .back(70)
                                .lineToLinearHeading(new Pose2d(45, -35, Math.toRadians(180)))
                                //.lineToLinearHeading(new Pose2d(38, 12, Math.toRadians(180)))
                                //.forward(90)
                                .forward(2)
                                .lineToLinearHeading(new Pose2d(43,-10, Math.toRadians(180)))
                                .back(10)
                                .build()
                );
*/
        // RED RIGHT LEFT
        /*RoadRunnerBotEntity myBot = new DefaultBotBuilder(meepmeep)
                .setConstraints(60, 60, Math.toRadians(100), Math.toRadians(180), 15)
                .followTrajectorySequence(drive ->
                        drive.trajectorySequenceBuilder(new Pose2d(-40,-62,Math.toRadians(90)))
                                .strafeLeft(6)
                                .forward(20)
                                .back(1)
                                .strafeRight(11)
                                .forward(32)

                                .turn(Math.toRadians(90))
                                .back(50)
                                .lineToLinearHeading(new Pose2d(50, -27, Math.toRadians(180)))
                                //.lineToLinearHeading(new Pose2d(38, -12, Math.toRadians(180)))
                                //.forward(90)
                                .lineToLinearHeading(new Pose2d(43,-10, Math.toRadians(180)))
                                .back(10)
                                .build()
                );*/
    }
}
