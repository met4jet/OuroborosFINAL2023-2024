package org.firstinspires.ftc.teamcode.Auto.HardwareClass;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.ColorRangeSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

public class Intake {
    public LinearOpMode opMode;
    public DcMotor intake;
    public Servo axon;
    public CRServo pixelServo;
    public ColorRangeSensor colorSensor;

    public Intake(LinearOpMode opMode){
        this.opMode = opMode;
        pixelServo = opMode.hardwareMap.get(CRServo.class, "pixelServo");
        intake = opMode.hardwareMap.get(DcMotor.class, "intake");
        axon = opMode.hardwareMap.get(Servo.class, "axon");
        colorSensor = opMode.hardwareMap.get(ColorRangeSensor.class, "color");

    }
//    public void axonUp (double sec){
//        ElapsedTime time = new ElapsedTime();
//        time.reset();
//
//        axon.setPower(.5);
//
//        time.reset();
//        while(time.seconds() < sec){
//        }
//    }

    public void axonDown (double sec){
        ElapsedTime time = new ElapsedTime();
        time.reset();

        axon.setPosition(0);
        time.reset();
        while(time.seconds() < sec){
        }

    }

    public void axonUp (double sec){
        ElapsedTime time = new ElapsedTime();

        axon.setPosition(1);

    }

    public void deliverPurple(double sec){
        ElapsedTime time = new ElapsedTime();
        time.reset();

//        axon.setPosition(.8);
        intake.setPower(-.38);
//        axon.setPower(.5);

        time.reset();
        while(time.seconds() < sec){
        }

        intake.setPower(0);
//        axon.setPower(0);
    }
//    public void getWhite(double sec){
//        ElapsedTime time = new ElapsedTime();
//        time.reset();
//
//        intake.setPower(.4);
//        axon.setPower(.5);
//
//        time.reset();
//        while(time.seconds() < sec){
//        }
//
//        intake.setPower(0);
//        axon.setPower(0);
//    }

        public void getWhite(double sec){
        ElapsedTime time = new ElapsedTime();
        time.reset();

//        axon.setPosition(.25);
        time.reset();
        while(time.seconds() < 1){
        }
        intake.setPower(.6);


        time.reset();
        while(time.seconds() < sec){
        }

        intake.setPower(0);
//        axon.setPower(0);
    }
    public void getWhite1(double sec){
        ElapsedTime time = new ElapsedTime();
        time.reset();
//        axon.setPosition(.475);
        intake.setPower(.75);

        time.reset();
        while(time.seconds() < sec){
        }

        intake.setPower(0);
//        axon.setPower(0);
    }
    public void getWhite2(double sec){
        ElapsedTime time = new ElapsedTime();
        time.reset();
        axon.setPosition(.7);
        while(colorSensor.getDistance(DistanceUnit.CM) > 4) {
//            axon.setPosition(.475);
            intake.setPower(.75);
            opMode.telemetry.addData("Distance", colorSensor.getDistance(DistanceUnit.CM));
            opMode.telemetry.update();
        }

        intake.setPower(-.75);
        opMode.telemetry.addData("Distance", colorSensor.getDistance(DistanceUnit.CM));
        opMode.telemetry.update();
        while(time.seconds() < sec){

        }
    }
    public void getWhite2low(double sec){
        ElapsedTime time = new ElapsedTime();
        time.reset();
        axon.setPosition(.6);
        while(//colorSensor.getDistance(DistanceUnit.CM) > 4 &&
         time.seconds() < sec) {
//            axon.setPosition(.475);
            intake.setPower(.75);
            opMode.telemetry.addData("Distance", colorSensor.getDistance(DistanceUnit.CM));
            opMode.telemetry.update();
        }


        intake.setPower(-.75);
        opMode.telemetry.addData("Distance", colorSensor.getDistance(DistanceUnit.CM));
        opMode.telemetry.update();
    }
    public void deposit(){
        pixelServo.setPower(-.3);
    }
    public void killDeposit(){
        pixelServo.setPower(0);
    }
    public void getWhite2new(double sec){
        ElapsedTime time = new ElapsedTime();
        time.reset();
        axon.setPosition(.7);
        intake.setPower(.75);
        while(time.seconds() < sec){
            intake.setPower(.75);
        }
        intake.setPower(.75);
    }
    public void backIntake(double sec){
        ElapsedTime time = new ElapsedTime();
        time.reset();
        intake.setPower(-1);
    }
    public void inIntake(){
        intake.setPower(1);
    }
    public void reverseIntake(double sec){
        ElapsedTime time = new ElapsedTime();
        time.reset();
        intake.setPower(-1);
    }
    public void kill(){
        intake.setPower(0);
    }
}