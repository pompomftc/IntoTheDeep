package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Gamepad;

@TeleOp (name = "GamepadTest")
public class GamepadTelemetry extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {
        waitForStart();
        while(opModeInInit())
        {
            telemetry.addData("Status:", "initialized");
            telemetry.update();
        }
        
        while(opModeIsActive())
        {
            telemetry.addData("Status:", "activated");
            
            gamepadTest(gamepad1, 1);
            gamepadTest(gamepad2, 2);

            telemetry.update();
        }
    }
    
    public void gamepadTest(Gamepad gamepad, int number){

        telemetry.addData("Gamepad",number);
        telemetry.addData("ID", gamepad.id);
        telemetry.addData("Timestamp", gamepad.timestamp);
        telemetry.addData("Type", gamepad.type);
        telemetry.addData("Next Rumble Approx Finish Time", gamepad.nextRumbleApproxFinishTime);



        if (gamepad.left_trigger != 0) {
            telemetry.addData("Left Trigger", gamepad.left_trigger);
        }
        if (gamepad.left_bumper) {
            telemetry.addData("Left Bumper", "pressed");
        }
        if (gamepad.right_trigger != 0) {
            telemetry.addData("Right Trigger", gamepad.right_trigger);
        }
        if (gamepad.right_bumper) {
            telemetry.addData("Right Bumper", "pressed");
        }
        
        
        if (gamepad.dpad_up) {
            telemetry.addData("DPad", "UP");
        }
        if (gamepad.dpad_down) {
            telemetry.addData("DPad", "DOWN");
        }
        if (gamepad.dpad_left) {
            telemetry.addData("DPad", "LEFT");
        }
        if (gamepad.dpad_right) {
            telemetry.addData("DPad", "RIGHT");
        }
        
        
        if (gamepad.y) {
            telemetry.addData("Button", "Y");
        }
        if (gamepad.a) {
            telemetry.addData("Button", "A");
        }
        if (gamepad.x) {
            telemetry.addData("Button", "X");
        }
        if (gamepad.b) {
            telemetry.addData("Button", "B");
        }


        if (gamepad.left_stick_button) {
            telemetry.addData("Left Stick Button", "pressed");
        }
        if (gamepad.right_stick_button) {
            telemetry.addData("Right Stick Button", "pressed");
        }




        if (gamepad.left_stick_y != 0) {
            telemetry.addData("Left Stick Y", gamepad.left_stick_y);
        }
        if (gamepad.right_stick_y != 0) {
            telemetry.addData("Right Stick Y", gamepad.right_stick_y);
        }
        if (gamepad.left_stick_x != 0) {
            telemetry.addData("Left Stick X", gamepad.left_stick_x);
        }
        if (gamepad.right_stick_x != 0) {
            telemetry.addData("Right Stick X", gamepad.right_stick_x);
        }
        
        
        
        if (gamepad.triangle) {
            telemetry.addData("Triangle", "pressed");
        }
        if (gamepad.cross) {
            telemetry.addData("Cross", "pressed");
        }
        if (gamepad.square) {
            telemetry.addData("Triangle", "pressed");
        }
        if (gamepad.circle) {
            telemetry.addData("Circle", "pressed");
        }
        
        
        if (gamepad.guide) {
            telemetry.addData("Guide", "pressed");
        }
        if (gamepad.back) {
            telemetry.addData("Back", "pressed");
        }
        if (gamepad.options) {
            telemetry.addData("Options", "pressed");
        }
        if (gamepad.ps) {
            telemetry.addData("Ps", "pressed");
        }
        if (gamepad.share) {
            telemetry.addData("Share", "pressed");
        }
        if (gamepad.start) {
            telemetry.addData("Start", "pressed");
        }
        
        
        if (gamepad.touchpad) {
            telemetry.addData("Touchpad", "pressed");
        }
        if (gamepad.touchpad_finger_1) {
            telemetry.addData("Touchpad Finger 1", "pressed");
        }
        if (gamepad.touchpad_finger_2) {
            telemetry.addData("Touchpad Finger 2", "pressed");
        }
        if (gamepad.touchpad_finger_1_y != 0) {
            telemetry.addData("Touchpad Finger 1 Y", gamepad.touchpad_finger_1_y);
        }
        if (gamepad.touchpad_finger_2_y != 0) {
            telemetry.addData("Touchpad Finger 2 Y", gamepad.touchpad_finger_2_y);
        }
        if (gamepad.touchpad_finger_1_x != 0) {
            telemetry.addData("Touchpad Finger 1 X", gamepad.touchpad_finger_1_x);
        }
        if (gamepad.touchpad_finger_2_x != 0) {
            telemetry.addData("Touchpad Finger 2 X", gamepad.touchpad_finger_2_x);
        }

        telemetry.addData("","");
    }
}
