package ua.lpnu.students.labs.laba2.decoration.model.shared;

import ua.lpnu.students.labs.laba2.decoration.model.ElectricDecoration;
import ua.lpnu.students.labs.laba2.decoration.model.LongDecoration;
import ua.lpnu.students.labs.laba2.decoration.model.OrganicDecoration;
import ua.lpnu.students.labs.laba2.decoration.model.PieceDecoration;

/**
 * The type enum of the models.
 */
public enum Type {
  PIECE_DECORATION, ORGANIC_DECORATION, LONG_DECORATION, ELECTRIC_DECORATION;

  /**
   * Convert all possible value to string with their values.
   *
   * @return the string
   */
  public static String allToString() {
    StringBuffer out = new StringBuffer();
    Type[] types = Type.values();
    for (Type type : types) {
      out.append(String.format("%d - %s%n", type.ordinal(), type.toString()));
    }
    return out.toString();
  }

  public Template createDecoration() {
    return decorationsCreaters[this.ordinal()].createDecoration();
  }

  static final CreateDecoration[] decorationsCreaters = {
      () -> new PieceDecoration(),
      () -> new OrganicDecoration(),
      () -> new LongDecoration(),
      () -> new ElectricDecoration(),
  };

  @FunctionalInterface
  interface CreateDecoration {
    Template createDecoration();
  }
}