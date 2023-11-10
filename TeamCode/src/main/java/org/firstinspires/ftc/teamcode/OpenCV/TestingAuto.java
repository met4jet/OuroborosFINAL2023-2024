package org.firstinspires.ftc.teamcode.OpenCV;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Auto.DriveTrain;

@Autonomous(group = "Auto", name = "TestingAuto")
public class TestingAuto extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {

        DriveTrain dt = new DriveTrain(this);

        waitForStart();
        //if(opModeIsActive()) {
            dt.timeMove(.5, 2000);
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