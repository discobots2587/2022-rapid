/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.IntakeRollersConstants;

public class IntakeRollers extends SubsystemBase {
  private TalonSRX rollers = new TalonSRX(IntakeRollersConstants.kRollersID);

  private final Solenoid deploy = new Solenoid(IntakeRollersConstants.kdeployChannel);

  public static enum IntakeRollersStates {
    OFF, IN, OUT
  }

  private IntakeRollersStates state = IntakeRollersStates.OFF;

  /**
   * Creates a new IntakeRollers.
   */
  public IntakeRollers() {
    rollers.setNeutralMode(NeutralMode.Brake);
    rollers.setInverted(true);
  }

  /**
   * Spins the rollers at a given power
   * 
   * @param power the power to spin the rollers at [-1, 1]. Negative values spin outwards.
   */
  public void spin(double power) {
    rollers.set(ControlMode.PercentOutput, power);
  }

  /**
   * Stop the rollers
   */
  public void stop() {
    rollers.set(ControlMode.PercentOutput, 0);
  }

  public void deploy() {
    deploy.set(!deploy.get());
  }

  public void setState(IntakeRollersStates istate) {
    state = istate;
  }

  public IntakeRollersStates getState() {
    return state;
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}