package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.RobotHardware;
import org.firstinspires.ftc.teamcode.enums.LiftState;

public class Lift extends SubSystem {
    private RobotHardware robot;

    public boolean liftDownPressed = false,liftLowPressed = false,liftHighPressed = false;

    public LiftState liftState;
    // Encoder positions for the lift.
    //TODO Test to find the proper values.
    private final int LIFT_DOWN = 0, LIFT_LOW = -797, LIFT_HIGH = -2601;

    private final double LIFT_MAX_POWER = .7;
    private final int LIFT_POSITION_TOLERANCE = 20;

    public Lift(RobotHardware robot) {
        this.robot = robot;
    }
    @Override
    public void init() {
        liftState = LiftState.DOWN;
        // Make sure encoder is 0 at start
        robot.liftL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.liftR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.liftL.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        robot.liftR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    @Override
    public void start() {

    }

    @Override
    public void update() {
        switch (liftState) {
            case DOWN:
                if (Math.abs(robot.liftL.getCurrentPosition() - LIFT_DOWN) < LIFT_POSITION_TOLERANCE) {
                    if (liftLowPressed) {
                        robot.liftL.setTargetPosition(LIFT_LOW);
                        robot.liftR.setTargetPosition(LIFT_LOW);
                        robot.liftL.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                        robot.liftR.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                        robot.liftL.setPower(LIFT_MAX_POWER);
                        robot.liftR.setPower(LIFT_MAX_POWER);

                        liftState = LiftState.LOW_BASKET;
                    }

                    if (liftHighPressed) {
                        robot.liftL.setTargetPosition(LIFT_HIGH);
                        robot.liftR.setTargetPosition(LIFT_HIGH);
                        robot.liftL.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                        robot.liftR.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                        robot.liftL.setPower(LIFT_MAX_POWER);
                        robot.liftR.setPower(LIFT_MAX_POWER);

                        liftState = LiftState.HIGH_BASKET;
                    }
                }
                break;
            case LOW_BASKET:
                if (Math.abs(robot.liftL.getCurrentPosition() - LIFT_LOW) < LIFT_POSITION_TOLERANCE) {
                    if (liftDownPressed) {
                        robot.liftL.setTargetPosition(LIFT_DOWN);
                        robot.liftR.setTargetPosition(LIFT_DOWN);
                        robot.liftL.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                        robot.liftR.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                        robot.liftL.setPower(LIFT_MAX_POWER);
                        robot.liftR.setPower(LIFT_MAX_POWER);

                        liftState = LiftState.DOWN;
                    }

                    if (liftHighPressed) {
                        robot.liftL.setTargetPosition(LIFT_HIGH);
                        robot.liftR.setTargetPosition(LIFT_HIGH);
                        robot.liftL.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                        robot.liftR.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                        robot.liftL.setPower(LIFT_MAX_POWER);
                        robot.liftR.setPower(LIFT_MAX_POWER);

                        liftState = LiftState.HIGH_BASKET;
                    }
                }
                break;
            case HIGH_BASKET:
                if (Math.abs(robot.liftL.getCurrentPosition() - LIFT_HIGH) < LIFT_POSITION_TOLERANCE) {
                    if (liftDownPressed) {
                        robot.liftL.setTargetPosition(LIFT_DOWN);
                        robot.liftR.setTargetPosition(LIFT_DOWN);
                        robot.liftL.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                        robot.liftR.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                        robot.liftL.setPower(LIFT_MAX_POWER);
                        robot.liftR.setPower(LIFT_MAX_POWER);

                        liftState = LiftState.DOWN;
                    }
                }
                break;
            default:
                // if get here, there is a problem
                robot.liftL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                robot.liftR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                robot.liftL.setPower(0);
                robot.liftR.setPower(0);
                liftState = LiftState.DOWN;
        }

    }

    // Respond to gamepad inputs.
    public void setProperties(boolean buttonDown, boolean buttonLow, boolean buttonHigh) {
        liftDownPressed = buttonDown;
        liftLowPressed = buttonLow;
        liftHighPressed = buttonHigh;
    }
}
