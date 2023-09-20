package org.firstinspires.ftc.teamcode.Auto;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Servo;

public class Outake {
    public LinearOpMode opMode;

    public Servo outakeServo;

    public Outake(LinearOpMode opMode){
        this.opMode = opMode;
        outakeServo = opMode.hardwareMap.servo.get("outakeServo");
    }

    public void depositOne(){
        outakeServo.setPosition(.5);
    }
    public void depositTwo(){
        outakeServo.setPosition(1);
    }
    public void resetOutake(){
        outakeServo.setPosition(0);
    }
}
