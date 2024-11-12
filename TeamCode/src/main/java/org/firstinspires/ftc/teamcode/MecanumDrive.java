package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

@TeleOp(name="MecanumDrive")

public class MecanumDrive extends LinearOpMode {
    DcMotor leftF,leftD,rightF,rightD;
    boolean toggleDirection = false;

    @Override
    public void runOpMode() throws InterruptedException {
        leftF = hardwareMap.get(DcMotor.class, "leftF");
        leftD = hardwareMap.get(DcMotor.class, "leftB");
        rightF = hardwareMap.get(DcMotor.class, "rightF");
        rightD = hardwareMap.get(DcMotor.class, "rightB");

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
        }

    }
}