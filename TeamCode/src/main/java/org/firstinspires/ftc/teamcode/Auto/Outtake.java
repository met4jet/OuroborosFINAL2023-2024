package org.firstinspires.ftc.teamcode.Auto;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.opMode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Servo;

public class Outtake {
    public LinearOpMode opMode;
    public Servo outtakeLeft;
    public Servo outtakeRight;

    public Outtake(LinearOpMode opMode) {
        this.opMode = opMode;

        outtakeLeft = opMode.hardwareMap.servo.get("outtakeLeft");
        outtakeRight = opMode.hardwareMap.servo.get("outtakeRight");
    }

    public void flipperLeft(){
        outtakeLeft.setPosition(1);
        while (outtakeLeft.getPosition() != 1){
            // do nothing
        }
        outtakeLeft.setPosition(0);
    }

    public void flipperRight(){
        outtakeRight.setPosition(1);
        while (outtakeRight.getPosition() != 1){
            // do nothing
        }
        outtakeRight.setPosition(0);
    }

}
