package org.firstinspires.ftc.teamcode.Auto;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.telemetry;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

public class DriveTrain {

    public LinearOpMode opMode;

    // Difficulty: EASY
    // Krish: Create your motors
    public DcMotor br;
    public DcMotor bl;
    public DcMotor fr;
    public DcMotor fl;


    public DriveTrain(LinearOpMode opMode){
        this.opMode = opMode;

        // Difficulty: EASY
        // Krish: Hardware map the motors
        // Assume we are using a four motor drivetrain
        br = opMode.hardwareMap.dcMotor.get("br");
        bl = opMode.hardwareMap.dcMotor.get("bl");
        fr = opMode.hardwareMap.dcMotor.get("fr");
        fl = opMode.hardwareMap.dcMotor.get("fl");

        // Difficulty: EASY
        // Krish: Set motors' zero power behavior
        br.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        fr.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        bl.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        fl.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        // Difficulty: EASY

        // Krish: Set motors' direction
        br.setDirection(DcMotor.Direction.FORWARD);
        bl.setDirection(DcMotor.Direction.REVERSE);
        fr.setDirection(DcMotor.Direction.FORWARD);
        fl.setDirection(DcMotor.Direction.REVERSE);

    }
    public void initalize(LinearOpMode opMode){
        this.opMode = opMode;

        // Difficulty: EASY
        // Krish: Hardware map the motors
        // Assume we are using a four motor drivetrain
        br = opMode.hardwareMap.dcMotor.get("br");
        bl = opMode.hardwareMap.dcMotor.get("bl");
        fr = opMode.hardwareMap.dcMotor.get("fr");
        fl = opMode.hardwareMap.dcMotor.get("fl");

        // Difficulty: EASY
        // Krish: Set motors' zero power behavior
        br.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        fr.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        bl.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        fl.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        // Difficulty: EASY

        // Krish: Set motors' direction
        br.setDirection(DcMotor.Direction.FORWARD);
        bl.setDirection(DcMotor.Direction.REVERSE);
        fr.setDirection(DcMotor.Direction.FORWARD);
        fl.setDirection(DcMotor.Direction.REVERSE);

    }

    public void resetEncoders(){
        // Difficulty: EASY
        // Krish
        br.setMode(DcMotor.RunMode.RESET_ENCODERS);
        br.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        fr.setMode(DcMotor.RunMode.RESET_ENCODERS);
        fr.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        bl.setMode(DcMotor.RunMode.RESET_ENCODERS);
        bl.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        fl.setMode(DcMotor.RunMode.RESET_ENCODERS);
        fl.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }

    public void power(double output){
        fl.setPower(-output);
        bl.setPower(-output);
        fr.setPower(output);
        br.setPower(output);
    }

    public void timeMove(double power, double runtime){
        ElapsedTime time = new ElapsedTime();
        time.reset();
        while(time.seconds() < runtime){
            br.setPower(power);
            bl.setPower(power);
            fr.setPower(power);
            fl.setPower(power);
            opMode.telemetry.addData("time", time.seconds());
            opMode.telemetry.update();
        }

        br.setPower(0);
        bl.setPower(0);
        fr.setPower(0);
        fl.setPower(0);
    }
    public void timeTurnLeft(double power, double runtime) {
        ElapsedTime time = new ElapsedTime();
        time.reset();
        while (time.seconds() < runtime) {
            br.setPower(power);
            bl.setPower(-power);
            fr.setPower(power);
            fl.setPower(-power);
            opMode.telemetry.addData("time", time.seconds());
            opMode.telemetry.update();
        }
        br.setPower(0);
        bl.setPower(0);
        fr.setPower(0);
        fl.setPower(0);
    }
    public void timeTurnRight(double power, double runtime) {
        ElapsedTime time = new ElapsedTime();
        time.reset();
        while (time.seconds() < runtime) {
            br.setPower(-power);
            bl.setPower(power);
            fr.setPower(-power);
            fl.setPower(power);
            opMode.telemetry.addData("time", time.seconds());
            opMode.telemetry.update();
        }
        br.setPower(0);
        bl.setPower(0);
        fr.setPower(0);
        fl.setPower(0);
    }

    public void timeStrafeLeft(double power, double runtime){


        ElapsedTime time = new ElapsedTime();
        time.reset();
        while(time.seconds() < runtime){
            br.setPower(-power);
            bl.setPower(power);
            fr.setPower(power);
            fl.setPower(-power);
            opMode.telemetry.addData("time", time.seconds());
            opMode.telemetry.update();
        }

        br.setPower(0);
        bl.setPower(0);
        fr.setPower(0);
        fl.setPower(0);
    }

    public void timeStrafeRight(double power, double runtime){
        ElapsedTime time = new ElapsedTime();
        time.reset();
        while(time.seconds() < runtime){
            br.setPower(power);
            bl.setPower(-power);
            fr.setPower(-power);
            fl.setPower(power);
            opMode.telemetry.addData("time", time.seconds());
            opMode.telemetry.update();
        }

        br.setPower(0);
        bl.setPower(0);
        fr.setPower(0);
        fl.setPower(0);
    }

    public void encoderMove(boolean isForward, double power, double distance, double runtime){
        // Difficulty: MEDIUM
        // Krish
        ElapsedTime time = new ElapsedTime();
        resetEncoders();
        time.reset();
        double movement = 0;

        while (time.seconds() < runtime && movement < distance && opMode.opModeIsActive()){
            // Sophia: you dont need to average them out because all of them will essentially be at the
            // same position, but if you are then make sure to use telemetry to show this movement variable
            // because it will be more useful than what you have right now
            movement = (bl.getCurrentPosition() + br.getCurrentPosition() + fl.getCurrentPosition() + fr.getCurrentPosition())/4;
            if (isForward) {
                br.setPower(power);
                bl.setPower(power);
                fr.setPower(power);
                fl.setPower(power);
            }
            else{
                br.setPower(-power);
                bl.setPower(-power);
                fr.setPower(-power);
                fl.setPower(-power);
            }

            telemetry.addLine("" + bl.getCurrentPosition());
            telemetry.addLine("" + fl.getCurrentPosition());
            telemetry.addLine("" + br.getCurrentPosition());
            telemetry.addLine("" + fr.getCurrentPosition());
            telemetry.addLine("" + movement);
        }
        kill();
        // Add telemetry
        // Sophia: you need to update the telemetry everytime it runs through the loops
        // Sophia: Also you need to put the telemetry inside the loop or all it will show is the final position
    }

    public void encoderTurn(boolean turnLeft, double power, double distance, double runtime){
        // Difficulty: MEDIUM
        // Krish
        ElapsedTime time = new ElapsedTime();
        resetEncoders();
        time.reset();
        double movement = 0;

        while (time.seconds() < runtime && movement < distance && opMode.opModeIsActive()){
            movement = (bl.getCurrentPosition() + br.getCurrentPosition() + fl.getCurrentPosition() + fr.getCurrentPosition())/4;
            // Sophia: can you change the isForward variable to turnLeft to make it more clear
            if (turnLeft) {
                br.setPower(0);
                bl.setPower(0);
                fr.setPower(power);
                fl.setPower(power);
            }
            else{
                br.setPower(power);
                bl.setPower(power);
                fr.setPower(0);
                fl.setPower(0);
            }
            telemetry.addLine("bl:" + bl.getCurrentPosition());
            telemetry.addLine("fl:" + fl.getCurrentPosition());
            telemetry.addLine("br:" + br.getCurrentPosition());
            telemetry.addLine("fr:" + fr.getCurrentPosition());
            telemetry.addLine("movement:" + movement);
        }
        kill();
        // Add telemetry
        // Sophia: You need to update the telemetry everytime it loops through the loop
        // Sophia: Also you need to put the telemetry inside the loop or all it will show is the final position
    }

    public void turnPID(/*add parameters*/){
        // Difficulty: HARD
        // Optional: Krish
        // Not required but feel free to add additional components like
        // feedforward, integral limit, or a low pass filter if you want to
        // Add telemetry


        // Sophia: NOOO KRISH, GIVE IT A TRYYY D:
        // Krish: I might later, but I'm sorta doing this entire assignment last second before I go on vacation so i don't really have time
    }

    public void kill(){
        // Difficulty: EASY
        // Krish
        fr.setPower(0);
        br.setPower(0);
        bl.setPower(0);
        fl.setPower(0);
    }

}