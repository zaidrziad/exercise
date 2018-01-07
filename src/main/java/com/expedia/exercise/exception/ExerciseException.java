package com.expedia.exercise.exception;

public class ExerciseException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	int errorCode;

	public int getErrorCode() {
		return errorCode;
	}

	public ExerciseException(int errorCode) {
		this.errorCode = errorCode;
	}
	
	public ExerciseException() {
	}

	public ExerciseException(String message) {
		super(message);
	}
	
	public ExerciseException(Throwable throwable) {
		super(throwable);
	}
}
