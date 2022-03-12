// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

// import java.util.Timer;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.subsystems.Conveyer;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Flywheel;
import frc.robot.subsystems.IntakeRollers;
import frc.robot.Constants.IntakeRollersConstants;
import frc.robot.Constants.FlywheelConstants;
import frc.robot.Constants.ConveyerConstants;
/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  private Command m_autonomousCommand;
  // private RobotContainer m_robotContainer;
  TalonSRX talon0 = new TalonSRX(0);
  private final XboxController m_stick = new XboxController(0);
  private final Timer m_timer = new Timer(); 
  private DriveTrain m_robotDrive = new DriveTrain();
  private IntakeRollers m_robotIntake = new IntakeRollers();
  private Conveyer m_robotConveyer = new Conveyer();
  private Flywheel m_robotFlywheel = new Flywheel();
  final JoystickButton leftBumperButton = new JoystickButton(m_stick, 9);
  /**
   * This funct ion is run when the robot is first started up and should be used for any
   * initialization code.
   */
  @Override
  public void robotInit() {
    // Instantiate our RobotContainer.  This will perform all our button bindings, and put our
    // autonomous chooser on the dashboard.
    // m_robotContainer = new RobotContainer();

  }

  /**
   * This function is called every robot packet, no matter the mode. Use this for items like
   * diagnostics that you want ran during disabled, autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before LiveWindow and
   * SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
    // Runs the Scheduler.  This is responsible for polling buttons, adding newly-scheduled
    // commands, running already-scheduled commands, removing finished or interrupted commands,
    // and running subsystem periodic() methods.  This must be called from the robot's periodic
    // block in order for anything in the Command-based framework to work.
    CommandScheduler.getInstance().run();
    teleopPeriodic();
  }
  @Override
  public void disabledInit() {}

  @Override
  public void disabledPeriodic() {
    
  }

  /** This autonomous runs the autonomous command selected by your {@link RobotContainer} class. */
  // @Override
  public void autonomousInit() {
   
     if (m_autonomousCommand != null) {
      m_autonomousCommand.schedule();
     }
   }

  /** This function is called periodically during autonomous. */
  @Override
  public void autonomousPeriodic() {
    if(m_timer.get() < 2.0){  //error with get timer
      m_robotDrive.arcadeDrive(0.5, 0);
    } else {
      m_robotDrive.stopMotor(); //stop robot 
    }
  }

  @Override
  public void teleopInit() {
    // This makes sure that the autonomous stops running when
    // teleop starts running. If you want the autonomous to
    // continue until interrupted by another command, remove
    // this line or comment it out.
    if (m_autonomousCommand != null) {
      m_autonomousCommand.cancel();
    }
  }

  /** This function is called periodically during operator control. */
  @Override
  public void teleopPeriodic() { // works
    
    m_robotDrive.tankDrive(-m_stick.getLeftY() * 0.5, m_stick.getRightY() * 0.5);  // LDR revert to previous tested code
    m_robotIntake.intakeRun(m_stick, IntakeRollersConstants.kIntakeSpeed);
    m_robotFlywheel.flywheelRun(m_stick, FlywheelConstants.kFlywheelLowSpeed, FlywheelConstants.kFlywheelHighSpeed);
    m_robotConveyer.conveyerRun(m_stick, ConveyerConstants.kConveyerSpeed);
    //m_robotDrive.arcadeDrive(-m_stick.getLeftY(), m_stick.getLeftX());

  }

  @Override
  public void testInit() {
    // Cancels all running commands at the start of test mode.
    CommandScheduler.getInstance().cancelAll();
  }

  /** This function is called periodically during test mode. */
  @Override
  public void testPeriodic() {

  }
}
