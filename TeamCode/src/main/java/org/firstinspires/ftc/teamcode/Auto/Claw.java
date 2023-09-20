package org.firstinspires.ftc.teamcode.Auto;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Servo;

public class Claw {

    public LinearOpMode opMode;

    // Difficulty: EASY
    // Elin: Create your servo
    public Servo clawServo;

    public Claw(LinearOpMode opMode){
        this.opMode = opMode;

        // Difficulty: EASY
        // ELin: Hardware Map servo
        clawServo = opMode.hardwareMap.servo.get("clawServo");

    }

    public void openClaw(){
        // Difficulty: EASY
        // Elin
        clawServo.setPosition(0);
    }

    public void closeClaw(){
        // Difficulty: EASY
        // ELin
        clawServo.setPosition(1);
    }
}
