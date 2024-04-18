package org.firstinspires.ftc.teamcode.Auto.Random;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

public class MotorTestMethods {
    public LinearOpMode opMode;
    public DcMotor motor;
    //public DcMotor motor2;
    public MotorTestMethods (LinearOpMode opMode){
        this.opMode = opMode;
        motor = opMode.hardwareMap.dcMotor.get("motor");
        //motor2 = opMode.hardwareMap.dcMotor.get("motor2");
    }
    public void run(){
        motor.setPower(1);
        //motor2.setPower(1);
    }
}
