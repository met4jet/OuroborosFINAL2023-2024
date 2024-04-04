package com.example.meepmeeptesting;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

import kotlin.math.UMathKt;

public class MeepMeepTesting {
    public static void main(String[] args) {
        MeepMeep meepMeep = new MeepMeep(700);

        RoadRunnerBotEntity myBot = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 14)
                .followTrajectorySequence(drive ->
                        drive.trajectorySequenceBuilder(new Pose2d(14,62,Math.toRadians(270)))
                                .splineToLinearHeading(new Pose2d(32, 38, Math.toRadians(0)), Math.toRadians(0))
                                .lineToLinearHeading(new Pose2d(49,30, Math.toRadians(180)))
                                .back(3)
                                .waitSeconds(.25)
                                .forward(1)
                                .splineTo(new Vector2d(15, 10), Math.toRadians(180))
                                //.splineToLinearHeading(new Pose2d(15.00, 11.00, Math.toRadians(180.00)), Math.toRadians(180.00))
                                .forward(50)
                                .splineTo(new Vector2d(-58, 11), Math.toRadians(180))
                                .waitSeconds(.1)
                                .splineToLinearHeading(new Pose2d(-57, 10, Math.toRadians(180)), Math.toRadians(180))
                                //.splineToLinearHeading(new Pose2d(-58, 10, Math.toRadians(180)), Math.toRadians(0))
                                .back(70)
                                .splineTo(new Vector2d(51, 35), Math.toRadians(0))
                                //.splineToLinearHeading(new Pose2d(51,35, Math.toRadians(180)), Math.toRadians(0))
                                .forward(1)
                                .splineToLinearHeading(new Pose2d(43,55, Math.toRadians(180)), Math.toRadians(180))
                                .back(10)
                                //.splineToLinearHeading(new Pose2d(23, -40, Math.toRadians(270)), Math.toRadians(74.30))



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
                                /*//UNDER GATE
                                .splineToLinearHeading(new Pose2d(-47, 26, Math.toRadians(0)), Math.toRadians(0))
                                .back(2)
                                .lineToLinearHeading(new Pose2d(-55,35,Math.toRadians(180)))
                                .forward(3)
                                .back(1)
                                .splineTo(new Vector2d(-48, 10), Math.toRadians(0))
                                //.splineToLinearHeading(new Pose2d(-48, 10, Math.toRadians(180)), Math.toRadians(0))
                                .back(70)
                                .splineTo(new Vector2d(51, 35), Math.toRadians(0))
                                //.splineToLinearHeading(new Pose2d(51,35, Math.toRadians(180)), Math.toRadians(0))
                                .forward(1)
                                .splineToLinearHeading(new Pose2d(43,13, Math.toRadians(180)), Math.toRadians(180))
                                .back(10)*/

                                /*//BLUE FAR LEFT
                                //UNDER GATE
                                .splineToConstantHeading(new Vector2d(-43, 33), Math.toRadians(-54.37))
                                .splineToLinearHeading(new Pose2d(-24.22, 34, Math.toRadians(270.00)), Math.toRadians(-64.16))
                                .strafeRight(10)
                                .lineToLinearHeading(new Pose2d(-55,35,Math.toRadians(180)))
                                .forward(3)
                                .back(1)
                                //.splineToLinearHeading(new Pose2d(-48, 10, Math.toRadians(180)), Math.toRadians(0))
                                .splineTo(new Vector2d(-35, 12), Math.toRadians(0))
                                .back(65)
                                .splineTo(new Vector2d(51, 35), Math.toRadians(0))
                                //.splineToLinearHeading(new Pose2d(51,35, Math.toRadians(180)), Math.toRadians(0))
                                .forward(1)
                                .splineToLinearHeading(new Pose2d(43,13, Math.toRadians(180)), Math.toRadians(180))
                                .back(10)*/
                                /*//UNDER TRUSS
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

                                /*//UNDER GATE
                                .splineToLinearHeading(new Pose2d(-47, 33, Math.toRadians(270)), Math.toRadians(270))
                                .back(2)
                                .lineToLinearHeading(new Pose2d(-57, 50, Math.toRadians(180)))
                                .lineToLinearHeading(new Pose2d(-57,35,Math.toRadians(180)))
                                .forward(3)
                                .back(3)
                                .strafeLeft(24)
                                //.splineToLinearHeading(new Pose2d(-57,11, Math.toRadians(180)), Math.toRadians(0))
                                .back(60)
                                .splineTo(new Vector2d(51, 35), Math.toRadians(0))
                                //.splineToLinearHeading(new Pose2d(51,35, Math.toRadians(180)), Math.toRadians(0))
                                .forward(1)
                                .splineToLinearHeading(new Pose2d(43,13, Math.toRadians(180)), Math.toRadians(180))
                                .back(10)*/

                               //BLUE CLOSE MIDDLE
                               //new Pose2d(14,62,Math.toRadians(270))
                               //UNDER GATE

                                /*.splineToLinearHeading(new Pose2d(22, 25, Math.toRadians(0)), Math.toRadians(270))
                                .splineToLinearHeading(new Pose2d(47,30, Math.toRadians(180)), Math.toRadians(0))
                                .back(5)
                                .forward(1)
                                .splineTo(new Vector2d(15, 11), Math.toRadians(180))
                               .forward(55)
                                .splineTo(new Vector2d(-58, 10), Math.toRadians(180))
                               .back(70)
                                .splineTo(new Vector2d(51, 35), Math.toRadians(0))
                               .forward(1)
                               .splineToLinearHeading(new Pose2d(43,60, Math.toRadians(180)), Math.toRadians(180))
                               .back(10)*/
                               /*// UNDER TRUSS
                               .splineToLinearHeading(new Pose2d(22, 25, Math.toRadians(180)), Math.toRadians(270))
                               .back(2)
                               .splineToLinearHeading(new Pose2d(51,30, Math.toRadians(180)), Math.toRadians(0))
                               .back(5)
                               .forward(1)
                                .splineTo(new Vector2d(1, 59), Math.toRadians(180))
                               //.splineToLinearHeading(new Pose2d(1.00, 59, Math.toRadians(180.00)), Math.toRadians(180.00))
                               .forward(35)
                                .splineTo(new Vector2d(-58, 35), Math.toRadians(180))
                               //.splineToLinearHeading(new Pose2d(-58, 35, Math.toRadians(180)), Math.toRadians(180))
                               .back(1)
                                .splineTo(new Vector2d(-30, 59), Math.toRadians(0))
                               //.splineToLinearHeading(new Pose2d(-44,59, Math.toRadians(180)), Math.toRadians(0))
                               .back(35)
                                .splineTo(new Vector2d(51, 35), Math.toRadians(0))
                               //.splineToLinearHeading(new Pose2d(51,35, Math.toRadians(180)), Math.toRadians(0))
                               .forward(1)
                               .splineToLinearHeading(new Pose2d(43,58, Math.toRadians(180)), Math.toRadians(180))
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

                                /*//UNDER TRUSS
                                .forward(5)
                                .splineToConstantHeading(new Vector2d(17.41, 37.38), Math.toRadians(234.55))
                                .splineToLinearHeading(new Pose2d(2.00, 37.00, Math.toRadians(270.00)), Math.toRadians(180))
                                .strafeLeft(10)
                                .lineToLinearHeading(new Pose2d(51,35, Math.toRadians(180)))
                                .forward(1)
                                .splineTo(new Vector2d(1, 59), Math.toRadians(180))
                                //.splineToLinearHeading(new Pose2d(1.00, 59, Math.toRadians(180.00)), Math.toRadians(180.00))
                                .forward(35)
                                .splineTo(new Vector2d(-58, 35), Math.toRadians(180))
                                //.splineToLinearHeading(new Pose2d(-58, 35, Math.toRadians(180)), Math.toRadians(180))
                                .back(1)
                                .splineTo(new Vector2d(-30, 59), Math.toRadians(0))
                                //.splineToLinearHeading(new Pose2d(-44,59, Math.toRadians(180)), Math.toRadians(0))
                                .back(35)
                                .splineTo(new Vector2d(51, 35), Math.toRadians(0))
                                //.splineToLinearHeading(new Pose2d(51,35, Math.toRadians(180)), Math.toRadians(0))
                                .forward(1)
                                .splineToLinearHeading(new Pose2d(43,58, Math.toRadians(180)), Math.toRadians(180))
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

                                /*
                                //UNDER TRUSS
                                .forward(5)
                                .splineToLinearHeading(new Pose2d(22, 43, Math.toRadians(270.00)), Math.toRadians(0))
                                .back(3)
                                .lineToLinearHeading(new Pose2d(33, 57, Math.toRadians(180)))
                                .back(1)
                                .splineToLinearHeading(new Pose2d(51, 30, Math.toRadians(180)), Math.toRadians(0))
                                .forward(1)
                                .splineTo(new Vector2d(1, 59), Math.toRadians(180))
                                //.splineToLinearHeading(new Pose2d(1.00, 59, Math.toRadians(180.00)), Math.toRadians(180.00))
                                .forward(35)
                                .splineTo(new Vector2d(-58, 35), Math.toRadians(180))
                                //.splineToLinearHeading(new Pose2d(-58, 35, Math.toRadians(180)), Math.toRadians(180))
                                .back(1)
                                .splineTo(new Vector2d(-30, 59), Math.toRadians(0))
                                //.splineToLinearHeading(new Pose2d(-44,59, Math.toRadians(180)), Math.toRadians(0))
                                .back(35)
                                .splineTo(new Vector2d(51, 35), Math.toRadians(0))
                                //.splineToLinearHeading(new Pose2d(51,35, Math.toRadians(180)), Math.toRadians(0))
                                .forward(1)
                                .splineToLinearHeading(new Pose2d(43,58, Math.toRadians(180)), Math.toRadians(180))
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
                                /*//UNDER TRUSS
                                .splineToLinearHeading(new Pose2d(22, -25, Math.toRadians(180)), Math.toRadians(90))
                                .back(2)
                                .splineToLinearHeading(new Pose2d(51,-30, Math.toRadians(180)), Math.toRadians(0))
                                .back(5)
                                .forward(1)
                                .splineTo(new Vector2d(1, -59), Math.toRadians(180))
                                //.splineToLinearHeading(new Pose2d(1.00, 59, Math.toRadians(180.00)), Math.toRadians(180.00))
                                .forward(35)
                                .splineTo(new Vector2d(-58, -35), Math.toRadians(180))
                                //.splineToLinearHeading(new Pose2d(-58, 35, Math.toRadians(180)), Math.toRadians(180))
                                .back(1)
                                .splineTo(new Vector2d(-30, -59), Math.toRadians(0))
                                //.splineToLinearHeading(new Pose2d(-44,59, Math.toRadians(180)), Math.toRadians(0))
                                .back(35)
                                .splineTo(new Vector2d(51, -35), Math.toRadians(0))
                                //.splineToLinearHeading(new Pose2d(51,35, Math.toRadians(180)), Math.toRadians(0))
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
                                 /*//UNDER TRUSS
                                .forward(5)
                                .splineToLinearHeading(new Pose2d(19.56, -36.00, Math.toRadians(90.00)), Math.toRadians(128.40))
                                .splineToLinearHeading(new Pose2d(2, -37, Math.toRadians(90.00)), Math.toRadians(175.35))
                                .strafeRight(10)
                                .lineToLinearHeading(new Pose2d(49,-35, Math.toRadians(180)))
                                .back(3)
                                .forward(1)
                                .splineTo(new Vector2d(1, -59), Math.toRadians(180))
                                //.splineToLinearHeading(new Pose2d(1.00, 59, Math.toRadians(180.00)), Math.toRadians(180.00))
                                .forward(35)
                                .splineTo(new Vector2d(-58, -35), Math.toRadians(180))
                                //.splineToLinearHeading(new Pose2d(-58, 35, Math.toRadians(180)), Math.toRadians(180))
                                .back(1)
                                .splineTo(new Vector2d(-30, -59), Math.toRadians(0))
                                //.splineToLinearHeading(new Pose2d(-44,59, Math.toRadians(180)), Math.toRadians(0))
                                .back(35)
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
                                /*//UNDER TRUSS
                                .forward(5)
                                .splineToLinearHeading(new Pose2d(22, -43, Math.toRadians(90)), Math.toRadians(0))
                                .back(3)
                                .lineToLinearHeading(new Pose2d(33, -57, Math.toRadians(180)))
                                .back(1)
                                .splineToLinearHeading(new Pose2d(51, -30, Math.toRadians(180)), Math.toRadians(0))
                                .forward(1)
                                .splineTo(new Vector2d(1, -59), Math.toRadians(180))
                                //.splineToLinearHeading(new Pose2d(1.00, 59, Math.toRadians(180.00)), Math.toRadians(180.00))
                                .forward(35)
                                .splineTo(new Vector2d(-58, -35), Math.toRadians(180))
                                //.splineToLinearHeading(new Pose2d(-58, 35, Math.toRadians(180)), Math.toRadians(180))
                                .back(1)
                                .splineTo(new Vector2d(-30, -59), Math.toRadians(0))
                                //.splineToLinearHeading(new Pose2d(-44,59, Math.toRadians(180)), Math.toRadians(0))
                                .back(35)
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

                                //RED FAR RIGHT
                                //UNDER TRUSS
                                .splineToLinearHeading(new Pose2d(-33, -34, Math.toRadians(180)), Math.toRadians(0))
                                .splineToLinearHeading(new Pose2d(-40,-59, Math.toRadians(180)), Math.toRadians(0))
                                .back(60)
                                .splineTo(new Vector2d(51,-35), Math.toRadians(0))
                                //.splineToLinearHeading(new Pose2d(51,35, Math.toRadians(180)), Math.toRadians(0))
                                .forward(1)
                                .splineToLinearHeading(new Pose2d(43,-13, Math.toRadians(180)), Math.toRadians(180))
                                .build()


                                /*//RED FAR MIDDLE
                                //UNDER TRUSS
                                .splineToLinearHeading(new Pose2d(-43, -24, Math.toRadians(180)), Math.toRadians(90))
                                .forward(14)
                                .back(1)
                                .splineToLinearHeading(new Pose2d(-45.45,-59, Math.toRadians(180)), Math.toRadians(0))
                                .back(45)
                                .splineTo(new Vector2d(51,-35), Math.toRadians(0))
                                .forward(1)
                                .splineToLinearHeading(new Pose2d(43,-13, Math.toRadians(180)), Math.toRadians(180))
                                .back(10)
                                .build()*/
                );

        meepMeep.setBackground(MeepMeep.Background.FIELD_CENTERSTAGE_JUICE_DARK)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(myBot)
                .start();
    }
}