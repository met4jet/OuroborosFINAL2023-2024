package org.firstinspires.ftc.teamcode.Auto.HardwareClass;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.hardware.dfrobot.HuskyLens;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;

import java.util.Arrays;

public class HuskyLensMarker {
    int cx = 0;
    HuskyLens huskyLens;
    public LinearOpMode opMode;

    public String detection = "NOT DETECTED";

    public HuskyLensMarker(LinearOpMode opMode){
        this.opMode = opMode;
        huskyLens = opMode.hardwareMap.get(HuskyLens.class, "huskylens");
    }
    public int getPos(){
        huskyLens.selectAlgorithm(HuskyLens.Algorithm.OBJECT_TRACKING);

        for(HuskyLens.Block b : huskyLens.blocks()){

            opMode.telemetry.addData("Blocks Array :: ",b);
            opMode.telemetry.update();
            if(b.id == 1){
                cx = b.x;
                detection = "DETECTED";
            }
        }

        opMode.telemetry.addData("huskyLens.blocks(1) :: ", huskyLens.blocks(1));
        opMode.telemetry.update();


        opMode.telemetry.addData("Blocks Array :: ", huskyLens.blocks());
        opMode.telemetry.update();

        return cx;
    }
    public int getPosRed(){
        huskyLens.selectAlgorithm(HuskyLens.Algorithm.OBJECT_TRACKING);

        for(HuskyLens.Block b : huskyLens.blocks()){

            if(b.id == 2){
                cx = b.x;
            }
            else {
                cx = 0;
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