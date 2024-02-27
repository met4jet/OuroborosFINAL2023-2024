package org.firstinspires.ftc.teamcode.OpenCV;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Auto.HardwareClass.VerticalLift;

//@Autonomous(group = "Auto", name = "TestingAutoClose")
public class TestingAuto extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {

        VerticalLift vl = new VerticalLift(this);

        waitForStart();
        //vl.movePID(100,.005,0,0);
        sleep(1000);

        /*DriveTrain dt = new DriveTrain(this);
        Flip flip = new Flip(this);
        VerticalLift vl = new VerticalLift(this);


        waitForStart();
        //if(opModeIsActive()) {
        dt.timeMove(-.5, 1500);
        //dt.timeStrafeLeft(.5,1000);
        //dt.timeMove(-.5, 1500);
        //vl.moveEncoderLiftUp(3000);
        //sleep(100);
        //flip.rflip();
        //flip.lflip();
        //flip.lflip();
        //vl.moveEncoderLiftDown(-2000);
        //dt.timeStrafeLeft(-.5,600);
        //dt.timeMove(-.5,500);
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