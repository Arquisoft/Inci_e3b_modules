package model.util;

public class ModelException extends Exception {
	 private static final long serialVersionUID = 4001710687990554589L;

	 public ModelException() {
	 }

	 public ModelException(String message) {
	  super(message);
	 }

	 public ModelException(Throwable cause) {
	  super(cause);
	 }

	 public ModelException(String message, Throwable cause) {
	  super(message, cause);
	 }

	}
