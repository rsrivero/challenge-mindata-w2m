package com.project.challenge.application.exceptions;

public class SpaceshipNotFound extends Exception{

    @Override
    public String getMessage(){
        return "Spaceship not found";
    }
}
