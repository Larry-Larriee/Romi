// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.subsystems.RomiDrivetrain;
import edu.wpi.first.wpilibj2.command.CommandBase;

/** An example command that uses an example subsystem. */
public class Forward extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final RomiDrivetrain m_db;
  private final double distance;

  /**
   * Creates a new ExampleCommand.
   *
   * @param subsystem The subsystem used by this command.
   */
  public Forward(RomiDrivetrain db, double inches) {
    m_db = db;
    distance = inches;

    // Use addRequirements() here to declare subsystem dependencies (for libraries).
    addRequirements(db);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() { 
    m_db.resetEncoders(); 
    m_db.m_gyro.calibrate();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_db.arcadeDrive(0.5, 0);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_db.arcadeDrive(0,0);
    m_db.m_gyro.reset();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    
    if (getAverageDistanceInch() < distance){
      return false;
    }
    else{
      return true;
    }

  }
}
