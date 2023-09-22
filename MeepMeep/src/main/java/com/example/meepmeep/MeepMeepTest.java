package com.example.meepmeep;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

public class MeepMeepTest {
    public static void main(String[] args){
        MeepMeep meepmeep = new MeepMeep(800);

        RoadRunnerBotEntity myBot = new DefaultBotBuilder(meepmeep)
                .setConstraints(60, 60, Math.toRadians(100), Math.toRadians(180), 15)
                .followTrajectorySequence(drive ->
                                drive.trajectorySequenceBuilder(new Pose2d(-36,-68,Math.toRadians(90)))
                                        .forward(30)
                                        .turn(Math.toRadians(90))
                                        .back(30)
                                        .lineTo(new Vector2d(0,0))
                                        .lineToConstantHeading(new Vector2d(60,65)) //linear methods above
                                        .lineToSplineHeading(new Pose2d(0,0, Math.toRadians(0)))
// end with heading of 0 after movement over
                                        .splineTo(new Vector2d(20,20), Math.toRadians(90))
// moves in arc
                                        .splineToConstantHeading(new Vector2d(-55,-44), Math.toRadians(0)) // heading will never change when moving in arc motion
                                        .splineToLinearHeading(new Pose2d(40,40,Math.toRadians(225)), Math.toRadians(90))
// just move different than .splineToConstant…
                                        .splineToSplineHeading(new Pose2d(0,0,Math.toRadians(180)), Math.toRadians(90)) //
                                        .build()
                );
        meepmeep.setBackground(MeepMeep.Background.FIELD_POWERPLAY_OFFICIAL)
                .setDarkMode(true)
                .setBackgroundAlpha(.95f)
                .addEntity(myBot)
                .start();
    }

}