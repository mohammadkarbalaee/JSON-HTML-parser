package sbu.cs.parser.json;

public class WrongValueTypeRequestedException extends Exception{

  WrongValueTypeRequestedException(String errorMessage) {
    super(errorMessage);
  }
}
