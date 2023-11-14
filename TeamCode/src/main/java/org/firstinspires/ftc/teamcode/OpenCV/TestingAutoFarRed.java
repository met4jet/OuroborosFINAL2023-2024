package org.firstinspires.ftc.teamcode.OpenCV;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Auto.DriveTrain;
import org.firstinspires.ftc.teamcode.Auto.Flip;
import org.firstinspires.ftc.teamcode.Auto.VerticalLift;

@Autonomous(group = "Auto", name = "TestingAutoFarRed")
public class TestingAutoFarRed extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {

        DriveTrain dt = new DriveTrain(this);
        Flip flip = new Flip(this);
        VerticalLift vl = new VerticalLift(this);


        waitForStart();
        //if(opModeIsActive()) {
        //dt.timeMove(-.5, 1500);
        dt.timeMove(.5, 2500);
        dt.timeStrafeLeft(-.5,8000);
        dt.timeMove(-.5, 300);
        vl.moveEncoderLiftUpLeft(500);
        flip.rflip();

        //}

        /*Flip flip = new Flip(this);
        VerticalLift vl = new VerticalLift(this);

        waitForStart();

        vl.moveEncoderLiftUp(4000);
        sleep(100);
        flip.rflip();
        flip.lflip();
        vl.moveEncoderLiftDown(-3800);*/
    }
}