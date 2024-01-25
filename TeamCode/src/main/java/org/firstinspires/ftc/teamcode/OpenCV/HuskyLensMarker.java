package org.firstinspires.ftc.teamcode.OpenCV;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.hardware.dfrobot.HuskyLens;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;

public class HuskyLensMarker {
    int cx = 0;
    HuskyLens huskyLens;
    public LinearOpMode opMode;

    public double a = .8;

    public HuskyLensMarker(LinearOpMode opMode){
        this.opMode = opMode;
        huskyLens = opMode.hardwareMap.get(HuskyLens.class, "huskylens");
    }
    public int getPos(){
        huskyLens.selectAlgorithm(HuskyLens.Algorithm.OBJECT_TRACKING);

        for(HuskyLens.Block b : huskyLens.blocks()){
            if(b.id == 1){
                cx = b.x;
            }
        }

        return cx;
    }
    public void findPt(){
        while(opMode.opModeIsActive()) {
            opMode.telemetry.addData("Current cx :: ", getPos());
            opMode.telemetry.update();
        }
    }

}