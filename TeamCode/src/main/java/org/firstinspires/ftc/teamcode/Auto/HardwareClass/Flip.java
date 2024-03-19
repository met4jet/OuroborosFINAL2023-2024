package org.firstinspires.ftc.teamcode.Auto.HardwareClass;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Servo;

public class Flip {
    public LinearOpMode opMode;
    public Servo rflip;
    public Servo lflip;

    public Flip(LinearOpMode opMode) {
        this.opMode = opMode;

        rflip = opMode.hardwareMap.get(Servo.class, "rflip");
        lflip = opMode.hardwareMap.get(Servo.class, "lflip");
    }

    public void lflip(){
        lflip.setPosition(0);
        opMode.sleep(500);
//        lflip.setPosition(.8);
//        opMode.sleep(100);
    }
    public void lflipHold(){
        lflip.setPosition(.8);
        opMode.sleep(100);
//        lflip.setPosition(.8);
//        opMode.sleep(100);
    }

    public void rflip(){
        rflip.setPosition(.8);
        opMode.sleep(500);
        rflip.setPosition(.2);
        opMode.sleep(500);
    }
    public void rflipUno(){
        rflip.setPosition(.8);
        opMode.sleep(250);
    }
    public void holdRightFlip(){
        rflip.setPosition(.2);
    }

}
