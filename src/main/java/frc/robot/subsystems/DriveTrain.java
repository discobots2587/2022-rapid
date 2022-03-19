/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.ControlMode;
//import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.DriveTrainConstants;

public class DriveTrain extends SubsystemBase {
    private final TalonSRX leftMaster = new TalonSRX(DriveTrainConstants.kleftMasterID);
    private final TalonSRX rightMaster = new TalonSRX(DriveTrainConstants.krightMasterID);
    private final TalonSRX leftSlave = new TalonSRX(DriveTrainConstants.kleftSlaveID);
    private final TalonSRX rightSlave = new TalonSRX(DriveTrainConstants.krightSlaveID);

  /**
   * Creates a new DriveTrain.
   */
  public DriveTrain()  {
   
    setMotorCurrentLimits();

    leftSlave.follow(leftMaster);
    rightSlave.follow(rightMaster);
  }
  private void setMotorCurrentLimits() {
    rightMaster.configContinuousCurrentLimit(DriveTrainConstants.kmotorCurrentLimit);
    rightMaster.configPeakCurrentLimit(0);
    rightMaster.configPeakCurrentDuration(0);

    leftMaster.configContinuousCurrentLimit(DriveTrainConstants.kmotorCurrentLimit);
    leftMaster.configPeakCurrentLimit(0);
    leftMaster.configPeakCurrentDuration(0);

    rightSlave.configContinuousCurrentLimit(DriveTrainConstants.kmotorCurrentLimit);
    rightSlave.configPeakCurrentLimit(0);
    rightSlave.configPeakCurrentDuration(0);

    leftSlave.configContinuousCurrentLimit(DriveTrainConstants.kmotorCurrentLimit);
    leftSlave.configPeakCurrentLimit(0);
    leftSlave.configPeakCurrentDuration(0);
    
    rightMaster.enableCurrentLimit(true);
    leftMaster.enableCurrentLimit(true);
    rightSlave.enableCurrentLimit(true);
    leftSlave.enableCurrentLimit(true);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void arcadeDrive(double throttle, double turn) {   //commented out , this code does not build -Andy
    double leftPower = throttle + turn;
    double rightPower = throttle - turn;

    leftMaster.set(ControlMode.PercentOutput, leftPower);
    rightMaster.set(ControlMode.PercentOutput, rightPower);
  }

  public void arcadeCurvedDrive(double leftAxis, double rightAxis)
  {
    int LDire = 1; // Linear displacement direction
    int ADire = 1; // Angular displacement direction
  
    if (leftAxis < 0)  
    {                  
      LDire = -1;      
    }
    if (rightAxis < 0)
    {
      ADire = -1; //Math.pow() will not compute negative base with decimal exponent.
    }

    double leftPower = Math.pow(Math.abs(leftAxis), 1.8)*LDire + Math.pow(Math.abs(rightAxis), 1.8)*ADire;
    double rightPower = Math.pow(Math.abs(leftAxis), 1.8)*LDire - Math.pow(Math.abs(rightAxis), 1.8)*ADire;

    leftMaster.set(ControlMode.PercentOutput, leftPower);
    rightMaster.set(ControlMode.PercentOutput, rightPower);
  }

  public void tankDrive(double leftPower, double rightPower) {
    leftMaster.set(ControlMode.PercentOutput, leftPower);
    rightMaster.set(ControlMode.PercentOutput, rightPower);
  }
  
  public void tankCurvedDrive(double leftAxis, double rightAxis)
  {
    int LDire = 1; // Left axis displacement direction
    int RDire = 1; // Right axis displacement direction

    if (leftAxis < 0)
    {
      LDire = -1;
    }
    if (rightAxis < 0)
    {
      RDire = -1;
    }
    
    double leftPower = Math.pow(Math.abs(leftAxis), 1.8)*LDire;
    double rightPower = Math.pow(Math.abs(rightAxis), 1.8)*RDire;

    leftMaster.set(ControlMode.PercentOutput, leftPower);
    rightMaster.set(ControlMode.PercentOutput, rightPower);

  }

  public void forward(double power) {
    leftMaster.set(ControlMode.PercentOutput, power);
    rightMaster.set(ControlMode.PercentOutput, power);
  }

  public void rotate(double power) {
    leftMaster.set(ControlMode.PercentOutput, power);
    rightMaster.set(ControlMode.PercentOutput, -power);
  }

  public void stopMotor() {
    leftMaster.set(ControlMode.PercentOutput, 0);
    rightMaster.set(ControlMode.PercentOutput,0);
  }



}