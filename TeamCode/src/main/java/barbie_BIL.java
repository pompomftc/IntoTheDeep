package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name="Central Asia Teleop for BIL Barbie", group = "TeleOp")

public class barbie_BIL extends LinearOpMode {
    DcMotor leftFront,leftBack,rightFront,rightBack;
    Servo claw;
    CRServo armL, armR;
    boolean toggleDirection = false, duoMode = false;


    @Override
    public void runOpMode() throws InterruptedException {
        leftFront = hardwareMap.get(DcMotor.class, "leftFront");
        leftBack = hardwareMap.get(DcMotor.class, "leftBack");
        rightFront = hardwareMap.get(DcMotor.class, "rightFront");
        rightBack = hardwareMap.get(DcMotor.class, "rightBack");

        leftFront.setDirection(DcMotorSimple.Direction.REVERSE);
        leftBack.setDirection(DcMotorSimple.Direction.REVERSE);

        claw = hardwareMap.get(Servo.class, "claw");

        armL = hardwareMap.get(CRServo.class, "intakeR");
        armR = hardwareMap.get(CRServo.class, "intakeR");

        //     SOLO / DUO  mode selection before & after init state
        do {
            telemetry.update();

            if (gamepad1.back) duoMode = !duoMode;

            if (duoMode) telemetry.addData("Mode", "Duo");

            else telemetry.addData("Mode", "Solo");
        }
        while (gamepad1.back);

        waitForStart();

        while (opModeIsActive())
        {
            double lx = gamepad1.left_stick_x;
            double ly = gamepad1.left_stick_y;
            double rx = gamepad1.right_stick_x;

            double max = Math.max(Math.abs(ly)+Math.abs(lx)+Math.abs(rx), 1);
            double power = 0.2 + (0.6 * gamepad1.right_trigger);

            if (gamepad1.left_trigger == 1) toggleDirection = !toggleDirection;
            telemetry.addData("Direction", toggleDirection? "Front" : "Back");

            if (!toggleDirection)
            {
                leftFront.setPower(((ly-lx+rx)/max)*power);
                leftBack.setPower(((ly+lx+rx)/max)*power);
                rightFront.setPower(((ly+lx-rx)/max)*power);
                rightBack.setPower(((ly-lx-rx)/max)*power);
            }
            if (toggleDirection)
            {
                leftFront.setPower(((-ly+lx+rx)/max)*power);
                leftBack.setPower(((-ly-lx+rx)/max)*power);
                rightFront.setPower(((-ly-lx-rx)/max)*power);
                rightBack.setPower(((-ly+lx-rx)/max)*power);
            }

            if (duoMode)
            {
                if (gamepad2.dpad_up)
                {
                    armL.setPower(0.5);
                    armR.setPower(0.5);
                }
                else if (gamepad2.dpad_down)
                {
                    armL.setPower(-0.5);
                    armR.setPower(-0.5);
                }

                if (gamepad2.y) claw.setPosition(0);
                else if (gamepad2.a) claw.setPosition(1);
            }
            else
            {
                if (gamepad1.dpad_up)
                {
                    armL.setPower(0.5);
                    armR.setPower(0.5);
                }
                else if (gamepad1.dpad_down)
                {
                    armL.setPower(-0.5);
                    armR.setPower(-0.5);
                }
                if (gamepad1.y) claw.setPosition(0);
                else if (gamepad1.a) claw.setPosition(1);
            }



        }

    }
}