/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.DriveTrainConstants;

public class DriveTrain extends SubsystemBase {
  private TalonSRX leftMaster = new TalonSRX(DriveTrainConstants.kleftMasterID);
  private TalonSRX rightMaster = new TalonSRX(DriveTrainConstants.krightMasterID);
  private TalonSRX leftSlave = new TalonSRX(DriveTrainConstants.kleftSlaveID);
  private VictorSPX rightSlave = new VictorSPX(DriveTrainConstants.krightSlaveID);

  private final Solenoid shifter = new Solenoid(DriveTrainConstants.kshifterChannel);

  /**
   * Creates a new DriveTrain.
   */
  public DriveTrain() {
    this.rightSlave.setInverted(true);
    this.leftSlave.follow(this.leftMaster);
    this.rightSlave.follow(this.rightMaster);
  }

  /**
   * Moves the drive forward at a given power.
   * 
   * @param power the power to move forward at [-1, 1]
   */
  public void forward(double power) {
    this.leftMaster.set(ControlMode.PercentOutput, power);
    this.rightMaster.set(ControlMode.PercentOutput, power);
  }

  /**
   * Rotates the robot at a given power.
   * 
   * @param power the power to rotate at [-1, 1]
   */
  public void rotate(double power) {
    this.leftMaster.set(ControlMode.PercentOutput, power);
    this.rightMaster.set(ControlMode.PercentOutput, -power);
  }

  /**
   * Drives the robot using arcade controls.
   * 
   * @param forward the forward power [-1, 1]
   * @param turn the turning power [-1, 1]
   */
  public void arcadeDrive(double forward, double turn) {
    double leftPower = (forward + turn)*0.9;
    double rightPower = (forward - turn)*0.9;

    this.leftMaster.set(ControlMode.PercentOutput, leftPower);
    this.rightMaster.set(ControlMode.PercentOutput, rightPower);
  }

  /**
   * Drives the robot using tank controls.
   * 
   * @param leftPower the left side power [-1, 1]
   * @param rightPower the right side power [-1, 1]
   */
  public void tankDrive(double leftPower, double rightPower) {
    this.leftMaster.set(ControlMode.PercentOutput, leftPower);
    this.rightMaster.set(ControlMode.PercentOutput, rightPower);
  }

  public void shift() {
    shifter.set(!shifter.get());
  }
}