package org.firstinspires.ftc.teamcode.Auto.State;
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
import org.firstinspires.ftc.teamcode.Auto.HardwareClass.ShoomShoom;
import org.firstinspires.ftc.teamcode.Auto.HardwareClass.TrapMotionProfileRight;
import org.firstinspires.ftc.teamcode.Auto.HardwareClass.VerticalLift;
import org.firstinspires.ftc.teamcode.Auto.HardwareClass.HuskyLensMarker;
import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.trajectorysequence.TrajectorySequence;

import java.util.Arrays;
@Autonomous(group = "Auto", name = "TestAuto")
public class Myra extends LinearOpMode {
    //NOTES FOR MYRA!!!!!!

    //if you finish tuning roadrunner then this matters otherwise ignore it (if you have a question
    //just message sophia if you have her number or message Tanisha cuz shes sorta my sister)
    //This is like a stripped down version of our auto for you, at the bottom of the class I've left
    //all of our pathing in comments. First, doing the close gate pathings then the far under truss
    //pathings would be ideal yknow (youre the goat for doing this). Once you're done with a
    //particular path then just put it in the comment and put a title like
    //"UPDATED:RED CLOSE LEFT: UNDER GATE" so we know which is which. That's about it,
    //don't worry about OpenCV or detecting or anything that, you can just hardcode which path
    //its gonna run, theres a variable called "DETECTION" so whenever you are switching the
    //teammarker position just hardcode the variable to the corresponding name. Also like theres
    //3 trajectories just like split however it doesn't really matter too much as long as the pathing is chill
    //-krish
    @Override
    public void runOpMode() throws InterruptedException {
        SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);
        //change this for all diff sides
        //BLUE CLOSE:14,62,Math.toRadians(270)
        //BLUE FAR:-38,62,Math.toRadians(270)
        //RED CLOSE:14,-62,Math.toRadians(90)
        //RED FAR:-38,-62,Math.toRadians(90)
        Pose2d startPose = new Pose2d(14,62,Math.toRadians(90));

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
        //NOTE: the pathings in each part of the if statement aren't correct.. go to the comments
        //down at the bottom of the class and paste in the pathings you want before testing it
        String DETECTION = "LEFT";
        if(DETECTION.equals("LEFT")){
            trajSeq1 = drive.trajectorySequenceBuilder(startPose)
                    .forward(5)
                    .splineToLinearHeading(new Pose2d(32, 38, Math.toRadians(0)), Math.toRadians(0))
                    .lineToLinearHeading(new Pose2d(49,30, Math.toRadians(180)))
                    .back(3)
                    .build();
            trajSeq2 = drive.trajectorySequenceBuilder(trajSeq1.end())
                    .forward(1)
                    .splineTo(new Vector2d(15, 11), Math.toRadians(180))
                    .forward(50)
                    .splineTo(new Vector2d(-58, 10), Math.toRadians(180),SampleMecanumDrive.getVelocityConstraint(30, Math.toRadians(120), 14.95),
                            SampleMecanumDrive.getAccelerationConstraint(30))
                    .back(70)
                    .splineTo(new Vector2d(51, 35), Math.toRadians(0))
                    .back(5)

                    .build();
            trajSeq3 = drive.trajectorySequenceBuilder(trajSeq2.end())
                    .back(3)
                    .forward(1)
                    .splineToLinearHeading(new Pose2d(43,13, Math.toRadians(180)), Math.toRadians(180))
                    .back(10)
                    .build();
        }
        else if(DETECTION.equals("MIDDLE")){
            trajSeq1 = drive.trajectorySequenceBuilder(startPose)
                    .forward(5)
                    .splineToLinearHeading(new Pose2d(32, 38, Math.toRadians(0)), Math.toRadians(0))
                    .lineToLinearHeading(new Pose2d(49,30, Math.toRadians(180)))
                    .back(3)
                    .build();
            trajSeq2 = drive.trajectorySequenceBuilder(trajSeq1.end())
                    .forward(1)
                    .splineTo(new Vector2d(15, 11), Math.toRadians(180))
                    .forward(50)
                    .splineTo(new Vector2d(-58, 10), Math.toRadians(180),SampleMecanumDrive.getVelocityConstraint(30, Math.toRadians(120), 14.95),
                            SampleMecanumDrive.getAccelerationConstraint(30))
                    .back(70)
                    .splineTo(new Vector2d(51, 35), Math.toRadians(0))
                    .back(5)

                    .build();
            trajSeq3 = drive.trajectorySequenceBuilder(trajSeq2.end())
                    .back(3)
                    .forward(1)
                    .splineToLinearHeading(new Pose2d(43,13, Math.toRadians(180)), Math.toRadians(180))
                    .back(10)
                    .build();
        }
        else{
            trajSeq1 = drive.trajectorySequenceBuilder(startPose)
                    .forward(5)
                    .splineToLinearHeading(new Pose2d(32, 38, Math.toRadians(0)), Math.toRadians(0))
                    .lineToLinearHeading(new Pose2d(49,30, Math.toRadians(180)))
                    .back(3)
                    .build();
            trajSeq2 = drive.trajectorySequenceBuilder(trajSeq1.end())
                    .forward(1)
                    .splineTo(new Vector2d(15, 11), Math.toRadians(180))
                    .forward(50)
                    .splineTo(new Vector2d(-58, 10), Math.toRadians(180),SampleMecanumDrive.getVelocityConstraint(30, Math.toRadians(120), 14.95),
                            SampleMecanumDrive.getAccelerationConstraint(30))
                    .back(70)
                    .splineTo(new Vector2d(51, 35), Math.toRadians(0))
                    .back(5)

                    .build();
            trajSeq3 = drive.trajectorySequenceBuilder(trajSeq2.end())
                    .back(3)
                    .forward(1)
                    .splineToLinearHeading(new Pose2d(43,13, Math.toRadians(180)), Math.toRadians(180))
                    .back(10)
                    .build();
        }


        if (!isStopRequested()) {
            drive.followTrajectorySequence(trajSeq1);
            drive.followTrajectorySequence(trajSeq2);
            drive.followTrajectorySequence(trajSeq3);
        }
    }
}
//in these pathings theres like some commented out splines and stuff thats like remnants of old stuff
//dont worry about it

 /*//BLUE FAR MIDDLE
//UNDER TRUSS
.splineToLinearHeading(new Pose2d(-47, 26, Math.toRadians(180)), Math.toRadians(270))
.lineToLinearHeading(new Pose2d(-55,35,Math.toRadians(180)))
.forward(3)
.back(1)
.splineTo(new Vector2d(-35, 58), Math.toRadians(0))
//.splineToLinearHeading(new Pose2d(-44,59, Math.toRadians(180)), Math.toRadians(0))
.back(35)
.splineTo(new Vector2d(51, 35), Math.toRadians(0))
//.splineToLinearHeading(new Pose2d(51,35, Math.toRadians(180)), Math.toRadians(0))
.forward(1)
.splineToLinearHeading(new Pose2d(43,13, Math.toRadians(180)), Math.toRadians(180))
.back(10)*/

/*//BLUE FAR LEFT
//UNDER TRUSS
.splineToLinearHeading(new Pose2d(-31.5, 34, Math.toRadians(180)), Math.toRadians(0))
.lineToLinearHeading(new Pose2d(-55,35,Math.toRadians(180)))
.forward(3)
.back(1)
.splineTo(new Vector2d(-40, 59), Math.toRadians(0))
//.splineToLinearHeading(new Pose2d(-40, 59, Math.toRadians(180)), Math.toRadians(0))
.back(70)
.splineTo(new Vector2d(51, 35), Math.toRadians(0))
//.splineToLinearHeading(new Pose2d(51,35, Math.toRadians(180)), Math.toRadians(0))
.forward(1)
.splineToLinearHeading(new Pose2d(43,13, Math.toRadians(180)), Math.toRadians(180))
.back(10)*/

/*//BLUE FAR RIGHT
//UNDER TRUSS
.splineToLinearHeading(new Pose2d(-55, 33, Math.toRadians(180)), Math.toRadians(270))
.lineToLinearHeading(new Pose2d(-57,35,Math.toRadians(180)))
.forward(3)
.back(1)
.splineToLinearHeading(new Pose2d(-57,59, Math.toRadians(180)), Math.toRadians(0))
.back(60)
.splineTo(new Vector2d(51,35), Math.toRadians(0))
//.splineToLinearHeading(new Pose2d(51,35, Math.toRadians(180)), Math.toRadians(0))
.forward(1)
.splineToLinearHeading(new Pose2d(43,13, Math.toRadians(180)), Math.toRadians(180))
.back(10)*/


/*//BLUE CLOSE MIDDLE
//new Pose2d(14,62,Math.toRadians(270))
//UNDER GATE
.splineToLinearHeading(new Pose2d(22, 25, Math.toRadians(0)), Math.toRadians(270))
.splineToLinearHeading(new Pose2d(47,30, Math.toRadians(180)), Math.toRadians(0))
.back(5)
.forward(1)
.splineTo(new Vector2d(15, 11), Math.toRadians(180))
.forward(55)
.splineTo(new Vector2d(-58, 10), Math.toRadians(180))
.back(70)
.splineTo(new Vector2d(51, 35), Math.toRadians(0))
.forward(1)
.splineToLinearHeading(new Pose2d(43,13, Math.toRadians(180)), Math.toRadians(180))
.back(10)*/


/*//BLUE RIGHT CLOSE
// UNDER GATE
.splineToLinearHeading(new Pose2d(11, 37, Math.toRadians(0)), Math.toRadians(180))
.lineToLinearHeading(new Pose2d(49,32, Math.toRadians(180)))
.forward(1)
.splineTo(new Vector2d(15, 11), Math.toRadians(180))
//.splineToLinearHeading(new Pose2d(15.00, 11.00, Math.toRadians(180.00)), Math.toRadians(180.00))
.forward(55)
.splineTo(new Vector2d(-58, 10), Math.toRadians(180))
//.splineToLinearHeading(new Pose2d(-58, 10, Math.toRadians(180)), Math.toRadians(0))
.back(70)
.splineTo(new Vector2d(51, 35), Math.toRadians(0))
//.splineToLinearHeading(new Pose2d(51,35, Math.toRadians(180)), Math.toRadians(0))
.forward(1)
.splineToLinearHeading(new Pose2d(43,13, Math.toRadians(180)), Math.toRadians(180))
.back(10)*/


/*//BLUE LEFT CLOSE
//UNDER GATE
.splineToLinearHeading(new Pose2d(32, 38, Math.toRadians(0)), Math.toRadians(0))
.lineToLinearHeading(new Pose2d(49,30, Math.toRadians(180)))
.back(3)
.forward(1)
.splineTo(new Vector2d(15, 11), Math.toRadians(180))
//.splineToLinearHeading(new Pose2d(15.00, 11.00, Math.toRadians(180.00)), Math.toRadians(180.00))
.forward(55)
.splineTo(new Vector2d(-58, 10), Math.toRadians(180))
//.splineToLinearHeading(new Pose2d(-58, 10, Math.toRadians(180)), Math.toRadians(0))
.back(70)
.splineTo(new Vector2d(51, 35), Math.toRadians(0))
//.splineToLinearHeading(new Pose2d(51,35, Math.toRadians(180)), Math.toRadians(0))
.forward(1)
.splineToLinearHeading(new Pose2d(43,13, Math.toRadians(180)), Math.toRadians(180))
.back(10)*/

/*//RED MIDDLE CLOSE
//UNDER GATE
.splineToLinearHeading(new Pose2d(22, -25, Math.toRadians(0)), Math.toRadians(90))
.back(2)
.splineToLinearHeading(new Pose2d(47,-30, Math.toRadians(180)), Math.toRadians(0))
.back(5)
.forward(1)
.splineTo(new Vector2d(15, -11), Math.toRadians(180))
.forward(55)
.splineTo(new Vector2d(-58, -10), Math.toRadians(180))
.back(70)
.splineTo(new Vector2d(51, -35), Math.toRadians(0))
.forward(1)
.splineToLinearHeading(new Pose2d(43,-58, Math.toRadians(180)), Math.toRadians(180))
.back(10)*/

/*//RED CLOSE LEFT
//UNDER GATE
.splineToLinearHeading(new Pose2d(11, -37, Math.toRadians(0)), Math.toRadians(175.35))
.lineToLinearHeading(new Pose2d(49,-32, Math.toRadians(180)))
.back(3)
.forward(1)
.splineTo(new Vector2d(15, -11), Math.toRadians(180))
//.splineToLinearHeading(new Pose2d(15.00, 11.00, Math.toRadians(180.00)), Math.toRadians(180.00))
.forward(55)
.splineTo(new Vector2d(-58, -10), Math.toRadians(180))
//.splineToLinearHeading(new Pose2d(-58, 10, Math.toRadians(180)), Math.toRadians(0))
.back(70)
.splineTo(new Vector2d(51, -35), Math.toRadians(0))
//.splineToLinearHeading(new Pose2d(51,35, Math.toRadians(180)), Math.toRadians(0))
.forward(1)
.splineToLinearHeading(new Pose2d(43,-58, Math.toRadians(180)), Math.toRadians(180))
.back(10)*/

/*//RED CLOSE RIGHT
//UNDER GATE
.splineToLinearHeading(new Pose2d(33, -33, Math.toRadians(0)), Math.toRadians(0))
.splineToLinearHeading(new Pose2d(46, -30, Math.toRadians(180)), Math.toRadians(0))
.back(6)
.forward(1)
.splineTo(new Vector2d(15, -11), Math.toRadians(180))
//.splineToLinearHeading(new Pose2d(15.00, 11.00, Math.toRadians(180.00)), Math.toRadians(180.00))
.forward(55)
.splineTo(new Vector2d(-58, -10), Math.toRadians(180))
//.splineToLinearHeading(new Pose2d(-58, 10, Math.toRadians(180)), Math.toRadians(0))
.back(70)
.splineTo(new Vector2d(51, -35), Math.toRadians(0))
//.splineToLinearHeading(new Pose2d(51,35, Math.toRadians(180)), Math.toRadians(0))
.forward(1)
.splineToLinearHeading(new Pose2d(43,-58, Math.toRadians(180)), Math.toRadians(180))
.back(10)*/
//-----------------------------------------------------------------------------------------------------------------------------------------
/*//RED FAR LEFT
//UNDER TRUSS
.splineToLinearHeading(new Pose2d(-57,-35, Math.toRadians(180)), Math.toRadians(90))
.back(1)
.strafeLeft(10)
.splineToLinearHeading(new Pose2d(-50,-59, Math.toRadians(180)), Math.toRadians(0))
.back(60)
.splineTo(new Vector2d(51,-35), Math.toRadians(0))
//.splineToLinearHeading(new Pose2d(51,35, Math.toRadians(180)), Math.toRadians(0))
.forward(1)
.splineToLinearHeading(new Pose2d(43,-13, Math.toRadians(180)), Math.toRadians(180))
.back(10)*/

/*//RED FAR RIGHT
//UNDER TRUSS
.splineToLinearHeading(new Pose2d(-33, -34, Math.toRadians(180)), Math.toRadians(0))
.lineToLinearHeading(new Pose2d(-55,-35,Math.toRadians(180)))
.forward(3)
.back(1)
.splineToLinearHeading(new Pose2d(-50,-59, Math.toRadians(180)), Math.toRadians(0))
.back(60)
.splineTo(new Vector2d(51,-35), Math.toRadians(0))
//.splineToLinearHeading(new Pose2d(51,35, Math.toRadians(180)), Math.toRadians(0))
.forward(1)
.splineToLinearHeading(new Pose2d(43,-13, Math.toRadians(180)), Math.toRadians(180))*/

/*
//RED FAR MIDDLE
//UNDER TRUSS
.splineToLinearHeading(new Pose2d(-43, -24, Math.toRadians(180)), Math.toRadians(90))
.forward(14)
.back(1)
.splineToLinearHeading(new Pose2d(-45.45,-59, Math.toRadians(180)), Math.toRadians(0))
.back(45)
.splineTo(new Vector2d(51,-35), Math.toRadians(0))
.forward(1)
.splineToLinearHeading(new Pose2d(43,-13, Math.toRadians(180)), Math.toRadians(180))
*/