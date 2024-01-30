package org.firstinspires.ftc.teamcode.Auto.HardwareClass;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.hardware.dfrobot.HuskyLens;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;

public class HuskyLensDetection {
    int cx = 0;
    int cy = 0;
    private BNO055IMU imu;
    HuskyLens huskyLens;
    public LinearOpMode opMode;
    public double i;
    public double d;
    public double prevP = 0;
    public ElapsedTime timer = new ElapsedTime();
    double currFilterEst;
    double prevFilterEst = 0;
    public DcMotor br;
    public DcMotor bl;
    public DcMotor fr;
    public DcMotor fl;
    public double a = .8;
    double kp;
    double ki;
    double kd;
    double iLimit;
    public HuskyLensDetection(LinearOpMode opMode, double kp, double ki, double kd){
        this.opMode = opMode;

        this.kp = kp;
        this.ki = ki;
        this.kd = kd;

        iLimit = .25/ki;

        huskyLens = opMode.hardwareMap.get(HuskyLens.class, "huskylens");

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
    public int getPos(){
        huskyLens.selectAlgorithm(HuskyLens.Algorithm.TAG_RECOGNITION);

        for(HuskyLens.Block b : huskyLens.blocks()){
            if(b.id == 1) {
                cx = b.x;
            }
        }

        return cx;
    }


    public void findPt(){
        opMode.telemetry.addData("Current cx :: ", getPos());
        opMode.telemetry.update();
    }
    public void huskyPID1(){
        int CONSTANT_DESTINATION = 63;
        ElapsedTime time = new ElapsedTime();
        time.reset();

        while(getPos() == 0){
            br.setPower(.3);
            bl.setPower(-.3);
            fr.setPower(-.3);
            fl.setPower(.3);
        }

        while(CONSTANT_DESTINATION - getPos() > 2 && time.seconds() < 3) {
            double power = pid(CONSTANT_DESTINATION, getPos());
            br.setPower(power);
            bl.setPower(-power);
            fr.setPower(-power);
            fl.setPower(power);

            findPt();
        }
    }

    public double pid(double target, double current) {
        double p = target - current;

        i += p * timer.seconds();

        if (Math.abs(i) > iLimit) {
            i = Math.signum(i) * iLimit;
        }

        currFilterEst = (a * prevFilterEst) + (1 - a) * (p - prevP);
        prevFilterEst = currFilterEst;

        d = currFilterEst/timer.seconds();

        prevP = p;
        timer.reset();

        double power = p * kp + i * ki + d * kd;
        return power;
    }
}