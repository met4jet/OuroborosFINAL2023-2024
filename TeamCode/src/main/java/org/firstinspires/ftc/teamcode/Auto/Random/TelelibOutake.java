package org.firstinspires.ftc.teamcode.Auto.Random;

import static android.os.SystemClock.sleep;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

public abstract class TelelibOutake extends OpMode {
    public DcMotor verticalLiftRight;
    public DcMotor verticalLiftLeft;
    public Servo rflip;
    public Servo lflip;
    public Servo shoomShoomSub;
    public Servo pixelServo;

    public void init(){
        rflip = hardwareMap.get(Servo.class, "rflip");
//        lflip = hardwareMap.get(Servo.class, "lflip");
        shoomShoomSub = hardwareMap.get(Servo.class, "shoomShoomSub");
        pixelServo = hardwareMap.get(Servo.class, "pixelServo");


//        verticalLiftRight = hardwareMap.get(DcMotor.class, "vlr");
//        verticalLiftLeft = hardwareMap.get(DcMotor.class, "vll");


//        verticalLiftRight.setDirection(DcMotorSimple.Direction.REVERSE);
//        verticalLiftLeft.setDirection(DcMotorSimple.Direction.FORWARD);
    }

    public void sub(){
        if(gamepad1.left_bumper) {
            shoomShoomSub.setPosition(.4);
            sleep(300);
            shoomShoomSub.setPosition(1);
        }
    }
    public void rflip(){
        rflip.setDirection(Servo.Direction.FORWARD);
        if(gamepad2.right_bumper) {
            rflip.setPosition(1);
            sleep(1000);
            rflip.setPosition(0);
        }
        /*else{
            rflip.setPosition(0);
        }*/
    }
    public void lflip(){
        if(gamepad2.left_bumper){
            lflip.setPosition(0);
            sleep(1000);
            lflip.setPosition(1);
            telemetry.addData("lbumper2", gamepad2.left_bumper);
            telemetry.addData("lbumper2", lflip.getDirection());
        }
        /*else{
            lflip.setPosition(1);
        }*/
    }
    public void deposit()
    {
        if (gamepad2.a) {
            pixelServo.setPosition(0);
            //sleep(500);
            //pixelServo.setPosition(1);
            telemetry.addData("Pos", pixelServo.getPosition());
        }
    }
    public void vertical_lift_left(){
        if (gamepad2.left_stick_y > .2) {
            verticalLiftLeft.setPower(1);
            if(!gamepad2.left_bumper)
                lflip.setPosition(.8);
            telemetry.addData("vLeft encoders", verticalLiftLeft.getCurrentPosition());
            telemetry.update();
        } else if (gamepad2.left_stick_y < -.2) {
            verticalLiftLeft.setPower(-1);
            if(!gamepad2.left_bumper)
                lflip.setPosition(.8);
            telemetry.addData("vLeft encoders", verticalLiftLeft.getCurrentPosition());
            telemetry.update();
        }
        else {
            verticalLiftLeft.setPower(0);
        }
    }
    public void vertical_lift_right(){
        if (gamepad2.right_stick_y > .2) {
            verticalLiftRight.setPower(-1);
            if(!gamepad2.right_bumper)
                rflip.setPosition(.3);
        } else if (gamepad2.right_stick_y < -.2) {
            verticalLiftRight.setPower(1);
            if(!gamepad2.right_bumper)
                rflip.setPosition(.3);
        }
        else {
            verticalLiftRight.setPower(0);
        }
    }
    public void kill(){

    }
}
