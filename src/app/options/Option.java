package app.options;

import app.relations.RelationInterface;

public abstract class Option {
   public final static String PACKAGE = "app.options";

   public abstract RelationInterface execute(String[] args);
}
