package org.firstinspires.ftc.teamcode.Auto.HardwareClass;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

public class Intake {
    public LinearOpMode opMode;
    public CRServo intake;
    public CRServo linac;
    public CRServo rollers;
    public Intake(LinearOpMode opMode){
        this.opMode = opMode;
        linac = opMode.hardwareMap.get(CRServo.class, "linac");
        rollers = opMode.hardwareMap.get(CRServo.class, "rollers");
        intake = opMode.hardwareMap.get(CRServo.class, "intake");
    }
    public void downLinac(){
        linac.setPower(1);
    }
    public void upLinac(){
        ElapsedTime time = new ElapsedTime();
        time.reset();
        linac.setPower(-1);
        while(time.seconds() < 2){
        }
        linac.setPower(0);
    }

    public void deliverPurple(double sec){
        ElapsedTime time = new ElapsedTime();
        time.reset();
        //linac.setPower(1);


        rollers.setPower(1);
        intake.setPower(1);

        time.reset();
        while(time.seconds() < sec){
        }

        //linac.setPower(0);
        rollers.setPower(0);
        intake.setPower(0);
    }
    public void backIntake(double sec){
        ElapsedTime time = new ElapsedTime();
        time.reset();
        intake.setPower(1);
        rollers.setPower(-1);
        if(time.seconds() < sec){
        }
        intake.setPower(0);
        rollers.setPower(0);
    }
}