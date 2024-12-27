package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

@TeleOp(name="Test lift + drive", group = "TeleOp")

public class Test extends LinearOpMode {
    DcMotor leftF,leftD,rightF,rightD,liftL,liftR,intake,claw;
    boolean toggleDirection = false;

    @Override
    public void runOpMode() throws InterruptedException {
        leftF = hardwareMap.get(DcMotor.class, "leftF");
        leftD = hardwareMap.get(DcMotor.class, "leftB");
        rightF = hardwareMap.get(DcMotor.class, "rightF");
        rightD = hardwareMap.get(DcMotor.class, "rightB");


        liftL = hardwareMap.get(DcMotor.class, "liftL");
        liftR = hardwareMap.get(DcMotor.class, "liftR");
        intake = hardwareMap.get(DcMotor.class, "intake");
        claw = hardwareMap.get(DcMotor.class, "claw");


        leftF.setDirection(DcMotorSimple.Direction.REVERSE);
        leftD.setDirection(DcMotorSimple.Direction.REVERSE);

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
                leftD.setPower(((ly+lx+rx)/max)*power);
                rightF.setPower(((ly+lx-rx)/max)*power);
                rightD.setPower(((ly-lx-rx)/max)*power);
            }
            if (toggleDirection)
            {

                leftF.setPower(((-ly+lx+rx)/max)*power);
                leftD.setPower(((-ly-lx+rx)/max)*power);
                rightF.setPower(((-ly-lx-rx)/max)*power);
                rightD.setPower(((-ly+lx-rx)/max)*power);

            }
            if (gamepad1.left_trigger == 1)
            {
                toggleDirection = !toggleDirection;
            }

            if (gamepad1.dpad_up)
            {
                liftL.setPower(-(0.2 + (0.6 * gamepad1.left_trigger)));
                liftR.setPower(0.2 + (0.6 * gamepad1.left_trigger));
            }
            if (gamepad1.dpad_down)
            {
                liftL.setPower(0.2 + (0.6 * gamepad1.left_trigger));
                liftR.setPower(-(0.2 + (0.6 * gamepad1.left_trigger)));
            }

            if (gamepad1.dpad_left)
            {
                intake.setPower(-(0.2 + (0.6 * gamepad1.left_trigger)));
            }
            if (gamepad1.dpad_right)
            {
                intake.setPower(0.2 + (0.6 * gamepad1.left_trigger));
            }

            if (gamepad1.y)
            {
                claw.setPower(-(0.2 + (0.6 * gamepad1.left_trigger)));
            }
            if (gamepad1.a)
            {
                claw.setPower(0.2 + (0.6 * gamepad1.right_trigger));
            }
        }

    }
}