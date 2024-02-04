package utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * This utility class provides a method for creating a deep copy of an object.
 */
public class DeepCopyUtil {
  /**
   * Creates a deep copy of the given object. The object must be serializable.
   *
   * @param object The object to be copied.
   * @param <T>    The type of the object.
   * @return The deep copy of the object.
   * @throws RuntimeException if an error occurs during the copy process.
   */
  @SuppressWarnings("unchecked")
  public static <T> T deepCopy(T object) {
    try {
      ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
      ObjectOutputStream out = new ObjectOutputStream(outputStream);
      out.writeObject(object);
      out.flush();

      ByteArrayInputStream inputStream = new ByteArrayInputStream(outputStream.toByteArray());
      ObjectInputStream in = new ObjectInputStream(inputStream);
      return (T) in.readObject();
    } catch (IOException | ClassNotFoundException e) {
      throw new RuntimeException(e);
    }
  }
}
