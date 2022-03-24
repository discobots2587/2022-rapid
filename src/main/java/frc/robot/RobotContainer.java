// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
//import edu.wpi.first.wpilibj.XboxController.Button;
//import frc.robot.subsystems.Conveyer;
//import frc.robot.subsystems.DriveTrain;
//import frc.robot.subsystems.IntakeRollers;
//import frc.robot.subsystems.Flywheel.FlywheelStates;
//import frc.robot.subsystems.IntakeRollers.IntakeRollersStates;
//import frc.robot.subsystems.Flywheel;
import edu.wpi.first.wpilibj2.command.Command;
//import edu.wpi.first.wpilibj2.command.RunCommand;
//import edu.wpi.first.wpilibj2.command.InstantCommand;
//import edu.wpi.first.wpilibj2.command.ParallelRaceGroup;
//import edu.wpi.first.wpilibj2.command.RunCommand;
//import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
//import edu.wpi.first.wpilibj2.command.button.JoystickButton;
//import edu.wpi.first.wpilibj2.command.button.Trigger;


/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  // private final DriveTrain driveTrain = new DriveTrain();
  // private final  XboxController controller = new XboxController(0);
  //private final IntakeRollers intakeRollers = new IntakeRollers(); //had error, need to check -Andy 

  //private final DriveTrain driveTrain = new DriveTrain();
  //private final IntakeRollers intakeRollers = new IntakeRollers();
  
  //private final XboxController controller = new XboxController(0);

  private final Command m_autonomousCommand = new WaitCommand(2);
  
  //private final Command m_autonomousCommand = new RunCommand(m_toRun);
  

  /*private final Command m_simpleAuto =
      new DriveDistance(
          AutoConstants.kAutoDriveDistanceInches, AutoConstants.kAutoDriveSpeed, m_robotDrive); */ 

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() 
  {
    // Configure the button bindings
    configureButtonBindings();
    
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() 
  {
    
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  
  public Command getAutonomousCommand() 
  {
    //return auto;
    return m_autonomousCommand;
  }

}
