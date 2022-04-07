package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.Constants.ConveyerConstants;

public class Conveyer 
{
  private TalonSRX conveyer = new TalonSRX(ConveyerConstants.kRightConveyerID);
  private TalonSRX leftConveyer = new TalonSRX(ConveyerConstants.kLeftConveyerID);
  
  public static enum ConveyerStates 
  {
    OFF, IN, OUT
  }

  private ConveyerStates state = ConveyerStates.OFF;

  // Creates a new Conveyer.
  
  public Conveyer() 
  {
    leftConveyer.follow(conveyer);
    conveyer.setNeutralMode(NeutralMode.Brake);
    conveyer.setInverted(true);

  }

  public void conveyerRun(XboxController controller, XboxController controller2, double power)
  {
    if (controller.getPOV() == 0 || controller2.getPOV() == 0)
    {
      index(power);
    }
    else if (controller.getPOV() == 180 || controller2.getPOV() == 180)
    {
      index(-power);
    }
    else if (controller.getPOV() == -1 || controller2.getPOV() == -1)
    {
      stop();
    }

  }
  
  // spins the conveyer at a given power
   
  // @param power the power to spin the conveyer at [-1, 1]. Negative values spin outwards.
  
  public void index(double power) 
  {
    conveyer.set(ControlMode.PercentOutput, power);
  }

  // Stop the conveyer
  
  public void stop() 
  {
    conveyer.set(ControlMode.PercentOutput, 0);
  }

  public void setState(ConveyerStates istate) 
  {
    state = istate;
  }

  public ConveyerStates getState() 
  {
    return state;
  }

  public boolean getLeftConveyerState()
  {
    boolean status = false;  
    if (leftConveyer.getMotorOutputPercent() != 0)
    {
      status = true;
    }
    return status;
  }

  public boolean getRightConveyerState()
  {
    boolean status = false;  
    
    //System.out.println(conveyer.getMotorOutputPercent() + "\n");

    if (conveyer.getMotorOutputPercent() != 0)
    {
      status = true;
    }
    return status;
  }
}
