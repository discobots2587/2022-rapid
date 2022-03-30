package frc.robot.subsystems;


import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.Compressor;
//import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.XboxController;
//import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
//import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.IntakeRollersConstants;

public class IntakeRollers {
    private TalonSRX rollers = new TalonSRX(IntakeRollersConstants.kRollersID);
    private Solenoid zero = new Solenoid(IntakeRollersConstants.kPCMID, PneumaticsModuleType.CTREPCM, IntakeRollersConstants.kdeployForwardChannel);
    private Solenoid one = new Solenoid(IntakeRollersConstants.kPCMID, PneumaticsModuleType.CTREPCM, IntakeRollersConstants.kdeployBackwardChannel);
    private boolean toggle = false;
    Compressor pcmCompressor = new Compressor(IntakeRollersConstants.kPCMID, PneumaticsModuleType.CTREPCM);



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
    pcmCompressor.enableDigital();
  }

  


  public void intakeToggle(XboxController controller, XboxController controller2, double power)
  {

    if (controller.getLeftBumperPressed()) {
      if (toggle) {
          // Current state is true so turn off
          toggle = false;
          one.toggle();
          zero.toggle();
      } else  {
          // Current state is false so turn on
          toggle = true;
          one.toggle();
          zero.toggle();

      }
    }

    if (controller.getRightBumperPressed()) {
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
    } else if((controller.getRightTriggerAxis() == 0 && toggle == false) || (controller2.getRightTriggerAxis() == 0 && toggle == false))
    {
      stop();
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

  public void intakeSpin(XboxController controller, XboxController controller2, double power)
  {
    if(controller.getRightTriggerAxis() > 0 || controller2.getRightTriggerAxis() > 0)
    {
      spin(power);
    }
    else
    {
      stop();
    }
  }

  public void moveIntake()
  {
      one.toggle();
      zero.toggle();
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
