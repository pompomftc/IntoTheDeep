package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name="Test lift + drive", group = "TeleOp")

public class Test extends LinearOpMode {
    DcMotor leftF,leftB,rightF,rightB,liftL,liftR;
    DcMotorEx hliftL,hliftR;
    Servo claw, armL, armR, intake_claw, intakeL, intakeR;
    CRServo intake;
    boolean toggleDirection = true;

    double inc = 25 ;
    int pos=0;

    @Override
    public void runOpMode() throws InterruptedException {
        leftF = hardwareMap.get(DcMotor.class, "leftF");
        leftB = hardwareMap.get(DcMotor.class, "leftB");
        rightF = hardwareMap.get(DcMotor.class, "rightF");
        rightB = hardwareMap.get(DcMotor.class, "rightB");


        liftL = hardwareMap.get(DcMotor.class, "liftL");
        liftR = hardwareMap.get(DcMotor.class, "liftR");

        liftL.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        liftR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        liftL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        liftR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        liftL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        liftR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        hliftL = hardwareMap.get(DcMotorEx.class, "hLiftL");
        hliftR = hardwareMap.get(DcMotorEx.class, "hLiftR");
//        liftR.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
//        intake = hardwareMap.get(DcMotor.class, "intake");
        claw = hardwareMap.get(Servo.class, "claw");
        intake_claw = hardwareMap.get(Servo.class, "intake_claw");
        intakeL = hardwareMap.get(Servo.class, "intakeR");
        intakeR = hardwareMap.get(Servo.class, "intakeR");


        armL = hardwareMap.get(Servo.class, "armL");
        armR = hardwareMap.get(Servo.class, "armR");



        liftL.setDirection(DcMotorSimple.Direction.REVERSE);
        leftF.setDirection(DcMotorSimple.Direction.REVERSE);
        leftB.setDirection(DcMotorSimple.Direction.REVERSE);

        waitForStart();

        while (opModeIsActive())
        {
            double lx = gamepad1.left_stick_x;
            double ly = gamepad1.left_stick_y;
            double rx = gamepad1.right_stick_x;

            double max = Math.max(Math.abs(ly)+Math.abs(lx)+Math.abs(rx), 1);
            double power = 0.2 + (0.6 * gamepad1.right_trigger);


            if (!toggleDirection)
            {
                leftF.setPower(((ly-lx+rx)/max)*power);
                leftB.setPower(((ly+lx+rx)/max)*power);
                rightF.setPower(((ly+lx-rx)/max)*power);
                rightB.setPower(((ly-lx-rx)/max)*power);
            }
            if (toggleDirection)
            {

                leftF.setPower(((-ly+lx+rx)/max)*power);
                leftB.setPower(((-ly-lx+rx)/max)*power);
                rightF.setPower(((-ly-lx-rx)/max)*power);
                rightB.setPower(((-ly+lx-rx)/max)*power);

            }
            if (gamepad1.left_bumper)
            {
                toggleDirection = !toggleDirection;
            }
//            else {
//                toggleDirection = false;
//            }

            if (gamepad2.dpad_up)
            {
                pos += inc;
            }
            else if (gamepad2.dpad_down)
            {
                pos-=inc;
            }

            liftL.setTargetPosition(pos);
            liftR.setTargetPosition(pos);
            liftL.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            liftR.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            liftL.setPower(0.8);
            liftR.setPower(0.8);

//            else {
//                liftL.setPower(0);
//                liftR.setPower(0);
//            }

            if (gamepad2.dpad_left)
            {
                hliftL.setPower(0.2 + (0.8 * gamepad2.left_trigger));
                hliftR.setPower(-(0.2 + (0.8 * gamepad2.left_trigger)));
            }
            else if (gamepad2.dpad_right)
            {
                hliftR.setPower(0.2 + (0.8 * gamepad2.left_trigger));
                hliftL.setPower(-(0.2 + (0.8 * gamepad2.left_trigger)));
            }
            else {

                hliftL.setPower(0);
                hliftR.setPower(0);
            }
//            arm
            if (gamepad2.y)
            {
                armL.setPosition(0);
                armR.setPosition(1);
            } else if (gamepad2.a)
            {
                armL.setPosition(1);
                armR.setPosition(0);
            }


//            claw
            if (gamepad2.b)
            {
                claw.setPosition(1);
            } else if (gamepad2.x)
            {
                claw.setPosition(0);
            }
            if (gamepad2.b)
            {
                claw.setPosition(1);
            } else if (gamepad2.x)
            {
                claw.setPosition(0);
            }
            if (gamepad2.right_bumper)
            {
                intake_claw.setPosition(1);
            } else if (gamepad2.left_bumper)
            {
                intake_claw.setPosition(0);
            }
            if (gamepad2.right_stick_y > 0)
            {
                intakeL.setPosition(1);
                intakeR.setPosition(0);
            } else if (gamepad2.right_stick_y<0)
            {
                intakeL.setPosition(0);
                intakeR.setPosition(1);
            }



        }

    }
}