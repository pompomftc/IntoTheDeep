package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

enum ArmState {
    INIT,
    GETSPECIMEN, BEFORESETSPECIMEN, SETSPECIMEN,
    BEFOREGETSAMPLE, GETSAMPLE ,SETBASKETSAMPLE
}
@TeleOp(name="Teleop", group = "TeleOp")

public class Teleop extends LinearOpMode {
//    boolean duoMode = false;
    DcMotor leftFront,leftBack,rightFront,rightBack,
            leftFrontArm,leftBackArm,rightFrontArm,rightBackArm;
    Servo intake;
    ArmState armState;
    boolean toggleDirection = false;
    boolean devMode = true;
    int frontArmPosition = 0, backArmPosition = 0;

    @Override
    public void runOpMode() throws InterruptedException {
        //Initialize drive motors
        leftFront = hardwareMap.get(DcMotor.class, "leftFront");
        leftBack = hardwareMap.get(DcMotor.class, "leftBack");
        rightFront = hardwareMap.get(DcMotor.class, "rightFront");
        rightBack = hardwareMap.get(DcMotor.class, "rightBack");

        //Reversing arm motor direction
        leftFront.setDirection(DcMotor.Direction.REVERSE);
        leftBack.setDirection(DcMotor.Direction.REVERSE);

        //Initialize arm motors
        leftFrontArm= hardwareMap.get(DcMotor.class, "leftFrontArm");
        leftBackArm = hardwareMap.get(DcMotor.class, "leftBackArm");
        rightFrontArm = hardwareMap.get(DcMotor.class, "rightFrontArm");
        rightBackArm = hardwareMap.get(DcMotor.class, "rightBackArm");

        //Reversing arm motor direction
        leftFrontArm.setDirection(DcMotor.Direction.REVERSE);
        leftBackArm.setDirection(DcMotor.Direction.REVERSE);
        rightBackArm.setDirection(DcMotor.Direction.FORWARD);

        leftFrontArm.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftBackArm.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightFrontArm.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightBackArm.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        leftFrontArm.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftBackArm.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightFrontArm.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightBackArm.setMode(DcMotor.RunMode.RUN_USING_ENCODER);



        //Setting arm state to init
//        armState = ArmState.INIT;
        
        //Initialize intake servo
        //intake = hardwareMap.get(Servo.class, "intake");


        // SOLO / DUO  mode selection before & after init state
//        do {
//            telemetry.update();
//
//            if (gamepad1.back) duoMode = !duoMode;
//
//            if (duoMode) telemetry.addData("Mode", "Duo");
//
//            else telemetry.addData("Mode", "Solo");
//        }
//        while (gamepad1.back);
        ///////////////////////////////////////////////////////

        
        //Wait until press play button on DRIVER STATION
        waitForStart();
        while (opModeIsActive())
        {
            //Movement buttons variables
            double lx = gamepad1.left_stick_x;
            double ly = gamepad1.left_stick_y;
            double rx = gamepad1.right_stick_x;

            //Some Math to make sure that our max value is no more than 1
            double max = Math.max(Math.abs(ly)+Math.abs(lx)+Math.abs(rx), 1);
            //Motor power accelerates when user1 press Right Trigger
            double power = 0.2 + (0.8 * gamepad1.right_trigger);
            telemetry.addData("Movement power", power);

            // When user1 press Left Trigger until its value will be 1, it change movement direction
            if (gamepad1.left_trigger == 1) toggleDirection = !toggleDirection;
            telemetry.addData("Direction", toggleDirection ? "Back" : "Front");
            //Movement directions toggles FORWARD side driven / BACKWARD side driven
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
            //////////////////////////////////////////////////////////////////////
            //Switching mode  DEVELOPMENT / GAMEREADY
            if (gamepad2.right_bumper) devMode = !devMode;
            telemetry.addData("Mode", devMode? "DEVELOPMENT" : "GAMEREADY");


            if (!devMode) {
                //Changing arm state by left hand
                if (gamepad2.dpad_up) armState = ArmState.BEFORESETSPECIMEN;
                if (gamepad2.dpad_down) armState = ArmState.SETSPECIMEN;
                if (gamepad2.dpad_left) armState = ArmState.GETSPECIMEN;

                //Changing arm state by right hand
                if (gamepad2.y) armState = ArmState.BEFOREGETSAMPLE;
                if (gamepad2.a) armState = ArmState.GETSAMPLE;
                if (gamepad2.b) armState = ArmState.SETBASKETSAMPLE;

                switch (armState) {
                    case INIT:
                        backArmPosition = 0;
                        frontArmPosition = 0;
                        break;

                    // LEFT HAND CONTROL
                    case BEFORESETSPECIMEN:
                        backArmPosition = 0;
                        frontArmPosition = 0;
                        break;
                    case SETSPECIMEN:
                        backArmPosition = 0;
                        frontArmPosition = 0;
                        break;
                    case GETSPECIMEN:
                        backArmPosition = 0;
                        frontArmPosition = 0;
                        break;

                    // RIGHT HAND CONTROL
                    case BEFOREGETSAMPLE:
                        backArmPosition = 0;
                        frontArmPosition = 0;
                        break;
                    case GETSAMPLE:
                        backArmPosition = 0;
                        frontArmPosition = 0;
                        break;
                    case SETBASKETSAMPLE:
                        backArmPosition = 0;
                        frontArmPosition = 0;
                        break;
                }
                // Setting target position
                leftFrontArm.setTargetPosition(frontArmPosition);
                leftBackArm.setTargetPosition(backArmPosition);
                rightFrontArm.setTargetPosition(frontArmPosition);
                rightBackArm.setTargetPosition(backArmPosition);
                // Setting Mode to RUN_TO_POSITION
                leftFrontArm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                leftBackArm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                rightFrontArm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                rightBackArm.setMode(DcMotor.RunMode.RUN_TO_POSITION);


                // Setting power of motors
//                leftFrontArm.setPower(0.5);
//                leftBackArm.setPower(0.5);
//                rightBackArm.setPower(0.5);
//                rightBackArm.setPower(0.5);
            }
            if (devMode)
            {
                if (gamepad2.dpad_up)
                {
                    frontArmPosition +=1;


                }
                else if (gamepad2.dpad_down)
                {
                    frontArmPosition -=1;

                }
                else if (gamepad2.dpad_left)
                {
                    backArmPosition += 1;

                }
                else if (gamepad2.dpad_right)
                {
                    backArmPosition -= 1;


                }

//                leftFrontArm.setTargetPosition(gamepad2.x ? frontArmPosition++  : frontArmPosition--);
//                leftBackArm.setTargetPosition(gamepad2.y ? backArmPosition++ : backArmPosition--);
//                rightFrontArm.setTargetPosition(gamepad2.b ? frontArmPosition++ : frontArmPosition--);
//                rightBackArm.setTargetPosition(gamepad2.a ? backArmPosition++ : backArmPosition--);

//                leftFrontArm.setPower(0.5);
//                leftBackArm.setPower(0.5);
//                rightFrontArm.setPower(0.5);
//                rightBackArm.setPower(0.5);


                leftBackArm.setTargetPosition(backArmPosition);
                rightBackArm.setTargetPosition(backArmPosition);
                leftBackArm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                rightBackArm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                leftBackArm.setPower(0.5);
                rightBackArm.setPower(0.5);

                leftFrontArm.setTargetPosition(frontArmPosition);
                rightFrontArm.setTargetPosition(frontArmPosition);
                leftFrontArm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                rightFrontArm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                leftFrontArm.setPower(0.5);
                rightFrontArm.setPower(0.5);
            }
//            telemetry.addData("Left Front Arm", leftFrontArm.getCurrentPosition());
            telemetry.addData("Left Back Arm", leftBackArm.getCurrentPosition());
//            telemetry.addData("Right Front Arm", rightFrontArm.getCurrentPosition());
            telemetry.addData("Right Back Arm", rightBackArm.getCurrentPosition());
            telemetry.update();

        }
        //When user pressed play, code below will starts execute 

    }
}