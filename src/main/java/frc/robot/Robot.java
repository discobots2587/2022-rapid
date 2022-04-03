// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

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
import frc.robot.subsystems.Climber;
import frc.robot.subsystems.IntakeRollers;
//import frc.robot.subsystems.IntakeRollers.IntakeRollersStates;
import frc.robot.Constants.IntakeRollersConstants;
import frc.robot.Constants.FlywheelConstants;
import frc.robot.Constants.ConveyerConstants;
import frc.robot.Constants.ClimberConstants;
/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot 
{
  private Command m_autonomousCommand;
  private RobotContainer m_robotContainer;
  TalonSRX talon0 = new TalonSRX(0);
  private final XboxController m_stick = new XboxController(0);
  private final XboxController m_stick2 = new XboxController(1);
  private DriveTrain m_robotDrive = new DriveTrain();
  private IntakeRollers m_robotIntake = new IntakeRollers();
  private Conveyer m_robotConveyer = new Conveyer();
  private Climber m_robotClimber = new Climber();
  private Flywheel m_robotFlywheel = new Flywheel();
  final JoystickButton leftBumperButton = new JoystickButton(m_stick, 9);
  private double autoStart = 0;
  private double autoTimeElapsed = 0;
  private boolean autoToggle = false;
  /**
   * This funct ion is run when the robot is first started up and should be used for any
   * initialization code.
   */
  @Override
  public void robotInit() 
  {
    // Instantiate our RobotContainer.  This will perform all our button bindings, and put our
    // autonomous chooser on the dashboard.
    m_robotContainer = new RobotContainer();

  }

  /**
   * This function is called every robot packet, no matter the mode. Use this for items like
   * diagnostics that you want ran during disabled, autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before LiveWindow and
   * SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() 
  {
    // Runs the Scheduler.  This is responsible for polling buttons, adding newly-scheduled
    // commands, running already-scheduled commands, removing finished or interrupted commands,
    // and running subsystem periodic() methods.  This must be called from the robot's periodic
    // block in order for anything in the Command-based framework to work.
    CommandScheduler.getInstance().run();

  }
  @Override
  public void disabledInit() {}

  @Override
  public void disabledPeriodic() 
  {
    
  }

  /** This autonomous runs the autonomous command selected by your {@link RobotContainer} class. */
  // @Override
  public void autonomousInit() 
  {
    autoStart = Timer.getFPGATimestamp();    
    m_autonomousCommand = m_robotContainer.getAutonomousCommand(); // this has an error, getAutonomousCommand doesnt exist -Andy 
    // schedule the autonomous command (example)
   
    if (m_autonomousCommand != null) 
    {
      m_autonomousCommand.schedule();
    }
   }

  /** This function is called periodically during autonomous. */
  @Override
  public void autonomousPeriodic() 
  {
    autoTimeElapsed = Timer.getFPGATimestamp() - autoStart;
  
    if(autoTimeElapsed < 5.0)  
    {
      m_robotFlywheel.shoot(FlywheelConstants.kFlywheelHighSpeed);
    }
    else if (autoTimeElapsed > 10 && autoTimeElapsed < 15)
    {
      m_robotFlywheel.shoot(FlywheelConstants.kFlywheelHighSpeed);
    }
    else
    {
      m_robotFlywheel.stop();
    }
    
    if(autoTimeElapsed > 2.0 && autoTimeElapsed < 5.0)
    {
      m_robotConveyer.index(ConveyerConstants.kConveyerSpeed);
    }
    else if (autoTimeElapsed > 12.0 && autoTimeElapsed < 15.0)
    {
      m_robotConveyer.index(ConveyerConstants.kConveyerSpeed);
    }
    else
    {
      m_robotConveyer.stop();
    }
    
    if(autoTimeElapsed > 6.0 && autoTimeElapsed < 7.7)
    {
      m_robotDrive.forward(0.5);
    }
    else if(autoTimeElapsed > 9.0 && autoTimeElapsed < 10.7)
    {
      m_robotDrive.forward(-0.5);
    }
    else
    {
      m_robotDrive.stopMotor();
    }
    
    if(autoTimeElapsed > 6.0 && autoTimeElapsed < 13.0)
    {
      m_robotIntake.spin(-IntakeRollersConstants.kIntakeSpeed);
    }
    else
    {
      m_robotIntake.stop();
    }

    if(autoTimeElapsed > 1.0)
    {
      if (autoToggle == false)
      {
        System.out.println(autoTimeElapsed);
        m_robotIntake.moveIntake();
        autoToggle = true;
      }
    }

  }

  @Override
  public void teleopInit() 
  {
    // This makes sure that the autonomous stops running when
    // teleop starts running. If you want the autonomous to
    // continue until interrupted by another command, remove
    // this line or comment it out.
    if (m_autonomousCommand != null) 
    {
      m_autonomousCommand.cancel();
    }
  }

  /** This function is called periodically during operator control. */
  @Override
  public void teleopPeriodic() 
  {
    //m_robotDrive.tankCurvedDrive(-m_stick.getLeftY(), m_stick.getRightY());  // LDR revert to previous tested code
    m_robotIntake.intakeToggle(m_stick, m_stick2, IntakeRollersConstants.kIntakeSpeed);
    m_robotFlywheel.flyWheelToggle(m_stick, m_stick2, FlywheelConstants.kFlywheelLowSpeed, FlywheelConstants.kFlywheelHighSpeed);
    m_robotConveyer.conveyerRun(m_stick, m_stick2, ConveyerConstants.kConveyerSpeed);
    m_robotClimber.ClimberRun(m_stick, m_stick2, ClimberConstants.kClimberSpeed);
    m_robotDrive.arcadeCurvedDrive(m_stick.getRightX()*0.65, -m_stick.getLeftY());
    m_robotClimber.climberjoystick(m_stick2, m_stick2.getLeftY());
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
