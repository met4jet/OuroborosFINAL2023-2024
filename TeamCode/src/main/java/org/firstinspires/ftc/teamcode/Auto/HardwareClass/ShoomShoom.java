package org.firstinspires.ftc.teamcode.Auto.HardwareClass;

import static android.os.SystemClock.sleep;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

public class ShoomShoom {
    public Servo dom;
    public Servo sub;
    public LinearOpMode opMode;
    public ShoomShoom(LinearOpMode opMode){
        this.opMode = opMode;

        dom = opMode.hardwareMap.get(Servo.class, "shoomShoomDom");
        sub = opMode.hardwareMap.get(Servo.class, "shoomShoomSub");
    }

    public void transfer(){
        dom.setPosition(.3);


        ElapsedTime time = new ElapsedTime();
        time.reset();

        while(time.milliseconds() < 500){
        }

        sub.setPosition(.45);

        sub.setPosition(1);
        time.reset();
        while(time.milliseconds() < 500){
        }
        dom.setPosition(.72);
    }

    public void transferSub(){

        dom.setPosition(1);
        ElapsedTime time = new ElapsedTime();
        time.reset();
        while(time.milliseconds() < 500){
        }
        dom.setPosition(.72);

    }
    public void transferSub1(){
        ElapsedTime time = new ElapsedTime();
        time.reset();
        while(time.milliseconds() < 500){
        }
        dom.setPosition(.72);
    }
}
