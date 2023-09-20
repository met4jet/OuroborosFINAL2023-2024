package org.firstinspires.ftc.teamcode.Auto;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

public class PID {
    public LinearOpMode opMode;
    public DcMotor motorPID;

    public double kp, ki, kd;
    public double a;
    public double prevP;
    public double target;
    public ElapsedTime timer = new ElapsedTime();


    public PID(LinearOpMode opMode, double target, double kp, double ki, double kd,
               double a){
        // Sophia
        this.opMode = opMode;

        //Hardware Map motors
        motorPID = opMode.hardwareMap.dcMotor.get("lift");

        opMode.telemetry.addLine("lift init");

        //Set your motor's zero power behavior
        motorPID.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        this.target = target;
        this.kp = kp;
        this.ki = ki;
        this.kd = kd;
        this.a = a;
    }

    public void resetEncoders(){
        // Sophia

        motorPID.setMode(DcMotor.RunMode.RESET_ENCODERS);
        motorPID.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }

    public double updatePID(double error) {
        // Sophia

        double p;
        double i = 0;
        double d;
        double power;
        double iLimit = .25 / ki;

        double currFilterEst;
        double prevFilterEst = 0;

        int targetPosition;

        timer.reset();

        //p
        p = error;

        //i
        i += (p - prevP) * timer.seconds();

        // Integral windup
        if (Math.abs(i) > iLimit) {
            i = Math.signum(i) * iLimit;
        }

        // Low pass filter
        currFilterEst = (a * prevFilterEst) + (1 - a) * (p - prevP);
        prevFilterEst = currFilterEst;

        // d
        d = currFilterEst / timer.seconds();

        power = p * kp + i * ki + d * kd;

        prevP = p;

        timer.reset();

        opMode.telemetry.addData("p :: ", p * kp);
        opMode.telemetry.addData("i :: ", i * ki);
        opMode.telemetry.addData("d :: ", d * kd);
        opMode.telemetry.addData("power", power);
        opMode.telemetry.update();
        return power;
    }

    public void staticFrictionFeedforward(){
        // Sophia
    }
}
