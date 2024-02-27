package org.firstinspires.ftc.teamcode.Auto.Random;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Auto.HardwareClass.DriveTrain;
import org.firstinspires.ftc.teamcode.Auto.HardwareClass.Flip;
import org.firstinspires.ftc.teamcode.Auto.HardwareClass.Intake;
import org.firstinspires.ftc.teamcode.Auto.HardwareClass.VerticalLift;

//@Autonomous(group = "Auto", name = "RedRight3")
public class RedRight3 extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {

        DriveTrain dt = new DriveTrain(this);
        Flip flip = new Flip(this);
        VerticalLift vl = new VerticalLift(this);
        Intake intake = new Intake(this);

        waitForStart();
        dt.timeMove(.3, 2);
        sleep(1000);

        dt.timeMove(-.3,2);
        dt.timeStrafeRight(.5,3);
        intake.deliverPurple(5);

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