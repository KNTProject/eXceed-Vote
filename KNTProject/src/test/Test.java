package test;


/**
 * @author Thomas
 *
 * Java Class to map to the datbase Contact Table
 * In this case the DB Table is just TEST
 */
public class Test {
  private String text;
  private long id;

  public String getText() {
	return text;
}

public void setText(String text) {
	this.text = text;
}

/**
 * @return ID Returns ID
 */
  public long getId() {
  return id;
  }

  /**
 * @param l Sets the ID
 */
  public void setId(long l) {
  id = l;
  }

}