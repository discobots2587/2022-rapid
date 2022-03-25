package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.IntakeRollers;
import frc.robot.subsystems.IntakeRollers.IntakeRollersStates;
import frc.robot.Constants.IntakeRollersConstants;

public class RunIntake extends CommandBase {
  private final IntakeRollers rollers;
  private IntakeRollersStates state;

  /**
   * Creates a new RunIntake.
   */
  public RunIntake(IntakeRollers irollers) {
    // Use addRequirements() here to declare subsystem dependencies.
    rollers = irollers;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    state = rollers.getState();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    rollers.spin(IntakeRollersConstants.kIntakeSpeed);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    rollers.stop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
