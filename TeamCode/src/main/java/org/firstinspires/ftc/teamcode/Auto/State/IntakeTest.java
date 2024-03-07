package org.firstinspires.ftc.teamcode.Auto.State;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Auto.HardwareClass.Intake;

@Autonomous(group = "Auto", name = "IntakeTest")
public class IntakeTest extends LinearOpMode {
    public void runOpMode() throws InterruptedException {
        waitForStart();
        Intake intake = new Intake(this);
        intake.getWhite2(2);
    }
}
