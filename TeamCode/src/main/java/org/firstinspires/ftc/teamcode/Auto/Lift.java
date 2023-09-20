package org.firstinspires.ftc.teamcode.Auto;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

public class Lift {

    // Sophia
    public LinearOpMode opMode;
    public DcMotor lift;

    public Lift(LinearOpMode opMode){
        // Sophia
        this.opMode = opMode;

        //Hardware Map motors
        lift = opMode.hardwareMap.dcMotor.get("lift");

        opMode.telemetry.addLine("lift init");

        //Set your motor's zero power behavior
        lift.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

    }

    public void resetEncoders(){
        // Sophia

        lift.setMode(DcMotor.RunMode.RESET_ENCODERS);
        lift.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }

    public void moveUp(int position){
        // Difficulty: MEDIUM
        // Phoenix
        // Assume there are 4 positions (starting position: 0, low: 1, medium: 2, high: 3)
        // Assume position 1 is 100 encoders, position 2 is 200 encoders, position 3 is 300 encoders
        // Make sure to check if opmode is active (opMode.opModeIsActive())
        // Add telemetry to check encoder positions

        int targetPosition;
        switch (position) {                 // this section is just setting the positions to be
            case 0: targetPosition = 0;     // used later in the code.
                break;
            case 1: targetPosition = 100;
                break;
            case 2: targetPosition = 200;
                break;
            default: targetPosition = 300;
        }

        if (opMode.opModeIsActive()) {
            lift.setTargetPosition(targetPosition);
            lift.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            lift.setPower(0.5); // this should be adjusted based on our lift.

            while (opMode.opModeIsActive() && lift.isBusy()); {
                opMode.telemetry.addData("Target Position", targetPosition);
                opMode.telemetry.addData("Current Position", lift.getCurrentPosition());
                opMode.telemetry.update();
            }
        }
    }

    public void moveDown(int position){
        // Difficulty: EASY
        // Phoenix
        // Bring lift position all the way down
        // Make sure to check if opmode is active (opMode.opModeIsActive())
        // Assume starting position is 0 encoders

        int targetPosition = 0;  // tells the lift where it needs to be (all the way down)

        if (opMode.opModeIsActive()) {
            lift.setTargetPosition(lift.getTargetPosition());
            lift.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            lift.setPower(.5); // this should be changed based on our lift

            while (opMode.opModeIsActive() && lift.isBusy()) {
                opMode.telemetry.addData("Target Position", targetPosition);
                opMode.telemetry.addData("Current Position", lift.getCurrentPosition());
                opMode.telemetry.update();
            }
            lift.setPower(0.0); // stops motor once lift reaches the bottom
        }
    }

    public void movePID(int position, double kp, double ki, double kd){
        // Sophia
        // i is not extremely needed in pid for lifts

        // WARNING: This method only allows for downward movements to position 0 and
        // upward movements from position 0

        // Allows lift to be brought down
        if(position != 0) {
            resetEncoders();
        }

        ElapsedTime timer = new ElapsedTime();

        double p;
        double i = 0;
        double d;
        double a = .8; // need to tune, can be anything from 0 < a < 1
        double prevP = 0;
        double power;
        double iLimit = .25/ki;

        double currFilterEst;
        double prevFilterEst = 0;

        int targetPosition;

        // Determines encoder distance from position number
        switch (position) {
            case 0: targetPosition = 0;
                break;
            case 1: targetPosition = 100;
                break;
            case 2: targetPosition = 200;
                break;
            default: targetPosition = 300;
                break;
        }

        timer.reset();

        while(Math.abs(lift.getCurrentPosition() - position) > 10 && opMode.opModeIsActive()){

            // Calculates the error (distance between desired and current position)
            // Slows motor down so prevent overshooting
            p = lift.getCurrentPosition() - targetPosition;

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
            lift.setPower(power);

            prevP = p;

            timer.reset();

            opMode.telemetry.addData("p :: ", p * kp);
            opMode.telemetry.addData("i :: ", i * ki);
            opMode.telemetry.addData("d :: ", d * kd);
            opMode.telemetry.addData("power", power);
            opMode.telemetry.update();
        }
    }
}
