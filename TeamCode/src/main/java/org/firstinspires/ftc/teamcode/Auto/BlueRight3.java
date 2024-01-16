package org.firstinspires.ftc.teamcode.Auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Auto.DriveTrain;
import org.firstinspires.ftc.teamcode.Auto.Flip;
import org.firstinspires.ftc.teamcode.Auto.VerticalLift;

@Autonomous(group = "Auto", name = "BlueRight3")
public class BlueRight3 extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {

        DriveTrain dt = new DriveTrain(this);
        Flip flip = new Flip(this);
        VerticalLift vl = new VerticalLift(this);


        waitForStart();
        //if(opModeIsActive()) {
        //dt.timeMove(-.5, 1500);
        sleep(10);
        dt.timeMove(.3, 2);
        /*dt.timeStrafeLeft(.5,8);
        dt.timeMove(-.5, .3);
        vl.moveEncoderLiftUp(500);
        flip.rflip();*/

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