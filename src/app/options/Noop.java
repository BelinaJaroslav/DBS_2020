package app.options;

import app.relations.None;
import app.relations.RelationInterface;

public class Noop extends Option {
   @Override
   public RelationInterface execute(String[] args) {
      return this.execute();
   }

   public RelationInterface execute() {
      return new None();
   }
}
