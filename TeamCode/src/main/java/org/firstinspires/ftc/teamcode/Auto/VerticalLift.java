package org.firstinspires.ftc.teamcode.Auto;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;

public class VerticalLift {
    public LinearOpMode opMode;
    public DcMotor verticalLiftRight;
    public DcMotor verticalLiftLeft;

    public double a;

    public VerticalLift(LinearOpMode opMode){
        this.opMode = opMode;
        verticalLiftRight = opMode.hardwareMap.get(DcMotor.class, "vlr");
        verticalLiftLeft = opMode.hardwareMap.get(DcMotor.class, "vll");

        verticalLiftRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        verticalLiftLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        verticalLiftRight.setDirection(DcMotorSimple.Direction.FORWARD);
        verticalLiftLeft.setDirection(DcMotorSimple.Direction.REVERSE);
    }

    public void resetEncoders(){
        verticalLiftRight.setMode(DcMotor.RunMode.RESET_ENCODERS);
        verticalLiftRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        verticalLiftLeft.setMode(DcMotor.RunMode.RESET_ENCODERS);
        verticalLiftLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }

    public void moveEncoderLiftDown(double distance){
        resetEncoders(); // negative

        while (verticalLiftRight.getCurrentPosition() > distance && opMode.opModeIsActive()){
            verticalLiftRight.setPower(-1);
            verticalLiftLeft.setPower(-1);
            opMode.telemetry.addLine("verticalLiftRight pos:" + verticalLiftRight.getCurrentPosition());
        }
        verticalLiftRight.setPower(0);
        verticalLiftLeft.setPower(0);
    }

    public void moveEncoderLiftUp(double distance){
        ElapsedTime time = new ElapsedTime();
        resetEncoders(); // positive
        time.reset();
        while (verticalLiftRight.getCurrentPosition() < distance && opMode.opModeIsActive() && time.seconds() < .33){
            verticalLiftRight.setPower(1);
            //verticalLiftLeft.setPower(1);
            opMode.telemetry.addLine("verticalLiftRight pos:" + verticalLiftRight.getCurrentPosition());
            opMode.telemetry.update();
        }
        verticalLiftRight.setPower(0);
        //verticalLiftLeft.setPower(0);
    }
    public void moveEncoderLiftUpLeft(double distance){
        ElapsedTime time = new ElapsedTime();
        resetEncoders(); // positive
        time.reset();
        while (verticalLiftRight.getCurrentPosition() < distance && opMode.opModeIsActive() && time.seconds() < .33){
            verticalLiftRight.setPower(1);
            //verticalLiftLeft.setPower(1);
            opMode.telemetry.addLine("verticalLiftRight pos:" + verticalLiftRight.getCurrentPosition());
            opMode.telemetry.addLine("time:" + time.seconds());
            opMode.telemetry.update();
        }
        verticalLiftRight.setPower(0);
        //verticalLiftLeft.setPower(0);
    }

    public void movePIDRight(int position, double kp, double ki, double kd, int time){
        if(position != 0) {
            resetEncoders();
        }

        ElapsedTime timer = new ElapsedTime();
        double p;
        double i = 0;
        double d;
        double a = .5;
        double prevP = 0;
        double power;
        double iLimit = .25/ki;

        double currFilterEst;
        double prevFilterEst = 0;

        //int currPos = verticalLiftRight.getCurrentPosition();

        timer.reset();

        while(Math.abs(Math.abs(verticalLiftRight.getCurrentPosition()) - position) > 5 && opMode.opModeIsActive() && timer.seconds() < time){

            p = verticalLiftRight.getCurrentPosition() - position;

            i += (p - prevP) * timer.seconds();

            if (Math.abs(i) > iLimit) {
                i = Math.signum(i) * iLimit;
            }

            currFilterEst = (a * prevFilterEst) + (1 - a) * (p - prevP);
            prevFilterEst = currFilterEst;
            d = currFilterEst/timer.seconds();
            power = p * kp + i * ki + d * kd;
            verticalLiftRight.setPower(power);

            prevP = p;

            timer.reset();

            opMode.telemetry.addData("p :: ", p * kp);
            opMode.telemetry.addData("i :: ", i * ki);
            opMode.telemetry.addData("d :: ", d * kd);
            opMode.telemetry.addData("power", power);
            opMode.telemetry.addData("Current Position", verticalLiftRight.getCurrentPosition());
            opMode.telemetry.addData("Target Position", position);
            opMode.telemetry.update();
        }
    }
    public void movePIDLeft(int position, double kp, double ki, double kd, int time){
        if(position != 0) {
            resetEncoders();
        }

        ElapsedTime timer = new ElapsedTime();
        double p;
        double i = 0;
        double d;
        double a = .5;
        double prevP = 0;
        double power;
        double iLimit = .25/ki;

        double currFilterEst;
        double prevFilterEst = 0;

        //int currPos = verticalLiftRight.getCurrentPosition();

        timer.reset();

        while(Math.abs(Math.abs(verticalLiftLeft.getCurrentPosition()) - position) > 5 && opMode.opModeIsActive() && timer.seconds() < time){

            // Calculates the error (distance between desired and current position)
            // Slows motor down so prevent overshooting
            p = verticalLiftLeft.getCurrentPosition() - position;

            // Calculates the area under the curve
            // Adds additional power to overcome outside forces like friction preventing undershooting
            i += (p - prevP) * timer.seconds();

            // Sets limit on integral to prevent integral windup
            if (Math.abs(i) > iLimit) {
                i = Math.signum(i) * iLimit;
            }

            // Low pass filter used to filter noise for d
            currFilterEst = (a * prevFilterEst) + (1 - a) * (p - prevP);
            prevFilterEst = currFilterEst;

            // Calculates change in error
            // Tune to reduce oscillations
            d = currFilterEst/timer.seconds();

            // Calculates and sets power
            power = p * kp + i * ki + d * kd;
            verticalLiftLeft.setPower(power);

            prevP = p;

            timer.reset();

            opMode.telemetry.addData("p :: ", p * kp);
            opMode.telemetry.addData("i :: ", i * ki);
            opMode.telemetry.addData("d :: ", d * kd);
            opMode.telemetry.addData("power", power);
            opMode.telemetry.addData("Current Position", verticalLiftLeft.getCurrentPosition());
            opMode.telemetry.addData("Target Position", position);
            opMode.telemetry.update();
        }
    }
}