package frc.robot.subsystems;


import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
//import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.IntakeRollersConstants;

public class IntakeRollers {
    private TalonSRX rollers = new TalonSRX(IntakeRollersConstants.kRollersID);
    private DoubleSolenoid deploy = new DoubleSolenoid(IntakeRollersConstants.kPCMID, PneumaticsModuleType.CTREPCM, IntakeRollersConstants.kdeployForwardChannel, IntakeRollersConstants.kdeployBackwardChannel);
    private boolean toggle = false;
    //private final Solenoid deploy = new Solenoid(IntakeRollersConstants.kdeployChannel);


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
    deploy.set(Value.kForward);
    
  }

  public void intakeRun(XboxController controller, double power)
  {
    if (controller.getLeftBumperPressed())
    {
      spin(power);
      
    }
    if (controller.getLeftBumperReleased())
    {
      stop();
    }
    if(controller.getXButtonPressed())
    {
      spin(-power);
    }
    if (controller.getXButtonReleased())
    {
      stop();
    }
  }
  public void intakeToggle(XboxController controller, double power)
  {
    
    if (controller.getRawButtonPressed(5)) {
      if (toggle) {
          // Current state is true so turn off
          stop();
          toggle = false;
      } else {
          // Current state is false so turn on
          stop();
          spin(power);
          toggle = true;
      }
    }

    if (controller.getRawButtonPressed(3)) {
      if (toggle) {
          // Current state is true so turn off
          stop();
          toggle = false;
      } else {
          // Current state is false so turn on
          stop();
          spin(-power);
          toggle = true;
      }
    }
  }
  /**
   * Spins the rollers at a given power
   * 
   * @param power the power to spin the rollers at [-1, 1]. Negative values spin outwards.
   */
  public void spin(double power) {
    rollers.set(ControlMode.PercentOutput, power);
  }

  public void moveIntake(XboxController controller)
  {
    if (controller.getStartButtonPressed())
    {
      deploy.toggle();
    }
  }
  
  /**
   * Stop the rollers
   */
  public void stop() {
    rollers.set(ControlMode.PercentOutput, 0);
  }


  public void setState(IntakeRollersStates istate) {
    state = istate;
  }

  public IntakeRollersStates getState() {
    return state;
  }

  // public void deploy() {
  //   deploy.set(!deploy.get());
  // }
}
