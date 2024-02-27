package com.example.meepmeep;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

public class MeepMeepTesting {
    public static void main(String[] args) {
        MeepMeep meepMeep = new MeepMeep(800);

        RoadRunnerBotEntity myBot = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 14)
                .followTrajectorySequence(drive ->
                        drive.trajectorySequenceBuilder(new Pose2d(14,62,Math.toRadians(270)))
                                .splineToLinearHeading(new Pose2d(22, 25, Math.toRadians(180)), Math.toRadians(270))
                                .back(8)
                                .strafeTo(new Vector2d(48,35))
                                .back(8)
                                .strafeTo(new Vector2d(38, 55))
                                .strafeTo(new Vector2d(43,60))
                                .back(10)
                                .build()
                );

        meepMeep.setBackground(MeepMeep.Background.FIELD_CENTERSTAGE_JUICE_DARK)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(myBot)
                .start();
    }
}
    /*
        RED CLOSE LEFT
        .back(27)
        .turn(Math.toRadians(-90))
        .forward(7)
        .back(50)
        .strafeTo(new Vector2d(43, -16))
        .back(10)

        RED CLOSE MIDDLE
            `.turn(Math.toRadians(180))
            .forward(33)
            .back(8)
            .turn(Math.toRadians(90))
            .strafeTo(new Vector2d(52,-35))
            .strafeTo(new Vector2d(43, -16))
            .back(10)`

        RED CLOSE RIGHT
        .turn(Math.toRadians(180))
        .strafeTo(new Vector2d(20,-34))
        .back(4)
        .back(7)
        .turn(Math.toRadians(90))
        .strafeTo(new Vector2d(45, -35))
        .back(8)
        .strafeTo(new Vector2d(43,-60))
        .back(15)

        BLUE FAR LEFT
        .back(27)
        .turn(Math.toRadians(-90))
        .forward(7)

        .back(11)
        .strafeRight(22)
        .turn(Math.toRadians(180))
        .back(70)
        .strafeTo(new Vector2d(45, 40))
        .back(7)

        .strafeTo(new Vector2d(43, 13))
        .back(5)

        BLUE FAR MIDDLE
        .turn(Math.toRadians(180))
        .forward(36)

        .back(3)
        .turn(Math.toRadians(-90))
        .forward(11)
        .strafeLeft(18)
        .back(70)
        .strafeTo(new Vector2d(49, 34))
        .back(3)

        .strafeTo(new Vector2d(43, 13))
        .back(6)

        BLUE FAR RIGHT
        .turn(Math.toRadians(180))
        .strafeTo(new Vector2d(-45,37))
        .back(4)

        .back(4)
        .strafeLeft(11)
        .turn(Math.toRadians(-90))
        .strafeLeft(33)
        .back(70)
        .strafeTo(new Vector2d(49, 31))
        .back(10)

        .strafeTo(new Vector2d(45, 15))
        .back(7)

        RED FAR LEFT
        .back(27)
        .turn(Math.toRadians(-90))
        .forward(5)

        .back(11)
        .strafeRight(22)
        .back(70)
        .strafeTo(new Vector2d(45, -40))
        .back(7)

        .strafeTo(new Vector2d(43, -13))
        .back(5)

        RED FAR MIDDLE
        .turn(Math.toRadians(180))
        .forward(36)

        .back(3)
        .turn(Math.toRadians(90))
        .forward(11)
        .strafeRight(18)
        .back(70)
        .strafeTo(new Vector2d(49, -34))
        .back(3)

        .strafeTo(new Vector2d(43, -13))
        .back(6)

        RED FAR RIGHT
        .back(27)
        .turn(Math.toRadians(90))
        .forward(7)

        .back(11)
        .strafeLeft(22)
        .turn(Math.toRadians(180))
        .back(70)
        .strafeTo(new Vector2d(45, -40))
        .back(7)

        .strafeTo(new Vector2d(43, -13))
        .back(5)

        BLUE CLOSE RIGHT
        .back(25)
        .turn(Math.toRadians(90))
        .forward(6)
        .back(50)
        .strafeTo(new Vector2d(43, 60))
        .back(5)

        BLUE CLOSE LEFT
        .turn(Math.toRadians(180))
        .strafeTo(new Vector2d(20,34))
        .back(4)
        .back(7)
        .turn(Math.toRadians(-90))
        .strafeTo(new Vector2d(45, 35))
        .back(8)
        .strafeTo(new Vector2d(43,16))
        .back(15)

        BLUE CLOSE MIDDLE
        .turn(Math.toRadians(180))
        .forward(33)
        .back(8)
        .turn(Math.toRadians(-90))
        .strafeTo(new Vector2d(52,35))
        .strafeTo(new Vector2d(43, 16))
        .back(10)

     */