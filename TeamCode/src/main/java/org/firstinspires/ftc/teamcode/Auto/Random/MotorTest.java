package org.firstinspires.ftc.teamcode.Auto.Random;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous(group = "Auto", name = "MotorTest")
public class MotorTest extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        MotorTestMethods a = new MotorTestMethods(this);
        waitForStart();
        ElapsedTime t = new ElapsedTime();
        while(t.seconds() <4){
            a.run();
        }
    }
}
